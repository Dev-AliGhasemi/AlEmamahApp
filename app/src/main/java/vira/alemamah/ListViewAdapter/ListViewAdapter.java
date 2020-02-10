package vira.alemamah.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

/**
 * Created by Computer Brain on 8/10/2018.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    String[] items;
    int[] image;
    TextView textView;
    ImageView imageView;
    SetFonts setFonts ;
    public ListViewAdapter(Context context) {
        //this.items = items;
        this.context = context;
        setFonts = new SetFonts(context,"fonts/iran_sans.otf");
        image = new int[]{R.drawable.icon_home_menu,R.drawable.icon_favorite_menu,R.drawable.icon_share_menu,R.drawable.icon_about_us_menu,R.drawable.icon_exit_menu};
        this.items = context.getResources().getStringArray(R.array.items);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.from(context).inflate(R.layout.navigation_menu,null);
        textView = (TextView) view.findViewById(R.id.text_item_menu);
        imageView = (ImageView) view.findViewById(R.id.image_item_menu);
        setFonts.setFont("TextView",textView);
        textView.setText(items[position]);
        imageView.setImageResource(image[position]);
        return view;
    }
}
