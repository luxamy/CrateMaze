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

        final int levels = 10;
        String levelDatas[] = new String[levels];

        levelDatas[0] = "111141111100020001100000001100000001100000001100000001100000001100020001111131111";
        levelDatas[1] = "111141111111101111110002011112020011110222011110000011112020211111101111111131111";
        levelDatas[2] = "111141111100002001100002001111111101110000201102000001101111111100000001111131111";
        levelDatas[3] = "111141111111100111120000201102101001100121001100101201100101021100000001111131111";
        levelDatas[4] = "111141111110020011100002201122110001100000001100000001111222111111000111111131111";
        levelDatas[5] = "111141111111200011110000211110100011110202011112111011110111211110000011111131111";
        levelDatas[6] = "111141111100200201100011001100201001111020001100002201102111001100202021111131111";
        levelDatas[7] = "111141111100000001101111001101111101120011101100011101101000001111021001111131111";
        levelDatas[8] = "111141111100100201100022001111000001100111211100002001121212121100000001111131111";
        levelDatas[9] = "111141111110000011111112011110000011110201011110121011112020211110202011111131111";

        dbmgr = new DatabaseManager(this);

        for(int n = dbmgr.getCount("level"); n < levels; n++)
            dbmgr.insertRecord("level", "content", levelDatas[n], false);

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
                int login = 0;
                int playerCount = dbmgr.getCount("player");
                String sUsername = username.getText().toString();
                String sPassword = password.getText().toString();

                int n;
                for(n = 0; n < playerCount; n++)
                {
                    login = 0;
                    String dbUsername = dbmgr.ausgabe("player", "name", n);
                    String dbPassword = dbmgr.ausgabe("player", "password", n);
                    if(dbUsername.equals(sUsername))
                    {
                        login = 1;
                        if(dbPassword.equals(sPassword))
                        {
                            Intent i = new Intent(this, MenuActivity.class);
                            i.putExtra("id", n);
                            startActivityForResult(i, REQUEST_CODE);
                            login = 2;
                        }
                        break;
                    }
                }
                if(login == 0)
                {
                    Toast.makeText(this, "Incorrect Username!", Toast.LENGTH_SHORT).show();
                }
                else if(login == 1)
                {
                    Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.exit:
                dbmgr.removeRecord("player", "");
                dbmgr.removeRecord("sqlite_sequence", "name='player'");
                finish();
                break;

            case R.id.register:
                dbmgr.insertRecord("player", "name", username.getText().toString(), true);
                dbmgr.insertRecord("player", "password", password.getText().toString(), true);
                dbmgr.insertRecord("player", "currentLevel", "1", false);
                Toast.makeText(this, "You are registered!", Toast.LENGTH_SHORT).show();
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
        dbmgr = new DatabaseManager(this);
        sqldb = dbmgr.getReadableDatabase();

        Cursor levelCursor = sqldb.rawQuery(DatabaseManager.CLASS_SELECT_RAW_LEVEL, null);
        Cursor playerCursor = sqldb.rawQuery(DatabaseManager.CLASS_SELECT_RAW_PLAYER, null);
    }
}
