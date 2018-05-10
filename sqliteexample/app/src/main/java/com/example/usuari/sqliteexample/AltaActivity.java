package com.example.usuari.sqliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AltaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
    }

    public void grabar(View v){
        String titulo = ((EditText) findViewById(R.id.edtTitulo)).getText().toString();
        String autor = ((EditText) findViewById(R.id.edtAutor)).getText().toString();
        String precio =((EditText)findViewById(R.id.edtPrecio)).getText().toString();
        Double dblPrecio = Double.parseDouble(precio);
        DBLibros adp = new DBLibros(this);
        adp.altaLibro(titulo, autor,dblPrecio);
        adp.close();
        this.finish();
    }




}
