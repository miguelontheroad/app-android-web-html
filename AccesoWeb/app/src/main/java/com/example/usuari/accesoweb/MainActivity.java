package com.example.usuari.accesoweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tvPagina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPagina = (TextView)this.findViewById(R.id.tvPagina);
    }


    //Metodo de respuesta del button
    public void cargar(View v){
        //iniciamos tarea asíncrona
        CommunicationTask ct = new CommunicationTask();
        //Ejecutamos la tarea pasandole nuestra URL
        ct.execute("https://www.google.es");
    }

    //param 1:String:lo que le pasamos al doInBackground
    //param 2:Void:
    //param 3:String:lo que nos devuelve la tarea, en este caso una web
    private class CommunicationTask extends AsyncTask<String, Void,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvPagina.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String resultado = "";
            try {
                //Creamos el objeto URL y su Conexion
                URL url = new URL(strings[0]);
                URLConnection conx = url.openConnection();
                //Recuperamos el contenido de la página
                String s;
                InputStream is = conx.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                while ((s=bf.readLine())!=null){
                    resultado+=s;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultado;
        }
    }




}
