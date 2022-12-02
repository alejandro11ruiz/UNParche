package com.example.unparche.interfaces.evento;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.R;
import com.example.unparche.entidades.Evento;
import com.example.unparche.interfaces.intermedios.PreListaEventosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.sitio.SitiosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerEventosActivity extends AppCompatActivity {

    EditText txtNombre, txtHora, txtSitio, txtActividad;
    Button btnGuardar, btnEditar, btnEliminar;
    Evento evento;
    FirebaseAuth firebaseAuth;
    String IDE;
    boolean sitios = false;
    String IDsit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_evento);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        txtNombre = findViewById(R.id.txtNombreE);
        txtHora = findViewById(R.id.txtHoraE);
        txtSitio = findViewById(R.id.txtSitioE);
        txtActividad = findViewById(R.id.txtActividadE);
        btnGuardar = findViewById(R.id.btnCreate);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            IDE =bundle.getString("ID");
            sitios =bundle.getBoolean("Sitios");
            IDsit =bundle.getString("IDsit");
        }

        evento = new Evento();

        if(evento != null){
            if (sitios){
                btnEditar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
            }
            btnGuardar.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtHora.setInputType(InputType.TYPE_NULL);
            txtSitio.setInputType(InputType.TYPE_NULL);
            txtActividad.setInputType(InputType.TYPE_NULL);
        }

        if (SitiosActivity.Espect){
            btnEditar.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.INVISIBLE);
        } else{
            btnEditar.setVisibility(View.VISIBLE);
            btnEliminar.setVisibility(View.VISIBLE);
        }


        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_EVENTOS).child(IDE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                evento = snapshot.getValue(Evento.class);
                if(!evento.getNombre().equals("null"))txtNombre.setText(evento.getNombre());
                if(!evento.getHora().equals("null"))txtHora.setText(evento.getHora());
                if(!evento.getSitio().equals("null"))txtSitio.setText(evento.getSitio());
                if(!evento.getActividad().equals("null"))txtActividad.setText(evento.getActividad());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });





        btnEditar.setOnClickListener(view -> {
            Intent intent = new Intent(VerEventosActivity.this, EditarEventoActivity.class);
            intent.putExtra("ID", IDE);
            intent.putExtra("Sitios", sitios);
            intent.putExtra("IDsit", IDsit);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(VerEventosActivity.this);
            builder.setMessage("Confirma para borrar este contacto").
                    setPositiveButton("Confirmar", (dialogInterface, i) -> {
                        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_EVENTOS).child(IDE).removeValue();
                        Intent intent = new Intent(VerEventosActivity.this, PreListaEventosActivity.class);
                        intent.putExtra("Sitios", sitios);
                        intent.putExtra("IDsit", IDsit);
                        startActivity(intent);
                        Toast.makeText(this, "Evento borrado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Atrás", (dialogInterface, i) -> {
                    }).show();
        });

    }

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(VerEventosActivity.this, PreListaEventosActivity.class);
            intent.putExtra("Sitios", sitios);
        intent.putExtra("IDsit", IDsit);
            startActivity(intent);
    }
}