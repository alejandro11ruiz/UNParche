package com.example.unparche.interfaces.usuario;

import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.interfaces.actividad.ListaActividadesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UsuarioAmigoActivity extends AppCompatActivity {

    TextView tvEmail, tvNombre, tvApellido, tvEdad, tvDescripcion;
    Button btnActividades, btnSeguir;

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
            if(bundle.getBoolean("amic")){
                btnSeguir.setEnabled(true);
                btnSeguir.setVisibility(View.VISIBLE);
            }else{
                btnSeguir.setEnabled(false);
                btnSeguir.setVisibility(View.INVISIBLE);
            }
        }

        btnActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAmigoActivity.this, ListaActividadesActivity.class);
                intent.putStringArrayListExtra("actividades", getIntent().getExtras().getStringArrayList("actividades"));
                startActivity(intent);
            }
        });
    }
}