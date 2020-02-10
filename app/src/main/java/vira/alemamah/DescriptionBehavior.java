package vira.alemamah;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class DescriptionBehavior extends Activity {
    private TextView titleTextVIew,descTextView;
    private SetFonts setFontsClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_desc_behavior);
        initializing();
        setFonts();
        titleTextVIew.setText(getIntent().getStringExtra("title"));
        descTextView.setText(getIntent().getStringExtra("text"));
    }

    private void setFonts() {
        setFontsClass = new SetFonts(DescriptionBehavior.this,getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",titleTextVIew);
        setFontsClass.setFont("TextView",descTextView);
    }

    private void initializing() {
        titleTextVIew = (TextView) findViewById(R.id.titleTextViewBehavior);
        descTextView = (TextView) findViewById(R.id.descTextViewBehavior);
    }
}
