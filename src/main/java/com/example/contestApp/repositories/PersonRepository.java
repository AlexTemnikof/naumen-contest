package com.example.contestApp.repositories;

import com.example.contestApp.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByName(String name);
}
