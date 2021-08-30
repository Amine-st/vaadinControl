package com.example.application.data.repository;

import com.example.application.data.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findByNameContainingIgnoreCase(String name);

}