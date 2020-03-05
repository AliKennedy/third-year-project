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

    private Button changeIDButton;
    private EditText newID;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference docRef;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tracker_id);

        changeIDButton = (Button) findViewById(R.id.updateArdIDButton);
        newID = (EditText) findViewById(R.id.updateArdID);

        //Info about notification
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();

        docRef = firebaseFirestore.collection("userDetails").document(uid);




        changeIDButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = newID.getText().toString().trim();
                if ((id.length() != 5))
                    toastMessage("ID must be 5 characters long");
                else
                {
                    docRef.update("ardID", id)
                            .addOnSuccessListener(new OnSuccessListener<Void>()
                            {
                                @Override
                                public void onSuccess(Void aVoid)
                                {
                                    toastMessage("Successfully updated");
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

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
