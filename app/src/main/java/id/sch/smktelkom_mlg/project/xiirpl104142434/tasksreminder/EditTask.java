package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditTask extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    final Context context = this;
    Button btnEdit, btnDel, btnrDate, btnrTime, bDueDatePicker;
    EditText etName, etDueDate, etNotes, etrDate, etrTime, ettasknameold, etduedateold;
    Spinner sspinner;
    TextView tvhasil;
    DB_Controller controller;
    Integer idsbj, position;
    List<DatabaseModel> dbList;
    private int mYear, mMonth, mDay, mHour, mMinute, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        setTitle("Edit Subject");

        pos = getIntent().getIntExtra("position", 0);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        controller = new DB_Controller(this, "", null, 1);

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
        btnDel = (Button) findViewById(R.id.buttonDel);
        btnEdit = (Button) findViewById(R.id.buttonEdit);
        ettasknameold = (EditText) findViewById(R.id.editTexttasknameold);
        etduedateold = (EditText) findViewById(R.id.editTextduedateold);
        bDueDatePicker.setOnClickListener(this);
        btnrDate.setOnClickListener(this);
        btnrTime.setOnClickListener(this);

        controller = new DB_Controller(this, "", null, 1);
        dbList = new ArrayList<DatabaseModel>();
        dbList = controller.getDataFromDB();

        if (dbList.size() > 0) {
            String idsubject = dbList.get(position).getIdsubject();
            String taskname = dbList.get(position).getTaskname();
            String duedates = dbList.get(position).getDuedates();
            String notes = dbList.get(position).getNotes();
            String rdate = dbList.get(position).getRdate();
            String rtime = dbList.get(position).getRtime();
            etName.setText(taskname);
//            sspinner.setText(email);
            etDueDate.setText(duedates);
            etNotes.setText(notes);
            etrDate.setText(rdate);
            etrTime.setText(rtime);
            ettasknameold.setText(taskname);
            etduedateold.setText(duedates);
        }
        // Loading spinner data from database
        loadSpinnerData();
        //loadData();

        findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Task has been saved", Toast.LENGTH_LONG).show();
                idsbj = sspinner.getSelectedItemPosition() + 1;
                controller.update_task(
                        etName.getText().toString(),
                        idsbj,
                        etDueDate.getText().toString(),
                        etNotes.getText().toString(),
                        etrDate.getText().toString(),
                        etrTime.getText().toString(),
                        ettasknameold.getText().toString(),
                        etduedateold.getText().toString());
                //tvhasil.setText(etrTime.getText().toString());
            }
        });

        findViewById(R.id.buttonDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("DELETE");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                controller.delete_task(etName.getText().toString());
                                onBackPressed();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

    }

    /*String taskNameOld;
    private void loadData() {
        DB_Controller db = new DB_Controller(this, "", null, 1);
        String[] dataDB;
        dataDB = db.getArray(pos+1);


        taskNameOld = dataDB[0];
        etName.setText(dataDB[0]);
        //sspinner.setAdapter(dataDB[1]);
        etDueDate.setText(dataDB[2]);
        etNotes.setText(dataDB[3]);
        etrDate.setText(dataDB[4]);
        etrTime.setText(dataDB[5]);

        for(int i = 0; i < dataDB.length; i++){
            Log.d("EMINEM", "dataDB" + i + " : " + dataDB[i]);
        }
    }*/

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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
