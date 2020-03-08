package ca326.petwatch.petwatch.ui.speed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import ca326.petwatch.petwatch.R;
import ca326.petwatch.petwatch.ui.map.MapFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.util.Collections.*;

public class SpeedFragment extends Fragment
{
    // Set up variables to use as XML Variables
    private Button startSpeed, stopSpeed;
    private TextView displaySpeed;

    // Access RealTime database
    private FirebaseDatabase database;

    // Get a reference from the database
    private DatabaseReference myRef;

    // Arduino id
    private String ardID;
    // used as a variable to obtain the Arduino id

    // Used to obtain latitude and longitude
    private String latString;
    private String lngString;

    private Double lat;
    private Double lng;

    // Used to store time
    private Date time1;
    private Date time2;

    // Used to obtain speed
    private Double speed;

    // Used to store url that is required to access database
    private String refFromUrl;// = "https://petwatch-519c6.firebaseio.com/";

    // Used to store a path that is required to access database
    private  String collectionPath;


    private String arduinoIDKey;

    // Earths Radius
    private int earthRadius = 6373; //in km

    //create List
    private String[] latLng = new String[4];

    private ArrayList<Double> speeds = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // Setting paths to database
        setCollectionPath("userDetails");
        setRefFromUrl("https://petwatch-519c6.firebaseio.com/");

        // Displaying the fragment within the main activity
        View root = inflater.inflate(R.layout.fragment_speed, container, false);

        // Setting variables to their corresponding XML Variables
        startSpeed = (Button) root.findViewById(R.id.countSpeed);
        stopSpeed = (Button) root.findViewById(R.id.stopCountSpeed);
        displaySpeed = (TextView) root.findViewById(R.id.speedHere);

        // Creates a user object
        FirebaseUser user;
        // Gets an instance of the current user
        // Authentication of user to ensure correct Arduino location is being displayed
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        // Gets the unique I.D. of the current user

        // Users unique id to get access to Arduino id
        assert user != null;
        String uid;
        uid = user.getUid();

        // Gets an instance of the Cloud Firestore Database
        // Access to the cloud firestore database to obtain Arduino id
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        // Accesses the current users document
        // document reference for the cloud firestore database
        DocumentReference docRef = firebaseFirestore.collection(getCollectionPath()).document(uid);



        // Gets an instance of the Real-Time database
        database = FirebaseDatabase.getInstance();
        // Retrieves the location of where the data is stored



        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        // If the document exists, get the data
                        if (documentSnapshot.exists())
                        {
                            setArduinoIDKey("ardID");
                            ardID = documentSnapshot.getString(getArduinoIDKey());
                            // Get the Arduino I.D. from the users database
                            myRef = database.getReferenceFromUrl(getRefFromUrl() + ardID); // URL plus Arduino id!

                            // A method that evaluates if there are any changes of the data in the database
                            Log.d(TAG, "url is " + refFromUrl + ardID);

                            myRef.addValueEventListener(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot)
                                {
                                    if (dataSnapshot.exists())
                                    {
                                        // Record the time
                                        Log.d(TAG, "data is " + dataSnapshot.getChildren().iterator().next());
                                        time1 = Calendar.getInstance().getTime();
                                        Log.d(TAG, "the time is " + time1);

                                        for (DataSnapshot data : dataSnapshot.getChildren())
                                        {
                                            if (data.getKey().equals("lat"))
                                            {
                                                // Get the latitude from database
                                                latString = (String) data.getValue();
                                                // Add the latitude to Array
                                                setLatLng(0, latString);
                                                lat = Double.parseDouble(latString);
                                                Log.d(TAG, "lat is " + getLatLng(0));

//
                                                if (getLatLng(1) == (null))
                                                {
                                                    //copy data across
                                                    //only happens with first instance
                                                    //no change (obviously)
                                                    setLatLng(1, latString);
                                                }


                                            }
                                            else if (data.getKey().equals("lng"))
                                            {
                                                // Gets the longitude
                                                lngString = (String) data.getValue();
                                                lng = Double.parseDouble(lngString);
                                                // Adds the longitude to Array
                                                setLatLng(2, lngString);
                                                Log.d(TAG, "lng is " + lng);

                                                if (getLatLng(3) == (null))
                                                {
                                                    //copy data across
                                                    //only happens with first instance
                                                    //no change (obviously)
                                                    setLatLng(3, lngString);
                                                }

                                                Log.d(TAG, "list values are" + getLatLng(0) + getLatLng(1) + getLatLng(2) + getLatLng(3));

                                                //distance(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));
                                                // Calculate the speed
                                                double ans = calculateSpeed(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));
                                                // Set the old time
                                                setOldTime(time1);
                                                // Adds the speed to the speed ArrayList
                                                speeds.add(ans);
                                            }

                                            //distance(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));

                                            setLatLng(1, getLatLng(0)); // copy down
                                            setLatLng(3, getLatLng(2)); //copy down after speed is calculated
                                        }


                                    }


                                }
                                // If the data does not exist the method goes here
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError)
                                {

                                    //Display message saying access to RealTime Database was denied
                                    //toastMessage("Accessing Co-ordinates could not happen, Please Try Again Later");
                                }
                            });
                        }
                    }
                })
                // If the procedure fails, go here
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        //Access to cloud firestore was not achieved.
                        toastMessage("Access to retrieve Tracker ID is denied, Please Try Again Later");
                    }
                });


        // A start Speed button to start requiring results.
        // This feature is not implemented correctly as of yet
        startSpeed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Make the start speed button disappear
                startSpeed.setVisibility(View.GONE);
                // Make the stop timer button appear
                stopSpeed.setVisibility(View.VISIBLE);


