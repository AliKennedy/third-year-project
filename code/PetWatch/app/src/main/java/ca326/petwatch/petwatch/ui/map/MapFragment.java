package ca326.petwatch.petwatch.ui.map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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


import java.util.Map;

import ca326.petwatch.petwatch.Main2Activity;
import ca326.petwatch.petwatch.R.*;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapFragment extends Fragment
{

    // Google map variable
    private GoogleMap googleMap;

    // Map view variable
    private MapView mMapView;

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

    // A map object created to access the data retrieved

    private String refFromUrl;// = "https://petwatch-519c6.firebaseio.com/";

    private  String collectionPath;

    private String arduinoIDKey;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState)
    {
        setCollectionPath("userDetails");
        setRefFromUrl("https://petwatch-519c6.firebaseio.com/");
        final View root = inflater.inflate(layout.fragment_map, container, false);

        // Creates a map view
        mMapView = root.findViewById(id.map_view);
        mMapView.onCreate(savedInstanceState);

        // needed to get the map to display immediately
        mMapView.onResume();

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

                                        for (DataSnapshot data : dataSnapshot.getChildren())
                                        {
                                            if (data.getKey().equals("lat"))
                                            {
                                                latString = (String) data.getValue();
                                                lat = Double.parseDouble(latString);
                                                Log.d(TAG, "lat is " + lat);
                                            }
                                            else if (data.getKey().equals("lng"))
                                            {
                                                lngString = (String) data.getValue();
                                                lng = Double.parseDouble(lngString);
                                                //gpsData.put("lng", data.getValue());
                                                Log.d(TAG, "lng is " + lng);
                                            }
                                            else
                                                {
                                                //Do Nothing for now
                                                //Intentions to add speed here
                                            }
                                        }


                                        createMap(lat, lng);
                                    }
                                    else
                                    {
                                        createmapNoCoordinates();
                                    }

                                }
                                // If the data does not exist the method goes here
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError)
                                {
                                    createmapNoCoordinates();
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


    private void createMap(final Double latitude, final Double longitude)
    {
        String sLat = latitude.toString();
        String sLng = longitude.toString();

        //send lat and lng to to main activity
        Intent intent = new Intent(getActivity().getBaseContext(), Main2Activity.class);
        intent.putExtra("latitude", sLat);
        intent.putExtra("longitude", sLng);
        //getActivity().startActivity(intent);

        // In a try as it can throw a NullPointerException
        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());

            // Displays Google Map in fragment
            mMapView.getMapAsync(new OnMapReadyCallback()
            {
                @Override
                public void onMapReady(GoogleMap mMap)
                {
                    googleMap = mMap;
                    googleMap.clear();

                    // For showing a move to my location button
                    //googleMap.setMyLocationEnabled(true);
                    //call function for Arduino

                    // For dropping a marker at a point on the Map
                    LatLng gpsTracker = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(gpsTracker).title("Tracker"));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(gpsTracker).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void createmapNoCoordinates()
    {
        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());

            // Displays Google Map in fragment
            mMapView.getMapAsync(new OnMapReadyCallback()
            {
                @Override
                public void onMapReady(GoogleMap mMap)
                {
                    googleMap = mMap;
                    googleMap.clear();

                    // For showing a move to my location button
                    //googleMap.setMyLocationEnabled(true);
                    //call function for Arduino

                    // For dropping a marker at a point on the Map
                    LatLng gpsTracker = new LatLng(53.3531, -6.2580);
                    googleMap.addMarker(new MarkerOptions().position(gpsTracker).title("Tracker"));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(gpsTracker).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void toastMessage(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private String getCollectionPath()
    {
        return collectionPath;
    }

    private void setCollectionPath(String collectionPath)
    {
        this.collectionPath = collectionPath;
    }

    private void setRefFromUrl(String refFromUrl)
    {
        this.refFromUrl = refFromUrl;
    }

    private String getRefFromUrl()
    {
        return this.refFromUrl;
    }

    private String getArduinoIDKey()
    {
        return arduinoIDKey;
    }

    private void setArduinoIDKey(String arduinoIDKey)
    {
        this.arduinoIDKey = arduinoIDKey;
    }

    //used in distance function in Speed fragment
    public Double getLat()
    {
        return this.lat;
    }

    public Double getLng()
    {
        return this.lng;
    }
}