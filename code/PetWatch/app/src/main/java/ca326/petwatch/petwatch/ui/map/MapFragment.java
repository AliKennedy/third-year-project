package ca326.petwatch.petwatch.ui.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

import ca326.petwatch.petwatch.R.*;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapFragment extends Fragment
{
    // Google map variable
    private GoogleMap googleMap;

    // Map view variable
    MapView mMapView;

    // Access RealTime database
    private FirebaseDatabase database;

    // Get a reference from the database
    private DatabaseReference myRef;

    // Authentication of user to ensure correct Arduino location is being displayed
    private FirebaseAuth firebaseAuth;

    // Users unique id to get access to Arduino id
    private String uid;
    // Arduino id
    private String ardID;
    // used as a variable to obtain the Arduino id

    private Double lat;
    private Double lng;

    // Access to the cloud firestore database to obtain Arduino id
    private FirebaseFirestore firebaseFirestore;
    // document reference for the cloud firestore database
    private DocumentReference docRef;

    // A map object created to access the data retrieved

    private String refFromUrl = "https://petwatch-519c6.firebaseio.com/";



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState)
    {
        final View root = inflater.inflate(layout.fragment_map, container, false);

        // Creates a map view
        mMapView = root.findViewById(id.map_view);
        mMapView.onCreate(savedInstanceState);

        // needed to get the map to display immediately
        mMapView.onResume();

        // Creates a user object
        FirebaseUser user;
        // Gets an instance of the current user
        firebaseAuth = firebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        // Gets the unique I.D. of the current user
        uid = user.getUid();

        // Gets an instance of the Cloud Firestore Database
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Accesses the current users document
        docRef = firebaseFirestore.collection("userDetails").document(uid);


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
                            ardID = documentSnapshot.getString("ardID");

                            myRef = database.getReferenceFromUrl(refFromUrl + ardID); // URL plus Arduino id!

                            // A method that evaluates if there are any changes of the data in the database
                            Log.d(TAG, "url is " + refFromUrl + ardID);

                            myRef.addValueEventListener(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot)
                                {
                                    Log.d(TAG, "data is " + dataSnapshot.getChildren().iterator().next());

                                    for (DataSnapshot data : dataSnapshot.getChildren())
                                    {
                                        if (data.getKey().equals("lat"))
                                        {
                                            lat = (Double) data.getValue();
                                            Log.d(TAG, "lat is " + lat);
                                        }
                                        else
                                        {
                                            lng = (Double) data.getValue();
                                            //gpsData.put("lng", data.getValue());
                                            Log.d(TAG, "lng is " + lng);
                                        }
                                    }

                                    // In a try as it can throw a NullPointerException
                                    try
                                    {
                                        MapsInitializer.initialize(getActivity().getApplicationContext());
                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                    // Displays Google Map in fragment
                                    mMapView.getMapAsync(new OnMapReadyCallback()
                                    {
                                        @Override
                                        public void onMapReady(GoogleMap mMap)
                                        {
                                            googleMap = mMap;

                                            // For showing a move to my location button
                                            //googleMap.setMyLocationEnabled(true);
                                            //call function for Arduino

                                            // For dropping a marker at a point on the Map
                                            LatLng gpsTracker = new LatLng(lat, lng);
                                            googleMap.addMarker(new MarkerOptions().position(gpsTracker).title("Tracker"));

                                            // For zooming automatically to the location of the marker
                                            CameraPosition cameraPosition = new CameraPosition.Builder().target(gpsTracker).zoom(15).build();
                                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                        }
                                    });
                                }
                                // If the data does not exist the method goes here
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError)
                                {

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

                    }
                });
        return root;
    }
}