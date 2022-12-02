package com.example.unparche.interfaces.sitio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.unparche.R;
import com.example.unparche.databinding.ActivitySitiosCercaBinding;
import com.example.unparche.interfaces.MenuPrincipalActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class SitiosCercaActivity extends FragmentActivity implements OnMapReadyCallback {

    ArrayList<String> sitios;
    ArrayList<String> direccion;
    ArrayList<String> latitudes;
    ArrayList<String> longitudes;
    Double radio = 1000.0, dlat, dlon, a, c, d;
    String marker;
    LatLng currentlatlng;

    private GoogleMap mMap;
    private ActivitySitiosCercaBinding binding;
    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySitiosCercaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            sitios=bundle.getStringArrayList("sitios");
            direccion=bundle.getStringArrayList("direcciones");
            latitudes=bundle.getStringArrayList("latitudes");
            longitudes=bundle.getStringArrayList("longitudes");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SitiosActivity.class);
        startActivity(intent);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //initialize task location
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                if(location != null){
                    //sync map
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            currentlatlng = latLng;
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Tu ubicación");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                            googleMap.addMarker(options);
                            for (int i=0; i < direccion.size(); i++){
                                dlat = Double.parseDouble(latitudes.get(i)) - currentlatlng.latitude;
                                dlon = Double.parseDouble(longitudes.get(i)) - currentlatlng.longitude;
                                a = Math.pow(Math.sin(dlat / 2),2) + Math.cos(currentlatlng.latitude) * Math.cos(Double.parseDouble(latitudes.get(i))) * Math.pow(Math.sin(dlon/2),2);
                                c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
                                d = 6371 * c;
                                if(d<=radio){
                                    LatLng latlng = new LatLng(Double.parseDouble(latitudes.get(i)),Double.parseDouble(longitudes.get(i)));
                                    marker = sitios.get(i)+ ": " + direccion.get(i);
                                    mMap.addMarker(new MarkerOptions().position(latlng).title(marker));
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        }else{
            ActivityCompat.requestPermissions(SitiosCercaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        /*for (int i=0; i < direccion.size(); i++){
            dlat = Double.parseDouble(latitudes.get(i)) - currentlatlng.latitude;
            dlon = Double.parseDouble(longitudes.get(i)) - currentlatlng.longitude;
            a = Math.pow(Math.sin(dlat / 2),2) + Math.cos(currentlatlng.latitude) * Math.cos(Double.parseDouble(latitudes.get(i))) * Math.pow(Math.sin(dlon/2),2);
            c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
            d = 6357 * c;
            if(d<=radio){
                LatLng latlng = new LatLng(Double.parseDouble(latitudes.get(i)),Double.parseDouble(longitudes.get(i)));
                mMap.addMarker(new MarkerOptions().position(latlng).title("Marker"));
            }
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}