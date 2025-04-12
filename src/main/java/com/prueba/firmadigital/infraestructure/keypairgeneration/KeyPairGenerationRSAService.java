package com.prueba.firmadigital.infraestructure.keypairgeneration;

import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationException;
import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationService;
import com.prueba.firmadigital.domain.KeyPair;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Base64;

@Service
public class KeyPairGenerationRSAService implements KeyPairGenerationService {

    public KeyPair generate() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            PublicKey publicKey = keyPair.getPublic();
            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());

            return new KeyPair(privateKeyBase64, publicKeyBase64);
        }
        catch(Exception ex) {
            String erroMesasge = "Error generando el par de claves privada/publica: " + ex.getMessage();
            throw new KeyPairGenerationException(erroMesasge, ex);
        }
    }
}
