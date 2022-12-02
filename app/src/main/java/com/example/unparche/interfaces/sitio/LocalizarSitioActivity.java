package com.example.unparche.interfaces.sitio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.unparche.R;
import com.example.unparche.databinding.ActivityLocalizarSitioBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class LocalizarSitioActivity extends FragmentActivity implements OnMapReadyCallback {

    Button btnOk;
    String IDS,nom,ciu,dir;
    Boolean fromEdit;
    Double latitud, longitud;
    Marker mm;

    private GoogleMap mMap;
    private ActivityLocalizarSitioBinding binding;
    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //btnOk = findViewById(R.id.btnOk);

        binding = ActivityLocalizarSitioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnOk = findViewById(R.id.btnOk);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            IDS = bundle.getString("ID");
            nom = bundle.getString("NOM");
            ciu = bundle.getString("CIU");
            dir = bundle.getString("DIR");
            latitud =bundle.getDouble("LAT");
            longitud =bundle.getDouble("LON");
            fromEdit = bundle.getBoolean("FE");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        client = LocationServices.getFusedLocationProviderClient(this);

        btnOk.setOnClickListener(view -> {
            if(fromEdit==null){
                fromEdit = false;
            }
            if(fromEdit){
                if((latitud != null && longitud != null)){
                    Intent intent = new Intent(LocalizarSitioActivity.this, EditarSitioActivity.class);
                    intent.putExtra("ID", IDS);
                    intent.putExtra("LAT", latitud);
                    intent.putExtra("LON", longitud);
                    startActivity(intent);
                }
            }else{
                if((latitud != null && longitud != null)){
                    Intent intent = new Intent(LocalizarSitioActivity.this, NuevoSitioActivity.class);
                    intent.putExtra("NOM",nom);
                    intent.putExtra("CIU",ciu);
                    intent.putExtra("DIR",dir);
                    intent.putExtra("LAT", latitud);
                    intent.putExtra("LON", longitud);
                    startActivity(intent);
                }
            }
        });
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
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Tu ubicación");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (fromEdit) {
            LatLng myUbi = new LatLng(latitud, longitud);
            mm = mMap.addMarker(new MarkerOptions().position(myUbi).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myUbi, 12));
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }else{
                ActivityCompat.requestPermissions(LocalizarSitioActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            }
        }
        mMap.setOnMapClickListener( new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng latlng) {
                if (mm != null){
                    mm.remove();
                }
                latitud = latlng.latitude;
                longitud = latlng.longitude;
                mm = mMap.addMarker(new MarkerOptions().position(latlng).title("Marker"));
            }
        });
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