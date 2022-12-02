package com.example.unparche.interfaces.actividad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaActividadesAdapter;
import com.example.unparche.interfaces.intermedios.PostAggActividadActivity;
import com.example.unparche.interfaces.intermedios.PreListaActividadesActivity;
import com.example.unparche.interfaces.intermedios.PreUsuarioActivity;
import com.example.unparche.interfaces.intermedios.PreUsuarioAmigoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListaActividadesActivity extends AppCompatActivity {

    RecyclerView listaDeActividades;
    ArrayList<String> listaActividades;
    FloatingActionButton btnAgAct;
    boolean yo;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividades);

        btnAgAct = findViewById(R.id.floatingActionButtonAgregarActividad);
        listaDeActividades=findViewById(R.id.listaDeActividades);
        listaDeActividades.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            listaActividades = bundle.getStringArrayList("actividades");
            yo = bundle.getBoolean("yo");
            if(yo) {
                btnAgAct.setEnabled(true);
                btnAgAct.setVisibility(View.VISIBLE);
            }else {
                btnAgAct.setEnabled(false);
                btnAgAct.setVisibility(View.INVISIBLE);
            }
            key = bundle.getString("key");
        }

        ListaActividadesAdapter adapter = new ListaActividadesAdapter(listaActividades);
        listaDeActividades.setAdapter(adapter);

        btnAgAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaActividadesActivity.this, PreListaActividadesActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(yo){
            Intent intent = new Intent(this, PreUsuarioActivity.class);
            intent.putExtra("key", key);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, PreUsuarioAmigoActivity.class);
            intent.putExtra("key", key);
            startActivity(intent);
        }

    }

}