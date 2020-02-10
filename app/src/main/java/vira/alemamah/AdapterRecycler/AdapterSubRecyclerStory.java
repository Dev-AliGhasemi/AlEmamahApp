package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vira.alemamah.ModelStory.ModelStory;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderStory;

public class AdapterSubRecyclerStory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList <ModelStory> subData;
    public AdapterSubRecyclerStory(Context context, ArrayList<ModelStory> subData) {
        this.context = context;
        this.subData = subData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sub_recycler,parent,false);
        return new ViewHolderStory(view,context,"sub");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolderStory viewHolderStory = (ViewHolderStory) holder;
        viewHolderStory.textView.setText(subData.get(position).getName());
        viewHolderStory.textView.setTag(subData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return subData.size();
    }
}
