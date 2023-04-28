package com.example.contestApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "frequency")
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "frequency")
    private Integer frequency;

    public Frequency(){}

    public Frequency(String name){
        this.name = name;
        this.frequency = 1;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }

    public void updateFrequency() {
        this.frequency++;
    }
}
