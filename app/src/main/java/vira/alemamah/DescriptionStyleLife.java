package vira.alemamah;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.IOException;

import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class DescriptionStyleLife  extends Activity {
    private TextView textViewTitle,textViewDesc;
    private SetFonts setFontsClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_desc_behavior);
        String text;
        initializing();
        setFonts();
        textViewTitle.setText(getIntent().getStringExtra("emam"));
        try {
            DatabaseHelperAlEmamah databaseHelperAlEmamah = new DatabaseHelperAlEmamah(this);
            textViewDesc.setText(databaseHelperAlEmamah.multiSelect("select styleLife from tbl_style_life where emam = '"+textViewTitle.getText()+"'"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initializing() {
        textViewTitle = (TextView) findViewById(R.id.titleTextViewBehavior);
        textViewDesc = (TextView) findViewById(R.id.descTextViewBehavior);
    }
    private void setFonts() {
        setFontsClass = new SetFonts(DescriptionStyleLife.this,getResources().getString(R.string.iranSansFont));
        setFontsClass.setFont("TextView",textViewTitle);
        setFontsClass.setFont("TextView",textViewDesc);
    }
}
