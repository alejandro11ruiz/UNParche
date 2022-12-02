package com.example.unparche.interfaces.evento;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.R;
import com.example.unparche.entidades.Evento;
import com.example.unparche.interfaces.intermedios.PreListaEventosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditarEventoActivity extends AppCompatActivity {

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
            btnGuardar.setVisibility(View.VISIBLE);
            btnEditar.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_CLASS_TEXT);
            txtHora.setInputType(InputType.TYPE_CLASS_TEXT);
            txtSitio.setInputType(InputType.TYPE_NULL);
            txtActividad.setInputType(InputType.TYPE_CLASS_TEXT);
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

        btnGuardar.setOnClickListener(view -> {

            if(!txtNombre.getText().toString().isEmpty()) evento.setNombre(txtNombre.getText().toString());
            if(!txtHora.getText().toString().isEmpty()) evento.setHora(txtHora.getText().toString());
            if(!txtSitio.getText().toString().isEmpty()) evento.setSitio(txtSitio.getText().toString());
            if(!txtActividad.getText().toString().isEmpty()) evento.setActividad(txtActividad.getText().toString());
            evento.setID(key);
            evento.setIdsitio(IDsit);

            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_EVENTOS).child(IDE).setValue(evento);


            Intent intent = new Intent(EditarEventoActivity.this, VerEventosActivity.class);
            intent.putExtra("ID", IDE);
            intent.putExtra("IDsit", IDsit);
            startActivity(intent);
        });


    }

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(EditarEventoActivity.this, PreListaEventosActivity.class);
            intent.putExtra("Sitios", sitios);
            intent.putExtra("ID", IDE);
            intent.putExtra("IDsit", IDsit);
            startActivity(intent);
    }
}