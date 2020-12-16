package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistapp.Adapters.AddNewListViewModel;
import com.example.todolistapp.Adapters.ListViewModel;
import com.example.todolistapp.Adapters.ListsAdapter;
import com.example.todolistapp.Adapters.OnItemCilckListener;
import com.example.todolistapp.Models.Lists;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
    private ListViewModel mlistViewModel ;
    EditText creatlist ;
    RecyclerView rv ;
    EditText search ;
    ArrayList<Lists> lists = new ArrayList<>();
    AddNewListViewModel mViewModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        rv=findViewById(R.id.list_rv);
        search = findViewById(R.id.search);
        creatlist = findViewById(R.id.creatlist);

        ListsAdapter adapter = new ListsAdapter(lists, new OnItemCilckListener() {
            @Override
            public void onItemClick(Lists lists) {
                Toast.makeText(ListsActivity.this, "clicked ", Toast.LENGTH_SHORT).show();
                Intent addTask= new Intent(getApplicationContext() , CategoryListActivity.class);
                addTask.putExtra("categoryID",lists.getId());
                addTask.putExtra("categoryName",lists.getListname());
                startActivity(addTask);

            }
        }, rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);



        //database
        mlistViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        mlistViewModel.getAllLists().observe(this, new Observer<List<Lists>>() {
            @Override
            public void onChanged(List<Lists> lists) {
                //Updaet ui
                //recycler view
                Toast.makeText(ListsActivity.this, "on changed work ", Toast.LENGTH_SHORT).show();
                adapter.setLists(lists);
            }
        });
        mViewModel = ViewModelProviders.of(this).get(AddNewListViewModel.class);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }




    public void back(View view) {
        startActivity(new Intent(getApplicationContext() , LoginActivity.class));
    }

    public void savetodatabase(View view) {
        String categ = creatlist.getText().toString();
        if (TextUtils.isEmpty(categ)){
            creatlist.setError("Enter List Name");

        }else {
            mViewModel.insert(new Lists(categ));
        }


    }
}