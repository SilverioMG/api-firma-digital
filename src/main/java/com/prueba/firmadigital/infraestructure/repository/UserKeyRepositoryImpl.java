package com.prueba.firmadigital.infraestructure.repository;

import com.prueba.firmadigital.application.UserKeyRepository;
import com.prueba.firmadigital.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserKeyRepositoryImpl implements UserKeyRepository {

    private final UserKeyDao userKeyDao;

    public UserKeyRepositoryImpl(UserKeyDao userKeyDao) {
        this.userKeyDao = userKeyDao;
    }

    @Override
    public Optional<UserKeyPairAggregate> findUserKeyPairById(String id) {

        return userKeyDao.findById(id)
                .map(this::mapToUserKeyPairDomain);
    }

    @Override
    public Optional<UserKeyPairAggregate> findUserKeyPairByName(String name) {

        return userKeyDao.findByName(name)
                .map(this::mapToUserKeyPairDomain);
    }

    @Override
    public void save(UserKeyPairAggregate userKeyPair) {
        UserKeyEntity userKeyEntity = mapToUserKeyEntity(userKeyPair);
        userKeyDao.save(userKeyEntity);
    }

    private User mapToUserDomain(UserKeyEntity entity) {

        return new User(entity.getName());
    }

    private KeyPair mapToKeyPairDomain(UserKeyEntity entity) {
        String decodedPrivateKey = decodeKey(entity.getEncodedPrivateKey());
        String denodedPublicKey = decodeKey(entity.getEncodedPublicKey());

        return new KeyPair(decodedPrivateKey, denodedPublicKey);
    }

    private UserKeyPairAggregate mapToUserKeyPairDomain(UserKeyEntity entity) {
        User user = mapToUserDomain(entity);
        KeyPair keyPair = mapToKeyPairDomain(entity);

        return new UserKeyPairAggregate(user, keyPair);
    }

    private UserKeyEntity mapToUserKeyEntity(UserKeyPairAggregate userKeyPairAggregate) {
        String id = Optional.ofNullable(userKeyPairAggregate.getUser())
                .map(User::getId)
                .orElse(null);
        String userName = Optional.ofNullable(userKeyPairAggregate.getUser())
                .map(User::getName)
                .map(NameVO::value)
                .orElse(null);
        String privateKey = Optional.ofNullable(userKeyPairAggregate.getKeyPair())
                .map(KeyPair::getPrivateKey)
                .map(KeyVO::value)
                .orElse(null);
        String publicKey = Optional.ofNullable(userKeyPairAggregate.getKeyPair())
                .map(KeyPair::getPublicKey)
                .map(KeyVO::value)
                .orElse(null);
        String encodedPrivateKey = encodeKey(privateKey);
        String encodedPublicKey = encodeKey(publicKey);

        return new UserKeyEntity(id, userName, encodedPrivateKey, encodedPublicKey);
    }

    private String encodeKey(String key) {
        if(StringUtils.isBlank(key)) {

            return null;
        }

        // TODO: Completar implementación.

        return key;
    }

    private String decodeKey(String key) {
        if(StringUtils.isBlank(key)) {

            return null;
        }

        // TODO: Completar implementación.

        return key;
    }
}
