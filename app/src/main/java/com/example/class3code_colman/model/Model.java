package com.example.class3code_colman.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance() {
        return _instance;
    }

    List<Student> data = new LinkedList<>();


//    private Model() {
//        for (int i = 0; i < 20; i++) {
//            addStudent(new Student("name " + i, "" + i, "", false));
//        }
//    }


    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student st) {
        data.add(st);
    }

    public boolean contains(String id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id.equals(id)) {
                return true;
            }
        }
        return false;
    }


}
