package ca326.petwatch.petwatch.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca326.petwatch.petwatch.R;

public class AboutInfo extends AppCompatActivity
{
    private Button supportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        supportButton = findViewById(R.id.AboutContactButton);

        supportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AboutInfo.this, SupportInfo.class);
                startActivity(intent);
            }
        });


    }
}
