package com.timilehin.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by tijanioluwatimilehin on 8/17/17.
 */

public class SignUpFragment extends Fragment
{

    // Variables declared here.
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView signInLink;
    private Button signInButton;
    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.signup_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        editTextUsername = getActivity().findViewById(R.id.signInName);
        editTextPassword = getActivity().findViewById(R.id.signInPswd);
        signInLink = getActivity().findViewById(R.id.signInLink);
        signInButton = getActivity().findViewById(R.id.signInButton);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {

            //already logged in.
            getActivity().finish();
            startActivity(new Intent(getContext(), ProfileActivity.class));

        }


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

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                registerUser();

            }
        });

    }


    public void registerUser()
    {

        // Get the input fields.
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if input strings are empty.
        if (TextUtils.isEmpty(email))
        {

            Toast.makeText(getContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(password))
        {

            Toast.makeText(getContext(), "Please enter a valid password of at least 6 characters", Toast.LENGTH_SHORT).show();
            return;

        }

        // Interface with FireBase that adds user.
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                    {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {

                            if (task.isSuccessful())
                            {

                                Toast.makeText(getContext(), "The User was added successfully", Toast.LENGTH_SHORT).show();

                                //already logged in.
                                getActivity().finish();
                                startActivity(new Intent(getContext(), ProfileActivity.class));

//                                Fragment fragment = new AddDriverFragment();
//                                FragmentManager fm = getFragmentManager();
//                                FragmentTransaction ft = fm.beginTransaction();
//                                ft.replace(R.id.MyFrameLayout, fragment);
//                                ft.commit();

                            } else
                            {

                                Toast.makeText(getContext(), "Registration Failed. Try again.", Toast.LENGTH_SHORT).show();

                            }

                        }

                    });

    }

}
