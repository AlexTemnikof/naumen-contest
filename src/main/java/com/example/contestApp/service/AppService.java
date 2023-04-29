package com.example.contestApp.service;

import com.example.contestApp.entities.Frequency;
import com.example.contestApp.entities.Person;
import com.example.contestApp.repositories.FrequencyRepository;
import com.example.contestApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class AppService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FrequencyRepository frequencyRepository;

    @PostConstruct
    public void init(){
        Path path = Paths.get("src/main/resources/data.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split("_");
                Person p = new Person(parts[0], Integer.parseInt(parts[1]));
                personRepository.save(p);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public List<Integer> getAge(String name) {
        this.updateFrequency(name);
        List<Integer> nameAge = new ArrayList<>();
        List<Person> persons = personRepository.findByName(name);
        for (Person p : persons) {
            nameAge.add(p.getAge());
        }
        if (nameAge.isEmpty()) {
            nameAge.add((int) (Math.random() * 100));
        }
        return nameAge;
    }

    public Map<String, Integer> getFrequency() {
        Map<String, Integer> result = new HashMap<>();
        List<Frequency> frequencies = frequencyRepository.findAll();
        for (Frequency f : frequencies){
            result.put(f.getName(), f.getFrequency());
        }

        return result;
    }

    public Person getNameWithMaxAge() {
        Person personWithMaxAge = null;
        int maxAge = 0;
        for (Person person : personRepository.findAll()) {
            int age = person.getAge();
            if (age > maxAge) {
                maxAge = age;
                personWithMaxAge = person;
            }
        }
        return personWithMaxAge;
    }

    private void updateFrequency(String name){
        Frequency temp = frequencyRepository.findByName(name);
        if (temp != null){
            temp.updateFrequency();
            frequencyRepository.save(temp);
        }
        else {
            temp = new Frequency(name);
            frequencyRepository.save(temp);
        }
    }
}
