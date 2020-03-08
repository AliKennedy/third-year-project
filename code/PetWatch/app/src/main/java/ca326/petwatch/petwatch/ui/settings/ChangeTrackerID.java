package ca326.petwatch.petwatch.ui.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import ca326.petwatch.petwatch.R;

public class ChangeTrackerID extends AppCompatActivity
{
    // Creating Java Variables for their Corresponding XML Variables
    private Button changeIDButton;
    private EditText newID;

    // Creating firebase instances to use for database access
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference docRef;

    // String variable to store uid
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Setting the layout for Screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tracker_id);

        // Assigning java variables to correspondin XML Variables
        changeIDButton = (Button) findViewById(R.id.updateArdIDButton);
        newID = (EditText) findViewById(R.id.updateArdID);

        // Getting a database instance to be able to change the tracker I.D.
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Getting the users Unique I.D.
        uid = firebaseAuth.getUid();



        docRef = firebaseFirestore.collection("userDetails").document(uid);



        // Setting up the Change ID button
        changeIDButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Ensuring the new I.D. is 5 characters long
                String id = newID.getText().toString().trim();
                if ((id.length() != 5))
                    toastMessage("ID must be 5 characters long");
                else
                {
                    // Update the I.D. for the tracker
                    docRef.update("ardID", id)
                            .addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    // A toast message to signify it was a success
                                    toastMessage("Successfully updated");
                                    // Move to the Account Screen
                                    Intent intent = new Intent(ChangeTrackerID.this, AccountInfo.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    toastMessage("Update failed, please try again later");
                                }
                            });
                }
            }
        });
    }
    // A method to create a toast message
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
