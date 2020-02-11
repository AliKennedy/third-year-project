package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInScreen extends AppCompatActivity
{

    private EditText emailAddress;
    private EditText passwordText;
    private Button buttonSignIn;
    private TextView warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        emailAddress = (EditText)findViewById(R.id.emailAddress);
        passwordText = (EditText)findViewById(R.id.passwordText);
        warningText = (TextView)findViewById(R.id.warningText);
        buttonSignIn = (Button)findViewById(R.id.buttonSignin);


        buttonSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate(emailAddress.getText().toString(), passwordText.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword)
    {
        //Database query here
        // if statement used for testing
        if ((userName.equals("Ethan")) && (userPassword.equals("ethan")))
        {
            Intent intent = new Intent(SignInScreen.this, Main2Activity.class);
            startActivity(intent);
        }
        else if ((userName.equals("Ali")) && (userPassword.equals("ali")))
        {
            Intent intent = new Intent(SignInScreen.this, Main2Activity.class);
            startActivity(intent);
        }
        else
        {
            TextView warning = SignInScreen.this.warningText;
            warning.setVisibility(View.VISIBLE);
        }
    }

}