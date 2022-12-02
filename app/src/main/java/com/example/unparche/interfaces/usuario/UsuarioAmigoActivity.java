package com.example.unparche.interfaces.usuario;

import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.interfaces.actividad.ListaActividadesActivity;
import com.example.unparche.interfaces.intermedios.PostAggAmigoActivity;
import com.example.unparche.interfaces.intermedios.PreListaAmigosActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsuarioAmigoActivity extends AppCompatActivity {

    TextView tvEmail, tvNombre, tvApellido, tvEdad, tvDescripcion;
    Button btnActividades, btnSeguir;
    boolean amic;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_amigo);

        tvEmail = findViewById(R.id.tvEmail);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvEdad = findViewById(R.id.tvEdad);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        btnActividades = findViewById(R.id.btnListaActividades);
        btnSeguir = findViewById(R.id.btnSeguir);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            if(!bundle.getString("email").equals("null"))tvEmail.setText(bundle.getString("email"));
            if(!bundle.getString("nombre").equals("null"))tvNombre.setText(bundle.getString("nombre"));
            if(!bundle.getString("apellido").equals("null"))tvApellido.setText(bundle.getString("apellido"));
            if(!bundle.getString("edad").equals("null"))tvEdad.setText(bundle.getString("edad"));
            if(!bundle.getString("descripcion").equals("null"))tvDescripcion.setText(bundle.getString("descripcion"));
            amic = bundle.getBoolean("amic");
            if(amic){
                btnSeguir.setEnabled(false);
                btnSeguir.setVisibility(View.INVISIBLE);
            }else{
                btnSeguir.setEnabled(true);
                btnSeguir.setVisibility(View.VISIBLE);
            }
            key = bundle.getString("key");
        }

        btnActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAmigoActivity.this, ListaActividadesActivity.class);
                intent.putStringArrayListExtra("actividades", getIntent().getExtras().getStringArrayList("actividades"));
                intent.putExtra("yo", bundle.getBoolean("yo"));
                intent.putExtra("key", bundle.getString("key"));
                startActivity(intent);
            }
        });

        btnSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAmigoActivity.this, PostAggAmigoActivity.class);
                intent.putExtra("key", key);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(amic) {
            Intent intent = new Intent(this, PreListaAmigosActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, ListaUsuariosActivity.class);
            startActivity(intent);
        }
    }

}