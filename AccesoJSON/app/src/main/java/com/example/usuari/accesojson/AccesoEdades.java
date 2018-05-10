package com.example.usuari.accesojson;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;

public class AccesoEdades extends AppCompatActivity {

        ListView lvDatosEdades;

         public ArrayList<String> misdatos = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_acceso_edades);
            lvDatosEdades=(ListView)this.findViewById(R.id.lvDatosEdades);
        }

        public void Mostrar(View v) {
            com.example.usuari.accesojson.AccesoEdades.ComunicacionTask com = new com.example.usuari.accesojson.AccesoEdades.ComunicacionTask();
            com.execute("http://datos.alcobendas.org/dataset/" +
                    "de24ee8b-9215-4ffd-8aa1-3ad40ce8559f/resource/d06a74c0-6b24-4490-a6ec-0c6083d39d64/" +
                    "download/poblacion-actual-por-distritos-grupos-de-edad-y-sexopara-tableau.json");
        }

    public void Volver(View v) {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }



    private class ComunicacionTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String cadenaJsonE = "";
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
                        cadenaJsonE+=s;
                    }
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
                return cadenaJsonE;
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
                        String intervalo = job.getString("Intervalo de edad");
                        String hombres = job.getString( "Hombres");
                        String mujeres = job.getString( "Mujeres");

                        int hom = Integer.parseInt(hombres);
                        int muj = Integer.parseInt(mujeres);

                        int personas = hom + muj;
                        misdatos.add(intervalo);

                        datos[i]= "Intervalo de edad: " + intervalo + "\n" +
                                "Total de personas: " + personas;
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
                        com.example.usuari.accesojson.AccesoEdades.this,
                        android.R.layout.simple_list_item_1,
                        datos);
                lvDatosEdades.setAdapter(adp);

                lvDatosEdades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                        long id) {
                        Toast.makeText(AccesoEdades.this, misdatos.get(position), Toast.LENGTH_LONG).show();
                    }
                });


            }


        }


}

