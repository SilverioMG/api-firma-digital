package com.prueba.firmadigital.application.verifysignaturedocument;

public record VerifySignatureDocumentCommand(String userName, String digitalSignatureBase64, String documentBase64) {
}
