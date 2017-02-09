package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreActivity extends Activity implements View.OnClickListener
{
    private DatabaseManager dbmgr;
    private SQLiteDatabase sqldb;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        dbmgr = new DatabaseManager(this);

        final Button bBack = (Button) findViewById(R.id.b_highscore_back);

        TextView player[] = new TextView[10];
        TextView score[] = new TextView[10];

        player[0] = (TextView) findViewById(R.id.fScore1);
        player[1] = (TextView) findViewById(R.id.fScore2);
        player[2] = (TextView) findViewById(R.id.fScore3);
        player[3] = (TextView) findViewById(R.id.fScore4);
        player[4] = (TextView) findViewById(R.id.fScore5);
        player[5] = (TextView) findViewById(R.id.fScore6);
        player[6] = (TextView) findViewById(R.id.fScore7);
        player[7] = (TextView) findViewById(R.id.fScore8);
        player[8] = (TextView) findViewById(R.id.fScore9);
        player[9] = (TextView) findViewById(R.id.fScore10);

        score[0] = (TextView) findViewById(R.id.fScore1_1);
        score[1] = (TextView) findViewById(R.id.fScore2_1);
        score[2] = (TextView) findViewById(R.id.fScore3_1);
        score[3] = (TextView) findViewById(R.id.fScore4_1);
        score[4] = (TextView) findViewById(R.id.fScore5_1);
        score[5] = (TextView) findViewById(R.id.fScore6_1);
        score[6] = (TextView) findViewById(R.id.fScore7_1);
        score[7] = (TextView) findViewById(R.id.fScore8_1);
        score[8] = (TextView) findViewById(R.id.fScore9_1);
        score[9] = (TextView) findViewById(R.id.fScore10_1);

        for(int i = 0; i <= dbmgr.getCount("level"); i++)
        {
            String playerid = dbmgr.ausgabe("level", "highscore", i);
            if(playerid.equals("null"))
                continue;

            int tmp = Integer.parseInt(playerid) - 1;
            player[i].setText(dbmgr.ausgabe("player", "name", tmp));
            score[i].setText(dbmgr.ausgabe("level", "time", i));
        }

        bBack.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.b_highscore_back:
                finish();
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
