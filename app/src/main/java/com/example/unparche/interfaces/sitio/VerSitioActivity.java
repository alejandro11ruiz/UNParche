package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.unparche.R;
import android.widget.Toast;

import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class VerSitioActivity extends AppCompatActivity {

    EditText txtNombre, txtDireccion, txtCiudad, txtCoorLat, txtCoorLon;
    Button btnGuardar, btnEditar, btnEliminar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sitio);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtWebpage);
        txtCiudad = findViewById(R.id.txtTelefono);
        txtCoorLat = findViewById(R.id.txtEmail);
        txtCoorLon = findViewById(R.id.txtProyser);
        btnGuardar = findViewById(R.id.btnCreate);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);



        sitio = new Sitio();


        if(sitio != null){
            txtNombre.setText(sitio.getNombre());
            txtDireccion.setText(sitio.getDireccion());
            txtCiudad.setText(sitio.getCiudad());
            txtCoorLat.setText(sitio.getCoordenadaLat());
            txtCoorLon.setText(sitio.getCoordenadaLon());

            btnGuardar.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtCiudad.setInputType(InputType.TYPE_NULL);
            txtCoorLat.setInputType(InputType.TYPE_NULL);
            txtCoorLon.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(VerSitioActivity.this, EditarSitioActivity.class);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerSitioActivity.this);
            //.setNegativeButton("No", new DialogInterface.OnClickListener() {
            builder.setMessage("Confirma para borrar este contacto").
                    setPositiveButton("Confirmar", (dialogInterface, i) -> {
                        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(key).removeValue();
                        startActivity(new Intent(this, ListaMisSitiosActivity.class));
                        Toast.makeText(this, "Contacto borrado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Atrás", (dialogInterface, i) -> {

                    }).show();
        });

    }
}