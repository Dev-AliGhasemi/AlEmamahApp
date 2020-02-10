package vira.alemamah.TabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import vira.alemamah.AdapterRecycler.AdapterRecyclerStory;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelStory.ModelStory;
import seens.alemamah.R;

public class FragStory extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private List<ModelStory> modelStories;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_frag_story,container,false);
        initializing();
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(getContext());
            modelStories = databaseHelperAlEmamah.getSubjectStory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("size list",modelStories.get(0).getEmam());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),1,false));
        recyclerView.setAdapter(new AdapterRecyclerStory(getContext(),modelStories));
        return view;
    }

    private void initializing() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerStory);
    }
}
