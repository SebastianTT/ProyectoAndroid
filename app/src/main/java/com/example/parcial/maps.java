package com.example.parcial;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat =0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        miUbicacion();

        mMap = googleMap;
        LatLng sydney = new LatLng(4.0685109, -76.1876254);
        LatLng univalle = new LatLng(4.0712032, -76.2054802);
        LatLng uceva = new LatLng(4.0632868, -76.2002699);
        mMap.addMarker(new MarkerOptions().position(sydney).title("ESBOL").icon(BitmapDescriptorFactory.fromResource(R.drawable.milocal)));
        mMap.addMarker(new MarkerOptions().position(univalle).title("UNIVALLE").icon(BitmapDescriptorFactory.fromResource(R.drawable.milocal)));
        mMap.addMarker(new MarkerOptions().position(uceva).title("UCEVA").icon(BitmapDescriptorFactory.fromResource(R.drawable.milocal)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.getUiSettings().setMyLocationButtonEnabled (true);


    }



    public void agregarmarcador (double lat, double lng){
        LatLng coordenadas = new LatLng(lat,lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
        if(marcador != null)marcador.remove();
        marcador=mMap.addMarker(new MarkerOptions().position(coordenadas).title("Posicion actual").icon(BitmapDescriptorFactory.fromResource(R.drawable.milocal)));
        mMap.animateCamera(miUbicacion);
    }

    public void  actualizarUbicacion(Location location){
        if(location!=null){
           lat =location.getLatitude();
           lng = location.getLongitude();
           agregarmarcador(lat,lng);
        }
    }

    LocationListener loclistener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private void  miUbicacion(){

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager  locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,loclistener);

    }
}
