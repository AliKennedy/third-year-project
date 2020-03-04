package ca326.petwatch.petwatch.ui.settings;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca326.petwatch.petwatch.R;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class ChangePassword extends AppCompatActivity
{
    private EditText oldPass, newPass, confirmPass;

    private TextView oldPassDontMatch, newPassDontMatch;

    private Button confirmChange;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private AuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        confirmPass = (EditText) findViewById(R.id.confirmPass);

        confirmChange = (Button) findViewById(R.id.confirmButton);

        oldPassDontMatch = (TextView) findViewById(R.id.oldPassWarning);
        newPassDontMatch = (TextView) findViewById(R.id.passDontMatch);


        confirmChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changePassword();

            }
        });

    }

    private void changePassword()
    {
        if (newPass.getText().toString().trim().equals(confirmPass.getText().toString().trim()))
        {


            firebaseAuth = FirebaseAuth.getInstance();
            user = firebaseAuth.getCurrentUser();
            final String email = user.getEmail();

            credential = EmailAuthProvider.getCredential(email, oldPass.getText().toString().trim());

            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                user.updatePassword(confirmPass.getText().toString().trim())
                                        .addOnCompleteListener(new OnCompleteListener<Void>()
                                        {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if (task.isSuccessful())
                                                {
                                                    Log.d(TAG, "Password Updated");
                                                    toastMessage("Password Updated");
                                                    Intent intent = new Intent(ChangePassword.this, AccountInfo.class);
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    // I dunno what the else would be
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener()
                                        {
                                            @Override
                                            public void onFailure(@NonNull Exception e)
                                            {
                                                // No Internet Access
                                                toastMessage("Did not work");
                                            }
                                        });
                            }
                            else
                            {
                                TextView warning = ChangePassword.this.oldPassDontMatch;
                                warning.setVisibility(View.VISIBLE);
                                toastMessage("Old Password was Incorrect");
                                // Old Password was Incorrect
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            // No internet access
                            toastMessage("Did not work");
                        }
                    });
        }
        else
        {
            TextView warning = ChangePassword.this.newPassDontMatch;
            warning.setVisibility(View.VISIBLE);
            toastMessage("Passwords do not match");
        }
    }

    private void toastMessage(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
