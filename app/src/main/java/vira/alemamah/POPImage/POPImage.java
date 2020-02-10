package vira.alemamah.POPImage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import vira.alemamah.Animation.ApplyAnimation;
import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class POPImage extends Activity {
    private ImageView imageView,imageViewShare;
    private TextView textView;
    private RelativeLayout popContainer;
    private static int CIRCULAR_DURATION=2000;
    SetFonts setFontsClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pop);
        initializing();
        setFonts();
        getWindow().setLayout((int) (getScreenSize().get("Width")*0.8),(int)(getScreenSize().get("Height")*0.8));
        imageView.setImageResource(getIntent().getIntExtra("address",0));
        textView.setText(getIntent().getStringExtra("text"));
        popContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ApplyAnimation.circularAnimation(imageViewShare,CIRCULAR_DURATION);
                popContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,textView.getText());
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"اشتراک گذاری متن."));
            }
        });
    }
    private void setFonts() {
        setFontsClass = new SetFonts(POPImage.this,getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textView);
    }
    private void initializing() {
        imageView = (ImageView) findViewById(R.id.popImage);
        textView = (TextView) findViewById(R.id.popTextDescription);
        popContainer = (RelativeLayout) findViewById(R.id.popContainer);
        imageViewShare = (ImageView) findViewById(R.id.popImageShare);
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
