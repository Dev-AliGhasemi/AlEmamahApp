package vira.alemamah.SetTabsForTabLayout;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class SetTabsForTabLayout {
    private TabLayout tabLayout;
    private String[] tabText;
    private int[] tabImage;
    private View view;
    private Context context;
    private TextView tabTextView;
    private ImageView tabImageView;
    SetFonts setFonts;

    public SetTabsForTabLayout(TabLayout tabLayout, Context context) {
        this.tabLayout = tabLayout;
        this.context = context;
        tabText = context.getResources().getStringArray(R.array.tab_text);
        tabImage = new int[]{R.mipmap.icon_story,R.mipmap.ic_emam,R.mipmap.icon_home};
        setFonts = new SetFonts(context,"fonts/iran_sans.otf");
    }
    public void setCustomView(){
        for (int i = 0 ; i<tabImage.length ; i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            view = LayoutInflater.from(context).inflate(R.layout.custom_tab,null,true);
            tabTextView = (TextView) view.findViewById(R.id.tab_text);
            tabImageView = (ImageView) view.findViewById(R.id.tab_image);
            tabImageView.setColorFilter(Color.parseColor("#eeeeee"));
            tabTextView.setTextColor(Color.parseColor("#eeeeee"));
            tabTextView.setText(tabText[i]);
            setFonts.setFont("TextView",tabTextView);
            tabImageView.setImageResource(tabImage[i]);
            if (tabImage[i]==R.mipmap.icon_home){
                tabImageView.setPadding(6,6,6,6);
                tabTextView.setPadding(0,0,0,6);
            }

            tab.setCustomView(view);
        }
        setSelectedTab(tabLayout.getTabAt(tabImage.length-1));
    }
    public void setSelectedTab(TabLayout.Tab tabSelected){
        view = tabSelected.getCustomView();
        tabTextView = (TextView) view.findViewById(R.id.tab_text);
        tabImageView = (ImageView) view.findViewById(R.id.tab_image);
        tabImageView.setColorFilter(Color.parseColor("#439EB8"));
        tabTextView.setTextColor(Color.parseColor("#439EB8"));
    }
    public void setUnselectedTab(TabLayout.Tab tabUnselected){
        view = tabUnselected.getCustomView();
        tabTextView = (TextView) view.findViewById(R.id.tab_text);
        tabImageView = (ImageView) view.findViewById(R.id.tab_image);
        tabImageView.setColorFilter(Color.parseColor("#eeeeee"));
        tabTextView.setTextColor(Color.parseColor("#eeeeee"));
    }
}
