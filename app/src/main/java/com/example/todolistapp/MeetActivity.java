package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.Adapters.MeetAdapter;
import com.example.todolistapp.Adapters.OnTaskClickListener;
import com.example.todolistapp.Adapters.TaskViewModel;
import com.example.todolistapp.Models.Tasks;

import java.util.ArrayList;
import java.util.List;

public class MeetActivity extends AppCompatActivity {
    RecyclerView rv ;
    int categoryID = 0;
    String categoryName="";

    ArrayList<Tasks> results = new ArrayList<>();
    private TaskViewModel mtaskViewModel;
    private com.example.todolistapp.Adapters.AddNewTaskViewModel mViewModel;
private EditText searchEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);
        rv = findViewById(R.id.meet_rv);
        searchEditText = findViewById(R.id.meet);

        categoryID =  getIntent().getExtras().getInt("categoryID");
        categoryName =  getIntent().getExtras().getString("categoryName");
        MeetAdapter adapter = new MeetAdapter(results, new OnTaskClickListener() {
            @Override
            public void onClickLisener(Tasks tasks) {
                Toast.makeText(MeetActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), ViewTaskActivity.class));

            }
        }, rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        mtaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mtaskViewModel.getSearchTasks(categoryID,searchEditText.getText().toString()).observe(MeetActivity.this, new Observer<List<Tasks>>() {
                    @Override
                    public void onChanged(List<Tasks> tasks) {

                        adapter.setTasks(tasks);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mViewModel = ViewModelProviders.of(this).get(com.example.todolistapp.Adapters.AddNewTaskViewModel.class);


    }


    public void backToTak(View view) {
        startActivity(new Intent(getApplicationContext() , CategoryListActivity.class));
    }
}