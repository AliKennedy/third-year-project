package ca326.petwatch.petwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener
{
    // Setting variables  to use as XML Variables
    private EditText firstName;
    private EditText lastName;
    private EditText petsName;
    private EditText emailAddress;
    private EditText createPassword;
    private EditText confirmPassword;

    private Button buttonSignUp;
    private TextView passwordNotMatch;

    // Setting an Arduino I.D. String
    private String ardID;

    // Setting firebase variables
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private DocumentReference docRef;

    // Creating a string variable to store their unique I.D.
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Setting up the XML layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        // Assigning vaiables to parts of the XML file
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.Surname);
        petsName = (EditText) findViewById(R.id.petsName);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        createPassword = (EditText) findViewById(R.id.passwordCreate);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        passwordNotMatch = (TextView) findViewById(R.id.passwordNotMatch);

        // Assigning firebase variable to instances of the database
        firebaseAuth = FirebaseAuth.getInstance();
        db =  FirebaseFirestore.getInstance();

        // setting up the sign up button
        buttonSignUp.setOnClickListener(this);

    }

    // A method that is called when the sign up button is clicked
    @Override
    public void onClick(View v)
    {
        // A method to register the user
        registerUser(firebaseAuth, getEAddress(), getconfirmPWord());
    }


    private void registerUser(final FirebaseAuth fb, final String eAddress, final String pWord)
    {
        // Create a map to store the variables
        final Map<String, Object> user = new HashMap<>();

        // Setting the variables from the edit text fields in the XML field to a java object
        String fName = getFName();
        String lName = getLName();
        String pName = getPName();
        String cPWord = getcreatePWord();

        // If all sections are filled out, continue, else, give a message explaining error
        if (!(checkIfSectionsFilled(eAddress, fName, lName, pName, pWord)))
        {
            //Stops function from executing further
            return;
        } // Ensure passwords are greater the 6 characters
        else if (cPWord.length() < 6)
        {
            toastMessage("Password must be at least 6 characters");
            return;
        }
        //Everything is valid
        else
        {
            // Ensure create password and confirm password are the same
            if ((passwordMatch(cPWord, pWord)))
            {
                // Set the new Arduino I.D.
                setNewArdID();
                // Add all the edit text fields to the map above
                user.put("fName", fName);
                user.put("lName", lName);
                user.put("pName", pName);
                user.put("ardID", getNewArdID());

                // Create user using email and password authentication
                fb.createUserWithEmailAndPassword(eAddress, pWord)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {

                                if (task.isSuccessful())
                                {
                                    // Sign in success, update UI with the signed-in user's information
                                    //toastMessage("It worked!!");
                                    // Add all the users details to the database upon the creation of the account
                                    uid = fb.getUid();
                                    docRef = db.collection("userDetails").document(uid);

                                    docRef.set(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void aVoid)
                                                {
                                                    toastMessage("Successfully created account");
                                                    // Move to the sign in screen to sign in with the new account
                                                    Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener()
                                            {
                                                @Override
                                                public void onFailure(@NonNull Exception e)
                                                {
                                                    toastMessage("Error Creating Account. Please Try Again Later");
                                                }
                                            });


                                }
                                else
                                {
                                    // Check if an account with that email address already exists
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {
                                        toastMessage("Email address already taken!");
                                        Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        toastMessage("Error Creating Account. Please Try Again Later.");
                                    }
                                }

                                // If sign in fails, display a message to the user.
                                //toastMessage("Successfully Registered");

                                // ...
                            }
                        });
            }
            else
            {
                toastMessage("Passwords do not match.");
            }

        }

    }

    // A method to create a toast message
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // A method to check if the two passwords match
    private boolean passwordMatch(String password, String otherPassword)
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

    // A method to check if all sections are filled
    private boolean checkIfSectionsFilled(String eAddress, String fName, String lName, String pName, String  pWord)
    {
        if ((fName.equals("")) || (lName.equals("")) || (pName.equals("")) || (eAddress.equals("")) || (pWord.equals("")))
        {
            toastMessage("Not all sections are filled!");
            //Stops function from executing further
            return false;
        }
        else
        {
            return true;
        }
    }

    // Adding setters and getters
    protected String getFName()
    {
        return firstName.getText().toString().trim();
    }

    protected String getLName()
    {
        return lastName.getText().toString().trim();
    }

    protected String getPName()
    {
        return petsName.getText().toString().trim();
    }


    protected String getEAddress()
    {
        return emailAddress.getText().toString().trim();
    }

    protected String getcreatePWord()
    {
        return createPassword.getText().toString().trim();
    }

    protected String getconfirmPWord()
    {
        return confirmPassword.getText().toString().trim();
    }

    protected String getNewArdID()
    {
        return ardID;
    }

    protected void setNewArdID()
    {
        ardID = "qwdb5";
    }


}
