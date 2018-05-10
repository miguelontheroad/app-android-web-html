package com.example.usuari.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void altalibro(View v){
        Intent i = new Intent(this,AltaActivity.class);
        startActivity(i);
    }

    public void mostrarLibros(View v){
        Intent i = new Intent(this, ListaActivity.class);
        startActivity(i);
    }

}
