package com.example.unparche.interfaces.intermedios;
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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class PreSitiosActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_sitios);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //String key = user.getEmail().replace(".",MainActivity.DOT_REPLACEMENT);
        String key = user.getUid();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sitio = snapshot.getValue(Sitio.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PreSitiosActivity.this, ListaMisSitiosActivity.class);
                intent.putExtra("nombre", sitio.getNombre());
                intent.putExtra("direccion", sitio.getDireccion());
                intent.putExtra("ciudad", sitio.getCiudad());
                intent.putExtra("latitud", sitio.getCoordenadaLat());
                intent.putExtra("longitud", sitio.getCoordenadaLon());
                intent.putStringArrayListExtra("actividades", sitio.getActividad());
                startActivity(intent);
            }
        },2000);
    }
}