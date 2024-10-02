package com.example.demoapp;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private SeekBar seekBarSatisfaction;
    private CheckBox checkFeature1, checkFeature2;
    private Switch switchRecommend;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        ratingBar = findViewById(R.id.ratingBar);
        seekBarSatisfaction = findViewById(R.id.seekBarSatisfaction);
        checkFeature1 = findViewById(R.id.checkFeature1);
        checkFeature2 = findViewById(R.id.checkFeature2);
        switchRecommend = findViewById(R.id.switchRecommend);
        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(v -> {

            float rating = ratingBar.getRating();
            int satisfactionLevel = seekBarSatisfaction.getProgress();
            boolean isServiceEnjoyed = checkFeature1.isChecked();
            boolean wantMoreFeatures = checkFeature2.isChecked();
            boolean wouldRecommend = switchRecommend.isChecked();


            String feedbackMessage = "Rating: " + rating + "\n" +
                    "Satisfaction Level: " + satisfactionLevel + "/100\n" +
                    "Enjoyed Service: " + (isServiceEnjoyed ? "Yes" : "No") + "\n" +
                    "Want More Features: " + (wantMoreFeatures ? "Yes" : "No") + "\n" +
                    "Would Recommend: " + (wouldRecommend ? "Yes" : "No");


            Toast.makeText(SecondActivity.this, feedbackMessage, Toast.LENGTH_LONG).show();
        });


        seekBarSatisfaction.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
