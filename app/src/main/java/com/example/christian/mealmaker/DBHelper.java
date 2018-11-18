package com.example.christian.mealmaker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME ="MealMaker.db";
    final static int VER = 1;
    final static String TABLE ="recipe";
    public DBHelper(Context context) {
        super(context,DBNAME, null, VER);
    }


public void db(){
        SQLiteDatabase db = this.getWritableDatabase();
    }




    public Cursor populateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM recipe";

        return db.rawQuery(query, null);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     //   String createTable = "CREATE TABLE recipe (RID INTEGER PRIMARY KEY AUTOINCREMENT, RName TEXT NOT NULL UNIQUE, RIngredients TEXT NOT NULL, RSteps TEXT NOT NULL,RImage TEXT NOT NULL)";
    //    db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable ="DROP TABLE IF EXISTS recipe";
        db.execSQL(deleteTable);
        onCreate(db);
    }


}
