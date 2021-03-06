package id.sch.smktelkom_mlg.project.xiirpl104142434.tasksreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimas Bramantyo on 11/20/2016.
 */
public class SecondActivitySubject extends AppCompatActivity implements RecyclerAdapterSubject.Eminem {
    DB_Controller helpher;
    List<DatabaseModelSubject> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab2;
    Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("ListSubject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        helpher = new DB_Controller(this, "", null, 1);
        dbList = new ArrayList<DatabaseModelSubject>();
        dbList = helpher.getDataFromDB2();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapterSubject(this, dbList);
        mRecyclerView.setAdapter(mAdapter);

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivitySubject.this, AddSubject.class));
            }
        });

        //RecyclerAdapterSubject.Eminem eminem
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doEdit(int pos) {
        startActivity(new Intent(SecondActivitySubject.this, EditSubject.class));
    }
}
