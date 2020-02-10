package vira.alemamah.TabFragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.daimajia.slider.library.SliderLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.Font.SetFonts;
import vira.alemamah.ModelHadisBottom.ModelHadis;
import seens.alemamah.R;
import vira.alemamah.SliderAdapter.SliderAdapter;

public class FragHome extends Fragment {
    private SliderLayout sliderLayout;
    private List<Integer> itemsSlider = new ArrayList<>();
    private View view;
    private ViewFlipper viewFlipper;
    private final static int INTERVALFLIPPING=5000;
    private final static int DURATIONFADES=1000;
    private LinearLayout linearLayout;
    private TextView textViewLifeStyle,textViewEtich,textViewSokhanNab,textNarrator,textHadisBottom;
    SetFonts setFontsPiramooz,setFontsIranSans;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_frag_home,container,false);
        setFontsPiramooz = new SetFonts(getActivity(),getString(R.string.piramoozFont));
        setFontsIranSans = new SetFonts(getActivity(),getString(R.string.iranSansFont));
        initializing();
        setupSliderLayout();
        setFontsFunction();
        setupViewFlipper();
        setHadisBottom();
        return view;
    }
    private void setHadisBottom(){
        DatabaseHelperAlEmamah databaseHelperAlEmamah = null;
        try {
            databaseHelperAlEmamah = new DatabaseHelperAlEmamah(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelHadis modelHadis = new ModelHadis();
        Random random = new Random();
        int idHadis = random.nextInt(14);
        if (idHadis==0)
            idHadis++;
        modelHadis = databaseHelperAlEmamah.getHadisFromDatabase("select hadisArabic,hadisFarsi,narrator,address from tbl_hadis_bottom where id = "+idHadis);
        textNarrator.setText(modelHadis.getNarrator());
        textHadisBottom.setText(modelHadis.getHadisArabi()+"\r\n\r\n"+modelHadis.getHadisFarsi()+"\r\n\r\n"+modelHadis.getAddress());
    }

    private void setupViewFlipper() {
        Animation fadein = new AlphaAnimation(0,1);
        fadein.setDuration(DURATIONFADES);
        Animation fadeout = new AlphaAnimation(1,0);
        fadeout.setDuration(DURATIONFADES);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(INTERVALFLIPPING);
        viewFlipper.setInAnimation(fadein);
        viewFlipper.setOutAnimation(fadeout);
        viewFlipper.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getScreenSize().get("Height")/5));
    }
    private HashMap<String,Integer> getScreenSize() {
        HashMap<String,Integer> size = new HashMap<>();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int x = point.x;
        int y = point.y;
        size.put("Width",x);
        size.put("Height",y);
        return size;
    }
    private void setupSliderLayout() {
        itemsSlider.add(R.drawable.pic1);
        itemsSlider.add(R.drawable.pic2);
        itemsSlider.add(R.drawable.pic3);
        itemsSlider.add(R.drawable.pic4);
        SliderAdapter sliderAdapter = new SliderAdapter(itemsSlider,sliderLayout,getActivity());
        sliderAdapter.setItemSlider();
        sliderLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getScreenSize().get("Height")/3));
    }
    private void initializing(){
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragHomeContainer);
        textViewLifeStyle = (TextView) view.findViewById(R.id.act2_lifeStyle);
        textViewSokhanNab = (TextView) view.findViewById(R.id.act2_sokhanText);
        textViewEtich = (TextView) view.findViewById(R.id.act2_etichText);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.act2_viewFlipper);
        textNarrator = (TextView) view.findViewById(R.id.act2_textNarratorHadisBottom);
        textHadisBottom = (TextView) view.findViewById(R.id.act2_textHadisBottom);
    }
    private void setFontsFunction(){
        setFontsPiramooz.setFont("TextView",textViewLifeStyle);
        setFontsPiramooz.setFont("TextView",textViewEtich);
        setFontsPiramooz.setFont("TextView",textViewSokhanNab);
        setFontsIranSans.setFont("TextView",textNarrator);
        setFontsIranSans.setFont("TextView",textHadisBottom);
    }
}
