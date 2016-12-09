package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static final int REQUEST_CODE = 1;
    private DatabaseManager dbmgr;
    private SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        dbmgr = new DatabaseManager(this);

        dbmgr.insertRecord("level", "content", "111141111100020001100000001100000001100000001100000001100000001100020001111131111" /*false*/); // Level 1
        //TODO: Add other Levels (alle mit wait == false)

        final ImageView titel = (ImageView) findViewById(R.id.titel);

        final Button login = (Button) findViewById(R.id.login);
        final Button register = (Button) findViewById(R.id.register);
        final Button exit = (Button) findViewById(R.id.exit);

        titel.setImageResource(R.mipmap.logo);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        switch (v.getId())
        {
            case R.id.login:
                int playerId = 1; //get that from the database
                if(username.getText().toString().equals("rk") && password.getText().toString().equals("rk"))
                {
                    Intent i = new Intent(this, MenuActivity.class);
                    i.putExtra("id", playerId);
                    startActivityForResult(i, REQUEST_CODE);
                }
                break;

            case R.id.exit:
                finish();
                break;

            case R.id.register:
                dbmgr.insertRecord("player", "name", username.getText().toString() /*true*/);
                dbmgr.insertRecord("player", "password", password.getText().toString() /*false*/);
                String msg = "You are registered!";
                Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        dbmgr.close();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sqldb = dbmgr.getReadableDatabase();

        Cursor levelCursor = sqldb.rawQuery(DatabaseManager.CLASS_SELECT_RAW_LEVEL, null);
        Cursor playerCursor = sqldb.rawQuery(DatabaseManager.CLASS_SELECT_RAW_PLAYER, null);
    }
}
