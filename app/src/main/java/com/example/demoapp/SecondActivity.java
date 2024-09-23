package com.example.demoapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        radioGroup = findViewById(R.id.radioGroup);
        seekBar = findViewById(R.id.seekBar_satisfaction);
        checkBox = findViewById(R.id.checkBox_comments);

        Button submitButton = findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(v -> showDialog());
    }

    public void showDialog() {
        // Get selected RadioButton text
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String feedbackType = radioButton != null ? radioButton.getText().toString() : "No Feedback";


        int satisfactionLevel = seekBar.getProgress();


        boolean additionalComments = checkBox.isChecked();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Feedback");
        builder.setMessage("Feedback: " + feedbackType + "\nSatisfaction Level: " + satisfactionLevel +
                "\nAdditional Comments: " + (additionalComments ? "Yes" : "No"));
        builder.setPositiveButton("Submit", (dialog, which) -> {

            Toast.makeText(SecondActivity.this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}
