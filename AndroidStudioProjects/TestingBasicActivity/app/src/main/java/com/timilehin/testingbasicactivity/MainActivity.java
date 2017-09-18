package com.timilehin.testingbasicactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void
    onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.YELLOW)
                        .setAction("Show Toast", new showToastMethod()).show();
            }
        });

    }

    public class showToastMethod implements View.OnClickListener
    {

        @Override
        public void
        onClick(View view)
        {
            Toast.makeText(getApplicationContext(), "You Clicked the Floating Button", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean
    onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean
    onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {

            return true;

        } else if (id == R.id.About)
        {

            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.Help)
        {

            Intent intent = new Intent(MainActivity.this, Help.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.Exit)
        {

            finish();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
