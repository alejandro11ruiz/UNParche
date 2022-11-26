package com.example.unparche.interfaces.intermedios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.UsuarioEditarActivity;
import com.example.unparche.interfaces.MenuPrincipalActivity;
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

public class PostLoginActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    FirebaseAuth firebaseAuth;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //String key = user.getEmail().replace(".",MainActivity.DOT_REPLACEMENT);
        String key = user.getUid();

        handler = new Handler();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(usuario.getNombre().equals("null")){
                    startActivity(new Intent(PostLoginActivity.this, UsuarioEditarActivity.class));
                } else {
                    startActivity(new Intent(PostLoginActivity.this, MenuPrincipalActivity.class));
                }
            }},2000);

    }
}