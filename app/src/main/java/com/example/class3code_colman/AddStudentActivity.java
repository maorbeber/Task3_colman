package com.example.class3code_colman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.class3code_colman.model.Model;
import com.example.class3code_colman.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        EditText nameET = findViewById(R.id.addstudent_name_tv);
        EditText idET = findViewById(R.id.addstudent_id_tv);
        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = findViewById(R.id.addstudent_cancel_btn);
        saveBtn.setOnClickListener(view -> {
            String name = nameET.getText().toString();
            String id = idET.getText().toString();
            Student st = new Student(name,id,"",false);
            Model.instance().addStudent(st);
            Intent intent = new Intent(this, StudentRecyclerList.class);
            startActivity(intent);
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}