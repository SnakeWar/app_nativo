package app.mayrcon.com.br.navigationview.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import app.mayrcon.com.br.navigationview.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("deprecation")
public class FixoFragment extends Fragment implements OnMapReadyCallback {


    public FixoFragment() {
        // Required empty public constructor
    }
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleMap mMap2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fixo, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync( this);
        if (mapFragment == null){
            FragmentManager fm =  getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map2, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

            /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


        return view;
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap2 = googleMap;
        float zoomLevel = 15.0f;
        mMap.setPadding(0,0,0,0);


        LatLng local = new LatLng(-5.9307943, -35.2944619);
        LatLng local2 = new LatLng(-5.9357568, -35.2984364);
        mMap.addMarker(new MarkerOptions().position(local).title(getString(R.string.local)));
        mMap2.addMarker(new MarkerOptions().position(local2).title(getString(R.string.local2)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local, zoomLevel));
        mMap2.moveCamera(CameraUpdateFactory.newLatLng(local2));
        mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(local2, zoomLevel));
        Circle circle = mMap.addCircle(new CircleOptions()
                        .center(local)
                        .radius(500)
                /*.strokeColor(Color.RED)
                .fillColor(Color.BLUE)*/);
        Circle circle2 = mMap2.addCircle(new CircleOptions()
                .center(local2)
                .radius(500)
                .strokeColor(Color.RED));
        //.fillColor(Color.BLUE));
    }
}