package ca326.petwatch.petwatch.ui.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import ca326.petwatch.petwatch.Main2Activity;
import ca326.petwatch.petwatch.R;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class AccountInfo extends AppCompatActivity
{
    // Creating variables to use for XML Variables
    private TextView name;
    private TextView pName;
    private TextView trackerID;
    private Button changePass, goBackButton;

    // Creating variables to use with the firebase database
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore dataBase;
    private DocumentReference docRef;

    // Creating a string object to store the current users Unique I.D.
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Create the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        // Assigning java variables to XML Variables
        name = (TextView) findViewById(R.id.nameFromDatabase);
        pName = (TextView) findViewById(R.id.pNameFromDatabase);
        trackerID = (TextView) findViewById(R.id.trackerIDFromDatabase);

        changePass = (Button) findViewById(R.id.changePassButton);
        goBackButton = (Button) findViewById(R.id.goBackButton);

        //Database stuff here
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        dataBase =  FirebaseFirestore.getInstance();



        //get user ID
        uid = firebaseAuth.getUid();
        docRef = dataBase.collection("userDetails").document(uid);

        // Retrieve the users details stored in the database
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Assign the information from database to string objects
                            String fName = (String) task.getResult().get("fName");
                            String surname = (String) task.getResult().get("lName");
                            String pName = (String) task.getResult().get("pName");
                            String ardID = (String) task.getResult().get("ardID");

                            String name = fName + " " + surname;
                            // Display these variables in the XML File
                            setTextForXML(name, pName, ardID);

                            // Used to log info during testing to ensure everything was functioning correctly
                            Log.d(TAG, name + " " + surname  + " " + pName  + " " + ardID);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                    }
                });


        // Set up the change Password Button
        changePass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AccountInfo.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        // Set up the go back button
        goBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AccountInfo.this, ChangeTrackerID.class);
                startActivity(intent);
            }
        });

    }
    public void setTextForXML(String fullName, String petsName, String id)
    {
        // Display the Details on to the Screen
        assert fullName != null;
        name.setText(fullName);
        pName.setText(petsName);
        trackerID.setText(id);
    }
}
