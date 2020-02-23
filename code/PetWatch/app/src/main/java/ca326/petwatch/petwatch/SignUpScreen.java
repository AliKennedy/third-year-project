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
    private EditText firstName;
    private EditText lastName;
    private EditText petsName;
    private EditText emailAddress;
    private EditText createPassword;
    private EditText confirmPassword;

    private Button buttonSignUp;
    private TextView passwordNotMatch;

    private String ardID = "uivwA";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private DocumentReference docRef;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.Surname);
        petsName = (EditText) findViewById(R.id.petsName);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        createPassword = (EditText) findViewById(R.id.passwordCreate);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        passwordNotMatch = (TextView) findViewById(R.id.passwordNotMatch);

        firebaseAuth = FirebaseAuth.getInstance();
        db =  FirebaseFirestore.getInstance();

        buttonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        registerUser(firebaseAuth, getEAddress(), getconfirmPWord());
    }


    private void registerUser(final FirebaseAuth fb, final String eAddress, final String pWord)
    {
        final Map<String, Object> user = new HashMap<>();

        String fName = getFName();
        String lName = getLName();
        String pName = getPName();
        String cPWord = getcreatePWord();

        if (!(checkIfSectionsFilled(eAddress, fName, lName, pName, pWord)))
        {
            //Stops function from executing further
            return;
        }
        //Everything is valid
        else
        {
            if ((passwordMatch(cPWord, pWord)))
            {
                user.put("fName", fName);
                user.put("lName", lName);
                user.put("pName", pName);
                user.put("ardID", ardID);

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
                                    uid = fb.getUid();
                                    docRef = db.collection("userDetails").document(uid);

                                    docRef.set(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void aVoid)
                                                {
                                                    toastMessage("Successfully created account");
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


    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


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

    public String getUniqueID()
    {
        return uid;
    }

}
