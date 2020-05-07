package com.example.youkonwapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OpenHelper extends SQLiteOpenHelper {

    //建表语句
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "userpwd text)";
    public static final String CREATE_Problem = "create table Problem ("
            + "Problemid integer primary key autoincrement, "
            + "username text,"
            + "problem text, "
            + "description text)";
    public static final String CREATE_Answer = "create table Answer ("
            + "Answerid integer primary key autoincrement, "
            + "username text,"
            + "answer text, "
            + "problem text)";

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);//创建用户表
        db.execSQL(CREATE_Problem);
        Log.i("create table","Problem");
        db.execSQL(CREATE_Answer);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}