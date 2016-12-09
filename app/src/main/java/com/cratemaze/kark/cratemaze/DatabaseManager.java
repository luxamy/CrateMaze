package com.cratemaze.kark.cratemaze;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kathiA on 11.11.2016.
 */
public class DatabaseManager extends SQLiteOpenHelper
{
    private SQLiteDatabase sqldb;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Crate_Maze_db";
    private static final String TABLE_PLAYER = "player";
    private static final String TABLE_LEVEL = "level";
    public static final String CLASS_SELECT_RAW_PLAYER = "SELECT * FROM" + DatabaseManager.TABLE_PLAYER;
    public static final String CLASS_SELECT_RAW_LEVEL = "SELECT * FROM" + DatabaseManager.TABLE_LEVEL;

    //Fields DB Level
    private static final String LEVEL_ID = "_id";
    private static final String CONTENT = "content";
    private static final String TIME = "time";
    private static final String HIGHSCORE = "highscore";

    //Fields DB Player
    private static final String PLAYER_ID = "_id";
    private static final String NAME = "name";
    private static final String PW = "password";
    private static final String CURRENT = "currentLevel";

    private String[] columsLevel = {CONTENT, TIME, HIGHSCORE};
    private String[] columsPlayer = {NAME, PW, CURRENT};

    Cursor cursorLevel = sqldb.query(TABLE_LEVEL, columsLevel, null, null, null, null, null);
    Cursor cursorPlayer = sqldb.query(TABLE_PLAYER, columsPlayer, null, null, null, null, null);

    public void onCreate(SQLiteDatabase sqldb)
    {
        String Player = "CREATE TABLE" + TABLE_PLAYER + "(" + PLAYER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + NAME +
                "VARCHAR NOT NULL," +PW + "VARCHAR NOT NULL" + CURRENT + "INTEGER);";
        sqldb.execSQL(Player);

        String Level = "CREATE TABLE" + TABLE_LEVEL + "(" + LEVEL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT +
                "CHAR(81)," + TIME + "INTEGER," + HIGHSCORE + "INTEGER);";
        sqldb.execSQL(Level);

    }

    public void onUpgrade(SQLiteDatabase sqldb, int oldVersion, int newVersion) {}

    public void onDowngrade(SQLiteDatabase sqldb, int oldVersion, int newVersion) {}

    public void close()
    {
        sqldb.close();
    }

    public DatabaseManager(Context activity)
    {
        super(activity, DB_NAME, null, DB_VERSION);
        sqldb = getWritableDatabase();
    }

    public long insertRecord(String table_name, String key, String value)
    {
        long rowId=0;
        if (table_name.equals(TABLE_PLAYER))
        {
            ContentValues cv = new ContentValues();
            cv.put(key, value);
            rowId = sqldb.insert(TABLE_PLAYER, null, cv);
        }

        else if(table_name.equals(TABLE_LEVEL))
        {
            ContentValues cv = new ContentValues();
            cv.put(key, value);
            rowId = sqldb.insert(TABLE_LEVEL, null, cv);
        }
        return rowId;
    }

    public void ausgabe(String table_name, String mInhalt)
    {
        if(table_name.equals(TABLE_LEVEL))
        {
            while (cursorLevel.moveToNext())
            {
                mInhalt += cursorLevel.getString(1) + " " + cursorLevel.getString(2) + " " + cursorLevel.getString(3) + "\n";
            }
        }
        else if (table_name.equals(TABLE_PLAYER))
        {
            while (cursorPlayer.moveToNext())
            {
                mInhalt += cursorPlayer.getString(1) + " " + cursorPlayer.getString(2) + " " + cursorPlayer.getString(3) + "\n";
            }
        }
    }
}
