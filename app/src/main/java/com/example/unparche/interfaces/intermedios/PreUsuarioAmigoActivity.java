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
import com.example.unparche.interfaces.usuario.UsuarioActivity;
import com.example.unparche.interfaces.usuario.UsuarioAmigoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreUsuarioAmigoActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Usuario usuario;
    Usuario myUsuario;
    String key;
    String myKey;
    boolean amic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_usuario_amigo);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            key=bundle.getString("key");
        }

        myKey = user.getUid();

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(myKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myUsuario = snapshot.getValue(Usuario.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                amic = esAmic(myUsuario.getAmigos(),key);

                Intent intent = new Intent(PreUsuarioAmigoActivity.this, UsuarioAmigoActivity.class);
                intent.putExtra("email", usuario.getEmail());
                intent.putExtra("nombre", usuario.getNombre());
                intent.putExtra("apellido", usuario.getApellido());
                intent.putExtra("edad", usuario.getEdad());
                intent.putExtra("descripcion", usuario.getDescripcion());
                //intent.putStringArrayListExtra("amigos", usuario.getAmigos());
                intent.putStringArrayListExtra("actividades", usuario.getActividades());
                intent.putExtra("amic", amic);
                //intent.putStringArrayListExtra("sitios", usuario.getSitios());
                //intent.putStringArrayListExtra("eventos", usuario.getEventos());
                startActivity(intent);
            }
        },2000);
    }

    private boolean esAmic(ArrayList<String> amigos, String key){
        for(String val : amigos){
            if(key.equals(val)){
                return true;
            }
        }
        return false;
    }

}