package vira.alemamah.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.DescriptionBehavior;
import vira.alemamah.ModelFavorite.ModelFavorite;
import vira.alemamah.POPSokhan.POPSokhan;
import seens.alemamah.R;
import vira.alemamah.ViewHolder.ViewHolderFavorite;

public class AdapterRecyclerFavorite extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ModelFavorite> lisyFavorite;
    private Context context;
    private TextView textView;
    private Button button;
    private DatabaseHelperAlEmamah databaseHelperAlEmamah;
    private static final int fontSize = 15;

    public AdapterRecyclerFavorite(List<ModelFavorite> lisyFavorite, Context context) {
        this.lisyFavorite = lisyFavorite;
        this.context = context;
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_favorite,null,false);
        return new ViewHolderFavorite(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolderFavorite viewHolderFavorite = (ViewHolderFavorite) holder;
        //code for remove the last seprator
//        if (position == lisyFavorite.size()-1){
//            viewHolderFavorite.view.setVisibility(View.INVISIBLE);
//        }

        if (lisyFavorite.get(position).getCategory().equals("desc")){
            viewHolderFavorite.textView.setText(lisyFavorite.get(position).getText());
        }else {
            viewHolderFavorite.textView.setText(lisyFavorite.get(position).getIdentity());
        }
        viewHolderFavorite.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                Log.e("info" , position+"");
                if (lisyFavorite.get(position).getCategory().equals("desc")){
                    intent = new Intent(context, DescriptionBehavior.class);
                    intent.putExtra("text",lisyFavorite.get(position).getText());
                    intent.putExtra("title",lisyFavorite.get(position).getIdentity());
                }else if (lisyFavorite.get(position).getCategory().equals("sokhanQuran")){
                    intent = new Intent(context, POPSokhan.class);
                    intent.putExtra("text",lisyFavorite.get(position).getText()+"\r\n\r\n"+lisyFavorite.get(position).getIdentity());
                }
                context.startActivity(intent);
            }
        });
        viewHolderFavorite.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(viewHolderFavorite.itemView,"آیا می خواهید این ایتم را پاک کنید؟",Snackbar.LENGTH_LONG);
                View view = snackbar.getView();
                Typeface font = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.iranSansFont));
                textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                button = (Button) view.findViewById(android.support.design.R.id.snackbar_action);
                button.setTextColor(Color.RED);
                view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                textView.setTypeface(font, Typeface.BOLD);
                button.setTypeface(font, Typeface.BOLD);
                textView.setTextSize(fontSize);
                button.setTextSize(fontSize);
                snackbar.setAction("بله", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lisyFavorite.get(position).getCategory().equals("sokhanQuran")){
                            databaseHelperAlEmamah.addOrRemoveFavorite("delete from tbl_favorite where category = 'sokhanQuran' and identity ='"+lisyFavorite.get(position).getIdentity()+"'");
                            databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_sokhan_quran set favorite = 0 where address='"+lisyFavorite.get(position).getIdentity()+"'");
                        }else if (lisyFavorite.get(position).getCategory().equals("desc")){
                            databaseHelperAlEmamah.addOrRemoveFavorite("delete from tbl_favorite where category = 'desc' and identity ='"+lisyFavorite.get(position).getIdentity()+"'");
                            databaseHelperAlEmamah.addOrRemoveFavorite("update tbl_desc set favorite = 0 where name='"+lisyFavorite.get(position).getIdentity()+"'");
                        }
                        lisyFavorite.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,lisyFavorite.size());
                    }
                });
                snackbar.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lisyFavorite.size();
    }
}
