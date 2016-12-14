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

        dbmgr.insertRecord("level", "content", "111141111100020001100000001100000001100000001100000001100000001100020001111131111", false); // Level 1
        dbmgr.insertRecord("level", "content", "111141111111101111110002011112020011110222011110000011112020211111101111111131111", false);
        dbmgr.insertRecord("level", "content", "111141111100002001100002001111111101110000201102000001101111111100000001111131111", false);
        dbmgr.insertRecord("level", "content", "111141111111100111120000201102101001100121001100101201100101021100000001111131111", false);
        dbmgr.insertRecord("level", "content", "111141111110020011100002201122110001100000001100000001111222111111000111111131111", false);
        dbmgr.insertRecord("level", "content", "111141111111200011110000211110100011110202011112111011110111211110000011111131111", false);
        dbmgr.insertRecord("level", "content", "111141111100200201100011001100201001111020001100002201102111001100202021111131111", false);
        dbmgr.insertRecord("level", "content", "111141111100000001101111001101111101120011101100011101101000001111021001111131111", false);

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
                dbmgr.insertRecord("player", "name", username.getText().toString(), true);
                dbmgr.insertRecord("player", "password", password.getText().toString(), false);
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
