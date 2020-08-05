package com.tdc.edu.vn.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.modals.ConvertType;
import com.tdc.edu.vn.myapplication.modals.ConvertUnit;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static String DB_NAME = "database";
    private static int DB_VERSION = 1;

    //Table properties for convert table
    private static final String TABLE_CONVERT = "convert";
    private static String ID = "_id";
    private static String UNIT_NAME = "unit_name";
    private static String RATE_TO_ROOT = "rate_to_root";
    private static String ROOT_ID = "root_id";
    private static String TYPE = "type";

    //Table convert type
    private static final String TABLE_TYPE = "convert_type";
    private static String TYPE_ID = "type_id";
    private static String TYPE_NAME = "type_name";
    private static String TYPE_SUB_TITLE = "type_sub_title";
    private static String TYPE_IMAGE = "type_image";

    public Database( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableConvert = "CREATE TABLE " + TABLE_CONVERT + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UNIT_NAME + " TEXT, " +
                RATE_TO_ROOT + " TEXT, " +
                ROOT_ID + " TEXT, " +
                TYPE + " TEXT)";

        String tableType = "CREATE TABLE " + TABLE_TYPE + "(" +
                TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TYPE_NAME + " TEXT, " +
                TYPE_SUB_TITLE + " TEXT, " +
                TYPE_IMAGE + " TEXT)";

        db.execSQL(tableConvert);
        db.execSQL(tableType);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONVERT);
        onCreate(db);*/
    }

    //APIs for update
    public void getAllConvert(ArrayList<ConvertUnit> convertUnits){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_CONVERT, new String[]{UNIT_NAME, RATE_TO_ROOT, ROOT_ID, TYPE},
                null, null,null, null, UNIT_NAME);
        if(cursor.moveToFirst()){
            do{
                ConvertUnit convertUnit = new ConvertUnit();
                convertUnit.setUnitName(cursor.getString(cursor.getColumnIndex(UNIT_NAME)));
                convertUnit.setRateToRootUnit(Float.parseFloat(cursor.getString(cursor.getColumnIndex(RATE_TO_ROOT))));
                convertUnit.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                convertUnits.add(convertUnit);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void getAllType(ArrayList<ConvertType> convertTypes){
        try{
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = db.query(TABLE_TYPE, new String[]{TYPE_NAME, TYPE_IMAGE, TYPE_SUB_TITLE},
                    null, null,null, null, TYPE_NAME);
            if(cursor.moveToFirst()){
                do{
                    ConvertType convertType = new ConvertType();
                    convertType.setTypeName(cursor.getString(cursor.getColumnIndex(TYPE_NAME)));
                    convertType.setSubTitle(cursor.getString(cursor.getColumnIndex(TYPE_SUB_TITLE)));
                    convertType.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex(TYPE_IMAGE))));
                    convertTypes.add(convertType);
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){
            Log.d("ERROR!: ", e.getMessage());
        }
    }

    //init db first run
    public void init(){
        ArrayList<ConvertUnit> convertUnitsList = new ArrayList<>();


        //String unitID, String unitName, float rateToRootUnit, String rootID, String type
        //Distance convert
        String root = "M";
        String type = "distance";
        int image = R.drawable.ic_ruler;

        String unitName1 =  "M";
        float rateToRoot1 = 1;

        String unitName2 =  "CM";
        float rateToRoot2 = (float) 0.01;

        String unitName3 =  "MM";
        float rateToRoot3 = (float) 0.0001;

        String unitName4 =  "KM";
        float rateToRoot4 = 1000;

        String unitName5 =  "DM";
        float rateToRoot5 = (float) 0.1;

        String unitName6 =  "MILE";
        float rateToRoot6 = (float) 1609.344;

        String unitName7 =  "INCHES";
        float rateToRoot7 = (float) 0.0254;

        convertUnitsList.add(new ConvertUnit("1", unitName1, rateToRoot1, root, type));
        convertUnitsList.add(new ConvertUnit("2", unitName2, rateToRoot2, root, type));
        convertUnitsList.add(new ConvertUnit("3", unitName3, rateToRoot3, root, type));
        convertUnitsList.add(new ConvertUnit("4", unitName4, rateToRoot4, root, type));
        convertUnitsList.add(new ConvertUnit("5", unitName5, rateToRoot5, root, type));
        convertUnitsList.add(new ConvertUnit("6", unitName6, rateToRoot6, root, type));
        convertUnitsList.add(new ConvertUnit("7", unitName7, rateToRoot7, root, type));


        //Currency convert
        root = "VND";
        type = "currency";

        String unitName10 =  "USD";
        float rateToRoot10 =  (float) 23176.5;

        String unitName11 =  "VND";
        float rateToRoot11 = 1 ;

        String unitName12 =  "JPY";
        float rateToRoot12 = (float) 218.72;

        String unitName13 =  "KRW";
        float rateToRoot13 = (float) 19.41;

        String unitName14 =  "GBP";
        float rateToRoot14 = (float) 30260.51;

        String unitName15 =  "THB";
        float rateToRoot15 = (float) 745.33;

        convertUnitsList.add(new ConvertUnit("10", unitName10, rateToRoot10, root, type));
        convertUnitsList.add(new ConvertUnit("11", unitName11, rateToRoot11, root, type));
        convertUnitsList.add(new ConvertUnit("12", unitName12, rateToRoot12, root, type));
        convertUnitsList.add(new ConvertUnit("13", unitName13, rateToRoot13, root, type));
        convertUnitsList.add(new ConvertUnit("14", unitName14, rateToRoot14, root, type));
        convertUnitsList.add(new ConvertUnit("15", unitName15, rateToRoot15, root, type));

        //Weight convert
        root = "KG";
        type = "weight";

        String unitName21 =  "KG";
        float rateToRoot21 = (float) 1;

        String unitName22 =  "G";
        float rateToRoot22 = (float) 0.001;

        String unitName23 =  "TON";
        float rateToRoot23 = (float) 1000;

        String unitName24 =  "MG";
        float rateToRoot24 = (float) 0.000001;

        convertUnitsList.add(new ConvertUnit("21", unitName21, rateToRoot21, root, type));
        convertUnitsList.add(new ConvertUnit("22", unitName22, rateToRoot22, root, type));
        convertUnitsList.add(new ConvertUnit("23", unitName23, rateToRoot23, root, type));
        convertUnitsList.add(new ConvertUnit("24", unitName24, rateToRoot24, root, type));

        //Data convert
        root = "KB";
        type = "data";

        String unitName30 =  "MB";
        float rateToRoot30 = (float) 1024;

        String unitName31 =  "BYTE";
        float rateToRoot31 = (float) 0.0009765625;

        String unitName32 =  "KB";
        float rateToRoot32 = (float) 1;

        String unitName33 =  "GB";
        float rateToRoot33 = (float) 1048576;

        String unitName34 =  "TB";
        float rateToRoot34 = (float) 1073741824;

        convertUnitsList.add(new ConvertUnit("30", unitName30, rateToRoot30, root, type));
        convertUnitsList.add(new ConvertUnit("31", unitName31, rateToRoot31, root, type));
        convertUnitsList.add(new ConvertUnit("32", unitName32, rateToRoot32, root, type));
        convertUnitsList.add(new ConvertUnit("33", unitName33, rateToRoot33, root, type));
        convertUnitsList.add(new ConvertUnit("34", unitName34, rateToRoot34, root, type));

        //Add data to sqlite

        SQLiteDatabase db = getWritableDatabase();
        for(ConvertUnit item : convertUnitsList){
            ContentValues values = new ContentValues();
            values.put(UNIT_NAME, item.getUnitName());
            values.put(RATE_TO_ROOT, item.getRateToRootUnit());
            values.put(ROOT_ID, item.getRootID());
            values.put(TYPE, item.getType());

            //Insert to db
            long success = db.insert(TABLE_CONVERT, null, values);
            Log.d("SUCCESS: ", success +"");
        }
        db.close();
    }

    public void initType(){
        Log.d("INIT: ", "init");
        ArrayList<ConvertType> convertTypesList = new ArrayList<>();
        convertTypesList.add(new ConvertType("1", "distance", "mm, cm, m...", R.drawable.ic_ruler));
        convertTypesList.add(new ConvertType("2", "currency", "VND, USD, KRW...", R.drawable.ic_monney));
        convertTypesList.add(new ConvertType("3", "weight", "g, kg, ton...", R.drawable.ic_can));
        convertTypesList.add(new ConvertType("4", "data", "byte, kb, mb...", R.drawable.ic_storage_black_24dp));

        SQLiteDatabase db = getWritableDatabase();

        for(ConvertType item : convertTypesList){
            ContentValues v = new ContentValues();
            v.put(TYPE_NAME, item.getTypeName());
            v.put(TYPE_SUB_TITLE, item.getSubTitle());
            v.put(TYPE_IMAGE, item.getImage());

            //Insert to db
            db.insert(TABLE_TYPE, null, v);
        }
        db.close();

    }

    //Select data from type
    public void getConvertByType(ArrayList<ConvertUnit> units, String key){
        String raw = "SELECT * FROM " + TABLE_CONVERT + " WHERE " + TYPE + " = " + "'" + key + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(raw,  null);
        if(cursor.moveToFirst()){
            do{
                ConvertUnit unit = new ConvertUnit();
                unit.setUnitName(cursor.getString(cursor.getColumnIndex(UNIT_NAME)));
                unit.setRateToRootUnit(Float.parseFloat(cursor.getString(cursor.getColumnIndex(RATE_TO_ROOT))));
                unit.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
                unit.setRootID(cursor.getString(cursor.getColumnIndex(ROOT_ID)));
                unit.setUnitID(cursor.getString(cursor.getColumnIndex(ID)));

                //Add to list
                units.add(unit);
            }while (cursor.moveToNext());
        }
    }
}
