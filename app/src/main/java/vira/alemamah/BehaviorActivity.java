package vira.alemamah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

import vira.alemamah.AdapterRecycler.AdapterRecycler;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelSubjectBehavior.ModelSubjectBehavior;
import seens.alemamah.R;

public class BehaviorActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private ArrayList<ModelSubjectBehavior> subject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_behavior);
        initializing();
        setupToolbar();
        subject = new ArrayList<>();
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(this);
            subject = databaseHelperAlEmamah.getSubjectBehavior();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this,1,false));
        recyclerView.setAdapter(new AdapterRecycler(this,subject));
    }
    private void setupToolbar() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
    }
    private void initializing() {
        toolbar = (Toolbar) findViewById(R.id.toolbarOfBehavior);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerBehavior);
    }
}
