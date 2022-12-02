package com.example.unparche.interfaces.intermedios;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.sitio.ListaMisSitiosActivity;
import com.example.unparche.interfaces.sitio.ListaSitiosActivity;
import com.example.unparche.interfaces.sitio.SitiosCercaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class PreSitiosActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Sitio sitio;
    String keys;
    Boolean fromCerca;
    FirebaseAuth firebaseAuth;
    ArrayList<String> sitios;
    ArrayList<String> direccion;
    ArrayList<String> ciudad;
    ArrayList<String> keylist;
    ArrayList<String> latitudes;
    ArrayList<String> longitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sitios);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        keys = user.getUid();

        sitios = new ArrayList<>();
        direccion = new ArrayList<>();
        ciudad = new ArrayList<>();
        keylist = new ArrayList<>();
        latitudes = new ArrayList<>();
        longitudes = new ArrayList<>();
        fromCerca = false;

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            fromCerca = bundle.getBoolean("CER");
        }

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot value : data) {
                    String nom = (String) value.child("nombre").getValue();
                    String dir = (String) value.child("direccion").getValue();
                    String ciu = (String) value.child("ciudad").getValue();
                    String lat = (String) value.child("coordenadaLat").getValue().toString();
                    String lon = (String) value.child("coordenadaLon").getValue().toString();
                    String key = (String) value.getKey();
                    sitios.add(nom);
                    direccion.add(dir);
                    ciudad.add(ciu);
                    keylist.add(key);
                    latitudes.add(lat);
                    longitudes.add(lon);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!fromCerca){
                    Intent intent = new Intent(PreSitiosActivity.this, ListaSitiosActivity.class);
                    intent.putStringArrayListExtra("sitios", sitios);
                    intent.putStringArrayListExtra("direcciones", direccion);
                    intent.putStringArrayListExtra("ciudades", ciudad);
                    intent.putStringArrayListExtra("keylist", keylist);
                    intent.putStringArrayListExtra("latitudes", latitudes);
                    intent.putStringArrayListExtra("longitudes", longitudes);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PreSitiosActivity.this, SitiosCercaActivity.class);
                    intent.putStringArrayListExtra("sitios", sitios);
                    intent.putStringArrayListExtra("direcciones", direccion);
                    intent.putStringArrayListExtra("latitudes", latitudes);
                    intent.putStringArrayListExtra("longitudes", longitudes);
                    startActivity(intent);
                }
            }
        },2000);
    }
}