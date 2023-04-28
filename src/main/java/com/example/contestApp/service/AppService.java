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

    public void addPerson(String name, int age) {
        personRepository.save(new Person(name, age));
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public List<Integer> getAge(String name) {
        Frequency temp = frequencyRepository.findByName(name);
        if (temp != null){
            temp.updateFrequency();
            frequencyRepository.save(temp);
        }
        else {
            temp = new Frequency(name);
            frequencyRepository.save(temp);
        }
        List<Integer> nameAge = new ArrayList<>();
        List<Person> persons = personRepository.findByName(name);
        if (persons.size() != 0) {
            for (Person p : persons) {
                nameAge.add(p.getAge());
            }
            return nameAge;
        } else {
            nameAge.add((int) (Math.random() * 100));
            return nameAge; // произвольное положительное число, если нет информации о возрасте
        }
    }

    public Map<String, Integer> getFrequency() {
        Map<String, Integer> result = new HashMap<>();
        List<Frequency> frequencies = frequencyRepository.findAll();
        for (Frequency f : frequencies){
            result.put(f.getName(), f.getFrequency());
        }

        return result;
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
