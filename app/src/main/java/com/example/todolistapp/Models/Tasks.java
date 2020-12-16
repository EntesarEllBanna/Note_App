package com.example.todolistapp.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private int categoryId;

    private Boolean isDone;
    private String tastName ;

    public Tasks(String tastName,int categoryId) {
        this.tastName = tastName;
        this.categoryId = categoryId;
        this.isDone = false;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public String getTastName() {
        return tastName;
    }
    public Boolean getIsDone() {
        return isDone;
    }
}
