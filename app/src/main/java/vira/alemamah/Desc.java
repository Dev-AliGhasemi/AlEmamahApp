package vira.alemamah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class Desc extends AppCompatActivity {
    private TextView textViewDesc,textViewTitleDesc;
    private ImageView imageView;
    private SetFonts setFontsClass;
    private int[] listPictures = {R.drawable.pic_desc_najaf,R.drawable.pic_desc_baghee,R.drawable.pic_desc_karbalah,R.drawable.pic_desc_baghee,R.drawable.pic_desc_baghee,R.drawable.pic_desc_baghee,R.drawable.pic_desc_kazemein,R.drawable.pic_desc_mashhad,R.drawable.pic_desc_kazemein,R.drawable.pic_desc_samera,R.drawable.pic_desc_samera,R.drawable.pic_desc_jamkaran};
    private RelativeLayout relativeLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_desc);
        initializing();
        setupToolbar();
        textViewTitleDesc.setText(getIntent().getStringExtra("title"));
        textViewDesc.setText(getIntent().getStringExtra("text"));
        imageView.setImageResource(listPictures[Integer.parseInt(getIntent().getStringExtra("pic"))]);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textViewTitleDesc.bringToFront();
                textViewTitleDesc.setY(imageView.getHeight()-(textViewTitleDesc.getHeight()/2));
                relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initializing() {
        textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        textViewTitleDesc = (TextView) findViewById(R.id.textViewTitleDesc);
        imageView = (ImageView) findViewById(R.id.imageTitleDesc);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeContainerDesc);
        toolbar = (Toolbar) findViewById(R.id.toolbarOfDesc);
        setFont();
    }

    private void setFont() {
        setFontsClass = new SetFonts(Desc.this,getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textViewDesc);
        setFontsClass.setFont("TextView",textViewTitleDesc);
    }
    private void setupToolbar() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
    }
}
