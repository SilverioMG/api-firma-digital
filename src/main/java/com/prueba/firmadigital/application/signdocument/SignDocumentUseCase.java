package com.prueba.firmadigital.application.signdocument;

import com.prueba.firmadigital.application.UserKeyRepository;
import com.prueba.firmadigital.application.utils.Base64Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.security.PrivateKey;

@Component
public class SignDocumentUseCase {

    private final UserKeyRepository userKeyRepository;
    private final SignDocumentService signDocumentService;

    public SignDocumentUseCase(
            UserKeyRepository userKeyRepository,
            SignDocumentService signDocumentService) {
        this.userKeyRepository = userKeyRepository;
        this.signDocumentService = signDocumentService;
    }

    public SignDocumentQuery signDocument(SignDocumentCommand command) {
        validateCommand(command);
        String userName = command.userName();
        String documentBase64 = command.documentBase64();

        KeyStore keyStore = userKeyRepository.loadKeyStore(userName).orElse(null);
        if(keyStore == null) {
            throw new SignDocumentValidationException("No existen claves guadadas para el usuario: " + userName);
        }

        PrivateKey privateKey = userKeyRepository.getPrivateKey(keyStore, userName);
        String digitalSignature = signDocumentService.buildSignature(documentBase64, privateKey, userName);

        return new SignDocumentQuery(digitalSignature);
    }

    private void validateCommand(SignDocumentCommand command) {
        if(command == null) throw new SignDocumentValidationException("Valor incorrecto para 'command': " + command);
        if(StringUtils.isBlank(command.userName())) throw new SignDocumentValidationException("Valor incorrecto para 'userName': " +command.userName());
        if(!Base64Validator.validate(command.documentBase64())) throw new SignDocumentValidationException("Codificación no base64 para 'documentBase64");
        if(!Base64Validator.isPdf(command.documentBase64())) throw new SignDocumentValidationException("Formato no 'pdf' para 'documentBase64'");
    }
}
