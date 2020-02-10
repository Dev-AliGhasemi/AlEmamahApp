package vira.alemamah.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import vira.alemamah.AdapterRecycler.AdapterSubRecycler;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.DescriptionBehavior;
import vira.alemamah.Font.SetFonts;
import vira.alemamah.ModelSubjectBehavior.ModelSubjectBehavior;
import seens.alemamah.R;


public class ViewHolderBehavior extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public RecyclerView recyclerView;
    private SetFonts setFontsClass;
    DatabaseHelperAlEmamah databaseHelperAlEmamah = null;
    boolean isExpand = false,isFavorite = false;
    private static ArrayList<ModelSubjectBehavior> type;
    Context context;
    String status;
    public ViewHolderBehavior(View itemView, Context context,String status) {
        super(itemView);
        setFontsClass = new SetFonts(context,context.getResources().getString(R.string.iranSansFont));
        this.context = context;
        this.status = status;
        initializing();
        setFonts();

    }

    private void setFonts() {
        setFontsClass.setFont("TextView",textView);
    }

    private void initializing() {
        if (status.equals("main")){
            textView = (TextView) itemView.findViewById(R.id.textViewBehavior);
            imageView = (ImageView) itemView.findViewById(R.id.imageMore);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.subTitleRecycler);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        databaseHelperAlEmamah = new DatabaseHelperAlEmamah(context);
                        type = databaseHelperAlEmamah.getTypeBehavior(textView.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!isExpand && imageView.getVisibility() == View.VISIBLE){
                        recyclerView.setLayoutManager(new LinearLayoutManager(context,1,false));
                        recyclerView.setAdapter(new AdapterSubRecycler(context,type));
                        imageView.setImageResource(R.drawable.icon_less);
                        isExpand = true;
                    }else if (isExpand && imageView.getVisibility() == View.VISIBLE){
                        recyclerView.setAdapter(null);
                        imageView.setImageResource(R.drawable.icon_more);
                        isExpand = false;
                    }else {
                        Intent intent = new Intent(context, DescriptionBehavior.class);
                        intent.putExtra("title",type.get(0).getType());
                        intent.putExtra("text",type.get(0).getText());
                        context.startActivity(intent);
                    }
                }
            });
        }else if (status.equals("sub")){
            textView = (TextView) itemView.findViewById(R.id.textViewSub);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (ModelSubjectBehavior modelSubjectBehavior : type){
                        if (modelSubjectBehavior.getType().equals(textView.getText())){
                            Intent intent = new Intent(context , DescriptionBehavior.class);
                            intent.putExtra("title",modelSubjectBehavior.getType());
                            intent.putExtra("text",modelSubjectBehavior.getText());
                            context.startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}
