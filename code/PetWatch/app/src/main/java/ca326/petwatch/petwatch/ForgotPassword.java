package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity
{
    // Declaring Variables for  XML purposes
    private Button sendEmail;
    private EditText enterEmail;

    // Declaring a Firebase Authentication Variable
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Set Up the XML File
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Instantiating variables in XML
        sendEmail = (Button) findViewById(R.id.sendForgotEmail);
        enterEmail = (EditText) findViewById(R.id.enterEmail);

        // Getting an instance of Firebase Authentication database
        firebaseAuth = FirebaseAuth.getInstance();

        // A method to use for clicking a button
        sendEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Send a reset password email
                firebaseAuth.sendPasswordResetEmail(enterEmail.getText().toString().trim());
                toastMessage("Email Sent!");

                // Move to the sign in screen
                Intent intent = new Intent(ForgotPassword.this, SignInScreen.class);
                startActivity(intent);
            }
        });

    }
    // A methos to create a toast message
    public void toastMessage(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
