package com.example.sivaram.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sivaram on 29/6/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_table3";
    private static final String COL1 = "NAME";
    private static final String COL2 = "SUBJECT";
    private static final String COL3 = "MARK";


    DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TABLE_NAME+" ( ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , SUBJECT TEXT ,  MARK INTEGER )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    boolean insert(String name, String sub, int marl){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2,sub);
        contentValues.put(COL3,marl);
        long result =db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;

        }
        else {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" select * from "+TABLE_NAME,null);
        return res;
    }
}
