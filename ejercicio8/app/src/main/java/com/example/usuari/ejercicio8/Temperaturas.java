package com.example.usuari.ejercicio8;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class Temperaturas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperaturas);
        MostrarTemperaturas();
    }

    public void MostrarTemperaturas(){

        SharedPreferences sp = this.getSharedPreferences("temperaturas" , MODE_PRIVATE);

        //Crear una collection tipo Map

        Map<String, ?> todasTemp = sp.getAll();
        Collection<?> valores = todasTemp.values();

        //Creamos Array de enteros
        Integer [] arTemps;
        arTemps = valores.toArray(new Integer[0]);
        ListView lvTemperaturas = (ListView) this.findViewById(R.id.lvTemperaturas);


        ArrayAdapter<Integer> adp = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,arTemps);
        lvTemperaturas.setAdapter(adp);
    }

    public void Cerrar(View v){

        this.finish();
    }


}
