package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity implements View.OnClickListener
{
    private int player1Time;
    private int time;
    private int mode;
    private int levelId;
    private String levelData;
    private boolean startTime;
    private Level level = new Level();
    private final ImageView field[][] = new ImageView[9][9];

    private DatabaseManager dbmgr;
    private SQLiteDatabase sqldb;

    private Timer timer = new Timer(true);
    private TimerTask task = new TimerTask()
    {

        @Override
        public void run()
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    time++;
                    String sTime = "";
                    final TextView tvTimer = (TextView) findViewById(R.id.timer);
                    tvTimer.setText(sTime.valueOf(time));
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        dbmgr = new DatabaseManager(this);

        time = 0;
        player1Time = 0;
        startTime = true;
        initFieldArray();

        final ImageButton bDown = (ImageButton) findViewById(R.id.bDown);
        final ImageButton bUp = (ImageButton) findViewById(R.id.bUp);
        final ImageButton bRight = (ImageButton) findViewById(R.id.bRight);
        final ImageButton bLeft = (ImageButton) findViewById(R.id.bLeft);

        bDown.setImageResource(R.mipmap.direction);
        bUp.setImageResource(R.mipmap.direction);
        bRight.setImageResource(R.mipmap.direction);
        bLeft.setImageResource(R.mipmap.direction);

        bDown.setOnClickListener(this);
        bUp.setOnClickListener(this);
        bRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        mode = extras.getInt("mode");
        levelId = extras.getInt("id");
        levelData = dbmgr.ausgabe("level", "content", levelId - 1);

        if (!level.LoadLevel(levelData))
        {
            Toast.makeText(this, "Couldn't Load Level!", Toast.LENGTH_SHORT).show();
        }
        DrawLevel();
    }

    @Override
    public void onClick(View v)
    {
        if(startTime)
        {
            startTime = false;
            timer.scheduleAtFixedRate(task, new Date(), 1000);
        }

        switch (v.getId())
        {
            case R.id.bDown:
                Move(1, 0);
                break;

            case R.id.bUp:
                Move(-1, 0);
                break;

            case R.id.bRight:
                Move(0, 1);
                break;

            case R.id.bLeft:
                Move(0, -1);
                break;
        }
        DrawLevel();
    }

    private void DrawLevel()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                switch (level.GetTile(n, i))
                {
                    case 0:
                        field[i][n].setImageResource(R.mipmap.bg_game);
                        break;
                    case 1:
                        field[i][n].setImageResource(R.mipmap.wall);
                        break;
                    case 2:
                        field[i][n].setImageResource(R.mipmap.crate);
                        break;
                    case 3:
                        field[i][n].setImageResource(R.mipmap.player);
                        break;
                    case 4:
                        field[i][n].setImageResource(R.mipmap.exit);
                        break;
                }

            }
        }
    }

    private void initField(int x, int y, int id)
    {
        field[x][y] = (ImageView) findViewById(id);
    }

    private void initFieldArray()
    {
        initField(0, 0, R.id.field1x1);
        initField(0, 1, R.id.field1x2);
        initField(0, 2, R.id.field1x3);
        initField(0, 3, R.id.field1x4);
        initField(0, 4, R.id.field1x5);
        initField(0, 5, R.id.field1x6);
        initField(0, 6, R.id.field1x7);
        initField(0, 7, R.id.field1x8);
        initField(0, 8, R.id.field1x9);

        initField(1, 0, R.id.field2x1);
        initField(1, 1, R.id.field2x2);
        initField(1, 2, R.id.field2x3);
        initField(1, 3, R.id.field2x4);
        initField(1, 4, R.id.field2x5);
        initField(1, 5, R.id.field2x6);
        initField(1, 6, R.id.field2x7);
        initField(1, 7, R.id.field2x8);
        initField(1, 8, R.id.field2x9);

        initField(2, 0, R.id.field3x1);
        initField(2, 1, R.id.field3x2);
        initField(2, 2, R.id.field3x3);
        initField(2, 3, R.id.field3x4);
        initField(2, 4, R.id.field3x5);
        initField(2, 5, R.id.field3x6);
        initField(2, 6, R.id.field3x7);
        initField(2, 7, R.id.field3x8);
        initField(2, 8, R.id.field3x9);

        initField(3, 0, R.id.field4x1);
        initField(3, 1, R.id.field4x2);
        initField(3, 2, R.id.field4x3);
        initField(3, 3, R.id.field4x4);
        initField(3, 4, R.id.field4x5);
        initField(3, 5, R.id.field4x6);
        initField(3, 6, R.id.field4x7);
        initField(3, 7, R.id.field4x8);
        initField(3, 8, R.id.field4x9);

        initField(4, 0, R.id.field5x1);
        initField(4, 1, R.id.field5x2);
        initField(4, 2, R.id.field5x3);
        initField(4, 3, R.id.field5x4);
        initField(4, 4, R.id.field5x5);
        initField(4, 5, R.id.field5x6);
        initField(4, 6, R.id.field5x7);
        initField(4, 7, R.id.field5x8);
        initField(4, 8, R.id.field5x9);

        initField(5, 0, R.id.field6x1);
        initField(5, 1, R.id.field6x2);
        initField(5, 2, R.id.field6x3);
        initField(5, 3, R.id.field6x4);
        initField(5, 4, R.id.field6x5);
        initField(5, 5, R.id.field6x6);
        initField(5, 6, R.id.field6x7);
        initField(5, 7, R.id.field6x8);
        initField(5, 8, R.id.field6x9);

        initField(6, 0, R.id.field7x1);
        initField(6, 1, R.id.field7x2);
        initField(6, 2, R.id.field7x3);
        initField(6, 3, R.id.field7x4);
        initField(6, 4, R.id.field7x5);
        initField(6, 5, R.id.field7x6);
        initField(6, 6, R.id.field7x7);
        initField(6, 7, R.id.field7x8);
        initField(6, 8, R.id.field7x9);

        initField(7, 0, R.id.field8x1);
        initField(7, 1, R.id.field8x2);
        initField(7, 2, R.id.field8x3);
        initField(7, 3, R.id.field8x4);
        initField(7, 4, R.id.field8x5);
        initField(7, 5, R.id.field8x6);
        initField(7, 6, R.id.field8x7);
        initField(7, 7, R.id.field8x8);
        initField(7, 8, R.id.field8x9);

        initField(8, 0, R.id.field9x1);
        initField(8, 1, R.id.field9x2);
        initField(8, 2, R.id.field9x3);
        initField(8, 3, R.id.field9x4);
        initField(8, 4, R.id.field9x5);
        initField(8, 5, R.id.field9x6);
        initField(8, 6, R.id.field9x7);
        initField(8, 7, R.id.field9x8);
        initField(8, 8, R.id.field9x9);
    }

    private void Move(int x, int y)
    {
        if(level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y) == TILES.BOX.getVal() &&
                level.GetTile(level.getPlayerX() + x + x, level.getPlayerY() + y + y) == TILES.EMPTY.getVal())
        {
            level.UpdateLevel(level.getPlayerX() + x, level.getPlayerY() + y, TILES.EMPTY.getVal());
            level.UpdateLevel(level.getPlayerX() + x + x, level.getPlayerY() + y + y, TILES.BOX.getVal());
        }
        if(level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y) == TILES.EMPTY.getVal())
        {
            level.SetPlayer(level.getPlayerX() + x, level.getPlayerY() + y);
        }
        if(level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y) == TILES.EXIT.getVal())
        {
            level.SetPlayer(level.getPlayerX() + x, level.getPlayerY() + y);
            Finish();
        }
    }

    private void Finish()
    {
        if(mode == 0)
        {
            Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();

            String currentScore = "" + dbmgr.ausgabe("level", "time", levelId);

            if(!currentScore.equals("null"))
            {
                if (time > Integer.valueOf(currentScore)) {
                    dbmgr.updateRecord("level", "time", levelId, "" + time);
                    Toast.makeText(this, "New Highscore!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                dbmgr.updateRecord("level", "time", levelId, "" + time);
                Toast.makeText(this, "New Highscore!", Toast.LENGTH_SHORT).show();
            }

            Intent data = new Intent();
            data.putExtra("finished", true);
            setResult(RESULT_OK, data);
            super.finish();
        }
        else if(mode == 1)
        {
            if (player1Time == 0)
            {
                timer.cancel();
                timer = new Timer(true);
                task.cancel();
                task = new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time++;
                                String sTime = "";
                                final TextView tvTimer = (TextView) findViewById(R.id.timer);
                                tvTimer.setText(sTime.valueOf(time));
                            }
                        });
                    }
                };

                player1Time = time;
                time = 0;
                String sTime = "";
                final TextView tvTimer = (TextView) findViewById(R.id.timer);
                tvTimer.setText(sTime.valueOf(time));
                startTime = true;
                Level player2Level = new Level();
                if(player2Level.LoadLevel(levelData))
                {
                    level = player2Level;
                    Toast.makeText(this, "Player 2s Turn!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if(player1Time < time)
                {
                    Toast.makeText(this, "Player 1 Won!", Toast.LENGTH_SHORT).show();
                }
                else if(player1Time > time)
                {
                    Toast.makeText(this, "Player 2 Won!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "DRAW!", Toast.LENGTH_SHORT).show();
                }

                Intent data = new Intent();
                data.putExtra("finished", true);
                setResult(RESULT_OK, data);
                super.finish();
            }
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
