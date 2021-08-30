package com.example.application.data.repository;

import com.example.application.data.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByNameContainingIgnoreCase(String name);
    @Query("SELECT p FROM Person  p WHERE p.name = :username")
    public Person getUserByUsername(@Param("username") String username);
}