package com.example.unparche.interfaces.actividad;

import androidx.appcompat.app.AppCompatActivity;
import com.example.unparche.R;
import com.example.unparche.entidades.Actividad;
import com.example.unparche.interfaces.intermedios.PreListaActividadesActivity;
import com.example.unparche.interfaces.login.MainActivity;
import com.example.unparche.interfaces.usuario.ListaAmigosActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActividadCrearActivity extends AppCompatActivity {
    
    EditText etNombre;
    Button btnCrear;
    ArrayList<String> actividadesActuales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crear);

        etNombre = findViewById(R.id.etNombre);
        btnCrear = findViewById(R.id.btnCrear);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            actividadesActuales = bundle.getStringArrayList("actividades");
        }

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nAct = etNombre.getText().toString();
                if(!nAct.equals("null")&&!nAct.equals("")) {
                    boolean exist = yaExiste(nAct, actividadesActuales);
                    if (exist) {
                        Toast.makeText(ActividadCrearActivity.this, "Esta actividad ya está registrada.", Toast.LENGTH_SHORT).show();
                    } else {
                        Actividad actividad = new Actividad(adaptarCadena(nAct));
                        FirebaseDatabase.getInstance().getReference(MainActivity.PATH_ACTIVIDADES).child(actividad.getNombre()).setValue(actividad);
                    }
                    startActivity(new Intent(ActividadCrearActivity.this, PreListaActividadesActivity.class));
                } else {
                    Toast.makeText(ActividadCrearActivity.this, "Ingrese una actividad valida.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean yaExiste(String nueva, ArrayList<String> acts){
        int tam = acts.size();
        for(int i = 0; i < tam; i++){
            if(acts.get(i).toLowerCase().equals(nueva.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private String adaptarCadena(String act){
        String aux = act.toLowerCase();
        String cap = aux.substring(0, 1).toUpperCase() + aux.substring(1);
        return cap;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreListaActividadesActivity.class);
        startActivity(intent);
    }

}