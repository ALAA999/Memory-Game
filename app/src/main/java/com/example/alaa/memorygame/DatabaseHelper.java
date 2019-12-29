package com.example.alaa.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Shougy on 3/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Levels.db";
    public static final String DBLOCATION = Environment.getDataDirectory()+"/data/com.example.alaa.memorygame/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public ArrayList<level> get_All_IDS() {
        ArrayList<level> arrayList = new ArrayList();
        openDatabase();
        Cursor res = mDatabase.rawQuery("select * from Levels", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int id = res.getInt(res.getColumnIndex("ID"));
            int row_num = res.getInt(res.getColumnIndex("Row_num"));
            int is_ans = res.getInt(res.getColumnIndex("Is_Answerd"));
            int colum_nmu = res.getInt(res.getColumnIndex("Coulm_num"));
            arrayList.add(new level(id, is_ans, colum_nmu, row_num));
            res.moveToNext();
        }
        res.close();
        closeDatabase();
        return arrayList;
    }

    public int get_total_scoore(){
        int total_scoors = 0;
        openDatabase();
        Cursor res = mDatabase.rawQuery("select Is_Answerd from Levels", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            int is_ans = res.getInt(res.getColumnIndex("Is_Answerd"));
            total_scoors = total_scoors +is_ans;
            res.moveToNext();
        }
        res.close();
        closeDatabase();
        return total_scoors;
    }

    public void change_level_scoore(int rate, int ID){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Is_Answerd", rate);
        openDatabase();
        mDatabase.update("levels", contentValues, "ID=?", new String[]{ID + ""});
    }
}

