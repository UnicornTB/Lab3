package com.example.lab3.models;

import androidx.annotation.NonNull;

import java.util.List;

public class TaskList {
    private List<TaskModel> allTasks;

    public TaskList(List<TaskModel> allTasks) {
        this.allTasks = allTasks;
    }

    public void addTask(TaskModel taskModel) {
        allTasks.add(taskModel);
    }

    public TaskModel getTask(int id){
        TaskModel originalTask = allTasks.get(id);
        return new TaskModel(originalTask.getId(), originalTask.getName(), originalTask.getDescription());
    }

    public void editTask(TaskModel taskModel) {
        TaskModel originalTask = allTasks.get(taskModel.getId());
        originalTask.setName(taskModel.getName()).setDescription(taskModel.getDescription());
    }

    public List<TaskModel> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(List<TaskModel> allTasks) {
        this.allTasks = allTasks;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TaskModel taskModel : allTasks) {
            stringBuilder.append(taskModel.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
