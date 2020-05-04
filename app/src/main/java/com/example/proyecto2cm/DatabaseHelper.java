package com.example.proyecto2cm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Books.db";
    public static final String TABLE_NAME = "Books_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "AUTHOR";
    public static final String COL_4 = "EDITORIAL";
    public static final String COL_5 = "PLACE";
    public static final String COL_6 = "GENRE";
    public static final String COL_7 = "YEAR";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TITLE TEXT, " +
                "AUTHOR TEXT, " +
                "EDITORIAL TEXT, " +
                "PLACE TEXT, " +
                "GENRE TEXT, " +
                "YEAR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String author, String editorial, String place, String genre, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, author);
        contentValues.put(COL_4, editorial);
        contentValues.put(COL_5, place);
        contentValues.put(COL_6, genre);
        contentValues.put(COL_7, year);
        long res = db.insert(TABLE_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
}
