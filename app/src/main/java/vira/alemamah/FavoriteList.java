package vira.alemamah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vira.alemamah.AdapterRecycler.AdapterRecyclerFavorite;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelFavorite.ModelFavorite;
import seens.alemamah.R;

public class FavoriteList extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private List<ModelFavorite> modelFavorites = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_favorite);
        initializing();
        setupToolbar();
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(this);
            modelFavorites = databaseHelperAlEmamah.getFavorites();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        recyclerView.setAdapter(new AdapterRecyclerFavorite(modelFavorites, this));
    }

    private void initializing() {
        toolbar = (Toolbar) findViewById(R.id.toolbarOfFavorite);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerFavorite);
    }

    private void setupToolbar() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
    }
}