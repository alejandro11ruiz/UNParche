package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import com.example.unparche.R;
import com.example.unparche.adaptadores.ListaMisSitiosAdapter;
import com.example.unparche.entidades.Sitio;

import java.util.ArrayList;

public class ListaMisSitiosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaMisSitios;
    ArrayList<Sitio> listaArrayMisSitios;
    ListaMisSitiosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mis_sitios);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaMisSitios=findViewById(R.id.listaMisSitios);
        listaMisSitios.setLayoutManager(new LinearLayoutManager(this));

        listaArrayMisSitios = new ArrayList<>();


        //adapter = new ListaMisSitiosAdapter(); // aquí se agrega la lista del recycler
        //listaMisSitios.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mis_sitios, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevoSitio:
                nuevoRegistro();
                return true;
            case R.id.menuClasificacionMisSitios:
                showDialog(1);
            default:
                return super.onOptionsItemSelected(item);
        }
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
}