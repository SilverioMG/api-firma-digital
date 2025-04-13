package com.prueba.firmadigital.application.signdocument;

import java.security.PrivateKey;

public interface SignDocumentService {

    /**
     * Este método devuelve la 'firmaDigital' (no el documento firmado).
     * @param document
     * @param privateKey
     * @param userName
     * @return firmaDitital del documento (Hash).
     * @throws Exception
     */
    String buildSignature(String document, PrivateKey privateKey,String userName);
}
