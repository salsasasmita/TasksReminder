package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Dwi Enggar on 14/11/2016.
 */

public class AddTask extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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

        bDueDatePicker.setOnClickListener(this);
        btnrDate.setOnClickListener(this);
        btnrTime.setOnClickListener(this);

        sspinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

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

    private void loadSpinnerData() {
        DB_Controller db = new DB_Controller(this, "", null, 1);
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sspinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == bDueDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etDueDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnrDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    etrDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnrTime) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            etrTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

