package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final Button bLevel1 = (Button) findViewById(R.id.bLevel1);
        final Button bLevel2 = (Button) findViewById(R.id.bLevel2);
        final Button bLevel3 = (Button) findViewById(R.id.bLevel3);
        final Button bLevel4 = (Button) findViewById(R.id.bLevel4);
        final Button bLevel5 = (Button) findViewById(R.id.bLevel5);
        final Button bBack = (Button) findViewById(R.id.bBack);

        bLevel1.setOnClickListener(this);
        bLevel2.setOnClickListener(this);
        bLevel3.setOnClickListener(this);
        bLevel4.setOnClickListener(this);
        bLevel5.setOnClickListener(this);
        bBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int level = 0;

        switch (view.getId())
        {
            case R.id.bLevel1:
                level = 1;
                break;
            case R.id.bLevel2:
                level = 2;
                break;
            case R.id.bLevel3:
                level = 3;
                break;
            case R.id.bLevel4:
                level = 4;
                break;
            case R.id.bLevel5:
                level = 5;
                break;
            case R.id.bBack:
                finish();
                break;

        }

        if(level != 0)
        {
            Intent i = new Intent(this, GameActivity.class);
            this.startActivity(i);
        }
    }
}
