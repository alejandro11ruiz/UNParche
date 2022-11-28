package com.example.unparche.interfaces.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaActividadesAdapter;
import com.example.unparche.adaptadores.ListaAmigosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListaAmigosActivity extends AppCompatActivity {

    RecyclerView listaDeAmigos;
    ArrayList<String> amigosKeys;
    ArrayList<String> amigosNombres;
    ArrayList<String> amigosApellidos;
    FloatingActionButton btnAgAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amigos);

        btnAgAct = findViewById(R.id.floatingActionButtonAgregarAmigos);
        listaDeAmigos =findViewById(R.id.listaDeAmigos);
        listaDeAmigos.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            amigosKeys = bundle.getStringArrayList("amigosKeys");
            amigosNombres = bundle.getStringArrayList("amigosNombres");
            amigosApellidos = bundle.getStringArrayList("amigosApellidos");
        }

        ListaAmigosAdapter adapter = new ListaAmigosAdapter(amigosKeys, amigosNombres, amigosApellidos);
        listaDeAmigos.setAdapter(adapter);

        btnAgAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ListaAmigosActivity.this, PreListaActividadesActivity.class));
            }
        });
    }
}