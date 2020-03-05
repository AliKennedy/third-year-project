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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.navigation_panic, navigation_speed, R.id.navigation_map, R.id.navigation_log, R.id.navigation_settings)
        //        .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //receive data from map fragment
        Intent intent = getIntent();
        String latString = intent.getStringExtra("latitude");
        String lngString = intent.getStringExtra("longitude");

        //send this data to Speed Fragment
        Bundle bundle=new Bundle();
        bundle.putString("latitude", "From Activity");
        bundle.putString("longitude", "From Activity");
        //set  Arguments
        SpeedFragment speedFrag = new SpeedFragment();
        speedFrag.setArguments(bundle);

    }

}

