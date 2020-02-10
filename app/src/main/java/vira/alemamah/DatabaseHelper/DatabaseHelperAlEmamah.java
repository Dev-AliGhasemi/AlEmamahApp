package vira.alemamah.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import vira.alemamah.ModelDesc.ModelDesc;
import vira.alemamah.ModelFavorite.ModelFavorite;
import vira.alemamah.ModelHadisBottom.ModelHadis;
import vira.alemamah.ModelSokhanQuran.ModelSokhanQuran;
import vira.alemamah.ModelStory.ModelStory;
import vira.alemamah.ModelSubjectBehavior.ModelSubjectBehavior;

/**
 * Created by Computer Brain on 8/10/2018.
 */

public class DatabaseHelperAlEmamah extends SQLiteOpenHelper{
    private static int DB_VERSION = 2;
    private static String DB_NAME="db_alemamah.db";
    private static String DB_PATH;
    private Context context;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    public DatabaseHelperAlEmamah(Context context) throws IOException {
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
        DB_PATH="/data/data/"+context.getPackageName()+"/databases/";
        if (isExists()){
            if (isOldVersion())
                copyDatabase();
        }else {
            copyDatabase();
        }
    }
    public String multiSelect(String command){
        String data ;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        data = cursor.getString(0);
        sqLiteDatabase.close();
        cursor.close();
        return data ;
    }
    public boolean isOldVersion(){
        int currentVersion = 1;
        try {
            this.getReadableDatabase();
            String path = DB_PATH+DB_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
            currentVersion = sqLiteDatabase.getVersion();
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
          return DB_VERSION > currentVersion;
    }
    public boolean isExists(){
        boolean exists = false;
        try {
            String path = DB_PATH+DB_NAME;
            File file = new File(path);
            exists =  file.exists();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return exists;
    }
    public void copyDatabase() throws IOException {
        this.getReadableDatabase();
        InputStream inputStream = this.context.getAssets().open(DB_NAME);
        String outputFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outputFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))>0 ) {
            outputStream.write(buffer,0,length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
    public ModelHadis getHadisFromDatabase(String command){
        ModelHadis modelHadis = new ModelHadis();
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery(command,null);
        cursor.moveToFirst();
        modelHadis.setHadisArabi(cursor.getString(0));
        modelHadis.setHadisFarsi(cursor.getString(1));
        modelHadis.setNarrator(cursor.getString(2));
        modelHadis.setAddress(cursor.getString(3));
        sqLiteDatabase.close();
        cursor.close();
        return modelHadis;
    }
    public ArrayList getSubjectBehavior(){
        ArrayList<ModelSubjectBehavior>  subject = new ArrayList<>();
        ModelSubjectBehavior modelSubjectBehavior;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select category , count(category) from tbl_behavior group by category",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            modelSubjectBehavior = new ModelSubjectBehavior();
            if (cursor.getString(1).equals("1")){
                modelSubjectBehavior.setCategory(cursor.getString(0));
                modelSubjectBehavior.setSubTitle(false);
                subject.add(modelSubjectBehavior);
            }
            else {
                modelSubjectBehavior.setCategory(cursor.getString(0));
                modelSubjectBehavior.setSubTitle(true);
                subject.add(modelSubjectBehavior);
            }
        }
        sqLiteDatabase.close();
        cursor.close();
        return subject;
    }
    public ArrayList getFavorites(){
        ArrayList<ModelFavorite>  subject = new ArrayList<>();
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select  * from tbl_favorite",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()) {
            ModelFavorite modelFavorite = new ModelFavorite();
            modelFavorite.setIdentity(cursor.getString(0));
            modelFavorite.setText(cursor.getString(1));
            modelFavorite.setCategory(cursor.getString(2));
            subject.add(modelFavorite);
        }
        sqLiteDatabase.close();
        cursor.close();
        return subject;
    }
    public ArrayList getTypeBehavior(String behavior){
        ArrayList<ModelSubjectBehavior>  type = new ArrayList<>();
        ModelSubjectBehavior modelSubjectBehavior;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select type , text from tbl_behavior where category ='"+behavior+"'",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            modelSubjectBehavior = new ModelSubjectBehavior();
            modelSubjectBehavior.setType(cursor.getString(0));
            modelSubjectBehavior.setText(cursor.getString(1));
            type.add(modelSubjectBehavior);
            }
        sqLiteDatabase.close();
        cursor.close();
        return type;
    }
    public void addOrRemoveFavorite(String command){
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        sqLiteDatabase.execSQL(command);
        sqLiteDatabase.close();
    }
    public List<ModelSokhanQuran> getDataSokhanQuran(){
        List<ModelSokhanQuran> listSokhanQuran = new ArrayList<>();
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select address,text,favorite from tbl_sokhan_quran",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            ModelSokhanQuran modelSokhanQuran = new ModelSokhanQuran();
            modelSokhanQuran.setAddress(cursor.getString(0));
            modelSokhanQuran.setText(cursor.getString(1));
            modelSokhanQuran.setFavorite(cursor.getString(2));
            listSokhanQuran.add(modelSokhanQuran);
        }
        sqLiteDatabase.close();
        return listSokhanQuran;
    }
    public List<ModelDesc> getDataDesc(){
        List<ModelDesc> modelDescList = new ArrayList<>();
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select name,dateBorn,dateDeath,fatherName,motherName,adjectives,text,favorite from tbl_desc",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            ModelDesc modelDesc = new ModelDesc();
            modelDesc.setName(cursor.getString(0));
            modelDesc.setDateBorn(cursor.getString(1));
            modelDesc.setDateDeath(cursor.getString(2));
            modelDesc.setFatherName(cursor.getString(3));
            modelDesc.setMotherName(cursor.getString(4));
            modelDesc.setAdjectives(cursor.getString(5));
            modelDesc.setText(cursor.getString(6));
            modelDesc.setFavorite(cursor.getString(7));
            modelDescList.add(modelDesc);
        }
        sqLiteDatabase.close();
        return modelDescList;
    }
    public ArrayList getSubjectStory(){
        ArrayList<ModelStory>  subject = new ArrayList<>();
        ModelStory modelStory;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select emam , count(emam) from tbl_story group by emam order by priority asc",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            modelStory = new ModelStory();
            if (cursor.getString(1).equals("1")){
                modelStory.setEmam(cursor.getString(0));
                modelStory.setSubTitle(false);
                subject.add(modelStory);
            }
            else {
                modelStory.setEmam(cursor.getString(0));
                modelStory.setSubTitle(true);
                subject.add(modelStory);
            }
        }
        sqLiteDatabase.close();
        cursor.close();
        return subject;
    }
    public ArrayList getEmamStory(String emam){
        ArrayList<ModelStory>  type = new ArrayList<>();
        ModelStory modelStory;
        sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        cursor = sqLiteDatabase.rawQuery("select name , text from tbl_story where emam ='"+emam+"'",null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++,cursor.moveToNext()){
            modelStory = new ModelStory();
            modelStory.setName(cursor.getString(0));
            modelStory.setText(cursor.getString(1));
            type.add(modelStory);
        }
        sqLiteDatabase.close();
        cursor.close();
        return type;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
