package vira.alemamah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import vira.alemamah.AdapterRecycler.AdapterRecyclerSokhan;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelSokhanQuran.ModelSokhanQuran;
import seens.alemamah.R;

public class SokhanQuran extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private List<ModelSokhanQuran> modelSokhanQurans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sokhan);
        initializing();
        setupToolbar();
        getSokhanQuran();
    }

    private void getSokhanQuran() {
        modelSokhanQurans = new ArrayList<>();
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(SokhanQuran.this);
            modelSokhanQurans = databaseHelperAlEmamah.getDataSokhanQuran();
            Log.e("info",modelSokhanQurans.size()+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(SokhanQuran.this,1,false));
        recyclerView.setAdapter(new AdapterRecyclerSokhan(modelSokhanQurans,SokhanQuran.this));
    }
    private void initializing() {
        toolbar = (Toolbar) findViewById(R.id.toolbarOfSokhan);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerSokhan);
    }
    private void setupToolbar() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
    }
}
