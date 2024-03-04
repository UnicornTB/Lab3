package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lab3.models.TaskModel;
import com.example.lab3.repositories.TaskRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private final TaskRepository taskRepository = new TaskRepository();
    private Button addNoteButton;
    private Button editNoteButton;
    private Button showLastNoteButton;
    private ImageButton previousNoteButton;
    private ImageButton nextNoteButton;
    private TextInputLayout nameInputLayot;
    private TextInputLayout descriptionInputLayot;
    private TextInputEditText nameInputText;
    private TextInputEditText descriptionInputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInputText = findViewById(R.id.nameInput);
        descriptionInputText = findViewById(R.id.descriptionInput);
        nameInputLayot = findViewById(R.id.nameInputLayot);
        descriptionInputLayot = findViewById(R.id.descriptionInputLayot);
        addNoteButton = findViewById(R.id.addButton);
        editNoteButton = findViewById(R.id.editButton);
        showLastNoteButton = findViewById(R.id.showLastNote);
        previousNoteButton = findViewById(R.id.previousNoteButton);
        nextNoteButton = findViewById(R.id.nextNoteButton);
    }
    public void addButtonClick(View view) {
        TaskModel taskModel = new TaskModel(0,
                nameInputText.getText().toString(),
                descriptionInputText.getText().toString());
        taskRepository.addTask(taskModel);
    }

    public void previousNoteButtonClick(View view) {
    }

    public void nextNoteButtonClick(View view) {
    }

    public void showLastNoteButtonClick(View view) {
    }

    public void editButtonClick(View view) {
    }
}