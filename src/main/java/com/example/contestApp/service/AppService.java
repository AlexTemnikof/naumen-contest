package com.example.contestApp.service;

import com.example.contestApp.dto.PersonDTO;
import com.example.contestApp.entities.Frequency;
import com.example.contestApp.entities.Person;
import com.example.contestApp.mapper.PersonMapper;
import com.example.contestApp.repositories.FrequencyRepository;
import com.example.contestApp.repositories.PersonRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Service
public class AppService {

    private final PersonRepository personRepository;

    private final FrequencyRepository frequencyRepository;

    private final PersonMapper personMapper;

    public AppService(PersonRepository personRepository, FrequencyRepository frequencyRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.frequencyRepository = frequencyRepository;
        this.personMapper = personMapper;
    }

    @PostConstruct
    public void init(){
        personRepository.deleteAll();
        frequencyRepository.deleteAll();
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
    public List<PersonDTO> getAll(){

        return personMapper.toDTOAll(personRepository.findAll());
    }

    public List<Integer> getAge(String name) {
        this.updateFrequency(name);
        List<Integer> nameAge = new ArrayList<>();
        List<Person> persons = personRepository.findByName(name);
        for (Person p : persons) {
            nameAge.add(p.getAge());
        }
        if (nameAge.isEmpty()) {
            try {
                nameAge.add(getAgeFromApi(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public PersonDTO getNameWithMaxAge() {
        Person personWithMaxAge = null;
        int maxAge = 0;
        for (Person person : personRepository.findAll()) {
            int age = person.getAge();
            if (age > maxAge) {
                maxAge = age;
                personWithMaxAge = person;
            }
        }
        return personMapper.toDTO(personWithMaxAge);
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

    private Integer getAgeFromApi(String name) throws IOException{

        URL url = new URL("https://api.agify.io?name=" + name);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONObject obj = new JSONObject(content.toString());
        return (Integer) obj.get("age");

    }
}
