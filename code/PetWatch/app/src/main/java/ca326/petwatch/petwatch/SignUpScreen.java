package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpScreen extends AppCompatActivity
{
    private EditText firstName;
    private EditText lastName;
    private EditText petsName;
    private EditText emailAddress;
    private EditText createPassword;
    private EditText confirmPassword;

    private Button signUp;
    private TextView passwordNotMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.Surname);
        petsName = (EditText)findViewById(R.id.petsName);
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        createPassword = (EditText)findViewById(R.id.passwordCreate);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);

        signUp = (Button)findViewById(R.id.buttonSignUp);
        passwordNotMatch = (TextView)findViewById(R.id.passwordNotMatch);

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void Onclick(View view)
            {
                if (passwordMatch(createPassword.getText().toString(), confirmPassword.getText().toString()) //&& (emailAlreadyTaken(emailAddress.getText().toString()) != true))
                    createAccount(firstName.getText().toString(), lastName.getText().toString(), petsName.getText().toString(), emailAddress.getText().toString(), confirmPassword.getText().toString());
                else
                {
                    passwordMatch(createPassword.getText().toString(), confirmPassword.getText().toString());
                    //emailAlreadyTaken(emailAddress.getText().toString());
                }
            }
        });
    }

    public void createAccount(String fName, String lName, String pName, String eAddress, String password)
    {
        //Database query here
    }

    public boolean passwordMatch(String password, String otherPassword)
    {
        if (password.equals(otherPassword))
            return true;
        else
        {
            TextView warning = SignInScreen.this.passwordNotMatch;
            warning.setVisibility(View.VISIBLE);
            return false;
        }
    }

// NEED DATABASE SETUP TO USE THIS METHOD
//    public boolean emailAlreadyTaken(String email)
//    {
//        //Database query here
//        if (true)
//        {
//            //Display error saying already taken
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
}
