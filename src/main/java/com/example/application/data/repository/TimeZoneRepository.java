package com.example.application.data.repository;

import com.example.application.data.entity.TimeZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeZoneRepository extends JpaRepository<TimeZone, Integer> {
    List<TimeZone> findByNameContainingIgnoreCase(String name);

}