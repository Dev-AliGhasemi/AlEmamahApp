package vira.alemamah;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import vira.alemamah.DatabaseHelper.DatabaseHelperAlEmamah;
import vira.alemamah.Font.SetFonts;
import vira.alemamah.ListViewAdapter.ListViewAdapter;
import vira.alemamah.POPImage.POPImage;
import seens.alemamah.R;
import vira.alemamah.SetTabsForTabLayout.SetTabsForTabLayout;
import vira.alemamah.ViewPagerAdapter.AdapterViewPager;


public class MainPage extends AppCompatActivity {
    private Toolbar toolbar;
    private static final int fontSize = 18;
    private ListView navListView;
    private DrawerLayout drawerLayout;
    private TextView textViewSokhanRandom,textViewNavUp,textViewNavDown,textViewTitleSoftware,textViewTextSoftware,textViewTextAboutUs,textViewTitleAnoutUs,textViewToast;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button buttonCloseDialog;
    private Dialog dialog;
    long lastTime = 0;
    SetTabsForTabLayout setTabsForTabLayout;
    SetFonts setFonts;
    int currentNum=0;
    final static int COUNT_IDS=3;
    final static int COUNT_TABS=3;
    int[] ids = new int[COUNT_IDS];
    String[] hadis = new String[COUNT_IDS];
    String[] narrator = new String[COUNT_IDS];
    static DatabaseHelperAlEmamah databaseHelperAlEmamah;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        init();
        textViewSokhanRandom.setSelected(true);
        viewPager.setAdapter(new AdapterViewPager(getSupportFragmentManager(),COUNT_TABS));
        tabLayout.setupWithViewPager(viewPager);
        setTabsForTabLayout = new SetTabsForTabLayout(tabLayout,this);
        setTabsForTabLayout.setCustomView();
        viewPager.setCurrentItem(COUNT_TABS);
        setFonts = new SetFonts(this,"fonts/iran_sans.otf");
        setFont();
        try {
            if (databaseHelperAlEmamah == null)
                databaseHelperAlEmamah = new DatabaseHelperAlEmamah(this);
                Random rnd = new Random();
                for (int i = 0; i < COUNT_IDS; i++) {
                    currentNum = rnd.nextInt(13);
                    if (currentNum == 0)
                        currentNum++;
                    for (int b = 0; b < COUNT_IDS; b++) {
                        if (ids[b] != currentNum)
                            ids[i] = currentNum;
                        else {
                            break;
                        }
                    }
                }
                for (int i = 0; i < COUNT_IDS; i++) {
                    if (ids[i] == 0)
                        ids[i]++;
                }
                for (int i = 0; i < COUNT_IDS; i++) {
                    hadis[i] = databaseHelperAlEmamah.multiSelect("select hadis from tbl_hadis_kotah where id =" + ids[i]);
                    narrator[i] = databaseHelperAlEmamah.multiSelect("select narrator from tbl_hadis_kotah where id =" + ids[i]);
                }
                for (int i = 0; i < 3; i++) {
                    textViewSokhanRandom.setText(textViewSokhanRandom.getText() + narrator[i] + " " + hadis[i] + "        ");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSupportActionBar(toolbar);
        if (actionBarDrawerToggle == null)
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        navListView.setAdapter(listViewAdapter);
        navListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        viewPager.setCurrentItem(COUNT_TABS);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case 1:
                        startActivity(new Intent(MainPage.this,FavoriteList.class));
                        break;
                    case 2:
                        try {
                            startActivity(shareApplication());
                        }catch (ActivityNotFoundException e){
                            Toast toast = Toast.makeText(MainPage.this, "لطفا برنامه ایران اپس را نصب نمایید.", Toast.LENGTH_LONG);
                            View viewToast = toast.getView();
                            textViewToast = viewToast.findViewById(android.R.id.message);
                            setFonts = new SetFonts(MainPage.this,getResources().getString(R.string.chamranFont));
                            setFonts.setFont("TextView",textViewToast);
                            textViewToast.setTextSize(fontSize);
                            toast.show();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://iranapps.ir/dl"));
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        showAboutUs();
                        break;
                    case 4:
                        finishAffinity();
                        break;

                }
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabsForTabLayout.setSelectedTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabsForTabLayout.setUnselectedTab(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showAboutUs() {
         dialog = new Dialog(MainPage.this);
         dialog.setContentView(R.layout.layout_about_us);
         setFonts=  new SetFonts(this,getResources().getString(R.string.chamranFont));
         textViewTitleSoftware = (TextView) dialog.findViewById(R.id.textTitleAboutSoftware);
         textViewTextSoftware = (TextView) dialog.findViewById(R.id.textAboutSoftware);
         textViewTitleAnoutUs = (TextView) dialog.findViewById(R.id.textTitleAboutSeens);
         textViewTextAboutUs = (TextView) dialog.findViewById(R.id.textAboutSeens);
         buttonCloseDialog = (Button) dialog.findViewById(R.id.btnClose);
         setFonts.setFont("TextView",textViewTitleAnoutUs);
         setFonts.setFont("TextView",textViewTitleSoftware);
         setFonts.setFont("Button",buttonCloseDialog);
         setFonts = new SetFonts(this,getResources().getString(R.string.iranSansFont));
         setFonts.setFont("TextView",textViewTextSoftware);
         setFonts.setFont("TextView",textViewTextAboutUs);
         dialog.show();
         buttonCloseDialog.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.cancel();
             }
         });
    }

