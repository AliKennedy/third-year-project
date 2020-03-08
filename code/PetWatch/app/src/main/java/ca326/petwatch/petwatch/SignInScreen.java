package ca326.petwatch.petwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInScreen extends AppCompatActivity
{
    // Setting variables to be used as XML variables
    private EditText emailAddress;
    private EditText passwordText;
    private Button buttonSignIn;
    private TextView warningText;
    private Button forgotPasswordButton;

    // Setting a Firebase Authentication variable
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Set the XML layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        // Mapping variables to parts of the corresponding XML File
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        passwordText = (EditText)findViewById(R.id.passwordText);
        warningText = (TextView)findViewById(R.id.warningText);
        buttonSignIn = (Button)findViewById(R.id.buttonSignin);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);

        // Getting an instance of the firebaseAuth database
        firebaseAuth = FirebaseAuth.getInstance();

        // Setting up the sign in button
        buttonSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Moving to the validate method
                validate(emailAddress.getText().toString(), passwordText.getText().toString());
            }
        });

        // Setting up the forgot password button
        forgotPasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Changing to the Forgot Password Button
                Intent intent = new Intent(SignInScreen.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    // A method to sign the user in
    private void validate(String userName, String userPassword)
    {
        //Database query here
        // A firebase method to sign a user in
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            toastMessage("You are now logged in!");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(SignInScreen.this, Main2Activity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            toastMessage("Email or password is incorrect!");
                            TextView warning = SignInScreen.this.warningText;
                            warning.setVisibility(View.VISIBLE);
                        }
                    }
                });

        // if statement used for testing


    }

    // A method to create a toast message
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}