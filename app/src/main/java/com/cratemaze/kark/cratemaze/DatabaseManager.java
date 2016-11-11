package com.cratemaze.kark.cratemaze;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kathiA on 11.11.2016.
 */
public class DatabaseManager extends SQLiteOpenHelper
{
    private SQLiteDatabase sqldb;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Crate_Maze_db";
    private static final String DATABASE_TABLE = "user_data";
    private static final String CLASS_SELECT_RAW = "SELECT * FROM" + DatabaseManager.DATABASE_TABLE;

    public void onCreate(SQLiteDatabase sqldb)
    {
        String sql = "CREATE TABLE" + DATABASE_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + "surname VARCHAR(20) NOT NULL,"
                + "forename VARCHAR(20) NOT NULL)";
        sqldb.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqldb, int oldVersion, int newVersion)
    {

    }

    public void onDowngrade(SQLiteDatabase sqldb, int oldVersion, int newVersion)
    {
        oldVersion = DATABASE_VERSION;
        //newVersion =
    }

    public DatabaseManager(Context activity)
    {
        super(activity, DATABASE_NAME, null, DATABASE_VERSION);
        sqldb = getWritableDatabase();
    }

    public void close()
    {

    }

    public long insertRecord(String key, String value)
    {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        long rowId = sqldb.insert(DATABASE_TABLE, null, cv);
        return rowId;
    }
}
