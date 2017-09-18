package com.timilehin.fragmenttutorial;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by tijanioluwatimilehin on 8/12/17.
 */

public class FragmentTwo extends Fragment {
    @Nullable
    @Override
    public View
    onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_two, container, false);

    }

    @Override
    public void
    onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        Button button = getActivity().findViewById(R.id.convert);
        final RadioButton toCelcius = getActivity().findViewById(R.id.radioButton);
        final RadioButton toFahrenheit = getActivity().findViewById(R.id.radioButton2);
        final EditText editText = getActivity().findViewById(R.id.tempValue);
        final TextView textView = getActivity().findViewById(R.id.convertedValue);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void
            onClick(View view)
            {

                float tempValue = Float.parseFloat(editText.getText().toString());

                if (toCelcius.isChecked()) 
                {
                 
                    float value = TempConverter.getTempInCelcius(tempValue);
                    String result = String.valueOf(value) + " Celsius";
                    textView.setText(result);
                    toCelcius.setChecked(true);
                    toFahrenheit.setChecked(false);
                    
                } else if (toFahrenheit.isChecked())
                {

                    float value = TempConverter.getTempInFahrenheit(tempValue);
                    String result = String.valueOf(value) + " Fahrenheit";
                    textView.setText(result);
                    toCelcius.setChecked(false);
                    toFahrenheit.setChecked(true);

                }

            }
        });

        super.onViewCreated(view, savedInstanceState);

    }
}
