package com.example.lab3.repositories;

import com.example.lab3.models.TaskList;
import com.example.lab3.models.TaskModel;

public class TaskRepository {
    private TaskList taskList;

    private int currentId = -1;

    private int maxTaskId = -1;

    public TaskRepository(TaskList taskList) {
        this.taskList = taskList;
    }

    public void addTask(TaskModel taskModel) {
        currentId++;
        maxTaskId++;

        if (taskModel.getId() == -1) {
            taskModel.setId(currentId);
        }

        taskList.addTask(taskModel);

    }

    public void editTask(TaskModel taskModel) {
        if (taskModel.getId() == -1) {
            taskModel.setId(currentId);
        }

        taskList.editTask(taskModel);
    }

    public TaskModel getPreviousTask() {
        if (currentId == 0) {
            currentId = maxTaskId;

        } else {
            currentId--;
        }
        return taskList.getTask(currentId);
    }

    public TaskModel getNextTask() {
        if (currentId == maxTaskId) {
            currentId = 0;
            return taskList.getTask(currentId);
        } else {
            currentId++;
        }
        return taskList.getTask(currentId);
    }

    public TaskModel getLastTask() {
        return taskList.getTask(maxTaskId);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public int getMaxTaskId() {
        return maxTaskId;
    }

    public void setMaxTaskId(int maxTaskId) {
        this.maxTaskId = maxTaskId;
    }
}

