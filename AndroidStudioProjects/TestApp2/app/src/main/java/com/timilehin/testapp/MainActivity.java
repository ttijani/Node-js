package com.timilehin.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class
MainActivity extends AppCompatActivity
{

    // Declarations.
    Button bt, bt1;
    TextView text;
    EditText et;
    ToggleButton tg;

    @Override
    protected void
    onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializations.
        bt = (Button) findViewById(R.id.button);
        bt1 = (Button) findViewById(R.id.button2);
        text = (TextView) findViewById(R.id.text);
        et = (EditText) findViewById(R.id.editText);
        tg = (ToggleButton) findViewById(R.id.toggleButton);

        // Change TextView To Show What Was Entered.
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void
            onClick(View view)
            {

                String name = et.getText().toString();
                text.setText(name);

            }

        });

        // Start New Activity When Clicked.
        bt1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void
            onClick(View view)
            {
                Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(mIntent);
            }

        });

        // Display a Toast When Toggled.
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void
            onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                    Toast.makeText(getApplicationContext(), "Toggle On", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Toggle Off", Toast.LENGTH_LONG).show();
            }

        });

    }

}
