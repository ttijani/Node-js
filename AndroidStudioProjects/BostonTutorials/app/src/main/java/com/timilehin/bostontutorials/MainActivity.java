package com.timilehin.bostontutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements HeaderFragment.TopSectionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void createMeme(String top, String bottom)
    {

        BottomFragment bottomFrag = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFrag.setMemeText(top, bottom);

    }
}
