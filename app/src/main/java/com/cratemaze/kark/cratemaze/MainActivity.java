package com.cratemaze.kark.cratemaze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
                if(username.getText().toString().equals("rk") && password.getText().toString().equals("rk"))
                {
                    Intent i = new Intent(this, MenuActivity.class);
                    Log.e("Test", "Test");
                    this.startActivity(i);
                    Log.e("Test", "Test2");
                }
                break;

            case R.id.exit:
                finish();
                break;

            case R.id.password:
                break;
        }
    }
}
