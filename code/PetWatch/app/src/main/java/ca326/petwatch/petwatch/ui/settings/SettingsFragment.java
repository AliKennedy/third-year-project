package ca326.petwatch.petwatch.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseAuth;

import ca326.petwatch.petwatch.LoadingScreen;
import ca326.petwatch.petwatch.R;
import ca326.petwatch.petwatch.StartUpScreen;

public class SettingsFragment extends Fragment {

    private Button account;
    private Button notifications;
    private Button privacySecurity;
    private Button support;
    private Button about;
    private Button logOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        account = (Button) root.findViewById(R.id.Account);
        notifications = (Button) root.findViewById(R.id.Notifications);
        privacySecurity = (Button) root.findViewById(R.id.PrivacySecurity);
        support = (Button) root.findViewById(R.id.Support);
        about = (Button) root.findViewById(R.id.About);
        logOut = (Button) root.findViewById(R.id.logOut);


        account.setOnClickListener(new OnClickListener()
        {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getActivity(), AccountInfo.class);
                    startActivity(intent);
                }

        }
        );

        notifications.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), NotificationInfo.class);
                startActivity(intent);
            }
        });

        privacySecurity.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), PrivacySecurityInfo.class);
                startActivity(intent);
            }
        });

        support.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SupportInfo.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AboutInfo.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), StartUpScreen.class);
                getActivity().startActivity(intent);

                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
            }
        });

        return root;
    }

//
//    public void moveScreen(String name)
//    {
//        Intent intent = new Intent(getActivity(), ".class");
//        startActivity(intent);
//    }
}