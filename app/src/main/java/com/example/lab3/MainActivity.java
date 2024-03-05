package com.example.lab3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab3.models.TaskList;
import com.example.lab3.models.TaskModel;
import com.example.lab3.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random random = new Random();
    ActivityResultLauncher<Intent> addActivityResult = registerForActivityResult
                            (new ActivityResultContracts.StartActivityForResult(),
                                    new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    Intent intent = activityResult.getData();
                    TaskModel taskModel = new TaskModel(intent.getStringExtra("name"),
                            intent.getStringExtra("description"));
                    if (activityResult.getResultCode() == 1) {
                        taskRepository.addTask(taskModel);
                    }
                    if (activityResult.getResultCode() == 2) {
                        taskRepository.editTask(taskModel);
                    }
                    nameInputText.setText(taskModel.getName());
                    descriptionInputText.setText(taskModel.getDescription());
                }
            });
    private final TaskRepository taskRepository = new TaskRepository(new TaskList(new ArrayList<>()));
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
        Intent intent = new Intent(this, AddNoteActivity.class);
        intent.putExtra("name", "Название " + random.nextInt(1000));
        intent.putExtra("description", "Описание " + random.nextInt(1000));
        addActivityResult.launch(intent);
    }

    public void editButtonClick(View view) {
        try {
            Intent intent = new Intent(this, EditNoteActivity.class);
            intent.putExtra("name", nameInputText.getText().toString());
            intent.putExtra("description", descriptionInputText.getText().toString());
            addActivityResult.launch(intent);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            showToast("Нет записи, сначала добавьте запись!");
        }
    }

    public void previousNoteButtonClick(View view) {
        try{
            TaskModel taskModel = taskRepository.getPreviousTask();
            nameInputText.setText(taskModel.getName());
            descriptionInputText.setText(taskModel.getDescription());
            showToast("Предыдущая запись");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            showToast("Нет записей!");
        }

    }

    public void nextNoteButtonClick(View view) {
        try{
            TaskModel taskModel = taskRepository.getNextTask();
            nameInputText.setText(taskModel.getName());
            descriptionInputText.setText(taskModel.getDescription());
            showToast("Следущая запись");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            showToast("Нет записей!");
        }

    }

    public void showLastNoteButtonClick(View view) {
        try{
            TaskModel taskModel = taskRepository.getLastTask();
            nameInputText.setText(taskModel.getName());
            descriptionInputText.setText(taskModel.getDescription());
            showToast("Последняя запись");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            showToast("Нет записей!");
        }

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
        savedInstanceState.putInt("currentId", taskRepository.getCurrentId());
        savedInstanceState.putInt("maxId", taskRepository.getMaxTaskId());
        savedInstanceState.putString("taskList", taskRepository.getTaskList().toString());
        savedInstanceState.putString("noteName", nameInputText.getText().toString());
        savedInstanceState.putString("noteDescription", descriptionInputText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        taskRepository.setCurrentId(savedInstanceState.getInt("currentId"));
        taskRepository.setMaxTaskId(savedInstanceState.getInt("maxId"));
        taskRepository.setTaskList(parseTasks(savedInstanceState.getString("taskList")));
        nameInputText.setText(savedInstanceState.getString("noteName"));
        descriptionInputText.setText(savedInstanceState.getString("noteDescription"));
    }

    private TaskList parseTasks(String data) {
        TaskList taskList = new TaskList(new ArrayList<>());
        String[] arrays = data.split("\n");
        for (String string : arrays) {
            String[] fields = string.split(", ");
            TaskModel taskModel = new TaskModel(Integer.parseInt(fields[0]), fields[1],  fields[2]);
            taskList.addTask(taskModel);
        }
        return taskList;
    }
}