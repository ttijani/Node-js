package com.timilehin.fragmenttutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        String[] myArray = {"one", "two", "three", "four", "five"};

        ListView mListView = (ListView) findViewById(R.id.mListView);

        ListAdapter mAdapter = new CustomAdapter(this, myArray);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(NewActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
