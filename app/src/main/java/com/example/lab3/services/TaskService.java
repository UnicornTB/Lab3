package com.example.lab3.services;

import com.example.lab3.models.TaskModel;
import com.example.lab3.repositories.TaskRepository;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    private int currentTaskId = 0;

    public void addTask(TaskModel taskModel) {
        if (taskModel.getId() == 0) {
            taskModel.setId(taskRepository.getNotes().size() - 1);
        }
        currentTaskId = taskModel.getId();
        taskRepository.addTask(taskModel);
    }

    public void editTask(TaskModel taskModel) {
        taskRepository.editTask(currentTaskId, taskModel);
    }

    public TaskModel getPreviousTask(){
        if (currentTaskId == 0) {
            currentTaskId = taskRepository.getNotes().size() - 1;
            return taskRepository.getTask(currentTaskId);
        }
        return taskRepository.getTask(--currentTaskId);
    }

    public TaskModel getNextTask() {
        if (currentTaskId == currentTaskId = taskRepository.getNotes().size())
    }

    public TaskModel getLastTask() {

    }




}
