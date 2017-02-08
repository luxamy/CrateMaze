package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends Activity implements View.OnClickListener
{
    private static final int REQUEST_CODE = 2;
    private int playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final ImageView titel = (ImageView) findViewById(R.id.titel);

        final Button highscore = (Button) findViewById(R.id.highscore);
        final Button logout = (Button) findViewById(R.id.logout);

        final ImageButton singlePlayer = (ImageButton) findViewById(R.id.single);
        final ImageButton twoPlayer = (ImageButton) findViewById(R.id.twoplayer);
        final ImageButton wifiPlayer = (ImageButton) findViewById(R.id.wifi);

        Bundle extras = getIntent().getExtras();
        playerId = extras.getInt("id");

        titel.setImageResource(R.mipmap.logo);
        singlePlayer.setImageResource(R.mipmap.one_player);
        twoPlayer.setImageResource(R.mipmap.two_player_one);
        wifiPlayer.setImageResource(R.mipmap.two_player);

        highscore.setOnClickListener(this);
        logout.setOnClickListener(this);
        singlePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        wifiPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i = new Intent(this, SelectionActivity.class);
        boolean start = false;
        switch (view.getId())
        {
            case R.id.highscore:
                Intent h = new Intent(this, HighscoreActivity.class);
                startActivity(h);
                break;
            case R.id.logout:
                finish();
                break;
            case R.id.single:
                start = true;
                i.putExtra("id", playerId);
                i.putExtra("mode", 0);
                break;
            case R.id.twoplayer:
                start = true;
                i.putExtra("id", playerId);
                i.putExtra("mode", 1);
                break;
            case R.id.wifi:
                Toast.makeText(this, "Sorry but this feature is not yet available", Toast.LENGTH_SHORT).show();
                i.putExtra("id", playerId);
                i.putExtra("mode", 2);
                break;
        }
        if(start)
        {
            startActivityForResult(i, REQUEST_CODE);
        }
    }
}
