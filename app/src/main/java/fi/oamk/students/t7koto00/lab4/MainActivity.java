package fi.oamk.students.t7koto00.lab4;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button buttonStart = null;
    public TextView textView1 = null;
    static final int ADD_NEW_PART_REQ_ID = 311;

    private PartArrayAdapter partArrayAdapter;
    ArrayList<WorkoutPartBase> WorkoutPartBase = new ArrayList<>();
    ListView listView1 = null;
    int totalLength = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart =(Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        listView1 = (ListView) findViewById(R.id.listView1);
        textView1 = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_part:
                Intent intent = new Intent(this, AddPartActivity.class);
                startActivityForResult(intent, ADD_NEW_PART_REQ_ID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //Intent intent = new Intent(this, WorkoutActivity.class);
            //startActivity(intent);

            Intent intent = new Intent(this, WorkoutActivity.class);
            intent.putExtra("DATA", WorkoutPartBase);
            startActivity(intent);
        }
    }

        @Override
    protected void onResume() {
        super.onResume();

        partArrayAdapter = new PartArrayAdapter(this,WorkoutPartBase);
        listView1.setAdapter(partArrayAdapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_PART_REQ_ID && resultCode == RESULT_OK) {
            WorkoutPartBase workoutPartBase = (WorkoutPartBase) data.getSerializableExtra("DATA");
            WorkoutPartBase.add(workoutPartBase);
            totalLength = 0;
            for (int i = 0; i < WorkoutPartBase.size(); i++) {
                WorkoutPartBase part = WorkoutPartBase.get(i);
                totalLength = totalLength + part.getLength();
            }
            textView1.setText("Total length: " + totalLength + " seconds.");
        }
    }
}
