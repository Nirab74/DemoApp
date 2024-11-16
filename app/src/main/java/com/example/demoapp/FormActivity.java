package com.example.demoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class FormActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, emailEditText, numEditText;
    private TextInputEditText passwordEditText;
    private Spinner spinner;
    private LinearLayout outputLayout;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        numEditText = findViewById(R.id.num);
        passwordEditText = findViewById(R.id.pass);
        spinner = findViewById(R.id.spinner);
        Button submitButton = findViewById(R.id.submit);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);


        String[] options = {"Select Course", "Computer Science", "Mathematics", "Physics", "Chemistry"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spinner.setAdapter(adapter);

        submitButton.setOnClickListener(v -> handleSubmit());
    }

    private void handleSubmit() {

        String name = nameEditText.getText().toString().trim();
        String studentId = idEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String mobileNumber = numEditText.getText().toString().trim();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();
        String selectedCourse = spinner.getSelectedItem().toString();


        if (name.isEmpty() || studentId.isEmpty() || email.isEmpty() || mobileNumber.isEmpty() || password.isEmpty() || selectedCourse.equals("Select Course")) {
            Toast.makeText(FormActivity.this, "Please fill in all fields and select a course", Toast.LENGTH_SHORT).show();
            return;
        }


        String output = "Name: " + name + "\n" +
                "Student ID: " + studentId + "\n" +
                "Email: " + email + "\n" +
                "Mobile: " + mobileNumber + "\n" +
                "Course: " + selectedCourse;

        outputText.setText(output);
        outputLayout.setVisibility(View.VISIBLE);


        clearForm();
    }

    private void clearForm() {
        nameEditText.setText("");
        idEditText.setText("");
        emailEditText.setText("");
        numEditText.setText("");
        passwordEditText.setText("");
        spinner.setSelection(0);
    }
}
