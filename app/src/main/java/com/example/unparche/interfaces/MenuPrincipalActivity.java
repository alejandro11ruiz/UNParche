package com.example.unparche.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.interfaces.intermedios.PreListaAmigosActivity;
import com.example.unparche.interfaces.intermedios.PreUsuarioActivity;
import com.example.unparche.interfaces.sitio.SitiosActivity;
import com.example.unparche.interfaces.usuario.ListaAmigosActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    Button buttonSitios, buttonPerfil, buttonEventos, buttonAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        buttonSitios = findViewById(R.id.buttonSitios);
        buttonPerfil = findViewById(R.id.buttonPerfil);
        buttonEventos = findViewById(R.id.buttonEventos);
        buttonAmigos = findViewById(R.id.buttonAmigos);

        buttonSitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, SitiosActivity.class);
                startActivity(intent);

            }
        });

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, PreUsuarioActivity.class));
            }
        });

        buttonEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipalActivity.this, PreListaAmigosActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

}