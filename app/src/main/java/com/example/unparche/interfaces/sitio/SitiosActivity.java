package com.example.unparche.interfaces.sitio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.unparche.R;

public class SitiosActivity extends AppCompatActivity {

    Button btnMisSit, btnBusSit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios);

        btnMisSit=findViewById(R.id.btnMisSit);
        btnBusSit=findViewById(R.id.btnBusSit);

        btnMisSit.setOnClickListener(view -> {
            Intent intent = new Intent(SitiosActivity.this, ListaMisSitiosActivity.class);
            startActivity(intent);
        });

        btnBusSit.setOnClickListener(view -> {
            Intent intent = new Intent(SitiosActivity.this, ListaMisSitiosActivity.class);
            startActivity(intent);
        });

    }
}