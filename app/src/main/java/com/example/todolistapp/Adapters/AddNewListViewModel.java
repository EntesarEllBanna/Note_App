package com.example.todolistapp.Adapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.Models.Lists;

import java.util.List;

public class AddNewListViewModel extends AndroidViewModel {

    private ListsRepository mRepository ;
    public AddNewListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ListsRepository(application);
    }

    public void insert(Lists lists){
        mRepository.insert(lists);
    }

    public void update(Lists lists){
        mRepository.update(lists);
    }

}
