package com.bsav157.infodominio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goConsultar(View view) {
        Intent in = new Intent(this, ConsultaActivity.class);
        startActivity(in);
    }

    public void goHistorial(View view) {
        Intent in = new Intent(this, HistorialActivity.class);
        startActivity(in);
    }
}
