package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Level level = new Level();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView test = (TextView) findViewById(R.id.test);

        final ImageButton bDown = (ImageButton) findViewById(R.id.bDown);
        final ImageButton bUp = (ImageButton) findViewById(R.id.bUp);
        final ImageButton bRight = (ImageButton) findViewById(R.id.bRight);
        final ImageButton bLeft = (ImageButton) findViewById(R.id.bLeft);

        bDown.setOnClickListener(this);
        bUp.setOnClickListener(this);
        bRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);

        level.LoadLevel(0);

        DrawLevel();
    }

    @Override
    public void onClick(View v)
    {
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
        final TextView test = (TextView) findViewById(R.id.test);
        String temp = "";

        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                temp += level.level[i][n];
            }
            temp += "\n";
        }

        test.setText(temp);
    }

    private void Move(int x, int y)
    {
        Toast.makeText(this, "X: " + (level.getPlayerX() + x) + " Y: " + (level.getPlayerY() + y) + " Tile: " + level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y), Toast.LENGTH_SHORT).show();

        if(level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y) == TILES.BOX.getVal())
        {
            Toast.makeText(this, "Tile was a Box!", Toast.LENGTH_SHORT).show();
            level.UpdateLevel(level.getPlayerX() + x, level.getPlayerY() + y, TILES.EMPTY.getVal());
            level.UpdateLevel(level.getPlayerX() + x + x, level.getPlayerY() + y + y, TILES.BOX.getVal());
        }
        if(level.GetTile(level.getPlayerX() + x, level.getPlayerY() + y) == TILES.EMPTY.getVal())
        {
            level.SetPlayer(level.getPlayerX() + x, level.getPlayerY() + y);
        }
    }
}
