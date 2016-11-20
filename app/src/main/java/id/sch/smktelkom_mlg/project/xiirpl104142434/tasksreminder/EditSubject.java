package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder.RecyclerAdapterSubject.context;

public class EditSubject extends AppCompatActivity {

    EditText etSubject, etTeacher;
    TextView tvoldsbj, tvoldtch;
    Button buttonSave,  buttonDelete;
    DB_Controller controller;
    List<DatabaseModelSubject> dbList;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        setTitle("Edit Subject");

        etSubject = (EditText) findViewById(R.id.editTextSubject);
        etTeacher = (EditText) findViewById(R.id.editTextTeacher);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        tvoldsbj = (TextView) findViewById(R.id.subjectold);
        tvoldtch = (TextView) findViewById(R.id.teacherold);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        controller = new DB_Controller(this, "", null, 1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        dbList= new ArrayList<DatabaseModelSubject>();
        dbList= controller.getDataFromDB2();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        if(dbList.size()>0){
            //String idsubject=  dbList.get(position).getIdsubject();
            String name= dbList.get(position).getSubject();
            String email=dbList.get(position).getTeacher();
            etSubject.setText(name);
            etTeacher.setText(email);
            tvoldsbj.setText(name);
            tvoldtch.setText(email);
            //tvsbj.setText(idsubject);
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Button OK KLIK!

                if (isValid()) {
                    doKlik();
                    Toast.makeText(getApplicationContext(), "Subject has been edited", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Warning");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure to delete this subject?")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                controller.delete_subject(etSubject.getText().toString(),etTeacher.getText().toString());
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
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
                onBackPressed();
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

    private void doKlik() {
        controller.update_subject(etSubject.getText().toString(), etTeacher.getText().toString(), tvoldsbj.getText().toString(), tvoldtch.getText().toString());
    }

    private boolean isValid() {
        boolean valid = true;

        String subject = etSubject.getText().toString();
        String teacher = etTeacher.getText().toString();

        if (subject.isEmpty()){
            etSubject.setError("Subject is Empty!");
            valid = false;
        }
        else if (subject.length()>21)
        {
            etSubject.setError("Maximun 20 caracters!");
            valid = false;
        }
        else
        {
            etSubject.setError(null);
        }
       if (teacher.isEmpty()){
            etTeacher.setError("Teacher is Empty!");
            valid = false;
        }
        else if (subject.length()>21)
        {
            etTeacher.setError("Maximun 20 caracters!");
            valid = false;
        }
        else {
            etTeacher.setError(null);
        }
            return valid;
    }

}
