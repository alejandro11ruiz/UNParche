package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.actividad.ListaActividadesTotalesActivity;
import com.example.unparche.interfaces.login.MainActivity;
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

public class PreListaActividadesActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    FirebaseAuth firebaseAuth;
    ArrayList<String> actividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_lista_actividades);

        actividades = new ArrayList<>();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //String key = user.getEmail().replace(".",MainActivity.DOT_REPLACEMENT);
        String key = user.getUid();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_ACTIVIDADES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot value : data) {
                    String val = (String) value.child("nombre").getValue();
                    actividades.add(val);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PreListaActividadesActivity.this, ListaActividadesTotalesActivity.class);
                /*intent.putExtra("email", usuario.getEmail());
                intent.putExtra("nombre", usuario.getNombre());
                intent.putExtra("apellido", usuario.getApellido());
                intent.putExtra("edad", usuario.getEdad());
                intent.putExtra("descripcion", usuario.getDescripcion());
                intent.putStringArrayListExtra("amigos", usuario.getAmigos());
                intent.putStringArrayListExtra("actividades", usuario.getActividades());
                intent.putStringArrayListExtra("sitios", usuario.getSitios());
                intent.putStringArrayListExtra("eventos", usuario.getEventos());*/
                intent.putStringArrayListExtra("actividadesTotales", actividades);
                startActivity(intent);
            }
        },3000);

    }

    /*private ArrayList<String> quitarElementosExistentes(ArrayList<String> exist, ArrayList<String> total){
        ArrayList<String> lista = new ArrayList<>();
        int tam1 = exist.size();
        int tam2 = total.size();
        for(int i=0; i<tam1; i++){
            String aux1 = exist.get(i);
            for(int j=0; j<tam2; j++){
                String aux2 = total.get(j);
                if()
            }
        }
        return lista;
    }*/
}