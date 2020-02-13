package ca326.petwatch.petwatch.ui.map;

import android.os.Bundle;
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


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import ca326.petwatch.petwatch.R;

public class MapFragment extends Fragment
{

    private MapViewModel mapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        //inflate layout
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_map);
        mapViewModel.getText().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                textView.setText(s);

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_fragment);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
                mapFragment.getMapAsync(new OnMapReadyCallback()
                {
                    @Override
                    public void onMapReady(GoogleMap mMap)
                    {
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                        mMap.clear(); //clear old markers

                        CameraPosition googlePlex = CameraPosition.builder()
                                .target(new LatLng(37.4219999, -122.0862462))
                                .zoom(10)
                                .bearing(0)
                                .tilt(45)
                                .build();

                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);


                    }
                });
            }
        });
        return root;

    }
}


