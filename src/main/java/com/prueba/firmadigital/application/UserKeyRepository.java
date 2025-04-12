package com.prueba.firmadigital.application;

import java.security.KeyPair;
import java.security.KeyStore;


public interface UserKeyRepository {

    KeyStore loadKeyStore(String userName);
    void save(String userName, KeyPair keyPair);

}
