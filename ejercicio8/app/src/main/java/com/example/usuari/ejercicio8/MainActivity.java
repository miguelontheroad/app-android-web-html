package com.example.usuari.ejercicio8;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void registrarTemperatura(View v){

        SharedPreferences sp= this.getSharedPreferences("temperaturas" , MODE_PRIVATE);

        //Creamos un objeto de tipo Editor para escribir en el sp
        SharedPreferences.Editor editor = sp.edit();

        EditText edMes = (EditText) this.findViewById(R.id.edtMes);
        EditText edTemp = (EditText) this.findViewById(R.id.edtTemperatura);
        String valorMes = edMes.getText().toString();
        int valorTemp = Integer.parseInt(edTemp.getText().toString());

        //insertamos valores
        editor.putInt(valorMes, valorTemp);

        //guardamos los valores
        editor.commit();

        //EDITOR.APPLY guarda de manera asincrona, en background

    }

    public void mostrarTemperaturas(View v){

        Intent intent = new Intent(this , Temperaturas.class);
        this.startActivity(intent);

    }

    public void limpiar(View v){
        SharedPreferences sp = this.getSharedPreferences("temperatures", MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();

        editor.clear(); //borra to do
        //editor.remove("enero") borra esa entrada con clave "enero"

        //guardamos los valores
        editor.commit();

    }





}
