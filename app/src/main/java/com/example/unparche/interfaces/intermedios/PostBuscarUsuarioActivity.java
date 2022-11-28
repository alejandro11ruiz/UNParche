package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.usuario.ListaAmigosActivity;
import com.example.unparche.interfaces.usuario.ListaUsuariosActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostBuscarUsuarioActivity extends AppCompatActivity {

    ProgressBar progressBar;
    String texto;
    ArrayList<Usuario> usuarios;
    ArrayList<String> usuarioKeys;
    ArrayList<String> usuarioNombres;
    ArrayList<String> usuarioApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_buscar_usuario);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            texto = bundle.getString("texto");
        }

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuarioKeys = buscarKeys(snapshot, texto);
                usuarios = llenarUsers(snapshot, usuarioKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                usuarioNombres = llenarNombre(usuarios);
                usuarioApellidos = llenarApellido(usuarios);

                Intent intent = new Intent(PostBuscarUsuarioActivity.this, ListaUsuariosActivity.class);
                intent.putStringArrayListExtra("amigosKeys", usuarioKeys);
                intent.putStringArrayListExtra("amigosNombres", usuarioNombres);
                intent.putStringArrayListExtra("amigosApellidos", usuarioApellidos);
                startActivity(intent);
            }
        },2000);

    }

    private ArrayList<String> buscarKeys(DataSnapshot snapshot, String texto){
        ArrayList<String> aux = new ArrayList<>();
        Iterable<DataSnapshot> data = snapshot.getChildren();
        for (DataSnapshot value : data) {
            String nom = (String) value.child("nombre").getValue().toString().toLowerCase().trim();
            String ape = (String) value.child("apellido").getValue().toString().toLowerCase().trim();
            if (nom.equals(texto)||ape.equals(texto)) {
                aux.add(value.getKey());
            }
        }
        return aux;
    }

    private ArrayList<Usuario> llenarUsers(DataSnapshot snapshot, ArrayList<String> keys){
        ArrayList<Usuario> amics = new ArrayList<>();
        Iterable<DataSnapshot> data = snapshot.getChildren();
        for (DataSnapshot value : data) {
            String val = (String) value.getKey();
            for (String key : keys){
                if (val.equals(key)) {
                    amics.add(value.getValue(Usuario.class));
                }
            }
        }
        return amics;
    }

    private ArrayList<String> llenarNombre(ArrayList<Usuario> usuarios){
        ArrayList<String> aux = new ArrayList<>();
        for(Usuario usuario : usuarios){
            aux.add(usuario.getNombre());
        }
        return aux;
    }

    private ArrayList<String> llenarApellido(ArrayList<Usuario> usuarios){
        ArrayList<String> aux = new ArrayList<>();
        for(Usuario usuario : usuarios){
            aux.add(usuario.getApellido());
        }
        return aux;
    }

}