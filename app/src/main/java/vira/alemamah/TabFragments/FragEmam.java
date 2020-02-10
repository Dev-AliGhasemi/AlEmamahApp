package vira.alemamah.TabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vira.alemamah.AdapterRecycler.AdapterRecyclerDes;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelDesc.ModelDesc;
import seens.alemamah.R;

public class FragEmam extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private List<ModelDesc> modelDescs = new ArrayList<>();
    private AdapterRecyclerDes adapterRecyclerDes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_frag_emam,container,false);
        initializing();
        setOrRefreshRecyclerAdapter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setOrRefreshRecyclerAdapter();
    }

    private void setOrRefreshRecyclerAdapter(){
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(getContext());
            modelDescs = databaseHelperAlEmamah.getDataDesc();
            adapterRecyclerDes = new AdapterRecyclerDes(getContext(),modelDescs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),1,false));
        recyclerView.setAdapter(adapterRecyclerDes);
    }
    private void initializing() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerEmam);
    }
}
