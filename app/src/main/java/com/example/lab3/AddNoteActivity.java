package com.example.lab3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNoteActivity extends AppCompatActivity {
    private TextInputLayout nameInputLayout;
    private TextInputLayout descriptionInputLayout;
    private TextInputEditText nameInputText;
    private TextInputEditText descriptionInputText;
    private Button addNoteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.add_view_vertical);
        } else {
            setContentView(R.layout.add_view_horizontal);
        }

        nameInputLayout = findViewById(R.id.noteName);
        descriptionInputLayout = findViewById(R.id.noteDescription);
        nameInputText = findViewById(R.id.nameText);
        descriptionInputText = findViewById(R.id.descriptionText);
        addNoteButton = findViewById(R.id.addButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameInputText.setText(extras.getString("name"));
            descriptionInputText.setText(extras.getString("description"));
        }
    }

    public void addButtonClick(View view) {
        try{
            String name = nameInputText.getText().toString();
            String description = descriptionInputText.getText().toString();
            if (name.isEmpty()) {
                throw new RuntimeException();
            }
            Intent data = new Intent();
            data.putExtra("name", name);
            data.putExtra("description",description);
            setResult(1, data);
            finish();
        } catch (RuntimeException runtimeException) {
            showToast("Нельзя оставлять название пустым");
        }
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("name", nameInputText.getText().toString());
        savedInstanceState.putString("desc", descriptionInputText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nameInputText.setText(savedInstanceState.getString("name"));
        descriptionInputText.setText(savedInstanceState.getString("desc"));
    }


}
