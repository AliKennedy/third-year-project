package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoadingScreen extends AppCompatActivity
{
    // A timer for the loading screen
    private static int SPLASH_TIME_OUT = 2000;

    // Declaring a Firebase Authentication Variable
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Set the layout for this class
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        // A method to allow the loading screen be a loading screen
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent homeIntent = new Intent(LoadingScreen.this, StartUpScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}
