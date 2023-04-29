package com.example.contestApp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return this.age;
    }
}
