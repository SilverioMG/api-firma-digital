package com.prueba.firmadigital.infraestructure.repository;

import com.prueba.firmadigital.application.UserKeyRepository;

import com.prueba.firmadigital.application.UserKeyRepositoryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Optional;

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
    public Optional<KeyStore> loadKeyStore(String userName) {
        try {
            return keyStoreDao.loadKeyStore(userName, KEY_STORE_PASSWORD, KEYSTORE_DIR_PATH);
        }
        catch(Exception ex) {
            throw new UserKeyRepositoryException("Error recuperando el keyStore para el usuario: " + userName, ex);
        }
    }

    @Override
    public void save(String userName, KeyPair keyPair) {
        String alias = userName;

        try {
            keyStoreDao.saveToKeyStore(keyPair, userName, alias, KEY_STORE_PASSWORD, KEYSTORE_DIR_PATH);
        }
        catch(Exception ex) {
            throw new UserKeyRepositoryException("Error guardando keys para el usuario: " + userName, ex);
        }
    }

    @Override
    public PrivateKey getPrivateKey(KeyStore keyStore, String userName) {
        try {
            String alias = userName;
            return (PrivateKey) keyStore.getKey(alias, KEY_STORE_PASSWORD.toCharArray());
        } catch (Exception ex) {
            throw new UserKeyRepositoryException("Error recuperando 'privateKey' para el usuario: " + userName, ex);
        }

    }

    @Override
    public X509Certificate getCertificate(KeyStore keyStore, String userName) {
        try{
            String alias = userName;
            return (X509Certificate) keyStore.getCertificate(alias);
        }
        catch(Exception ex) {
            throw new UserKeyRepositoryException("Error recuperando 'certificate' (publicKey) para el usuario: " + userName, ex);
        }
    }

}
