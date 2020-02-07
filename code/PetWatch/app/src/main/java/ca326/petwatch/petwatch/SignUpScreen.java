package ca326.petwatch.petwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpScreen extends AppCompatActivity
{
    private EditText firstName;
    private EditText lastName;
    private EditText petsName;
    private EditText emailAddress;
    private EditText createPassword;
    private EditText confirmPassword;

    private Button buttonSignUp;
    private TextView passwordNotMatch;

    DatabaseHelper mDatabaseHelper;



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

        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        passwordNotMatch = (TextView)findViewById(R.id.passwordNotMatch);

        mDatabaseHelper = new DatabaseHelper(this);

        buttonSignUp.setOnClickListener(new View.OnClickListener()
        {

                @Override
                public void onClick(View view)
                {
                    if (passwordMatch(createPassword.getText().toString(), confirmPassword.getText().toString())) //&& (emailAlreadyTaken(emailAddress.getText().toString()) != true))
                    {
                        if ((firstName.getText().toString().length() != 0) && (lastName.getText().toString().length() != 0) && (petsName.getText().toString().length() != 0) && (emailAddress.getText().toString().length() != 0))
                            createAccount(firstName.getText().toString(), lastName.getText().toString(), petsName.getText().toString(), emailAddress.getText().toString(), confirmPassword.getText().toString(), 1);
                        else
                            toastMessage("You must fill in all parameters!");
                    }
                        else
                    {
                        passwordMatch(createPassword.getText().toString(), confirmPassword.getText().toString());
                        //emailAlreadyTaken(emailAddress.getText().toString());
                    }
                }

        });
    }

    public void createAccount(String fName, String lName, String pName, String eAddress, String password, Integer ardID)
    {
        boolean db2 = mDatabaseHelper.addData(fName, "fName");
        boolean db3 = mDatabaseHelper.addData(lName, "sName");
        boolean db4 = mDatabaseHelper.addData(pName, "pName");
        boolean db5 = mDatabaseHelper.addData(eAddress, "eMail");
        boolean db6 = mDatabaseHelper.addData(password, "pWord");
        boolean db7 = mDatabaseHelper.addData(ardID.toString(), "ardID");

        if ((db2) && (db3) && (db4) && (db5) && (db6) && (db7))
            toastMessage("Hoorraayy it Works");
        else
            toastMessage("I broke it");


    }

    public boolean passwordMatch(String password, String otherPassword)
    {
        if (password.equals(otherPassword))
            return true;
        else
        {
            TextView warning = SignUpScreen.this.passwordNotMatch;
            warning.setVisibility(View.VISIBLE);
            return false;
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
