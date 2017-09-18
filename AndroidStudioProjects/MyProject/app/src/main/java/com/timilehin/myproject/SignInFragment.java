package com.timilehin.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Created by tijanioluwatimilehin on 8/20/17.
 */

public class SignInFragment extends Fragment
{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private TextView signUpLink;
    private Fragment fragment;
    private String email;
    private String password;

    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.signin_fragment, container, false);

    }

    private void signIn()
    {

        String email = editTextEmail.getText().toString().trim();
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

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if (task.isSuccessful())
                        {

                            // Go to profile render.
                            getActivity().finish();
                            startActivity(new Intent(getContext(), ProfileActivity.class));

                        } else
                        {

                            // Print error message.
                            Toast.makeText(getContext(), "Sign in failed.. Try again.", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        editTextEmail = getActivity().findViewById(R.id.signInEmail);
        editTextPassword = getActivity().findViewById(R.id.signInPassword);
        buttonSignIn = getActivity().findViewById(R.id.signInButton);
        signUpLink = getActivity().findViewById(R.id.signUpLink);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {

            //already logged in.
            getActivity().finish();
            startActivity(new Intent(getContext(), ProfileActivity.class));

        }

        buttonSignIn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                signIn();

            }

        });

        signUpLink.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                fragment = new SignUpFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.MyFrameLayout, fragment);
                ft.commit();

            }

        });

    }
}
