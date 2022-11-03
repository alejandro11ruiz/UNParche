package com.example.unparche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.unparche.entidades.Usuario;
import com.example.unparche.interfaces.MenuPrincipalActivity;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final String PATH_USUARIOS = "Usuarios";
    public static final String PATH_SITIOS = "Sitios";
    public static final String PATH_EVENTOS = "Eventos";
    public static final String PATH_ACTIVIDADES = "Actividades";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= new Intent(MainActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

}