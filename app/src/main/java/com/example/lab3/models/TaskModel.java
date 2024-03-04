package com.example.lab3.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private int id;
    private String name;
    private String description;

    public TaskModel(){}
    public TaskModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TaskModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return  String.format("{ \"id\" : \"{0}\" " +
                              " \"name\" : \"{1}\" " +
                              " \"description\" : \"{2}\"}",
                              id, name, description);
    }
}
