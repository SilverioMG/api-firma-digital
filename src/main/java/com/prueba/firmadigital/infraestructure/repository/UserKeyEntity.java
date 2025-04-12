package com.prueba.firmadigital.infraestructure.repository;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_key")
public class UserKeyEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "encoded_private_key")
    private String encodedPrivateKey;

    @Column(name = "encoded_public_key")
    private String encodedPublicKey;

    public UserKeyEntity() {
        //Empty constructor.
    }

    public UserKeyEntity(String id, String name, String encodedPrivateKey, String encodedPublicKey) {
        this.id = id;
        this.name = name;
        this.encodedPrivateKey = encodedPrivateKey;
        this.encodedPublicKey = encodedPublicKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedPrivateKey() {
        return encodedPrivateKey;
    }

    public void setEncodedPrivateKey(String encodedPrivateKey) {
        this.encodedPrivateKey = encodedPrivateKey;
    }

    public String getEncodedPublicKey() {
        return encodedPublicKey;
    }

    public void setEncodedPublicKey(String encodedPublicKey) {
        this.encodedPublicKey = encodedPublicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserKeyEntity that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserKeyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", encodedPrivateKey='" + encodedPrivateKey + '\'' +
                ", encodedPublicKey='" + encodedPublicKey + '\'' +
                '}';
    }
}
