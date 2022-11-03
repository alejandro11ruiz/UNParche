package com.example.unparche.soloparatesteo;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unparche.MainActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Usuario;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CrearUsuarioActivity extends AppCompatActivity {

    EditText nick, nombre, apellido, edad, descripcion;
    Button guardar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        nick=findViewById(R.id.etNick);
        nombre=findViewById(R.id.etNombre);
        apellido=findViewById(R.id.etApellido);
        edad=findViewById(R.id.etEdad);
        descripcion=findViewById(R.id.etDescripcion);

        guardar=findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nick.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty()){
                    usuario=new Usuario(nick.getText().toString(),nombre.getText().toString(),apellido.getText().toString(),edad.getText().toString(),descripcion.getText().toString());
                    FirebaseDatabase.getInstance().getReference(MainActivity.PATH_USUARIOS).child(usuario.getNick()).setValue(usuario);
                }
            }
        });


    }
}