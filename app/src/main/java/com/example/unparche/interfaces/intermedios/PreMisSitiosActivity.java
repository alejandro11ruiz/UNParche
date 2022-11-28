package com.example.unparche.interfaces.intermedios;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.sitio.ListaMisSitiosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreMisSitiosActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;
    ArrayList<String> sitios;
    ArrayList<String> direccion;
    ArrayList<String> ciudad;
    ArrayList<String> keylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_mis_sitios);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String keys = user.getUid();

        sitios = new ArrayList<>();
        direccion = new ArrayList<>();
        ciudad = new ArrayList<>();
        keylist = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot value : data) {

                    String nom = (String) value.child("nombre").getValue();
                    String dir = (String) value.child("direccion").getValue();
                    String ciu = (String) value.child("ciudad").getValue();
                    String key = (String) value.getKey();
                    String idu = (String) value.child("id").getValue();

                    if (idu.equals(keys)){
                        sitios.add(nom);
                        direccion.add(dir);
                        ciudad.add(ciu);
                        keylist.add(key);}
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
                Intent intent = new Intent(PreMisSitiosActivity.this, ListaMisSitiosActivity.class);
                intent.putStringArrayListExtra("sitios", sitios);
                intent.putStringArrayListExtra("direcciones", direccion);
                intent.putStringArrayListExtra("ciudades", ciudad);
                intent.putStringArrayListExtra("keylist", keylist);
                startActivity(intent);
            }
        },2000);
    }
}