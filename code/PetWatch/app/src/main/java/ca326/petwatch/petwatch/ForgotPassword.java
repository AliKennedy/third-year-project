package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity
{
    private Button sendEmail;
    private EditText enterEmail;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        sendEmail = (Button) findViewById(R.id.sendForgotEmail);
        enterEmail = (EditText) findViewById(R.id.enterEmail);

        firebaseAuth = FirebaseAuth.getInstance();

        sendEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                firebaseAuth.sendPasswordResetEmail(enterEmail.getText().toString().trim());
            }
        });

    }
}
