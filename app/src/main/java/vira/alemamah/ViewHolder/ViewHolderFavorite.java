package vira.alemamah.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class ViewHolderFavorite extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;
    public View view;
    private SetFonts setFontsClass;
    private Context context;

    public ViewHolderFavorite(View itemView,Context context) {
        super(itemView);
        this.context = context;
        initializing();
        setFonts();
    }
    private void setFonts() {
        setFontsClass = new SetFonts(context,context.getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textView);
    }
    private void initializing() {
        imageView = (ImageView) itemView.findViewById(R.id.imageFavoriteList);
        textView = (TextView) itemView.findViewById(R.id.textNameItemFavorite);
        view = itemView.findViewById(R.id.viewSeprator);
    }
}
