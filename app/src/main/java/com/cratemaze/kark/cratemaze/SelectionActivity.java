package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectionActivity extends Activity implements View.OnClickListener
{
    private static final int REQUEST_CODE = 3;
    private final int levelCount = 5;

    private int curLevel;
    private int playedLevel;

    final Button[] bLevels = new Button[levelCount];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle extras = getIntent().getExtras();
        int playerId = extras.getInt("id");
        //TODO: get currentLevel from player in DB

        //temporary
        curLevel = 1;

        bLevels[0] = (Button) findViewById(R.id.bLevel1);
        bLevels[1] = (Button) findViewById(R.id.bLevel2);
        bLevels[2] = (Button) findViewById(R.id.bLevel3);
        bLevels[3] = (Button) findViewById(R.id.bLevel4);
        bLevels[4] = (Button) findViewById(R.id.bLevel5);

        final Button bBack = (Button) findViewById(R.id.bBack);

        int i;
        for(i = 0; i < curLevel; i++)
        {
            if(curLevel < levelCount)
            {
                bLevels[i].setOnClickListener(this);
                bLevels[i].setBackgroundColor(getResources().getColor(R.color.crate_maze_orange));
            }
        }
        int n = i;
        for(i = n; i < levelCount; i++)
        {
            bLevels[i].setBackgroundColor(getResources().getColor(R.color.crate_maze_gray));
        }

        bBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int level = 0;

        switch (view.getId())
        {
            case R.id.bLevel1:
                if (curLevel == 1) level = 1;
                break;
            case R.id.bLevel2:
                if (curLevel == 2) level = 2;
                break;
            case R.id.bLevel3:
                if (curLevel == 3) level = 3;
                break;
            case R.id.bLevel4:
                if (curLevel == 4) level = 4;
                break;
            case R.id.bLevel5:
                if (curLevel == 5) level = 5;
                break;
            case R.id.bBack:
                finish();
                break;

        }

        if(level != 0)
        {
            playedLevel = level;
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("id", level);
            startActivityForResult(i, REQUEST_CODE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            if(data.hasExtra("time"))
            {
                //TODO: compare result time with current highscore time and write into database if better.
                curLevel++;
                bLevels[curLevel - 1].setOnClickListener(this);
                bLevels[curLevel - 1].setBackgroundColor(getResources().getColor(R.color.crate_maze_orange));
                Toast.makeText(this, new String().valueOf(data.getExtras().getInt("time")), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
