package com.timilehin.persistentstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText mInput;
    private TextView mText;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInput = (EditText) findViewById(R.id.listInput);
        mText = (TextView) findViewById(R.id.textList);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();

    }

    public void onAddButtonClick(View view)
    {

        String itemValue = mInput.getText().toString();
        Items newItem = new Items(itemValue);
        dbHandler.addItem(newItem);
        printDatabase();

    }

    public void onDeleteButtonClick(View view)
    {

        String itemValue = mInput.getText().toString();
        dbHandler.deleteItem(itemValue);
        printDatabase();

    }

    // Print the database in the TextView.
    public void printDatabase()
    {

        String database = dbHandler.databaseToString();
        mText.setText(database);
        mInput.setText("");

    }

}
