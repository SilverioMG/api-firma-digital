package com.prueba.firmadigital.infraestructure.signdocument;

import com.prueba.firmadigital.application.signdocument.SignDocumentService;
import com.prueba.firmadigital.application.signdocument.SignDocumentServiceException;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.*;
import java.util.Base64;

@Service
public class SignDocumentBase64Service implements SignDocumentService {
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
            throw new SignDocumentServiceException("Error generando la 'firmaDigital' para el usuario: " + userName, ex);
        }
    }
}
