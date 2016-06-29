package com.example.hwang.testdb.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hwang.testdb.util.AppConstants;

/**
 * Created by hwang on 2016-06-20.
 */
public class DBManager extends SQLiteOpenHelper{


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(AppConstants.TAG, "테이블 생성");
        db.execSQL(DBIncomeConstants.IncomeDB.createTable());
}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBIncomeConstants.IncomeDB.createTable());
        //DBIncomeConstants.IncomeDB.onUpgrade(db, oldVersion, newVersion);
    }

    public void insert(String name, String price, String date) {

            String query = "insert into INCOME_DATA values(null, '" + name + "', " + price + ", '" +date+  "');";

            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(query);
            db.close();

    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String name) {
        String query = "delete from INCOME_DATA where NAME = '" + name + "';";
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from INCOME_DATA", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : NAME = "
                    + cursor.getString(1)
                    + ", AMOUNT = "
                    + cursor.getInt(2)
                    + ", INCOME_DATE = "
                    + cursor.getString(3)
                    + "\n";
        }

        return str;
    }

    public void update(String name, String price) {

        String query ="update INCOME_DATA set AMOUNT = " + price + " where NAME = '" + name + "';";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void deleteAll() {
        String query = "delete from INCOME_DATA" + ';';
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(query);
        db.close();
    }

    public void dropTable() {
        String sql = "drop table INCOME_DATA";
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
            db.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getDate() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("SELECT * INCOME_DATA WHERE strfTime(\"%Y.%m.%d.\", substr(\n" +
                "idate\n" +
                ", 0, 11))<= strftime(\"%Y.%m.%d\", \"2016.6.26\");", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : NAME = "
                    + cursor.getString(1)
                    + ", AMOUNT = "
                    + cursor.getInt(2)
                    + ", INCOME_DATE = "
                    + cursor.getString(3)
                    + "\n";
        }

        return str;
    }
}
