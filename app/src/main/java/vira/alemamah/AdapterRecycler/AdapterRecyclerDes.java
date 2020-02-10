package vira.alemamah.AdapterRecycler;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.Desc;
import vira.alemamah.ModelDesc.ModelDesc;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderDesc;

public class AdapterRecyclerDes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ModelDesc> modelDescList = new ArrayList<>();
    private int[] listPictures = {R.drawable.pic_small_desc_najaf,R.drawable.pic_small_desc_baghee,R.drawable.pic_small_desc_karbalah,R.drawable.pic_small_desc_baghee,R.drawable.pic_small_desc_baghee,R.drawable.pic_small_desc_baghee,R.drawable.pic_small_desc_kazemein,R.drawable.pic_small_desc_mashhad,R.drawable.pic_small_desc_kazemein,R.drawable.pic_small_desc_samera,R.drawable.pic_small_desc_samera,R.drawable.pic_small_desc_jamkaran};
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;

    public AdapterRecyclerDes(Context context, List<ModelDesc> modelDescList) {
        this.context = context;
        this.modelDescList = modelDescList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_recycler_desc_emam,parent,false);
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ViewHolderDesc(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderDesc viewHolderDesc = (ViewHolderDesc) holder;
        viewHolderDesc.textViewNameEmam.setText(modelDescList.get(position).getName());
        viewHolderDesc.textViewDateBornEdition.setText(modelDescList.get(position).getDateBorn());
        viewHolderDesc.textViewDateDeathEdition.setText(modelDescList.get(position).getDateDeath());
        viewHolderDesc.textViewFatherNameEdition.setText(modelDescList.get(position).getFatherName());
        viewHolderDesc.textViewMotherNameEdtion.setText(modelDescList.get(position).getMotherName());
        viewHolderDesc.textViewAdjectiveEdition.setText(modelDescList.get(position).getAdjectives());
        viewHolderDesc.buttonRead.setTag(modelDescList.get(position).getText());
        viewHolderDesc.circleImageView.setImageResource(listPictures[position]);
        if (modelDescList.get(position).getFavorite().equals("1")){
            viewHolderDesc.imageViewFavorite.setColorFilter(Color.parseColor("#BC3F3C"));
            viewHolderDesc.imageViewFavorite.setImageResource(R.drawable.icon_favorite);
        }else{
            viewHolderDesc.imageViewFavorite.setColorFilter(Color.parseColor("#999999"));
            viewHolderDesc.imageViewFavorite.setImageResource(R.drawable.icon_unfavorite);
        }
        viewHolderDesc.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View v) {
                if (modelDescList.get(position).getFavorite().equals("0")){
                    viewHolderDesc.imageViewFavorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_favorite));
                    viewHolderDesc.imageViewFavorite.setColorFilter(Color.parseColor("#BC3F3C"));
                    viewHolderDesc.imageViewFavorite.animate().rotationX(360).setDuration(1000);
                    databaseHelperAlEmamah.addOrRemoveFavorite("insert into tbl_favorite values('"+viewHolderDesc.textViewNameEmam.getText()+"','"+viewHolderDesc.buttonRead.getTag()+"','desc')");
                    databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_desc set favorite = 1 where text='"+modelDescList.get(position).getText()+"'");
                    modelDescList.get(position).setFavorite("1");
                }else {
                    viewHolderDesc.imageViewFavorite.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_unfavorite));
                    viewHolderDesc.imageViewFavorite.setColorFilter(Color.parseColor("#999999"));
                    viewHolderDesc.imageViewFavorite.animate().rotationX(720).setDuration(1000);
                    databaseHelperAlEmamah.addOrRemoveFavorite("delete from tbl_favorite where identity = '"+viewHolderDesc.textViewNameEmam.getText()+"'");
                    databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_desc set favorite = 0 where text='"+modelDescList.get(position).getText()+"'");
                    modelDescList.get(position).setFavorite("0");
                }
            }
        });
        viewHolderDesc.buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Desc.class);
                intent.putExtra("text",viewHolderDesc.buttonRead.getTag().toString());
                intent.putExtra("title",viewHolderDesc.textViewNameEmam.getText());
                intent.putExtra("pic",position+"");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context,new Pair<View, String>(viewHolderDesc.circleImageView,"imageTitle"));
                    context.startActivity(intent,activityOptions.toBundle());
                }else {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelDescList.size();
    }
}
