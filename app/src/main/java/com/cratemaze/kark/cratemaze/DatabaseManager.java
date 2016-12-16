package com.cratemaze.kark.cratemaze;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

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

    private ContentValues cv = new ContentValues();

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

    public boolean updateRecord(String table_name, String attribute, int id, String newValue)
    {
        SQLiteStatement sqlstm = null;
        boolean successCheck = true;

        try
        {
            sqlstm = sqldb.compileStatement("UPDATE " + table_name + " SET " + attribute + " = " + newValue + " WHERE _id=" + id + ";");
            sqlstm.execute();
        }
        catch (SQLiteException ex)
        {
            ex.printStackTrace();
            successCheck = false;
        }
        finally
        {
            try
            {
                sqlstm.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                successCheck = false;
            }
        }

        return successCheck;
    }

    public long insertRecord(String table_name, String key, String value, boolean wait)
    {
        long rowId=0;
        cv.put(key, value);

        if(!wait)
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

    public boolean removeRecord(String table_name, int id)
    {
        String cmd = "DELETE FROM " + table_name;
        if(id >= 0) cmd += " WHERE _id=" + id + ";";

        SQLiteStatement sqlstm = null;
        boolean successCheck = true;
        try
        {
            sqlstm = sqldb.compileStatement(cmd);
            sqlstm.execute();
        }
        catch (SQLiteException ex)
        {
            ex.printStackTrace();
            successCheck = false;
        }
        finally
        {
            try
            {
                sqlstm.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                successCheck = false;
            }
        }

        return successCheck;
    }

    public int getCount(String table_name)
    {
        int ret = -1;
        Cursor cursorLevel = sqldb.query(TABLE_LEVEL, columsLevel, null, null, null, null, null);
        Cursor cursorPlayer = sqldb.query(TABLE_PLAYER, columsPlayer, null, null, null, null, null);

        if(table_name.equals(TABLE_LEVEL))
        {
            ret = cursorLevel.getCount();
        }
        else if (table_name.equals(TABLE_PLAYER))
        {
            ret = cursorPlayer.getCount();
        }

        return ret;
    }

    public String ausgabe(String table_name, String attribute, int id)
    {
        String ret = "";
        Cursor cursorLevel = sqldb.query(TABLE_LEVEL, columsLevel, null, null, null, null, null);
        Cursor cursorPlayer = sqldb.query(TABLE_PLAYER, columsPlayer, null, null, null, null, null);

        if(table_name.equals(TABLE_LEVEL))
        {
            cursorLevel.moveToPosition(id);
            {
               switch (attribute)
               {
                   case CONTENT:
                       ret = cursorLevel.getString(0);
                       break;
                   case TIME:
                       ret = cursorLevel.getString(1);
                       break;
                   case HIGHSCORE:
                       ret = cursorLevel.getString(2);
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
                        ret = cursorPlayer.getString(0);
                        break;
                    case PW:
                        ret =  cursorPlayer.getString(1);
                        break;
                    case CURRENT:
                        ret = cursorPlayer.getString(2);
                        break;
            }
        }
        return ret;
    }
}
