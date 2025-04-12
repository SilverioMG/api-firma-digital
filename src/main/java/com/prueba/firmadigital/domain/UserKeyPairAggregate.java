package com.prueba.firmadigital.domain;

import java.util.Objects;

public class UserKeyPairAggregate {
    private User user;
    private KeyPair keyPair;

    public UserKeyPairAggregate(User user, KeyPair keyPair) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(keyPair);
        this.user = user;
        this.keyPair = keyPair;
    }

    public User getUser() {
        return user;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void changeKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserKeyPairAggregate that)) return false;

        if (!user.equals(that.user)) return false;
        return keyPair.equals(that.keyPair);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + keyPair.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserKeyPairAggregate{" +
                "user=" + user +
                ", keyPair=" + keyPair +
                '}';
    }
}
