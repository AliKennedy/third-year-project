package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartUpScreen extends AppCompatActivity
{
    // Setting up variables to be used as XML Files
    private Button signInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Setup the start screen layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        // Setting variables to elements of the XML File
        signInButton = (Button)findViewById(R.id.signInButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        // Setting up the Sign In Button
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                // Change to the Sign In Screen
                Intent intent = new Intent(StartUpScreen.this, SignInScreen.class);
                startActivity(intent);
            }
        });

        // Setting up the Sign Up Button
        signUpButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                // Change to the Sign Up Screen
                Intent intent = new Intent(StartUpScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });
    }
}
