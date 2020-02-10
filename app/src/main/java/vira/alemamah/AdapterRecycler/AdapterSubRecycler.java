package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vira.alemamah.ModelSubjectBehavior.ModelSubjectBehavior;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderBehavior;

public class AdapterSubRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList <ModelSubjectBehavior> subData;
    public AdapterSubRecycler(Context context, ArrayList<ModelSubjectBehavior> subData) {
        this.context = context;
        this.subData = subData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sub_recycler,parent,false);
        return new ViewHolderBehavior(view,context,"sub");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderBehavior viewHolderBehavior = (ViewHolderBehavior) holder;
        viewHolderBehavior.textView.setText(subData.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return subData.size();
    }
}
