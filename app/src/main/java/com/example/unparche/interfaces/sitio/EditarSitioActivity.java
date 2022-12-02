package com.example.unparche.interfaces.sitio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarSitioActivity extends AppCompatActivity {



    EditText txtNombre, txtDireccion, txtCiudad;
    TextView txtUbicacion;
    Button btnGuardar, btnEditar, btnEliminar, btnUbicar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;
    String IDS;
    Double coorLat, coorLon;
    ArrayList<String> actividades= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sitio);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtUbicacion = findViewById(R.id.tvUbi);

        btnUbicar = findViewById(R.id.btnLocalizar);
        btnGuardar = findViewById(R.id.btnCreate);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            IDS = bundle.getString("ID");
            coorLat = bundle.getDouble("LAT");
            coorLon = bundle.getDouble("LON");
        }

        sitio = new Sitio();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(IDS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sitio = snapshot.getValue(Sitio.class);

                if(!sitio.getNombre().equals("null"))txtNombre.setText(sitio.getNombre());
                if(!sitio.getDireccion().equals("null"))txtDireccion.setText(sitio.getDireccion());
                if(!sitio.getCiudad().equals("null"))txtCiudad.setText(sitio.getCiudad());
                if((!sitio.getCoordenadaLat().equals("null"))&&(!sitio.getCoordenadaLon().equals("null")))txtUbicacion.setText("El sitio tiene ubicación asignada");
                //if(!sitio.getCoordenadaLat().equals("null"))txtCoorLat.setText(sitio.getCoordenadaLat());
                //if(!sitio.getCoordenadaLon().equals("null"))txtCoorLon.setText(sitio.getCoordenadaLon());

                /*
                if(!sitio.getNombre().equals("null"))sitio.setNombre(txtNombre.getText().toString());
                if(!sitio.getDireccion().equals("null"))sitio.setCiudad(txtCiudad.getText().toString());
                if(!sitio.getCiudad().equals("null"))sitio.setDireccion(txtDireccion.getText().toString());
                if(!sitio.getCoordenadaLat().equals("null"))sitio.setCoordenadaLat(txtCoorLat.getText().toString());
                if(!sitio.getCoordenadaLon().equals("null"))sitio.setCoordenadaLon(txtCoorLon.getText().toString());
                 */

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        actividades.add("aalagrnadelepusecuca");
        actividades.add("aalagrnadelepusecuca");
        actividades.add("aalagrnadelepusecuca");
        actividades.add("aalagrnadelepusecuca");


        if(sitio != null){
            btnUbicar.setVisibility(View.VISIBLE);
            btnGuardar.setVisibility(View.VISIBLE);
            btnEditar.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.INVISIBLE);

            //txtNombre.setInputType(InputType.TYPE_NULL);
            //txtDireccion.setInputType(InputType.TYPE_NULL);
            //txtCiudad.setInputType(InputType.TYPE_NULL);
            //txtCoorLat.setInputType(InputType.TYPE_NULL);
            //txtCoorLon.setInputType(InputType.TYPE_NULL);
        }

        if(ContextCompat.checkSelfPermission(EditarSitioActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EditarSitioActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        btnGuardar.setOnClickListener(view -> {

            if(!txtNombre.getText().toString().isEmpty()) sitio.setNombre(txtNombre.getText().toString());
            if(!txtCiudad.getText().toString().isEmpty()) sitio.setCiudad(txtCiudad.getText().toString());
            if(!txtDireccion.getText().toString().isEmpty()) sitio.setDireccion(txtDireccion.getText().toString());
            if(coorLat != null) sitio.setCoordenadaLat(coorLat.toString());
            if(coorLon != null) sitio.setCoordenadaLon(coorLon.toString());
            sitio.setID(key);

            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(IDS).setValue(sitio);

            Intent intent = new Intent(EditarSitioActivity.this, VerSitioActivity.class);
            intent.putExtra("ID", IDS);
            startActivity(intent);
        });

        btnUbicar.setOnClickListener(view -> {
            Intent intent = new Intent(EditarSitioActivity.this, LocalizarSitioActivity.class);
            intent.putExtra("ID", IDS);
            intent.putExtra("LAT", coorLat);
            intent.putExtra("LON", coorLon);
            intent.putExtra("FE", true);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreMisSitiosActivity.class);
        startActivity(intent);
    }
}