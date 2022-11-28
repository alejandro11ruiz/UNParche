package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.intermedios.PreSitiosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class NuevoSitioActivity extends AppCompatActivity {

    EditText  txtNombre, txtCiudad, txtDireccion, txtCoorLat, txtCoorLon;
    Button btnCrear;
    Sitio sitio;

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
        txtCoorLat=findViewById(R.id.txtCoorLat);
        txtCoorLon=findViewById(R.id.txtCoorLon);
        btnCrear=findViewById(R.id.btnCrearSitio);


        btnCrear.setOnClickListener(view -> {

            if(!txtNombre.getText().toString().isEmpty()) sitio.setNombre(txtNombre.getText().toString());
            if(!txtCiudad.getText().toString().isEmpty()) sitio.setCiudad(txtCiudad.getText().toString());
            if(!txtDireccion.getText().toString().isEmpty()) sitio.setDireccion(txtDireccion.getText().toString());
            if(!txtCoorLat.getText().toString().isEmpty()) sitio.setCoordenadaLat(txtCoorLat.getText().toString());
            if(!txtCoorLon.getText().toString().isEmpty()) sitio.setCoordenadaLon(txtCoorLon.getText().toString());
            sitio.setID(key);

            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).push().setValue(sitio);


            Intent intent = new Intent(NuevoSitioActivity.this, PreMisSitiosActivity.class);


            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreMisSitiosActivity.class);
        startActivity(intent);
    }
}