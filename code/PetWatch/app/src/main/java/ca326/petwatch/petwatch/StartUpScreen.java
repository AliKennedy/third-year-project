package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartUpScreen extends AppCompatActivity
{

    private Button signInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        signInButton = (Button)findViewById(R.id.signInButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(StartUpScreen.this, SignInScreen.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StartUpScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });
    }
}
