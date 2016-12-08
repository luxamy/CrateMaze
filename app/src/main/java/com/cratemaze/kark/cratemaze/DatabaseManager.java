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
    private static final String DB_TABLE = "game_data";
    public static final String CLASS_SELECT_RAW = "SELECT * FROM" + DatabaseManager.DB_TABLE;

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

    Cursor cursorLevel = sqldb.query(DB_TABLE, columsLevel, null, null, null, null, null);
    Cursor cursorPlayer = sqldb.query(DB_TABLE, columsPlayer, null, null, null, null, null);

    public void onCreate(SQLiteDatabase sqldb)
    {
        String Player = "CREATE TABLE" + DB_TABLE + "(" + PLAYER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + NAME +
                "VARCHAR NOT NULL," +PW + "VARCHAR NOT NULL" + CURRENT + "INTEGER);";
        sqldb.execSQL(Player);

        String Level = "CREATE TABLE" + DB_TABLE + "(" + LEVEL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT +
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

    public long insertRecord(String key, String value)
    {
        String[] keyPlayer = new String[4];
        keyPlayer[0]=PLAYER_ID;
        keyPlayer[1]=NAME;
        keyPlayer[2]=PW;
        keyPlayer[3]=CURRENT;

        int i = 1;

        ContentValues cv = new ContentValues();
        cv.put(keyPlayer[i], value);
        long rowId = sqldb.insert(DB_TABLE, null, cv);
        return rowId;
    }

    public void ausgabe(String mInhalt)
    {
        while (cursorLevel.moveToNext())
        {
            mInhalt += cursorLevel.getString(1) + " " + cursorLevel.getString(2) + " " + cursorLevel.getString(3) + "\n";


        }
    }
}
