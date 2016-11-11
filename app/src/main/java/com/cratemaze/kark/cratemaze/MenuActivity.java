package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final ImageView titel = (ImageView) findViewById(R.id.titel);

        final Button highscore = (Button) findViewById(R.id.highscore);
        final Button logout = (Button) findViewById(R.id.logout);
        final Button exit = (Button) findViewById(R.id.exit);

        final ImageButton singlePlayer = (ImageButton) findViewById(R.id.single);
        final ImageButton twoPlayer = (ImageButton) findViewById(R.id.twoplayer);
        final ImageButton wifiPlayer = (ImageButton) findViewById(R.id.wifi);


        titel.setImageResource(R.mipmap.logo);
        highscore.setOnClickListener(this);
        logout.setOnClickListener(this);
        exit.setOnClickListener(this);
        singlePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        wifiPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {

    }
}
