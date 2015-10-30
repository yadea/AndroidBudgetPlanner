package com.yadea.budgetplanner.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.yadea.budgetplanner.model.Cashbook;

import java.util.Date;

public class SqlLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Cashbook";

    private static final String DATABASE_NAME = "contents.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_CASHBOOK = "create table "
            + TABLE_NAME
            + "( _id integer primary key autoincrement, "
            + "Expense boolean not null, "
            + "Title text not null, "
            + "Category text, "
            + "Date datetime, "
            + "Amount double"
            + ");";

    public SqlLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_CASHBOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS Cashbook");
        onCreate(db);
    }

}
