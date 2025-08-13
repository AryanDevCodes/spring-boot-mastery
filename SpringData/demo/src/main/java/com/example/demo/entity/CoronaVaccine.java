package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class CoronaVaccine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long regNO;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String company;
    @Column(length = 20)
    private String country;
    private Double price;
    private Integer requiredDoesCount;
}
