package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dwi Enggar on 14/11/2016.
 */

public class AddTask extends AppCompatActivity {
    Button bsave, btnrDate, btnrTime, bDueDatePicker;
    EditText etName, etDueDate, etNotes, etrDate, etrTime;
    Spinner sspinner;
    TextView tvhasil;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add New Task");
        etName = (EditText) findViewById(R.id.taskname);
        etDueDate = (EditText) findViewById(R.id.editTextduedate);
        etNotes = (EditText) findViewById(R.id.notes);
        etrDate = (EditText) findViewById(R.id.rDate);
        etrTime = (EditText) findViewById(R.id.rTime);
        sspinner = (Spinner) findViewById(R.id.spinner);
        tvhasil = (TextView) findViewById(R.id.textView10);
        btnrDate = (Button) findViewById(R.id.btnDateReminder);
        btnrTime = (Button) findViewById(R.id.btnTimeReminder);
        bDueDatePicker = (Button) findViewById(R.id.buttonDueDate);


        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Task has been saved", Toast.LENGTH_LONG).show();
                tvhasil.setText(etName.getText().toString() + " " + etDueDate.getText().toString() + " " + etNotes.getText().toString() + " " + etrDate.getText().toString()
                        + " " + etrTime.getText().toString());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

