package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.unparche.R;
import com.example.unparche.interfaces.MenuPrincipalActivity;
import com.example.unparche.interfaces.intermedios.PreMisSitiosActivity;
import com.example.unparche.interfaces.intermedios.PreSitiosActivity;

public class SitiosActivity extends AppCompatActivity {

    Button btnMisSit, btnBusSit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios);

        btnMisSit=findViewById(R.id.btnMisSit);
        btnBusSit=findViewById(R.id.btnBusSit);

        btnMisSit.setOnClickListener(view -> {
            Intent intent = new Intent(SitiosActivity.this, PreMisSitiosActivity.class);
            startActivity(intent);
        });

        btnBusSit.setOnClickListener(view -> {
            Intent intent = new Intent(SitiosActivity.this, PreSitiosActivity.class);
            startActivity(intent);
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

}