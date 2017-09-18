package com.timilehin.bostontutorials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tijanioluwatimilehin on 8/14/17.
 */

public class BottomFragment extends Fragment
{

    private static TextView bottomText;
    private static TextView topText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        // Sets the fragment to use the layout you created.
        View view = inflater.inflate(R.layout.image_fragment, container, false);

        topText = (TextView) view.findViewById(R.id.upText);
        bottomText = (TextView) view.findViewById(R.id.downText);


        return view;

    }

    public void setMemeText(String top, String bottom)
    {

        topText.setText(top);
        bottomText.setText(bottom);

    }

}
