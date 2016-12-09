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

    /*TODO: weiteren Parameter 'boolean wait' hinzufügen
        ContentValues cv = new ContentValues(); soll aus der funktion rausgenommen werden und als Private definiert werden (oben wo auch andere variablen definiert sind)
        wenn der parameter wait == true ist soll NUR long rowId=0; und cv.put(key, value); ausgeführt werden.
        wenn der parameter wait == false ist soll zusätzlich der restliche code und am ende cv.clear() ausgeführt werden.

        In MainActivity.java bei den insertRecord aufrufen bitte die kommentare entfernen und entweder true oder false einfügen.
    */
    public long insertRecord(String table_name, String key, String value)
    {
        long rowId=0;
        ContentValues cv = new ContentValues();
        cv.put(key, value);

        if (table_name.equals(TABLE_PLAYER))
        {
            rowId = sqldb.insert(TABLE_PLAYER, null, cv);
        }

        else if(table_name.equals(TABLE_LEVEL))
        {
            rowId = sqldb.insert(TABLE_LEVEL, null, cv);
        }
        return rowId;
    }

    /*TODO: funktion umbauen zu:
      public String ausgabe(String table_name, String attribute, int id)
        table_name bleibt gleich.
        attribute ist der name des attributes von dem man den wert haben möchte (bsp: NAME oder PW)
            am besten mit einem switch-case machen und den entsprechenden String zurückgeben (bsp für case name: cursorLevel.getString(1))
        id ist die PLAYER_ID bzw LEVEL_ID. am besten cursorLevel.moveToPosition(id) benutzen
        anstelle von mInhalt soll der String als return zurückgegeben werden. (deswegen funtktionstyp von void auf String ändern)

      Cursor cursorLevel und Cursor cursorPlayer sorgen für einen Crash wenn sie auserhalb dieser funktion sind also bitte drinnen lassen
      PS: du hattest beim erstellen der Tabellen in onCreate() die leerzeichen und ein Komma vor currentLevel in den strings vergessen.
    */
    public void ausgabe(String table_name, String mInhalt)
    {
        Cursor cursorLevel = sqldb.query(TABLE_LEVEL, columsLevel, null, null, null, null, null);
        Cursor cursorPlayer = sqldb.query(TABLE_PLAYER, columsPlayer, null, null, null, null, null);

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
