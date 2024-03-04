package com.example.lab3.services;

import com.example.lab3.models.TaskModel;
import com.example.lab3.repositories.TaskRepository;

import java.io.Serializable;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    private int currentTaskId = -1;

    public void addTask(TaskModel taskModel) {
        if (taskModel.getId() == 0) {
            taskModel.setId(taskRepository.getNotesCount());
        }
        currentTaskId = taskModel.getId();
        taskRepository.addTask(taskModel);
    }

    public void editTask(TaskModel taskModel) {
        taskRepository.editTask(currentTaskId, taskModel);
    }

    public TaskModel getPreviousTask(){
        if (currentTaskId == 0) {
            currentTaskId = taskRepository.getNotesCount();
            return taskRepository.getTask(currentTaskId);
        }
        currentTaskId--;
        return taskRepository.getTask(currentTaskId);
    }

    public TaskModel getNextTask() {
        if (currentTaskId == taskRepository.getNotesCount()){
            currentTaskId = 0;
            return taskRepository.getTask(currentTaskId);
        }
        currentTaskId++;
        return taskRepository.getTask(currentTaskId);
    }

    public TaskModel getLastTask() {
        return taskRepository.getTask(taskRepository.getNotesCount());
    }

    public int getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(int currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}
