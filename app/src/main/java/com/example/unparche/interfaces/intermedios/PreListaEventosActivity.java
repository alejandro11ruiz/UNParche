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
import com.example.unparche.interfaces.evento.ListaEventosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.sitio.SitiosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreListaEventosActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;
    ArrayList<String> nombre;
    ArrayList<String> hora;
    ArrayList<String> keylist;
    boolean sitios = false;
    //boolean sitiosPos = false;
    String IDsit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_lista_eventos);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String keys = user.getUid();

        nombre = new ArrayList<>();
        hora = new ArrayList<>();
        keylist = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            sitios = bundle.getBoolean("Sitios");
            //sitiosPos = bundle.getBoolean("SitiosPos");
            IDsit = bundle.getString("IDsit");
        }

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_EVENTOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot value : data) {
                    String nom = (String) value.child("nombre").getValue();
                    String dir = (String) value.child("hora").getValue();
                    String key = (String) value.getKey();
                    String idu = (String) value.child("id").getValue();
                    String ids = (String) value.child("idsitio").getValue();

                    if (!SitiosActivity.Espect){
                        if (sitios){
                            if (ids.equals(IDsit)){
                                nombre.add(nom);
                                hora.add(dir);
                                keylist.add(key);}
                            }else{
                                if (idu.equals(keys)) {
                                    nombre.add(nom);
                                    hora.add(dir);
                                    keylist.add(key);}
                                }}else if (ids.equals(IDsit)){
                            nombre.add(nom);
                            hora.add(dir);
                            keylist.add(key);
                        }
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
                Intent intent = new Intent(PreListaEventosActivity.this, ListaEventosActivity.class);
                intent.putStringArrayListExtra("nombre", nombre);
                intent.putStringArrayListExtra("hora", hora);
                intent.putStringArrayListExtra("keylist", keylist);
                intent.putExtra("Sitios", sitios);
                //intent.putExtra("SitiosPos", sitiosPos);
                intent.putExtra("IDsit", IDsit);
                startActivity(intent);
            }
        },2000);
    }
}