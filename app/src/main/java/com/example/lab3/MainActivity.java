package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab3.models.TaskModel;
import com.example.lab3.services.TaskService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final TaskService taskService = new TaskService();
    private Button addNoteButton;
    private Button editNoteButton;
    private Button showLastNoteButton;
    private ImageButton previousNoteButton;
    private ImageButton nextNoteButton;
    private TextView nameInputText;
    private TextView descriptionInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_horizontal);
        }
        nameInputText = findViewById(R.id.name);
        descriptionInputText = findViewById(R.id.description);
        addNoteButton = findViewById(R.id.addButton);
        editNoteButton = findViewById(R.id.editButton);
        showLastNoteButton = findViewById(R.id.showLastNote);
        previousNoteButton = findViewById(R.id.previousNoteButton);
        nextNoteButton = findViewById(R.id.nextNoteButton);
    }

    public void addButtonClick(View view) {
        taskService.addTask(getTaskFromUI());
        showToast("Запись добавлена!");
    }

    public void editButtonClick(View view) {
        taskService.editTask(getTaskFromUI());
        showToast("Запись изменена!");
    }

    public void previousNoteButtonClick(View view) {
        TaskModel taskModel = taskService.getPreviousTask();
        nameInputText.setText(taskModel.getName());
        descriptionInputText.setText(taskModel.getDescription());
        showToast("Предыдущая запись");
    }

    public void nextNoteButtonClick(View view) {
        TaskModel taskModel = taskService.getNextTask();
        nameInputText.setText(taskModel.getName());
        descriptionInputText.setText(taskModel.getDescription());
        showToast("Следущая запись");
    }

    public void showLastNoteButtonClick(View view) {
        TaskModel taskModel = taskService.getLastTask();
        nameInputText.setText(taskModel.getName());
        descriptionInputText.setText(taskModel.getDescription());
        showToast("Последняя запись");
    }

    private TaskModel getTaskFromUI() {
        return new TaskModel(nameInputText.getText().toString(),
                descriptionInputText.getText().toString());
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "Resume");
    }

    @Override
    protected void onPause() {
        Log.i("MainActivity", "Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("MainActivity", "Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("MainActivity", "Destroy");
        super.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrenId", taskService.getCurrentTaskId());
        ArrayList<String> data = new ArrayList<>();
        for (TaskModel taskModel : taskService.getTaskRepository().getNotes()) {
            data.add(taskModel.toString());
        }
        savedInstanceState.putStringArrayList("TaskModels", data);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        taskService.setCurrentTaskId(savedInstanceState.getInt("CurrenId"));
        taskService.getTaskRepository().setNotes(parseTasks(savedInstanceState.getStringArrayList("TaskModels")));

    }

    private List<TaskModel> parseTasks(List<String> strings) {
        List<TaskModel> taskModels = new ArrayList<>();
        for (String string : strings) {
            String[] fields = string.split(",");
            taskModels.add(new TaskModel(Integer.parseInt(fields[0]),
                                    fields[1], fields[2]));
        }
        return taskModels;
    }
}