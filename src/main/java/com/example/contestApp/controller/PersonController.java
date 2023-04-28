package com.example.contestApp.controller;

import com.example.contestApp.entities.Person;
import com.example.contestApp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {
    @Autowired
    private AppService appService;

    @PostMapping("/addPerson")
    public void addPerson(@RequestParam String name, @RequestParam int age) {
        appService.addPerson(name, age);
    }

    @GetMapping("/getAll")
    public List<Person> getAll(){return appService.getAll();}

    @GetMapping("/getAge")
    public int getAge(@RequestParam String name) {
        return appService.getAge(name);
    }

    @GetMapping("/getFrequency")
    public Map<String, Integer> getFrequency() {
        return appService.getFrequency();
    }

    @GetMapping("/getNameWithMaxAge")
    public String getNameWithMaxAge() {
        return appService.getNameWithMaxAge();
    }
}