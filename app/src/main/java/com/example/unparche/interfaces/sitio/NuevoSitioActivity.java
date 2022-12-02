package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.intermedios.PreSitiosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class NuevoSitioActivity extends AppCompatActivity {

    EditText  txtNombre, txtCiudad, txtDireccion;
    TextView txtUbicacion;
    Button btnCrear, btnUbicar;
    Sitio sitio;
    String nom, ciu, dir;
    Double latitud, longitud;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sitio);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        sitio= new Sitio();

        txtNombre=findViewById(R.id.txtNombre);
        txtCiudad=findViewById(R.id.txtCiudad);
        txtDireccion=findViewById(R.id.txtDireccion);
        txtUbicacion=findViewById(R.id.tvU);
        btnCrear=findViewById(R.id.btnCrearSitio);
        btnUbicar=findViewById(R.id.btnLocalizar);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            nom = bundle.getString("NOM");
            ciu = bundle.getString("CIU");
            dir = bundle.getString("DIR");
            latitud =bundle.getDouble("LAT");
            longitud =bundle.getDouble("LON");
        }

        if(nom!=null){
            txtNombre.setText(nom);
        }
        if(ciu!=null){
            txtCiudad.setText(ciu);
        }
        if(dir!=null){
            txtDireccion.setText(dir);
        }

        if((latitud!=null)&&(longitud!=null)) txtUbicacion.setText("El sitio tiene ubicación asignada");

        if(ContextCompat.checkSelfPermission(NuevoSitioActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(NuevoSitioActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        btnCrear.setOnClickListener(view -> {

            if(!txtNombre.getText().toString().isEmpty()) sitio.setNombre(txtNombre.getText().toString());
            if(!txtCiudad.getText().toString().isEmpty()) sitio.setCiudad(txtCiudad.getText().toString());
            if(!txtDireccion.getText().toString().isEmpty()) sitio.setDireccion(txtDireccion.getText().toString());
            if(latitud!=null) sitio.setCoordenadaLat(latitud.toString());
            if(longitud!=null) sitio.setCoordenadaLon(longitud.toString());
            sitio.setID(key);

            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).push().setValue(sitio);


            Intent intent = new Intent(NuevoSitioActivity.this, PreMisSitiosActivity.class);
            startActivity(intent);
        });

        btnUbicar.setOnClickListener(view -> {
            Intent intent = new Intent(NuevoSitioActivity.this, LocalizarSitioActivity.class);
            intent.putExtra("NOM",txtNombre.getText().toString());
            intent.putExtra("CIU",txtCiudad.getText().toString());
            intent.putExtra("DIR",txtDireccion.getText().toString());
            intent.putExtra("FE", false);
            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreMisSitiosActivity.class);
        startActivity(intent);
    }
}