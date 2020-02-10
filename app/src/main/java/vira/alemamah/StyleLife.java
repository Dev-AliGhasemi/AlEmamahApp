package vira.alemamah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class StyleLife extends AppCompatActivity {
    private TextView textViewEmamAli,textViewEmamHassan,textViewEmamHossein,textViewEmamSajad,textViewEmamBagher,textViewEmamSadegh,textViewEmamKazem,textViewEmamReza,textViewEmamJavad,textViewEmamHadi,textViewEmamHassanAskari,textViewEmamMahdi;
    private SetFonts setFontsClass;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_style_life);
        setFontsClass = new SetFonts(this,getResources().getString(R.string.piramoozFont));
        initializing();
        setFonts();
        setupToolbar();
    }

    private void setFonts() {
        setFontsClass.setFont("TextView",textViewEmamAli);
        setFontsClass.setFont("TextView",textViewEmamHassan);
        setFontsClass.setFont("TextView",textViewEmamHossein);
        setFontsClass.setFont("TextView",textViewEmamSajad);
        setFontsClass.setFont("TextView",textViewEmamBagher);
        setFontsClass.setFont("TextView",textViewEmamSadegh);
        setFontsClass.setFont("TextView",textViewEmamKazem);
        setFontsClass.setFont("TextView",textViewEmamReza);
        setFontsClass.setFont("TextView",textViewEmamJavad);
        setFontsClass.setFont("TextView",textViewEmamHadi);
        setFontsClass.setFont("TextView",textViewEmamHassanAskari);
        setFontsClass.setFont("TextView",textViewEmamMahdi);
    }

    private void initializing() {
        textViewEmamAli = (TextView) findViewById(R.id.textEmamAli);
        textViewEmamHassan = (TextView) findViewById(R.id.textEmamHassan);
        textViewEmamHossein = (TextView) findViewById(R.id.textEmamHossein);
        textViewEmamSajad = (TextView) findViewById(R.id.textEmamSajad);
        textViewEmamBagher = (TextView) findViewById(R.id.textEmamBagher);
        textViewEmamSadegh = (TextView) findViewById(R.id.textEmamSadegh);
        textViewEmamKazem = (TextView) findViewById(R.id.textEmamKazem);
        textViewEmamReza = (TextView) findViewById(R.id.textEmamReza);
        textViewEmamJavad = (TextView) findViewById(R.id.textEmamJavad);
        textViewEmamHadi = (TextView) findViewById(R.id.textEmamHadi);
        textViewEmamHassanAskari = (TextView) findViewById(R.id.textEmamHassanAskari);
        textViewEmamMahdi = (TextView) findViewById(R.id.textEmamMahdi);
        toolbar = (Toolbar) findViewById(R.id.toolbarStyleLife);
    }

    public void styleLifeEmamClick(View view){
        Intent intent = new Intent(StyleLife.this,DescriptionStyleLife.class);
        switch (view.getId()){
            case R.id.textEmamAli:
            case R.id.picEmamAli:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamHassan:
            case R.id.picEmamHassan:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamHossein:
            case R.id.picEmamHossein:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamSajad:
            case R.id.picEmamSajad:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamBagher:
            case R.id.picEmamBagher:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamSadegh:
            case R.id.picEmamSadegh:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamKazem:
            case R.id.picEmamKazem:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamReza:
            case R.id.picEmamReza:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamJavad:
            case R.id.picEmamJavad:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamHadi:
            case R.id.picEmamHadi:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamHassanAskari:
            case R.id.picEmamHassanAskari:
                intent.putExtra("emam",view.getTag()+"");
                break;
            case R.id.textEmamMahdi:
            case R.id.picEmamMahdi:
                intent.putExtra("emam",view.getTag()+"");
                break;
        }
        startActivity(intent);
    }
    private void setupToolbar() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
    }
    }
