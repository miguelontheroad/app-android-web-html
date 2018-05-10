package com.example.usuari.accesojson;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


public class MainActivity extends AppCompatActivity {

        ListView lvDatos;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            lvDatos=(ListView)this.findViewById(R.id.lvDatos);
        }

        public void Mostrar(View v) {
            ComunicacionTask com = new ComunicacionTask();
            com.execute("\n" +
                    "http://datos.alcobendas.org/dataset/61a5001c-08d9-470d-a90b-4a31982c9c8c/" +
                    "resource/b48ed90d-6b86-4cce-8801-440fa08c6b09/download/" +
                    "poblacion-por-nacionalidades2017-2018.json");
        }


    public void MostrarEdades(View v) {
        Intent i = new Intent(this, AccesoEdades.class);
        this.startActivity(i);
    }


    private class ComunicacionTask extends AsyncTask<String, Void, String> {
                @Override
                protected String doInBackground(String... params) {
                    String cadenaJson = "";
                    try{
                        URL url=new URL(params[0]);
                        URLConnection con = url.openConnection();
                        //recuperacion de la respuesta JSON
                        String s;
                        InputStream is=con.getInputStream();

                        //utilizamos UTF-8 para que interprete
                        //correctamente las ñ y acentos

                        BufferedReader bf=new BufferedReader(
                                new InputStreamReader(is, Charset.forName("UTF-8"))
                        );

                        while((s=bf.readLine())!=null){
                            cadenaJson+=s;
                        }
                    }
                    catch(IOException ex){
                        ex.printStackTrace();
                    }
                    return cadenaJson;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    String[] datos = null;

                    try{
                        //creamos un array JSON a partir de la cadena recibida

                        JSONArray jarray = new JSONArray(s);
                        //creamos el array de String con el tamaño del array JSON

                        datos=new String[jarray.length()];
                        for(int i=0;i<jarray.length();i++){
                            JSONObject job=jarray.getJSONObject(i);
                            String nac = job.getString("Nacionalidad");
                            String cant = job.getString( "Número de empadronados");

                            datos[i]= "Nacionalidad : " +nac + " \n " +
                                    "Número de empadronados : "+cant + "\n";
                        }
                        cargarLista(datos);
                    }
                    catch(JSONException ex){
                        ex.printStackTrace();
                    }
                }


                private void cargarLista(String[] datos){
                        //creamos un arrayadapter con los datos del array
                        // y lo asignamos al ListView
                    ArrayAdapter<String> adp=new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            datos);
                    lvDatos.setAdapter(adp);
                }


    }



}
