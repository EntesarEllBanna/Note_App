package com.example.todolistapp.Adapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.Models.Tasks;

import java.util.List;

public class AddNewTaskViewModel  extends AndroidViewModel {
    private TaskRepository mtRepository ;

    public AddNewTaskViewModel(@NonNull Application application) {
        super(application);
        mtRepository = new TaskRepository(application);
    }


    public void insert(Tasks tasks){
        mtRepository.insert(tasks);
    }
    public void update(Tasks tasks){
        mtRepository.update(tasks);
    }


}
