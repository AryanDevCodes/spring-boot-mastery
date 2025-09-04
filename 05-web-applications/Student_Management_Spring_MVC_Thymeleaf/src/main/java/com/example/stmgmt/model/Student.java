package com.example.stmgmt.model;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private String name;
    private String email;

    public Student(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Student() {}
}
