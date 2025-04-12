package com.prueba.firmadigital.application;

import com.prueba.firmadigital.domain.UserKeyPairAggregate;

import java.util.Optional;

public interface UserKeyRepository {

    Optional<UserKeyPairAggregate> findUserKeyPairById(String id);
    Optional<UserKeyPairAggregate> findUserKeyPairByName(String name);
    void save(UserKeyPairAggregate userKeyPair);

}
