package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.DescriptionBehavior;
import vira.alemamah.ModelStory.ModelStory;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderStory;

public class AdapterRecyclerStory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ModelStory> modelStories;
    DatabaseHelperAlEmamah databaseHelperAlEmamah = null;
    private static ArrayList<Boolean> isOpen = new ArrayList<>();

    private static ArrayList<ModelStory> type;
    public AdapterRecyclerStory(Context context, List<ModelStory> modelStories) {
        this.context = context;
        this.modelStories = modelStories;
        for (int i = 0 ; i<modelStories.size();i++){
            isOpen.add(i,false);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_behavior,null);
        return new ViewHolderStory(view,context,"main");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderStory viewHolderStory = (ViewHolderStory) holder;
        if (modelStories.get(position).isSubTitle()){
            viewHolderStory.imageView.setVisibility(View.VISIBLE);
            viewHolderStory.textView.setText(modelStories.get(position).getEmam());
            if (isOpen.get(position) == false){
                viewHolderStory.recyclerView.setAdapter(null);
                viewHolderStory.imageView.setImageResource(R.drawable.icon_more);
                isOpen.set(position,false);
            }else {
                viewHolderStory.recyclerView.setLayoutManager(new LinearLayoutManager(context,1,false));
                viewHolderStory.recyclerView.setAdapter(new AdapterSubRecyclerStory(context,type));
                viewHolderStory.imageView.setImageResource(R.drawable.icon_less);
            }
        }else {
            viewHolderStory.imageView.setVisibility(View.INVISIBLE);
            viewHolderStory.textView.setText(modelStories.get(position).getEmam());
        }
        viewHolderStory.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    databaseHelperAlEmamah = new DatabaseHelperAlEmamah(context);
                    type = databaseHelperAlEmamah.getEmamStory(viewHolderStory.textView.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (isOpen.get(position) == false){
                    viewHolderStory.recyclerView.setLayoutManager(new LinearLayoutManager(context,1,false));
                    viewHolderStory.recyclerView.setAdapter(new AdapterSubRecyclerStory(context,type));
                    viewHolderStory.imageView.setImageResource(R.drawable.icon_less);
                    isOpen.set(position,true);
                }else if (isOpen.get(position) == true){
                    viewHolderStory.recyclerView.setAdapter(null);
                    viewHolderStory.imageView.setImageResource(R.drawable.icon_more);
                    isOpen.set(position,false);
                }else {
                    Intent intent = new Intent(context, DescriptionBehavior.class);
                    intent.putExtra("title",type.get(0).getName());
                    intent.putExtra("text",type.get(0).getText());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelStories.size();
    }
}
