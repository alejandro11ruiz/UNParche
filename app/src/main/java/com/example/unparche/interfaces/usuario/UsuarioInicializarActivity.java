package com.example.unparche.interfaces.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UsuarioInicializarActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etEdad, etDescripcion;
    TextView tvEmail;
    Button btnGuardar;

    Usuario usuario;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_inicializar);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //String key = user.getEmail().replace(".", MainActivity.DOT_REPLACEMENT);
        String key = user.getUid();

        etNombre = findViewById(R.id.tvNombre);
        etApellido = findViewById(R.id.tvApellido);
        etEdad = findViewById(R.id.tvEdad);
        etDescripcion = findViewById(R.id.tvDescripcion);
        tvEmail = findViewById(R.id.tvEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
                if(!usuario.getNombre().equals("null"))etNombre.setText(usuario.getNombre());
                if(!usuario.getApellido().equals("null"))etApellido.setText(usuario.getApellido());
                if(!usuario.getEdad().equals("null"))etEdad.setText(usuario.getEdad());
                if(!usuario.getDescripcion().equals("null"))etDescripcion.setText(usuario.getDescripcion());
                if(!usuario.getEmail().equals("null"))tvEmail.setText(usuario.getEmail());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNombre.getText().toString().isEmpty()) usuario.setNombre(etNombre.getText().toString());
                if(!etApellido.getText().toString().isEmpty()) usuario.setApellido(etApellido.getText().toString());
                if(!etEdad.getText().toString().isEmpty()) usuario.setEdad(etEdad.getText().toString());
                if(!etDescripcion.getText().toString().isEmpty()) usuario.setDescripcion(etDescripcion.getText().toString());

                FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(key).setValue(usuario);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!usuario.getNombre().equals("null")){
                            startActivity(new Intent(UsuarioInicializarActivity.this, MenuPrincipalActivity.class));
                        }
                    }
                },1000);
            }
        });
    }
}