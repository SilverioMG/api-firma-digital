package com.prueba.firmadigital.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserKeyDao extends JpaRepository<UserKeyEntity, String> {

    @Query("SELECT u FROM UserKeyEntity u WHERE u.name = :name")
    Optional<UserKeyEntity> findByName(@Param("name") String name);
}
