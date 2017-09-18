package com.timilehin.fragmenttutorial;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by tijanioluwatimilehin on 8/12/17.
 */

public class FragmentOne extends Fragment {
    @Nullable
    @Override
    public View
    onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void
    onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        final RelativeLayout rl = getActivity().findViewById(R.id.background);
        final ToggleButton tg = getActivity().findViewById(R.id.toggleButton);
        final TextView tv = getActivity().findViewById(R.id.textView2);

        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rl.setBackgroundColor(Color.BLACK);
                    tg.setTextColor(Color.WHITE);
                    tv.setTextColor(Color.WHITE);
                } else {
                    rl.setBackgroundColor(Color.WHITE);
                    tg.setTextColor(Color.BLACK);
                    tv.setTextColor(Color.BLACK);
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }
}
