package com.example.class3code_colman.model;

public class Student {
    public String name;
    public String id;
    public String avatarUrl;
    public Boolean cb;
    //change public members to getter and setter

    public Student(String name, String id, String avatarUrl, Boolean cb) {
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.cb = cb;
    }
}
