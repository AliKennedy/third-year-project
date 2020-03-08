package ca326.petwatch.petwatch;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.util.HashMap;

import ca326.petwatch.petwatch.ui.speed.SpeedFragment;

import static ca326.petwatch.petwatch.R.id.navigation_speed;

public class Main2Activity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Creating the main activity layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Setting up the nav bar in the main activity
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);
    }

}

