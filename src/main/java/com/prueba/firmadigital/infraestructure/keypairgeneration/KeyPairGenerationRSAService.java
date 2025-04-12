package com.prueba.firmadigital.infraestructure.keypairgeneration;

import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationException;
import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationService;
import org.springframework.stereotype.Service;
import java.security.KeyPair;

import java.security.*;

@Service
public class KeyPairGenerationRSAService implements KeyPairGenerationService {

    public KeyPair generate() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        }
        catch(Exception ex) {
            String erroMesasge = "Error generando el par de claves privada/publica: " + ex.getMessage();
            throw new KeyPairGenerationException(erroMesasge, ex);
        }
    }
}
