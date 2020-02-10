package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.ModelSokhanQuran.ModelSokhanQuran;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderSokhan;

public class AdapterRecyclerSokhan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ModelSokhanQuran> modelSokhanQurans;
    private Context context;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    public AdapterRecyclerSokhan(List<ModelSokhanQuran> modelSokhanQurans, Context context) {
        this.modelSokhanQurans = modelSokhanQurans;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_sokhan,parent,false);
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ViewHolderSokhan(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderSokhan viewHolderSokhan = (ViewHolderSokhan) holder;
        if (modelSokhanQurans.get(position).getFavorite().equals("1")) {
            viewHolderSokhan.textViewSokhan.setText(modelSokhanQurans.get(position).getText());
            viewHolderSokhan.textViewSokhan.setTag(modelSokhanQurans.get(position).getAddress());
            viewHolderSokhan.imageViewFavorite.setColorFilter(Color.parseColor("#BC3F3C"));
            viewHolderSokhan.imageViewFavorite.setImageResource(R.drawable.icon_favorite);
        }else{
            viewHolderSokhan.textViewSokhan.setText(modelSokhanQurans.get(position).getText());
            viewHolderSokhan.textViewSokhan.setTag(modelSokhanQurans.get(position).getAddress());
            viewHolderSokhan.imageViewFavorite.setColorFilter(Color.parseColor("#999999"));
            viewHolderSokhan.imageViewFavorite.setImageResource(R.drawable.icon_unfavorite);
        }
        viewHolderSokhan.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View v) {
                if (modelSokhanQurans.get(position).getFavorite().equals("0")){
                    viewHolderSokhan.imageViewFavorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_favorite));
                    viewHolderSokhan.imageViewFavorite.setColorFilter(Color.parseColor("#BC3F3C"));
                    viewHolderSokhan.imageViewFavorite.animate().rotationX(360).setDuration(1000);
                    databaseHelperAlEmamah.addOrRemoveFavorite("insert into tbl_favorite values('"+viewHolderSokhan.textViewSokhan.getTag()+"','"+viewHolderSokhan.textViewSokhan.getText()+"','sokhanQuran')");
                    databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_sokhan_quran set favorite = 1 where address='"+modelSokhanQurans.get(position).getAddress()+"'");
                    modelSokhanQurans.get(position).setFavorite("1");
                }else {
                    viewHolderSokhan.imageViewFavorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_unfavorite));
                    viewHolderSokhan.imageViewFavorite.setColorFilter(Color.parseColor("#999999"));
                    viewHolderSokhan.imageViewFavorite.animate().rotationX(720).setDuration(1000);
                    databaseHelperAlEmamah.addOrRemoveFavorite("delete from tbl_favorite where identity = '"+viewHolderSokhan.textViewSokhan.getTag()+"'");
                    databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_sokhan_quran set favorite = 0 where address='"+modelSokhanQurans.get(position).getAddress()+"'");
                    modelSokhanQurans.get(position).setFavorite("0");
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return modelSokhanQurans.size();
    }
}
