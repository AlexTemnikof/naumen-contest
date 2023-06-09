package com.example.contestApp.repositories;

import com.example.contestApp.entities.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<Frequency, Integer> {

    Frequency findByName(String name);
}