    private Intent shareApplication() {
        //Share With Iran Apps
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.farsitel.bazaar");
        intent.setData(Uri.parse("bazaar://details?id="+getPackageName()));
        return intent;
    }
    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navListView = (ListView) findViewById(R.id.nav_list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        textViewSokhanRandom = (TextView) findViewById(R.id.act2_sokhan_random);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        textViewNavUp = (TextView) findViewById(R.id.textNavHeaderUp);
        textViewNavDown = (TextView) findViewById(R.id.textNavHeaderDown);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tabLayout.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = getScreenSize().get("Height")/8;
        tabLayout.setLayoutParams(params);
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
    private void setFont() {
        setFonts.setFont("TextView",textViewSokhanRandom);
        setFonts = new SetFonts(this , getResources().getString(R.string.chamranFont));
        setFonts.setFont("TextView",textViewNavDown);
        setFonts.setFont("TextView",textViewNavUp);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT))
            drawerLayout.closeDrawer(Gravity.RIGHT);
        else
            if (lastTime+2000>System.currentTimeMillis()){
                super.onBackPressed();
            }else {
                Toast toast = Toast.makeText(this, "برای خروج دوبار کلیک کنید.", Toast.LENGTH_SHORT);
                View viewToast = toast.getView();
                textViewToast = (TextView) viewToast.findViewById(android.R.id.message);
                setFonts = new SetFonts(this,getResources().getString(R.string.chamranFont));
                setFonts.setFont("TextView",textViewToast);
                textViewToast.setTextSize(fontSize);
                toast.show();
            }
        lastTime = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    public void imageViewFlipperClick(View v){
        Intent intent = new Intent(MainPage.this, POPImage.class);
        switch (v.getId()){
            case R.id.imageFlipper1:
                intent.putExtra("address",R.drawable.pic_flip1);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic1));
                break;
            case R.id.imageFlipper2:
                intent.putExtra("address",R.drawable.pic_flip2);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic2));
                break;
            case R.id.imageFlipper3:
                intent.putExtra("address",R.drawable.pic_flip3);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic3));
                break;
            case R.id.imageFlipper4:
                intent.putExtra("address",R.drawable.pic_flip4);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic4));
                break;
            case R.id.imageFlipper5:
                intent.putExtra("address",R.drawable.pic_flip5);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic5));
                break;
            case R.id.imageFlipper6:
                intent.putExtra("address",R.drawable.pic_flip6);
                intent.putExtra("text",getResources().getString(R.string.descriptionPic6));
                break;
        }
        startActivity(intent);
    }
    public void imageMiddleSectionClick(View view){
        switch (view.getId()){
            case R.id.act2_etich:
                startActivity(new Intent(MainPage.this,BehaviorActivity.class));
                break;
            case R.id.act2_styleOfLife:
                startActivity(new Intent(MainPage.this,StyleLife.class));
                break;
            case R.id.act2_sokhan:
                startActivity(new Intent(MainPage.this,SokhanQuran.class));
                break;
        }
    }
}

