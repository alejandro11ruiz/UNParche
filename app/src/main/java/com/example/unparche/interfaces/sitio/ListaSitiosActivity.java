package com.example.unparche.interfaces.sitio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaMisSitiosAdapter;

import java.util.ArrayList;

public class ListaSitiosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaMisSitios;
    ArrayList<String> listaArrayMisSitios;
    ListaMisSitiosAdapter adapter;


    ArrayList<String> direccion;
    ArrayList<String> ciudad;
    ArrayList<String> keylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mis_sitios);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaMisSitios=findViewById(R.id.listaMisSitios);
        listaMisSitios.setLayoutManager(new LinearLayoutManager(this));

        listaArrayMisSitios = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            listaArrayMisSitios=bundle.getStringArrayList("sitios");
            direccion=bundle.getStringArrayList("direcciones");
            ciudad=bundle.getStringArrayList("ciudades");
            keylist=bundle.getStringArrayList("keylist");
        }

        adapter = new ListaMisSitiosAdapter(listaArrayMisSitios,direccion,ciudad,keylist,true); // aquí se agrega la lista del recycler
        listaMisSitios.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

    }

    private void nuevoRegistro(){
        startActivity(new Intent(this, NuevoSitioActivity.class));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SitiosActivity.class);
        startActivity(intent);
    }

}