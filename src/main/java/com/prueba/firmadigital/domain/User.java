package com.prueba.firmadigital.domain;

import java.util.UUID;

public class User {

    private String id;
    private NameVO name;


    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = new NameVO(name);
    }

    public String getId() {
        return id;
    }

    public NameVO getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
