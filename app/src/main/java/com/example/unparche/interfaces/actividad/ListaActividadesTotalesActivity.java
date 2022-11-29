package com.example.unparche.interfaces.actividad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaActividadesAggAdapter;
import com.example.unparche.interfaces.intermedios.PostAggActividadActivity;
import com.example.unparche.interfaces.intermedios.PreListaActividadesActivity;
import com.example.unparche.interfaces.usuario.ListaAmigosActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListaActividadesTotalesActivity extends AppCompatActivity {

    RecyclerView listaDeActividades;
    ArrayList<String> listaActividades;
    FloatingActionButton btnAgAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividades_totales);

        btnAgAct = findViewById(R.id.floatingActionButtonAgregarActividad);
        listaDeActividades=findViewById(R.id.listaDeActividades);
        listaDeActividades.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            listaActividades=bundle.getStringArrayList("actividadesTotales");
        }

        ListaActividadesAggAdapter adapter = new ListaActividadesAggAdapter(listaActividades);
        listaDeActividades.setAdapter(adapter);

        btnAgAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActividadesTotalesActivity.this, ActividadCrearActivity.class);
                intent.putStringArrayListExtra("actividades", listaActividades);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PostAggActividadActivity.class);
        startActivity(intent);
    }

}