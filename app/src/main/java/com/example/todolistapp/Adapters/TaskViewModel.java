package com.example.todolistapp.Adapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mtRepository ;
    private LiveData<List<Tasks>> mAllTasks ;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mtRepository = new TaskRepository(application);
    }


    public void insert(Tasks tasks){
        mtRepository.insert(tasks);
    }
    public void delete(Tasks tasks){
        mtRepository.delete(tasks);
    }
    public void update(Tasks tasks){
        mtRepository.update(tasks);
    }
    public void deleteAllLists(){
        mtRepository.deleteAllTask();
    }

    public LiveData<List<Tasks>> getmAllTasks(int categoryID){
        return  mtRepository.getAllTask(categoryID);
    }
    public LiveData<List<Tasks>> getSearchTasks(int categoryID,String filter){
        return  mtRepository.getSearchTask(categoryID,filter);
    }

}
