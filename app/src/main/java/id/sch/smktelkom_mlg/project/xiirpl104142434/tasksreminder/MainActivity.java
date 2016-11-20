package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button button;
    DatePicker datePicker;
    Calendar calendar;
    TextView dateView, listsbj;
    int year, month, day;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.imgBtnSubject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivitySubject.class));
            }
        });

        findViewById(R.id.imgBtnTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListTask.class));
            }
        });

    }
}
