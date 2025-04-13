package com.prueba.firmadigital.application.signaturedocument;

import com.prueba.firmadigital.application.UserKeyRepository;
import com.prueba.firmadigital.application.utils.Base64Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.security.PrivateKey;

@Component
public class SignatureDocumentUseCase {

    private final UserKeyRepository userKeyRepository;
    private final SignatureDocumentService signDocumentService;

    public SignatureDocumentUseCase(
            UserKeyRepository userKeyRepository,
            SignatureDocumentService signDocumentService) {
        this.userKeyRepository = userKeyRepository;
        this.signDocumentService = signDocumentService;
    }

    public SignatureDocumentQuery signDocument(SignatureDocumentCommand command) {
        validateCommand(command);
        String userName = command.userName();
        String documentBase64 = command.documentBase64();

        KeyStore keyStore = userKeyRepository.loadKeyStore(userName).orElse(null);
        if(keyStore == null) {
            throw new SignatureDocumentValidationException("No existen claves guadadas para el usuario: " + userName);
        }

        PrivateKey privateKey = userKeyRepository.getPrivateKey(keyStore, userName);
        String digitalSignatureBase64 = signDocumentService.buildSignature(documentBase64, privateKey, userName);

        return new SignatureDocumentQuery(digitalSignatureBase64);
    }

    private void validateCommand(SignatureDocumentCommand command) {
        if(command == null) throw new SignatureDocumentValidationException("Valor incorrecto para 'command': " + command);
        if(StringUtils.isBlank(command.userName())) throw new SignatureDocumentValidationException("Valor incorrecto para 'userName': " +command.userName());
        if(!Base64Validator.isBase64(command.documentBase64())) throw new SignatureDocumentValidationException("Codificación no base64 para 'documentBase64");
        if(!Base64Validator.isPdf(command.documentBase64())) throw new SignatureDocumentValidationException("Formato no 'pdf' para 'documentBase64'");
    }
}
