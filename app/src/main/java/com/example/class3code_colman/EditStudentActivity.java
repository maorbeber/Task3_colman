package com.example.class3code_colman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class3code_colman.model.Model;
import com.example.class3code_colman.model.Student;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = Model.instance().getAllStudents();
        setContentView(R.layout.activity_edit_student);
        Button saveBtn = findViewById(R.id.editstudent_save_btn);
        Button cancelBtn = findViewById(R.id.editstudent_cancel_btn);
        Button deleteBtn = findViewById(R.id.editstudent_delete_btn);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String id = bundle.getString("id");
        int pos = bundle.getInt("position");
        String msg = "Student with this id is already exists";
        EditText nameET = findViewById(R.id.addstudent_name_tv);
        EditText idET = findViewById(R.id.addstudent_id_tv);
        TextView errorMsg = findViewById(R.id.editstudent_error_tv);
        nameET.setText(name);
        idET.setText(id);
        Intent intent = new Intent(this, StudentRecyclerList.class);
        saveBtn.setOnClickListener(view -> {
            data.get(pos).id = idET.getText().toString();
            data.get(pos).name = nameET.getText().toString();
            startActivity(intent);
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(pos);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}