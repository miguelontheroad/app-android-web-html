package com.example.usuari.gestionpedidos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLista = (ListView)this.findViewById(R.id.lvLista);

    }


    public void Enviar (View v){
        EditText edtProducto = (EditText)this.findViewById(R.id.edtProducto);
        EditText edtUnidades = (EditText)this.findViewById(R.id.edtUnidades);
        String producto = String.valueOf(edtProducto.getText());
        String unidades = edtUnidades.getText().toString();
        String registro = producto + " - " + unidades;
        CommunicationTask ctask = new CommunicationTask();
        ctask.execute("10.0.2.2", "9005", registro);
    }

    private class CommunicationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            BufferedReader bf = null;
            Socket sc = null;
            String resultado = "";

            try {
                sc = new Socket(strings[0], Integer.parseInt(strings[1]));
                PrintStream ps = new PrintStream(sc.getOutputStream());
                ps.println(strings[2]);
                ps.flush();
                InputStream is = sc.getInputStream();
                bf = new BufferedReader(new InputStreamReader(is));
                resultado = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (bf != null) {
                    try {
                        bf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return resultado;
        }


        /*@Override
        protected void onPostExecute(String s) {
            String[] tabla = null;

            JSONArray jarray = null;
            try {
                jarray = new JSONArray(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tabla = new String[jarray.length()];
            for (int i = 0; i < jarray.length(); i++) {
                try {
                    tabla[i] = jarray.getString(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, tabla);
            lvLista.setAdapter(adp);
        }*/


    }
}
