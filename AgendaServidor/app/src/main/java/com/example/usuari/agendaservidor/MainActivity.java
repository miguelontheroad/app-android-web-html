package com.example.usuari.agendaservidor;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDatos = (ListView)this.findViewById(R.id.lvDatos);
    }


    public void Enviar (View v){
        EditText edtNombre = (EditText)this.findViewById(R.id.edtNombre);
        String nombre = String.valueOf(edtNombre.getText());
        CommunicationTask ctask = new CommunicationTask();
        ctask.execute("10.0.2.2", "9000", nombre);
    }

    private class CommunicationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            BufferedReader bf = null;
            Socket sc = null;
            String resultado = "";

            try {
                Socket socket = sc = new Socket(strings[0], Integer.parseInt(strings[1]));
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


        @Override
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
            lvDatos.setAdapter(adp);
        }


    }
}

