package com.prueba.firmadigital.infraestructure.repository;

import com.prueba.firmadigital.application.UserKeyRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyStore;

@Service
public class UserKeyStoreRepository implements UserKeyRepository {

    @Value("${api.keystore.dirpath}")
    private String KEYSTORE_DIR_PATH;

    @Value("${api.keystore.password}")
    private String KEY_STORE_PASSWORD;

    private final KeyStoreDao keyStoreDao;

    public UserKeyStoreRepository(KeyStoreDao keyStoreDao) {
        this.keyStoreDao = keyStoreDao;
    }


    @Override
    public KeyStore loadKeyStore(String userName) {
        try {
            return keyStoreDao.loadKeyStore(userName, KEY_STORE_PASSWORD, KEYSTORE_DIR_PATH);
        }
        catch(Exception ex) {
            throw new UserKeyStoreRepositoryException("Error recuperando el keyStore para el usuario: " + userName, ex);
        }
    }

    @Override
    public void save(String userName, KeyPair keyPair) {
        String alias = userName;

        try {
            keyStoreDao.saveToKeyStore(keyPair, userName, alias, KEY_STORE_PASSWORD, KEYSTORE_DIR_PATH);
        }
        catch(Exception ex) {
            throw new UserKeyStoreRepositoryException("Error guardando keys para el usuario: " + userName, ex);
        }
    }

}
