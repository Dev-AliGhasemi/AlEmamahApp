package vira.alemamah;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import vira.alemamah.Font.SetFonts;
import seens.alemamah.R;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewLogo,imageViewSeens;
    private Timer timer = new Timer();
    private String textAddress;
    private int count=0;
    private SetFonts setFonts;
    private TextView textViewAddress,textSeensGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setFonts();
        imageViewLogo.animate().rotationX(360).setDuration(2000).setInterpolator(new OvershootInterpolator()).setStartDelay(900).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (count <= textAddress.length()){
                                    textViewAddress.setText(textAddress.substring(0,count));
                                    count++;
                                }
                            }
                        });
                    }
                },50,90);
                imageViewSeens.animate().alpha(1f).setDuration(1000);
                textSeensGroup.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Thread timer = new Thread(){
                            public void run(){
                                try {
                                    sleep(2000);
                                    Intent intent = new Intent(MainActivity.this,MainPage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        timer.start();
                    }
                });
            }
        });
    }
    private void init(){
        imageViewLogo = (ImageView) findViewById(R.id.act1_splash_logo);
        imageViewSeens = (ImageView) findViewById(R.id.act1_logoSeens);
        textAddress = getResources().getString(R.string.addressSite);
        textViewAddress = (TextView) findViewById(R.id.act1_addressSite);
        textSeensGroup = (TextView) findViewById(R.id.act1_seems);
    }
    private void setFonts(){
        setFonts = new SetFonts(MainActivity.this,getResources().getString(R.string.iranSansFont));
        setFonts.setFont("TextView",textSeensGroup);
    }
}
