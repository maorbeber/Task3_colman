package com.example.class3code_colman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.class3code_colman.model.Model;
import com.example.class3code_colman.model.Student;

import java.util.List;

public class StudentRecyclerList extends AppCompatActivity {
    List<Student> data;

    //instructions to task2:
    /*
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);
        data = Model.instance().getAllStudents();
        RecyclerView list = findViewById(R.id.studentRecycler_list);
        list.setHasFixedSize(true);
        // layout manager determines the order inside the recycler
        list.setLayoutManager(new LinearLayoutManager(this));
        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);
        Intent intent1 = new Intent(this, StudentDetailsActivity.class);
        Bundle bundle = new Bundle();
        Button btn = findViewById(R.id.addStudent_list);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                bundle.putString("name", data.get(pos).name);
                bundle.putString("id", data.get(pos).id);
                bundle.putInt("position", pos);
                intent1.putExtras(bundle);
                startActivity(intent1);
                Log.d("TAG", "row was clicked " + pos);
            }
        });

    }

    //create the Adapter (recyclerviewadapter)
    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentListRow_name_tv);
            idTv = itemView.findViewById(R.id.studentListRow_id_tv);
            cb = itemView.findViewById(R.id.studentListRow_cb);
            CheckBox cb = itemView.findViewById(R.id.studentListRow_cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //to get over recycling of lines in the list
                    int pos = (int) cb.getTag();
                    Student st = data.get(pos);
                    st.cb = cb.isChecked();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st, position);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
