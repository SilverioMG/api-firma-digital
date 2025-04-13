package com.prueba.firmadigital.application.verifysignaturedocument;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public interface VerifySignatureDocumentService {

    boolean verifySignature(String documentBase64, String digitalSignatureBase64, Certificate publicKey, String userName);
}
