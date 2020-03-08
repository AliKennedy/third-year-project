package ca326.petwatch.petwatch.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca326.petwatch.petwatch.R;

public class PrivacySecurityInfo extends AppCompatActivity
{

    // Creating a variable to use for an XML Variable
    private Button supportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Create the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_security_info);

        // Set up the support button for the occasion of clicking it
        supportButton = (Button)findViewById(R.id.PrivacyContactButton);
        supportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the contact us screen
                Intent intent = new Intent(PrivacySecurityInfo.this, ContactUsInfo.class);
                startActivity(intent);
            }
        });



    }
}
