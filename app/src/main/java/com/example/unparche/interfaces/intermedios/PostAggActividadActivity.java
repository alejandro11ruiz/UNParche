package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.actividad.ListaActividadesActivity;
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
import android.widget.Toast;

import java.util.ArrayList;

public class PostAggActividadActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    FirebaseAuth firebaseAuth;
    ArrayList<String> actividades;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_agg_actividad);

        actividades = new ArrayList<>();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //String key = user.getEmail().replace(".",MainActivity.DOT_REPLACEMENT);
        key = user.getUid();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                String nAct = "null";
                if(bundle!=null){
                    nAct = bundle.getString("key");
                    boolean ext = yaExiste(nAct, usuario.getActividades());
                    if(!ext&&!nAct.equals("null")){
                        ArrayList<String> aux = usuario.getActividades();
                        aux.add(nAct);
                        usuario.setActividades(aux);
                        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).setValue(usuario);
                    }else if(ext){
                        Toast.makeText(PostAggActividadActivity.this, "Esta actividad ya es una de tus favoritas.", Toast.LENGTH_SHORT).show();
                    }
                }

                Intent intent = new Intent(PostAggActividadActivity.this, ListaActividadesActivity.class);
                intent.putStringArrayListExtra("actividades", usuario.getActividades());
                intent.putExtra("yo", true);
                intent.putExtra("key", key);
                startActivity(intent);
            }
        },2000);

    }

    private boolean yaExiste(String nueva, ArrayList<String> acts){
        int tam = acts.size();
        for(int i = 0; i < tam; i++){
            if(acts.get(i).equals(nueva)){
                return true;
            }
        }
        return false;
    }
}