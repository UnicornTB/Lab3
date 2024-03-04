package com.example.lab3;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab3.services.TaskService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNoteActivity extends AppCompatActivity {
    private final TaskService taskService = new TaskService();
    private TextInputLayout nameInputLayout;
    private TextInputLayout descriptionInputLayout;
    private TextInputEditText nameInputText;
    private TextInputEditText descriptionInputText;
    private Button addNoteButton;


}
