package com.example.todolistapp.Adapters;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.Models.Lists;

import java.util.List;

@Dao
interface ListsDao  {
    @Insert
    void insert(Lists list);

    @Update
    void update(Lists lists);

    @Delete
    void  delete (Lists lists);

    @Query("DELETE FROM Lists ")
    void deleteAllLists();

    @Query("SELECT * FROM Lists")
    LiveData<List<Lists>> getAllWords();

}
