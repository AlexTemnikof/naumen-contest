package com.example.contestApp.controller;

import com.example.contestApp.entities.Person;
import com.example.contestApp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired
    private AppService appService;

    @PostMapping("/addPerson")
    public void addPerson(@RequestParam String name, @RequestParam int age) {
        appService.addPerson(name, age);
    }

    @GetMapping("/persons")
    public List<Person> getAll(){return appService.getAll();}

    @GetMapping("/age")
    public List<Integer> getAge(@RequestParam String name) {
        return appService.getAge(name);
    }

    @GetMapping("/frequency")
    public Map<String, Integer> getFrequency() {
        return appService.getFrequency();
    }

    @GetMapping("/getNameWithMaxAge")
    public String getNameWithMaxAge() {
        return appService.getNameWithMaxAge();
    }
}