package com.prueba.firmadigital.application;

import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Optional;


public interface UserKeyRepository {

    Optional<KeyStore> loadKeyStore(String userName);
    void save(String userName, KeyPair keyPair);
    PrivateKey getPrivateKey(KeyStore keyStore, String alias);
    X509Certificate getCertificate(KeyStore keyStore, String alias);

}
