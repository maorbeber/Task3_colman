package com.example.class3code_colman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.class3code_colman.model.Model;
import com.example.class3code_colman.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);
        Bundle bundle = getIntent().getExtras();
        data = Model.instance().getAllStudents();
        Intent intent1 = new Intent(this, EditStudentActivity.class);
        Bundle bundle1 = new Bundle();
        String name = bundle.getString("name");
        String id = bundle.getString("id");
        int pos = bundle.getInt("position");
        TextView nameTV = findViewById(R.id.addstudent_name_tv);
        TextView idTV = findViewById(R.id.addstudent_id_tv);
        nameTV.setText(name);
        idTV.setText(id);
        Button editBtn = findViewById(R.id.studentdetails_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle1.putString("name", data.get(pos).name);
                bundle1.putString("id", data.get(pos).id);
                bundle1.putInt("position", pos);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });
    }
}