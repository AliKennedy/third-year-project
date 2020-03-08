package ca326.petwatch.petwatch.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca326.petwatch.petwatch.R;

public class AboutInfo extends AppCompatActivity
{
    // Creating variables for XML variables
    private Button supportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Create the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        // Assigning Java variable to XML I.D.
        supportButton = findViewById(R.id.AboutContactButton);

        // Setting up the Support button, now the Contact Us button
        supportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the Contact Us Screen
                Intent intent = new Intent(AboutInfo.this, ContactUsInfo.class);
                startActivity(intent);
            }
        });


    }
}
