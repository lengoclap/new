package com.example.root.appday.ParsJSONSaveData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.root.appday.ParsJSONSaveData.CityListener;
import com.example.root.appday.ParsJSONSaveData.DataCity;

import java.util.ArrayList;

/**
 * Created by root on 28/09/2016.
 */
public class DBHandlerSample extends SQLiteOpenHelper implements CityListener {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "CityDatabase.db";
    private static final String TABLE_NAME = "city_table";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_STATE = "_state";
    private static final String KEY_DESCRIPTION = "_description";
    public String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT, " + KEY_STATE + " TEXT, " + KEY_DESCRIPTION + " TEXT)";
    public String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBHandlerSample(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //Search full name content,,,image chi khac la add vo
    public ArrayList<DataCity> getAllDataDistinct(String keySearch) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataCity> mListModelData = null;

        try {
            mListModelData = new ArrayList<DataCity>();
            String QUERY = "SELECT DISTINCT " + KEY_ID + ", "
                    + KEY_NAME + ", "
                    + KEY_STATE
                    + " FROM " + TABLE_NAME + " WHERE "+KEY_NAME+" LIKE "+"'%"+keySearch +"%'" ;
            Cursor cursor = db.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    DataCity modelData = new DataCity();
                    modelData.setId(cursor.getInt(0));
                    modelData.setName(cursor.getString(1));
                    modelData.setState(cursor.getString(2));
                    mListModelData.add(modelData);
                }
            }
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return mListModelData;
    }

    //Search trung name ngya colum dau tien la 0
    public ArrayList<DataCity> getNameDataDistinct() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataCity> mListModelData = null;

        try {
            mListModelData = new ArrayList<DataCity>();
            String QUERY = "SELECT DISTINCT " + KEY_NAME + " FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    DataCity modelData = new DataCity();
                    modelData.setName(cursor.getString(1));
                    mListModelData.add(modelData);
                }
            }
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return mListModelData;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addCity(DataCity city) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, city.getName());
            values.put(KEY_STATE, city.getState());
            values.put(KEY_DESCRIPTION, city.getDescription());
            sqLiteDatabase.insert(TABLE_NAME, null, values);
            sqLiteDatabase.close();
        } catch (Exception e) {
            Log.e("Problem", e + "");
        }
    }

    @Override
    public ArrayList<DataCity> getAllCity() {
        SQLiteDatabase sqLiteDatabase1 = this.getReadableDatabase();
        ArrayList<DataCity> cityList = null;
        try {
            cityList = new ArrayList<DataCity>();
            String QUERY = "SELECT * FROM"+ TABLE_NAME;
            Cursor cursor = sqLiteDatabase1.rawQuery(QUERY,null);
            if (!cursor.isLast()){
                while (cursor.moveToNext()){
                    DataCity dataCity = new DataCity();
                    dataCity.setId(cursor.getInt(0));
                    dataCity.setName(cursor.getString(1));
                    dataCity.setState(cursor.getString(2));
                    dataCity.setDescription(cursor.getString(3));
                }
            }
            sqLiteDatabase1.close();

        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return cityList;
    }


    @Override
    public int getCityCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }
}
