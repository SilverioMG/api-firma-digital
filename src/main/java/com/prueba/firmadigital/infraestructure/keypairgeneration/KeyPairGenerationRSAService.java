package com.prueba.firmadigital.infraestructure.keypairgeneration;

import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationException;
import com.prueba.firmadigital.application.generateuserkeys.KeyPairGenerationService;
import org.springframework.stereotype.Service;
import java.security.KeyPair;

import java.security.*;

@Service
public class KeyPairGenerationRSAService implements KeyPairGenerationService {

    public KeyPair generate(String userName) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        }
        catch(Exception ex) {

            throw new KeyPairGenerationException("Error generando el par de claves privada/publica para el usuario " + userName, ex);
        }
    }
}
