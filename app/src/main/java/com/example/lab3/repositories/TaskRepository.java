package com.example.lab3.repositories;

import com.example.lab3.models.TaskModel;

import java.util.ArrayList;
import java.util.List;


public class TaskRepository {
    private List<TaskModel> notes = new ArrayList<>();
    public void addTask(TaskModel taskModel) {
        notes.add(taskModel);
    }

    public void editTask(int id, TaskModel taskModel) {
        TaskModel editedTask = notes.get(id);
        editedTask.setName(taskModel.getName());
        editedTask.setDescription(taskModel.getDescription());
    }

    public TaskModel getTask(int id) {
        return notes.get(id);
    }

    public List<TaskModel> getNotes() {
        return notes;
    }

    public void setNotes(List<TaskModel> notes) {
        this.notes = notes;
    }
}

