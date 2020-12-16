package com.example.todolistapp.Adapters;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistapp.Models.Lists;
import com.example.todolistapp.Models.Tasks;

import java.util.List;

@Dao
interface TaskDao {
    @Insert
    void insert(Tasks tasks);

    @Update
    void update(Tasks tasks);

    @Delete
    void  delete (Tasks tasks);

    @Query("DELETE FROM Tasks ")
    void deleteAlltasks();

    @Query("SELECT * FROM Tasks WHERE categoryId = :categoryId")
    LiveData<List<Tasks>> getAlltasks(int categoryId);

    @Query("SELECT * FROM Tasks WHERE categoryId = :categoryId AND  isDone = 0 AND tastName LIKE  '%' || :filter || '%' " )
    LiveData<List<Tasks>> getOpenTask(int categoryId,String filter);

    @Query("UPDATE  Tasks SET isDone = 1  WHERE id = :taskId")
    void closeTask(int taskId);

}
