package com.example.unparche.interfaces.evento;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.unparche.R;
import com.example.unparche.entidades.Evento;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.intermedios.PreListaEventosActivity;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.sitio.LocalizarSitioActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NuevoEventoActivity extends AppCompatActivity {

    EditText  txtNombre, txtHora, txtSitio, txtActividad;
    Button btnCrear;
    Evento evento;
    Sitio sitio;
    String nom, hor, sit;
    String IDsit="";
    boolean sitios=false;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_evento);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String key = user.getUid();

        evento= new Evento();

        txtNombre=findViewById(R.id.txtNombre);
        txtHora=findViewById(R.id.txtHora);
        txtSitio=findViewById(R.id.txtSitio);
        txtActividad=findViewById(R.id.txtActividad);
        btnCrear=findViewById(R.id.btnCrearSitio);

        txtSitio.setInputType(InputType.TYPE_NULL);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            IDsit = bundle.getString("IDsit");
            sitios = bundle.getBoolean("Sitios");
        }


        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_SITIOS).child(IDsit).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sitio = snapshot.getValue(Sitio.class);
                txtSitio.setText(sitio.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnCrear.setOnClickListener(view -> {
            if(!txtNombre.getText().toString().isEmpty()) evento.setNombre(txtNombre.getText().toString());
            if(!txtHora.getText().toString().isEmpty()) evento.setHora(txtHora.getText().toString());
            evento.setSitio(txtSitio.getText().toString());
            evento.setID(key);
            evento.setIdsitio(IDsit);
            if(!txtActividad.getText().toString().isEmpty()) evento.setActividad(txtActividad.getText().toString());

            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_EVENTOS).push().setValue(evento);


            Intent intent = new Intent(NuevoEventoActivity.this, PreListaEventosActivity.class);
            intent.putExtra("Sitios", sitios);
            intent.putExtra("IDsit", IDsit);
            startActivity(intent);
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreListaEventosActivity.class);
        intent.putExtra("Sitios", sitios);
        intent.putExtra("IDsit", IDsit);
        startActivity(intent);
    }
}