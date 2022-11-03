package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.MainActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.UsuarioActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class PreUsuarioActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_usuario);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(bundle.getString("key")).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    usuario = snapshot.getValue(Usuario.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PreUsuarioActivity.this, UsuarioActivity.class);
                intent.putExtra("nick", usuario.getNick());
                intent.putExtra("nombre", usuario.getNombre());
                intent.putExtra("apellido", usuario.getApellido());
                intent.putExtra("edad", usuario.getEdad());
                intent.putExtra("descripcion", usuario.getDescripcion());
                intent.putStringArrayListExtra("amigos", usuario.getAmigos());
                intent.putStringArrayListExtra("actividades", usuario.getActividades());
                intent.putStringArrayListExtra("sitios", usuario.getSitios());
                intent.putStringArrayListExtra("eventos", usuario.getEventos());
                startActivity(intent);
            }
        },2000);
    }
}