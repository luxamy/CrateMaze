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

        final TextView level1  = (TextView) findViewById(R.id.fScore1);
        final TextView level2  = (TextView) findViewById(R.id.fScore2);
        final TextView level3  = (TextView) findViewById(R.id.fScore3);
        final TextView level4  = (TextView) findViewById(R.id.fScore4);
        final TextView level5  = (TextView) findViewById(R.id.fScore5);
        final TextView level6  = (TextView) findViewById(R.id.fScore6);
        final TextView level7  = (TextView) findViewById(R.id.fScore7);
        final TextView level8  = (TextView) findViewById(R.id.fScore8);
        final TextView level9  = (TextView) findViewById(R.id.fScore9);
        final TextView level10 = (TextView) findViewById(R.id.fScore10);

        final TextView level1score  = (TextView) findViewById(R.id.fScore1_1);
        final TextView level2score  = (TextView) findViewById(R.id.fScore2_1);
        final TextView level3score  = (TextView) findViewById(R.id.fScore3_1);
        final TextView level4score  = (TextView) findViewById(R.id.fScore4_1);
        final TextView level5score  = (TextView) findViewById(R.id.fScore5_1);
        final TextView level6score  = (TextView) findViewById(R.id.fScore6_1);
        final TextView level7score  = (TextView) findViewById(R.id.fScore7_1);
        final TextView level8score  = (TextView) findViewById(R.id.fScore8_1);
        final TextView level9score  = (TextView) findViewById(R.id.fScore9_1);
        final TextView level10score = (TextView) findViewById(R.id.fScore10_1);

        level1.setText(dbmgr.ausgabe("level", "highscore", 1));
        level1score.setText(dbmgr.ausgabe("level", "time", 1));

        level2.setText(dbmgr.ausgabe("level", "highscore", 2));
        level2score.setText(dbmgr.ausgabe("level", "time", 2));

        level3.setText(dbmgr.ausgabe("level", "highscore", 3));
        level3score.setText(dbmgr.ausgabe("level", "time", 3));

        level4.setText(dbmgr.ausgabe("level", "highscore", 4));
        level4score.setText(dbmgr.ausgabe("level", "time", 4));

        level5.setText(dbmgr.ausgabe("level", "highscore", 5));
        level5score.setText(dbmgr.ausgabe("level", "time", 5));

        level6.setText(dbmgr.ausgabe("level", "highscore", 6));
        level6score.setText(dbmgr.ausgabe("level", "time", 6));

        level7.setText(dbmgr.ausgabe("level", "highscore", 7));
        level7score.setText(dbmgr.ausgabe("level", "time", 7));

        level8.setText(dbmgr.ausgabe("level", "highscore", 8));
        level8score.setText(dbmgr.ausgabe("level", "time", 8));

        level9.setText(dbmgr.ausgabe("level", "highscore", 9));
        level9score.setText(dbmgr.ausgabe("level", "time", 9));

        level10.setText(dbmgr.ausgabe("level", "highscore", 10));
        level10score.setText(dbmgr.ausgabe("level", "time", 10));

        final Button bBack = (Button) findViewById(R.id.b_highscore_back);

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
