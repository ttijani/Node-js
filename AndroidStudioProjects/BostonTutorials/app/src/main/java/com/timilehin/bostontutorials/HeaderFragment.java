package com.timilehin.bostontutorials;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tijanioluwatimilehin on 8/14/17.
 */

public class HeaderFragment extends Fragment
{

    private EditText topText;
    private EditText bottomText;

    TopSectionListener activityCommander;

    public interface TopSectionListener
    {

        public void createMeme(String top, String bottom);

    }

    @Override
    public void onAttach(Context context)
    {

        super.onAttach(context);

        try
        {
            activityCommander = (TopSectionListener) getActivity();
        } catch (ClassCastException e)
        {
            throw new ClassCastException(getActivity().toString());
        }

        Log.d("From Header fragment", "Attached to activity");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        // Sets the fragment to use the layout you created.
        View view = inflater.inflate(R.layout.header_fragment, container, false);

        topText = (EditText) view.findViewById(R.id.topText);
        bottomText = (EditText) view.findViewById(R.id.bottomText);

        final Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                buttonClicked(view);

            }

        });

        return view;

    }

    public void buttonClicked(View view)
    {

        activityCommander.createMeme(topText.getText().toString(), bottomText.getText().toString());

    }
}
