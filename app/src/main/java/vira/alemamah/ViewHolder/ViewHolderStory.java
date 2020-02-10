package vira.alemamah.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.DescriptionBehavior;
import vira.alemamah.Font.SetFonts;
import vira.alemamah.ModelStory.ModelStory;
import seens.alemamah.R;


public class ViewHolderStory extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public RecyclerView recyclerView;
    private SetFonts setFontsClass;
    DatabaseHelperAlEmamah databaseHelperAlEmamah = null;
    boolean isExpand = false;
    private static ArrayList<ModelStory> type;
    Context context;
    String status;
    public ViewHolderStory(View itemView, Context context, String status) {
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
        }else if (status.equals("sub")){
            textView = (TextView) itemView.findViewById(R.id.textViewSub);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            Intent intent = new Intent(context , DescriptionBehavior.class);
                            intent.putExtra("title",textView.getText());
                            intent.putExtra("text",textView.getTag()+"");
                            context.startActivity(intent);
                        }
                    });
                }
            }
        }
