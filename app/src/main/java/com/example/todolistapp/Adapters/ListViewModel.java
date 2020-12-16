package com.example.todolistapp.Adapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolistapp.Models.Lists;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private ListsRepository mRepository ;
    private LiveData<List<Lists>> mAllLists ;
    public ListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ListsRepository(application);
        mAllLists = mRepository.getAllLists();
    }

    public void insert(Lists lists){
        mRepository.insert(lists);
    }
    public void delete(Lists lists){
        mRepository.delete(lists);
    }
    public void update(Lists lists){
        mRepository.update(lists);
    }
    public void deleteAllLists(){
        mRepository.deleteAllLists();
    }

    public LiveData<List<Lists>> getAllLists(){
        return mAllLists;
    }


}
