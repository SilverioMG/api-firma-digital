package com.prueba.firmadigital.domain;

import java.util.UUID;

public class KeyPair {

    private String id;
    private KeyVO privateKey;
    private KeyVO publicKey;

    public KeyPair(String privateKey, String publicKey) {
        this.id = UUID.randomUUID().toString();
        this.privateKey = new KeyVO(privateKey);
        this.publicKey = new KeyVO(publicKey);
    }

    public String getId() {
        return id;
    }

    public KeyVO getPrivateKey() {
        return privateKey;
    }

    public KeyVO getPublicKey() {
        return publicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyPair keyPair)) return false;

        return id.equals(keyPair.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "KeyPair{" +
                "id='" + id + '\'' +
                ", privateKey=" + privateKey +
                ", publicKey=" + publicKey +
                '}';
    }
}
