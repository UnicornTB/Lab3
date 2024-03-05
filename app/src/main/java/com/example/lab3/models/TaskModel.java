package com.example.lab3.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class TaskModel {
    private int id;
    
    private String name;
    
    private String description;

    public TaskModel(){}
    public TaskModel(int id,  String name,  String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TaskModel( String name, String description) {
        this.id = -1;
        this.name = name;
        this.description = description;
    }
    public TaskModel setId(int id) {
        this.id = id;
        return this;
    }

    public TaskModel setName(String name) {
        this.name = name;
        return this;
    }

    public TaskModel setDescription(String description) {
        this.description = description;
        return this;
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
        return id + ", " + name + ", " + description;
    }
}
