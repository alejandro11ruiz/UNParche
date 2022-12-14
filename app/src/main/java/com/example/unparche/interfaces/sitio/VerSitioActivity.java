package com.example.unparche.interfaces.sitio;

import static android.os.Build.ID;

import androidx.annotation.NonNull;
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
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerSitioActivity extends AppCompatActivity {

    EditText txtNombre, txtDireccion, txtCiudad, txtCoorLat, txtCoorLon;
    Button btnGuardar, btnEditar, btnEliminar;
    Sitio sitio;
    FirebaseAuth firebaseAuth;
    String IDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sitio);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtCoorLat = findViewById(R.id.txtCoorLat);
        txtCoorLon = findViewById(R.id.txtCoorLon);
        btnGuardar = findViewById(R.id.btnCreate);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            IDS =bundle.getString("ID");
        }

        sitio = new Sitio();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(IDS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sitio = snapshot.getValue(Sitio.class);

                if(!sitio.getNombre().equals("null"))txtNombre.setText(sitio.getNombre());
                if(!sitio.getDireccion().equals("null"))txtDireccion.setText(sitio.getDireccion());
                if(!sitio.getCiudad().equals("null"))txtCiudad.setText(sitio.getCiudad());
                if(!sitio.getCoordenadaLat().equals("null"))txtCoorLat.setText(sitio.getCoordenadaLat());
                if(!sitio.getCoordenadaLon().equals("null"))txtCoorLon.setText(sitio.getCoordenadaLon());

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



        if(sitio != null){
            btnGuardar.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtCiudad.setInputType(InputType.TYPE_NULL);
            txtCoorLat.setInputType(InputType.TYPE_NULL);
            txtCoorLon.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(VerSitioActivity.this, EditarSitioActivity.class);
            intent.putExtra("ID", IDS);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerSitioActivity.this);
            builder.setMessage("Confirma para borrar este contacto").
                    setPositiveButton("Confirmar", (dialogInterface, i) -> {
                        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(IDS).removeValue();
                        startActivity(new Intent(this, PreMisSitiosActivity.class));
                        Toast.makeText(this, "Contacto borrado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Atr??s", (dialogInterface, i) -> {
                    }).show();
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreMisSitiosActivity.class);
        startActivity(intent);
    }
}