package com.example.unparche.interfaces.evento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaEventosAdapter;
import com.example.unparche.interfaces.MenuPrincipalActivity;
import com.example.unparche.interfaces.sitio.SitiosActivity;
import com.example.unparche.interfaces.sitio.VerSitioActivity;
import com.example.unparche.interfaces.usuario.ListaUsuariosActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaEventosActivity extends AppCompatActivity {

    RecyclerView listaDeEventos;
    ArrayList<String> amigosKeys;
    ArrayList<String> eventosNombres;
    ArrayList<String> eventosHora;
    FloatingActionButton btnBsEvent;
    boolean sitios=false;
    String IDsit="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        btnBsEvent = findViewById(R.id.floatingActionButtonBuscarEvento);
        listaDeEventos =findViewById(R.id.listaDeEventos);
        listaDeEventos.setLayoutManager(new LinearLayoutManager(this));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            amigosKeys = bundle.getStringArrayList("keylist");
            eventosNombres = bundle.getStringArrayList("nombre");
            eventosHora = bundle.getStringArrayList("hora");
            sitios = bundle.getBoolean("Sitios");
            IDsit =bundle.getString("IDsit");
        }

        ListaEventosAdapter adapter = new ListaEventosAdapter(amigosKeys, eventosNombres, eventosHora, sitios,IDsit);
        listaDeEventos.setAdapter(adapter);

        if (SitiosActivity.Espect){
            btnBsEvent.setVisibility(View.INVISIBLE);
        } else if (sitios){
            btnBsEvent.setVisibility(View.VISIBLE);
        }else{
            btnBsEvent.setVisibility(View.INVISIBLE);
        }

        btnBsEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaEventosActivity.this, NuevoEventoActivity.class);
                intent.putExtra("IDsit", IDsit);
                intent.putExtra("Sitios", sitios);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (sitios){
            Intent intent = new Intent(this, VerSitioActivity.class);
            intent.putExtra("ID", IDsit);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        }
    }

}