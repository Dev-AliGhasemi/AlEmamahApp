package vira.alemamah.POPSokhan;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.widget.TextView;

import java.util.HashMap;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class POPSokhan extends Activity {
    private TextView textViewSokhanPOP;
    private SetFonts setFontsClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pop_sokhan);
        initializing();
        getWindow().setLayout((int) (getScreenSize().get("Width")*0.8),(int)(getScreenSize().get("Height")*0.8));
        textViewSokhanPOP.setText(getIntent().getStringExtra("text"));
    }

    private void initializing() {
        textViewSokhanPOP = (TextView) findViewById(R.id.textViewSokhanPOP);
        setFont();
    }

    private void setFont() {
        setFontsClass = new SetFonts(POPSokhan.this,getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textViewSokhanPOP);
    }
    private HashMap<String,Integer> getScreenSize() {
        HashMap<String,Integer> size = new HashMap<>();
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int x = point.x;
        int y = point.y;
        size.put("Width",x);
        size.put("Height",y);
        return size;
    }
}
