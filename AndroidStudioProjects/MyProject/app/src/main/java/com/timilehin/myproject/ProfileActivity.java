package com.timilehin.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity
{

    private TextView username;
    private Button logOutButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = findViewById(R.id.ProfileName);
        logOutButton = findViewById(R.id.logOutButton);

        firebaseAuth = FirebaseAuth.getInstance();

        // User not logged in, go to the log in page.
        if (firebaseAuth.getCurrentUser() == null)
        {

            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        // Gets the user-object for the current user.
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null)
        {

            // Switch the text view to what is below.
            username.setText("Welcome " + user.getEmail());

        }


        // Log out of your profile
        logOutButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Sign in page", "sign in");
                startActivity(intent);


            }

        });

    }

}
