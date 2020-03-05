package ca326.petwatch.petwatch.ui.speed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Date;

import ca326.petwatch.petwatch.R;
import ca326.petwatch.petwatch.ui.map.MapFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpeedFragment extends Fragment
{

    private SpeedViewModel speedViewModel;

    // Access RealTime database
    private FirebaseDatabase database;

    // Get a reference from the database
    private DatabaseReference myRef;

    // Arduino id
    private String ardID;
    // used as a variable to obtain the Arduino id

    private String latString;
    private String lngString;

    private Double lat;
    private Double lng;

    private Date time1;
    private Date time2;

    private Double speed;

    private String refFromUrl;// = "https://petwatch-519c6.firebaseio.com/";

    private  String collectionPath;

    private String arduinoIDKey;
    private int earthRadius = 6373; //in km

    //create List
    private String[] latLng = new String[4];

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        setCollectionPath("userDetails");
        setRefFromUrl("https://petwatch-519c6.firebaseio.com/");
        View root = inflater.inflate(R.layout.fragment_speed, container, false);

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
                                        Log.d(TAG, "data is " + dataSnapshot.getChildren().iterator().next());
                                        time1 = Calendar.getInstance().getTime();
                                        Log.d(TAG, "the time is " + time1);

                                        for (DataSnapshot data : dataSnapshot.getChildren())
                                        {
                                            if (data.getKey().equals("lat"))
                                            {
                                                latString = (String) data.getValue();
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
                                                lngString = (String) data.getValue();
                                                lng = Double.parseDouble(lngString);
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
                                                distance(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));
                                                calculateSpeed(getLatLng(0), getLatLng(1), getLatLng(2), getLatLng(3));
                                                setOldTime(time1);
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

    return root;
    }

    public Double distance(String lat1, String lat2, String lng1, String lng2)
    {
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

    private void toastMessage(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

//    private Double[] convertToDMS(String co)
//    {
//        Log.d(TAG, "string is " + co);
//        Double degrees = Double.parseDouble(co.split(".")[0]);
//
//        Double minutes = Double.parseDouble("." + co.split(".")[1]) * 60;
//
//        Double seconds = Double.parseDouble(("." + minutes.toString().split(".")[1])) * 60;
//
//        Double[] answer = new Double[] {degrees, minutes, seconds};
//
//        return answer;
//    }



    private Double calculateSpeed(String lat1, String lat2, String lng1, String lng2)
    {
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
}