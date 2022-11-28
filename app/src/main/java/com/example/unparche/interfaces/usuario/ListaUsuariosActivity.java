package com.example.unparche.interfaces.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaAmigosAdapter;
import com.example.unparche.interfaces.intermedios.PostBuscarUsuarioActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaUsuariosActivity extends AppCompatActivity {

    EditText txtBuscar;
    FloatingActionButton btnBuscar;
    RecyclerView listaUsuarios;
    ArrayList<String> usuarioKeys;
    ArrayList<String> usuarioNombres;
    ArrayList<String> usuarioApellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        txtBuscar = findViewById(R.id.txtBuscar);
        btnBuscar = findViewById(R.id.floatingActionButtonBuscar);
        listaUsuarios =findViewById(R.id.listaDeUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            usuarioKeys = bundle.getStringArrayList("amigosKeys");
            usuarioNombres = bundle.getStringArrayList("amigosNombres");
            usuarioApellidos = bundle.getStringArrayList("amigosApellidos");
            ListaAmigosAdapter adapter = new ListaAmigosAdapter(usuarioKeys, usuarioNombres, usuarioApellidos);
            listaUsuarios.setAdapter(adapter);
        }

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = txtBuscar.getText().toString().toLowerCase().trim();
                if(!aux.isEmpty()){
                    Intent intent = new Intent(ListaUsuariosActivity.this, PostBuscarUsuarioActivity.class);
                    intent.putExtra("texto", aux);
                    startActivity(intent);
                }else{
                    Toast.makeText(ListaUsuariosActivity.this, "Ingresar texto valido.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}