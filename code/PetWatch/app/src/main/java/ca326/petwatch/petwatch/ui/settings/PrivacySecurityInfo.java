package ca326.petwatch.petwatch.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca326.petwatch.petwatch.R;

public class PrivacySecurityInfo extends AppCompatActivity
{


    private Button supportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_security_info);

        supportButton = (Button)findViewById(R.id.PrivacySupportButton);
        supportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PrivacySecurityInfo.this, SupportInfo.class);
                startActivity(intent);
            }
        });



    }
}