//                while (!(stopSpeed.isPressed()))
//                {
//                    double ans = calculateSpeed(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));
//                    if ((speeds.size() > 1) && (!(ans == 0.0)))
//                    {
//                        speeds.add(ans);
//                    }
//                    else if (speeds.size() == 0)
//                    {
//                        speeds.add(ans);
//                    }
//                    else
//                    {
//                        speeds.add(ans);
//                    }
//                    Log.d(TAG, "This is Ethan's message to check answer " + ans + " " + getLatLng(0));
//                }
            }
        });

        stopSpeed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Make start timer button visible
                startSpeed.setVisibility(View.VISIBLE);
                // Make the stop button disappear
                stopSpeed.setVisibility(View.GONE);

                // Get the maximum time saved in the ArrayList
                double maximum = Collections.max(speeds);
                // Convert the Double to a String
                String s = Double.toString(maximum);
                // Display the result to the user
                displaySpeed.setText(s + " Km/h");
                displaySpeed.setVisibility(View.VISIBLE);
            }
        });


        return root;


    }

    public interface TextInterface
    {
        String getText();
    }

    private Double distance(String lat1, String lat2, String lng1, String lng2)
    {
        // Calculate the distance from one position to the other
        // Needed to calculate the speed
        final int radiusOfEarth = 6371; //km

        Double latitude1 = Double.parseDouble(lat1);
        Double latitude2 = Double.parseDouble(lat2);
        Double longitude1 = Double.parseDouble(lng1);
        Double longitude2 = Double.parseDouble(lng2);

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = radiusOfEarth * c ;

        double distanceSquared = Math.pow(distance, 2);

        distance =  Math.sqrt(distanceSquared);

        DecimalFormat df = new DecimalFormat("#.###");

        Double answer = Double.parseDouble(df.format(distance));

        Log.d(TAG, "distance is  " + answer);

        return  answer;

    }

    private Double calculateSpeed(String lat1, String lat2, String lng1, String lng2)
    {
        // Calculate the distance using the distance using the distance method created above and the time of each position change
        if (time2 == null)
        {
            return 0.000;
        }

        Double distance = distance(lat1, lat2, lng1, lng2);
        int secondsPassed = (int) (this.time1.getTime()-this.time2.getTime()) / 1000;
        Log.d(TAG, "seconds is " + secondsPassed);

        if (secondsPassed == 0)
        {
            return 0.000;
        }
        Double speed = distance/(secondsPassed * 3600);

        DecimalFormat df = new DecimalFormat("#.###");
        Double answer = Double.parseDouble(df.format(distance));
        this.speed = answer;
        Log.d(TAG, "speed is " + answer);
        return answer;
    }


    // Setters and getters
    private void setCollectionPath(String collectionPath)
    {
        this.collectionPath = collectionPath;
    }

    private void setArduinoIDKey(String arduinoIDKey)
    {
        this.arduinoIDKey = arduinoIDKey;
    }

    private String getArduinoIDKey()
    {
        return arduinoIDKey;
    }

    private String getRefFromUrl()
    {
        return this.refFromUrl;
    }

    private void setRefFromUrl(String refFromUrl)
    {
        this.refFromUrl = refFromUrl;
    }

    private String getCollectionPath()
    {
        return collectionPath;
    }


    private String getLatLng(int index)
    {
        return latLng[index];
    }

    private void setLatLng(int index, String str)
    {
        latLng[index] = str;
    }

    private void setOldTime(Date time)
    {
        this.time2 = time;
    }

    // A method to create toast messages
    private void toastMessage(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }





}