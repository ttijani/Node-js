package com.timilehin.fragmenttutorial;

import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by tijanioluwatimilehin on 8/12/17.
 */

public class FragmentThree extends Fragment {
    @Nullable
    @Override
    public View
    onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_three, container, false);

    }

    @Override
    public void
    onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        String[] myArray = {"one", "two", "three", "four", "five"};

        ListView mListView = getActivity().findViewById(R.id.listView);

        ListAdapter mAdapter = new ArrayAdapter<String>(mListView.getContext(), android.R.layout.simple_list_item_1, myArray);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }
}
