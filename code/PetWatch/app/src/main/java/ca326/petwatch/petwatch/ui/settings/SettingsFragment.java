package ca326.petwatch.petwatch.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import ca326.petwatch.petwatch.R;
import ca326.petwatch.petwatch.StartUpScreen;


public class SettingsFragment extends Fragment
{
    // Setting Variables for XML Variables
    private Button account;
    private Button notifications;
    private Button privacySecurity;
    private Button support;
    private Button about;
    private Button logOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // Display the fragment on screen
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Assigning the variables to their XML counterparts
        account = (Button) root.findViewById(R.id.Account);
        notifications = (Button) root.findViewById(R.id.Notifications);
        privacySecurity = (Button) root.findViewById(R.id.PrivacySecurity);
        support = (Button) root.findViewById(R.id.Contact);
        about = (Button) root.findViewById(R.id.About);
        logOut = (Button) root.findViewById(R.id.logOut);


        // Setting up Account button
        account.setOnClickListener(new OnClickListener()
        {
                @Override
                public void onClick(View view)
                {
                    // Move to the Account Screen
                    Intent intent = new Intent(getActivity(), AccountInfo.class);
                    startActivity(intent);
                }

        }
        );

        // Setting up Change tracker I.D. button
        notifications.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the Change Tracker I.D. Screen
                Intent intent = new Intent(getActivity(), ChangeTrackerID.class);
                startActivity(intent);
            }
        });

        // Setting up Privacy & Security Button
        privacySecurity.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the Privacy & Security Screen
                Intent intent = new Intent(getActivity(), PrivacySecurityInfo.class);
                startActivity(intent);
            }
        });

        // Setting up the Support button, now known as the Contact Us Button
        support.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the Contact Us Screen
                Intent intent = new Intent(getActivity(), ContactUsInfo.class);
                startActivity(intent);
            }
        });

        // Setting up the About Button
        about.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the About Screen
                Intent intent = new Intent(getActivity(), AboutInfo.class);
                startActivity(intent);
            }
        });

        // Setting up the Logout Button
        logOut.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Move to the Sign in Screen
                Intent intent = new Intent(getActivity(), StartUpScreen.class);
                getActivity().startActivity(intent);

                // Get instance of the firebase Auth to allow signing out
                FirebaseAuth.getInstance().signOut();

                // Toast message displaying a successful log out
                toastMessage("You have logged out.");
                getActivity().finish();
            }
        });



        return root;
    }

//
//    public void moveScreen(String name)
//    {
//        Intent intent = new Intent(getActivity(), name + ".class");
//        startActivity(intent);
//    }

    // A method to create a toast message
    private void toastMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}