package fi.oamk.students.t7koto00.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class WorkoutActivity extends AppCompatActivity{
    ArrayList<WorkoutPartBase> WorkoutPartBase = new ArrayList<>();
    private TextView textViewCounter = null;
    private TextView textViewType = null;
    int length = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        textViewType = (TextView) findViewById(R.id.textViewType);
        Intent intent = getIntent();
        WorkoutPartBase = (ArrayList<WorkoutPartBase>) intent.getSerializableExtra("DATA");
        if (WorkoutPartBase.isEmpty()) {

        } else {
            WorkoutPartBase part = WorkoutPartBase.get(0);
            String type = part.getName();
            length = part.getLength();
            textViewType.setText(type);
            textViewCounter.setText("" + length);
            countDown(length);
        }
    }


    private void countDown(int length) {
        CountDownTimer timer = new CountDownTimer(length * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewCounter.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter++;
                if (counter < WorkoutPartBase.size()) {
                    WorkoutPartBase part = WorkoutPartBase.get(counter);
                    String type = part.getName();
                    int length2 = part.getLength();
                    textViewType.setText(type);
                    textViewCounter.setText("" + length2);
                    countDown(length2);
                } else {

                    finish();
                }
            }
        };
        timer.start();

    }
}