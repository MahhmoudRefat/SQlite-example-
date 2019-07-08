package com.example.sql_lite_ex;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_sqlite extends SQLiteOpenHelper {

    public static final String DBname ="data.db";
    public DB_sqlite(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table mytable(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,email TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS mytable");

    }

    public boolean Insert_Data(String name , String email ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        Long result = sqLiteDatabase.insert("mytable",null,contentValues);

        if(result == -1) {return false;}

        else {return true;}
    }


public ArrayList getallrecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase dbb = this.getReadableDatabase();
        Cursor res = dbb.rawQuery("select * from mytable",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){

            //String t1 = res.getString(res.getColumnIndex("id"));
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            arrayList.add(t1+" - "+ t2 + "  "+t3);
            res.moveToNext();
        }
        return arrayList;

}

public boolean updatedata(String id,String name,String email){
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("name",name);
    contentValues.put("email",email);
    sqLiteDatabase.update("mytable",contentValues,"id = ? ",new String[]{id});
    return true;

}

public Integer Delete(String id){

    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("mytable","id=?", new String[]{id});
}
}
