package com.example.todolistapp.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lists {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String listname ;

    public Lists(String listname) {
        this.listname = listname;
    }



    public int getId() {
        return id;
    }

    public String getListname() {
        return listname;
    }

    public void setId(int id) {
        this.id = id;
    }
}
