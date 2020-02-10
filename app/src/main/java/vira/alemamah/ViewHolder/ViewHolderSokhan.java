package vira.alemamah.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import vira.alemamah.POPSokhan.POPSokhan;
import seens.alemamah.R;

public class ViewHolderSokhan extends RecyclerView.ViewHolder {
    public TextView textViewSokhan;
    public ImageView imageViewFavorite,imageViewShare;
    private SetFonts setFontsClass;
    private Context context;
    public ViewHolderSokhan(View itemView, final Context context) {
        super(itemView);
        this.context = context;
        initializing();
        imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,textViewSokhan.getText());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent,"اشتراک گذاری متن."));
            }
            });
        textViewSokhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, POPSokhan.class);
                intent.putExtra("text",textViewSokhan.getText()+"\r\n\r\n"+textViewSokhan.getTag());
                context.startActivity(intent);
            }
        });
    }

    private void initializing() {
        textViewSokhan = (TextView) itemView.findViewById(R.id.textViewSokhanQuran);
        imageViewFavorite = (ImageView) itemView.findViewById(R.id.imageFavoriteSokhan);
        imageViewShare = (ImageView) itemView.findViewById(R.id.imageShare);
        setFonts();
    }

    private void setFonts() {
        setFontsClass = new SetFonts(context,context.getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textViewSokhan);
    }

}
