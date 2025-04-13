package com.prueba.firmadigital.application.verifysignaturedocument;

import com.prueba.firmadigital.application.UserKeyRepository;
import com.prueba.firmadigital.application.utils.Base64Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.security.cert.X509Certificate;

@Component
public class VerifysignaturedocumentUseCase {

    private final UserKeyRepository userKeyRepository;
    private final VerifySignatureDocumentService verifySignatureDocumentService;

    public VerifysignaturedocumentUseCase(
            UserKeyRepository userKeyRepository,
            VerifySignatureDocumentService verifySignatureDocumentService){
        this.userKeyRepository = userKeyRepository;
        this.verifySignatureDocumentService = verifySignatureDocumentService;
    }

    public boolean verifySignatureDocument(VerifySignatureDocumentCommand command) {
        validateCommand(command);
        String userName = command.userName();
        String documentBase64 = command.documentBase64();
        String digitalSignatureBase64 = command.digitalSignatureBase64();

        KeyStore keyStore = userKeyRepository.loadKeyStore(userName).orElse(null);
        if(keyStore == null) {

            throw new VerifySignatureDocumentValidationException("No existen claves guadadas para el usuario: " + userName);
        }

        X509Certificate publicKey = userKeyRepository.getCertificate(keyStore, userName);

        return verifySignatureDocumentService.verifySignature(documentBase64, digitalSignatureBase64, publicKey, userName);
    }

    private void validateCommand(VerifySignatureDocumentCommand command) {
        if(command == null) throw new VerifySignatureDocumentValidationException(("Valor incorrecto para 'command': " + command));
        if(StringUtils.isBlank(command.userName())) throw new VerifySignatureDocumentValidationException("Valor incorrecto para 'userName': " + command.userName());
        if(!Base64Validator.isBase64(command.documentBase64())) throw new VerifySignatureDocumentValidationException("Codificación no base64 para 'documentBase64");
        if(!Base64Validator.isPdf(command.documentBase64())) throw new VerifySignatureDocumentValidationException("Formato no 'pdf' para 'documentBase64'");
        if(!Base64Validator.isBase64(command.digitalSignatureBase64())) throw new VerifySignatureDocumentValidationException("Codificación no base64 para 'digitalSignatureBase64'");
    }

}
