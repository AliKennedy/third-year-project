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

    private EditText emailAddress;
    private EditText passwordText;
    private Button buttonSignIn;
    private TextView warningText;
    private Button forgotPasswordButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        emailAddress = (EditText)findViewById(R.id.emailAddress);
        passwordText = (EditText)findViewById(R.id.passwordText);
        warningText = (TextView)findViewById(R.id.warningText);
        buttonSignIn = (Button)findViewById(R.id.buttonSignin);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate(emailAddress.getText().toString(), passwordText.getText().toString());
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignInScreen.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void validate(String userName, String userPassword)
    {
        //Database query here
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

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}