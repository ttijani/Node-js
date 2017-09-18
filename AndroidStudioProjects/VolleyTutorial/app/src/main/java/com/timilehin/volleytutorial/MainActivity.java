package com.timilehin.volleytutorial;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity
{

    // Tag for the message to be printed by Log.
    final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
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
                        .setAction("Action", null).show();
            }
        });

        String url = "https://google.com";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>()
        {

            // Print the reference preceded by tag- TAG on success.
            @Override
            public void onResponse(String response)
            {

                Log.d(TAG, response);

            }

        }, new Response.ErrorListener()
        {

            // Create a toast to display error message in case of an error.
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText(getApplicationContext(), "Error while reading google", Toast.LENGTH_SHORT).show();

            }

        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}
