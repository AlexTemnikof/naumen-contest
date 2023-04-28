package com.example.contestApp.service;

import com.example.contestApp.entities.Person;
import com.example.contestApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class AppService {

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void init(){
        Path path = Paths.get("src/main/resources/data.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split("_");
                personRepository.save(new Person(parts[0], Integer.parseInt(parts[1])));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPerson(String name, int age) {
        personRepository.save(new Person(name, age));
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public int getAge(String name) {
        Person person = personRepository.findByName(name);
        if (person != null) {
            return person.getAge();
        } else {
            return (int) (Math.random() * 100); // произвольное положительное число, если нет информации о возрасте
        }
    }

    public Map<String, Integer> getFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (Person person : personRepository.findAll()) {
            String name = person.getName();
            int count = frequencyMap.getOrDefault(name, 0);
            frequencyMap.put(name, count + 1);
        }
        return frequencyMap;
    }

    public String getNameWithMaxAge() {
        String personWithMaxAge = null;
        int maxAge = 0;
        for (Person person : personRepository.findAll()) {
            int age = person.getAge();
            if (age > maxAge) {
                maxAge = age;
                personWithMaxAge = person.toString();
            }
        }
        return personWithMaxAge;
    }
}
