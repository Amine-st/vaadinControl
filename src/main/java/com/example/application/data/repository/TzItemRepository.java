package com.example.application.data.repository;

import com.example.application.data.entity.TzItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TzItemRepository extends JpaRepository<TzItem, Integer> {

}