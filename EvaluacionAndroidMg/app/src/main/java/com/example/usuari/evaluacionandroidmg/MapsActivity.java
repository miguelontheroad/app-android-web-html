package com.example.usuari.evaluacionandroidmg;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;

    public Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public void Iniciar(View v) {

        t = new Timer();
        //inicia el temporizador de manera inmediata
        // con una repetición cada 5 segundos
        t.schedule(new TimerTask(){
            @Override
            public void run(){
                String mensaje="Petición de localización aleatoria";
                ComunicacionTask com = new ComunicacionTask();
                com.execute("10.0.2.2","9000", mensaje);

            }
        }, 0, 5000);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //Establecemos como originales las coordenadas de Sidney
        //y las guardamos como referencia en un array
        LatLng direccion = new LatLng(-34, 151);
        Integer[] integerArray = new Integer[]{-34, 151};

        try {

            //creamos un objeto JSON a partir de la cadena recibida

            JSONObject jsonob = new JSONObject(s);

            Integer latitud = (Integer) jsonob.get("latitud");
            Integer longuitud = Integer.valueOf(jsonob.getString("longuitud"));

            //Comprobamos que la latitud no se repite

            for(int i=0; i<integerArray.length; i++) {
                if (latitud != integerArray[i]) {
                    integerArray[i + 1] = latitud;
                    //comprobar que la longuitud no se repite
                    for (int y = 0; y < integerArray.length; y++) {
                        if (longuitud != integerArray[i]) {
                            //Establecemos la latitud y longuitud aleatorios
                            // recibidos del servidor
                            direccion = new LatLng(latitud, longuitud);
                        }
                    }

                }

            }

            mMap.addMarker(new MarkerOptions().position(direccion).title("Aleatorio"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(direccion));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }


    public void Deleter(View v){
        t.cancel();
    }
    private class ComunicacionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            Socket sc = null;
            try {
                sc = new Socket("10.0.2.2", 9000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String cadenaJson = "";
            try {
                //recuperacion de la respuesta JSON
                String s;
                InputStream is = sc.getInputStream();

                //utilizamos UTF-8 para que interprete
                //correctamente las ñ y acentos

                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8"))
                );

                while ((s = bf.readLine()) != null) {
                    cadenaJson += s;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return cadenaJson;
        }

    }


}
