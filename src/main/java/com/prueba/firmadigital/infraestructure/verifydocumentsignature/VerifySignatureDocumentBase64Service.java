package com.prueba.firmadigital.infraestructure.verifydocumentsignature;

import com.prueba.firmadigital.application.verifysignaturedocument.VerifySignatureDocumentService;
import com.prueba.firmadigital.application.verifysignaturedocument.VerifySignatureDocumentServiceException;
import org.springframework.stereotype.Service;

import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Base64;

@Service
public class VerifySignatureDocumentBase64Service implements VerifySignatureDocumentService {
    @Override
    public boolean verifySignature(String documentBase64, String digitalSignatureBase64, Certificate publicKey, String userName) {
        try{
            byte[] documentBytes = Base64.getDecoder().decode(documentBase64);
            byte[] digitalSignatureBytes = Base64.getDecoder().decode(digitalSignatureBase64);
            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initVerify(publicKey);
            signature.update(documentBytes);

            return signature.verify(digitalSignatureBytes);
        }
        catch(Exception ex) {
            throw new VerifySignatureDocumentServiceException("Error verificando la firma digital en documento del usuario: " + userName, ex);
        }
    }

}
