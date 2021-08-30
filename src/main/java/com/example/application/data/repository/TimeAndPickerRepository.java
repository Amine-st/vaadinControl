package com.example.application.data.repository;

import com.example.application.data.entity.TimeAndPickerVaadin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeAndPickerRepository extends JpaRepository<TimeAndPickerVaadin, Integer> {
    List<TimeAndPickerVaadin> findByNameContainingIgnoreCase(String name);

}