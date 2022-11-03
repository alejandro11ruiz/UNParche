package com.example.unparche.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.interfaces.intermedios.PreUsuarioActivity;
import com.example.unparche.soloparatesteo.CrearUsuarioActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    Button buttonSitios, buttonPerfil, buttonEventos, buttonAmigos;
    Button buttonCrearUsuario, buttonCrearActividad, buttonCrearSitio, buttonCrearEvento;

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

            }
        });

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, PreUsuarioActivity.class);
                intent.putExtra("key", "todo");
                startActivity(intent);
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

            }
        });














        ///////////////////////////////////////////////////////////////
        ///////////////
        buttonCrearActividad = findViewById(R.id.buttonCrearAtividad);
        buttonCrearEvento = findViewById(R.id.buttonCrearEvento);
        buttonCrearSitio = findViewById(R.id.buttonCrearSitio);
        buttonCrearUsuario = findViewById(R.id.buttonCrearUsuario);
        /////////////////
        buttonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, CrearUsuarioActivity.class);
                startActivity(intent);
            }
        });


    }
}