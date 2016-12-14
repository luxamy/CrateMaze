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
    public static final String CLASS_SELECT_RAW_PLAYER = "SELECT * FROM " + DatabaseManager.TABLE_PLAYER;
    public static final String CLASS_SELECT_RAW_LEVEL = "SELECT * FROM " + DatabaseManager.TABLE_LEVEL;

    private ContentValues cv;

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


    public void onCreate(SQLiteDatabase sqldb)
    {
        String Player = "CREATE TABLE " + TABLE_PLAYER + " (" + PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME +
                " VARCHAR NOT NULL, " + PW + " VARCHAR NOT NULL, " + CURRENT + " INTEGER);";
        sqldb.execSQL(Player);

        String Level = "CREATE TABLE " + TABLE_LEVEL + " " +
                "(" + LEVEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTENT +
                " CHAR(81), " + TIME + " INTEGER, " + HIGHSCORE + " INTEGER);";
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

    public long insertRecord(String table_name, String key, String value, boolean wait)
    {
        long rowId=0;
        cv.put(key, value);

        if(wait==false)
        {

            if (table_name.equals(TABLE_PLAYER))
            {
                rowId = sqldb.insert(TABLE_PLAYER, null, cv);
            }
            else if (table_name.equals(TABLE_LEVEL))
            {
                rowId = sqldb.insert(TABLE_LEVEL, null, cv);
            }
            cv.clear();
        }
        return rowId;
    }

    public String ausgabe(String table_name, String attribute, int id)
    {
        Cursor cursorLevel = sqldb.query(TABLE_LEVEL, columsLevel, null, null, null, null, null);
        Cursor cursorPlayer = sqldb.query(TABLE_PLAYER, columsPlayer, null, null, null, null, null);

        if(table_name.equals(TABLE_LEVEL))
        {
            cursorLevel.moveToPosition(id);
            {
               switch (attribute)
               {
                   case CONTENT:
                       cursorLevel.getString(1);
                       break;
                   case TIME:
                       cursorLevel.getString(2);
                       break;
                   case HIGHSCORE:
                       cursorLevel.getString(3);
                       break;
               }
            }
        }
        else if (table_name.equals(TABLE_PLAYER))
        {
            cursorPlayer.moveToPosition(id);

                switch (attribute)
                {
                    case NAME:
                        cursorPlayer.getString(1);
                        break;
                    case PW:
                        cursorPlayer.getString(2);
                        break;
                    case CURRENT:
                        cursorPlayer.getString(3);
                        break;
            }
        }
        return attribute;
    }
}
