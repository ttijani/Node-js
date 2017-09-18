package com.timilehin.myproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by tijanioluwatimilehin on 8/16/17.
 */

public class AddDriverFragment extends Fragment
{

    private EditText editTextname;
    private EditText editTextusername;
    private EditText editTextpassword;
    private EditText editTextage;
    private TextView signInLink;
    private Button signUpButton;

    String name;
    String username;
    String password;
    int age;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.adddriver_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        this.getView().setBackgroundColor(Color.parseColor("#ffebcc"));
        super.onViewCreated(view, savedInstanceState);

        editTextname = getActivity().findViewById(R.id.name);
        editTextusername = getActivity().findViewById(R.id.username);
        editTextpassword = getActivity().findViewById(R.id.password);
        editTextage = getActivity().findViewById(R.id.age);
        signInLink = getActivity().findViewById(R.id.signInLink);
        signUpButton = getActivity().findViewById(R.id.addDriverButton);

        // Redirection to the sign in page.
        signInLink.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Fragment fragment = new SignInFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.MyFrameLayout, fragment);
                ft.commit();

            }

        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                addDriverCLicked(view);

            }

        });

    }

    // Takes care of registration of new user.
    public void addDriverCLicked(View view)
    {

        name = editTextname.getText().toString();
        username = editTextusername.getText().toString();
        password = editTextpassword.getText().toString();
        age = Integer.parseInt(editTextage.getText().toString());

    }

}
