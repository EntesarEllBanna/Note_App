package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolistapp.Adapters.AddNewTaskViewModel;
import com.example.todolistapp.Adapters.ListsAdapter;
import com.example.todolistapp.Adapters.OnItemCilckListener;
import com.example.todolistapp.Adapters.OnTaskClickListener;
import com.example.todolistapp.Adapters.TaskAdapter;
import com.example.todolistapp.Adapters.TaskViewModel;
import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity<AddNewTaskViewModel> extends AppCompatActivity {

    int categoryID = 0;
    String categoryName="";
    RecyclerView rv;
    ArrayList<Tasks> tasks = new ArrayList<>();
    private TaskViewModel mtaskViewModel;
    EditText creattask;
    private com.example.todolistapp.Adapters.AddNewTaskViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        rv = findViewById(R.id.task_rv);
        creattask = findViewById(R.id.creattask);

        categoryID =  getIntent().getExtras().getInt("categoryID");
        categoryName =  getIntent().getExtras().getString("categoryName");

        TextView  textCategoryName =(TextView)findViewById(R.id.note_name);
        textCategoryName.setText(categoryName);
        TaskAdapter adapter = new TaskAdapter(tasks, new OnTaskClickListener() {
            @Override
            public void onClickLisener(Tasks tasks) {
//               Toast.makeText(CategoryListActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), MeetActivity.class));

            }
        }, rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        mtaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mtaskViewModel.getmAllTasks(categoryID).observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                //update ui
                //update rv
//                Toast.makeText(CategoryListActivity.this, "on changed work", Toast.LENGTH_SHORT).show();
                adapter.setTasks(tasks);

            }
        });

        mViewModel = ViewModelProviders.of(this).get(com.example.todolistapp.Adapters.AddNewTaskViewModel.class);


    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ListsActivity.class));
    }

    public void savetask(View view) {
        String task = creattask.getText().toString();
        if (TextUtils.isEmpty(task)) {
            creattask.setError("task name is required");
        } else {
            mViewModel.insert(new Tasks(task,categoryID));
        }
    }

    public void searchResults(View view) {
      Intent meetActivity=  new Intent(getApplicationContext() , MeetActivity.class);
        meetActivity.putExtra("categoryID",categoryID);
        meetActivity.putExtra("categoryName",categoryName);
        startActivity(meetActivity);
    }
}