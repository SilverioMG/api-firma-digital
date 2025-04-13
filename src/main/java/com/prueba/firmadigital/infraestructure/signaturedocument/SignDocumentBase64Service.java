package com.prueba.firmadigital.infraestructure.signaturedocument;

import com.prueba.firmadigital.application.signaturedocument.SignatureDocumentService;
import com.prueba.firmadigital.application.signaturedocument.SignatureDocumentServiceException;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.*;
import java.util.Base64;

@Service
public class SignDocumentBase64Service implements SignatureDocumentService {
    @Override
    public String buildSignature(String documentBase64, PrivateKey privateKey, String userName) {
        try {
            byte[] pdfBytes = Base64.getDecoder().decode(documentBase64);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(pdfBytes);
            byte[] signedData = signature.sign();

            return Base64.getEncoder().encodeToString(signedData);
        }
        catch(Exception ex) {

            throw new SignatureDocumentServiceException("Error generando la 'firmaDigital' para el usuario: " + userName, ex);
        }
    }
}
