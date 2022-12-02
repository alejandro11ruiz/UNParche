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

public class PostAggAmigoActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    FirebaseAuth firebaseAuth;
    ArrayList<String> amigos;
    String keyToAgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_agg_amigo);

        amigos = new ArrayList<>();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String key = user.getUid();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            keyToAgg = bundle.getString("key");
        }


        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
                if(usuario.getAmigos().isEmpty()){
                    amigos.add(keyToAgg);
                }else {
                    amigos = usuario.getAmigos();
                    boolean existe = yaAmigo(amigos, keyToAgg);
                    if(existe){
                        Toast.makeText(PostAggAmigoActivity.this, "Ya sigues a este usuario.", Toast.LENGTH_SHORT).show();
                    }else{
                        amigos.add(keyToAgg);
                    }
                }
                usuario.setAmigos(amigos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).setValue(usuario);

                Intent intent = new Intent(PostAggAmigoActivity.this, PreUsuarioAmigoActivity.class);
                intent.putExtra("key", keyToAgg);
                startActivity(intent);
            }
        },2000);

    }

    private boolean yaAmigo(ArrayList<String> amigos, String amigo){
        for(String val : amigos){
            if(val.equals(amigo)){
                return true;
            }
        }
        return false;
    }

}