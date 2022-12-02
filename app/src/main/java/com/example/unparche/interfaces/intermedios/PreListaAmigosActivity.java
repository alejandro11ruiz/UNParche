package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.MenuPrincipalActivity;
import com.example.unparche.interfaces.actividad.ListaActividadesTotalesActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.usuario.ListaAmigosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class PreListaAmigosActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    ArrayList<Usuario> usuarios;
    ArrayList<String> amigosKeys;
    ArrayList<String> amigosNombres;
    ArrayList<String> amigosApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_lista_amigos);

        amigosNombres = new ArrayList<>();
        amigosApellidos = new ArrayList<>();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String key = user.getUid();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                amigosKeys = llenarKeys(snapshot, key);
                usuarios = llenarUsers(snapshot, amigosKeys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                amigosNombres = llenarNombre(usuarios);
                amigosApellidos = llenarApellido(usuarios);

                Intent intent = new Intent(PreListaAmigosActivity.this, ListaAmigosActivity.class);
                intent.putStringArrayListExtra("amigosKeys", amigosKeys);
                intent.putStringArrayListExtra("amigosNombres", amigosNombres);
                intent.putStringArrayListExtra("amigosApellidos", amigosApellidos);
                startActivity(intent);
            }
            },2000);

    }

    private ArrayList<String> llenarKeys(DataSnapshot snapshot, String key){
        ArrayList<String> aux;
        Iterable<DataSnapshot> data = snapshot.getChildren();
        for (DataSnapshot value : data) {
            String val = (String) value.getKey();
            if (val.equals(key)) {
                Usuario usuario = value.getValue(Usuario.class);
                return usuario.getAmigos();
            }
        }
        return new ArrayList<>();
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