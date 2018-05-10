package com.example.usuari.locationapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    LocationManager lm; //Creamos la variable para el LocationManager
    //Creamos un escuchador de posiciones, que nos avisara de los cambios
    LocationListener escuchador = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            double Altitud = location.getAltitude();
            double Longitud = location.getLongitude();
            double Latitud = location.getLatitude();

            DBlocations dblocat = new DBlocations(MainActivity.this);

            dblocat.altaLocation(Altitud, Longitud, Latitud);

            Cursor c = dblocat.recuperarLocations();
            //dblocat.close();

            ListView lvDatos = (ListView) findViewById(R.id.lvDatos);
            String[] columnas = new String[]{"altitud","longuitud","latitud"};
            int[] vistas = new int[]{R.id.altitud, R.id.longuitud, R.id.latitud};

            SimpleCursorAdapter sc = new SimpleCursorAdapter(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    c,
                    columnas,
                    vistas,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
            );

            lvDatos.setAdapter(sc);
            dblocat.close();
             /*

            String datos = "Altitud:" + location.getAltitude() +
                    "|Longitud:" + location.getLongitude() +
                    "|Latitud:" + location.getLatitude();
            //muestra los datos a enviar en un toast
            Toast.makeText(MainActivity.this, datos, Toast.LENGTH_LONG).show();

            /*Creamos un objeto de la clase AsyncTask
            ComunicacionTask ctask = new ComunicacionTask();
            //Ejecuta la tarea con la dirección del servidor,puerto y datos a enviar

            ctask.execute("10.0.2.2", "9000", datos); */

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtener objeto LocationManager que representa
        //el servicio de localización
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //eliminamos la vinculacion con escuchador
        lm.removeUpdates(escuchador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //vinculamos el location manager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10,
                escuchador);
    }


/*    //Creamos nuestra clase de comunicacion
    private class ComunicacionTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Socket sc = null;
            String resultado ="";
            try {
                sc = new Socket(strings[0], Integer.parseInt(strings[1]));
                PrintStream ps = new PrintStream(sc.getOutputStream());
                //enviamos los datos
                ps.println(strings[2]);
                ps.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {//cierre de objetos en el finally
                if (sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //debe devolver null por ser Void y no void
            return null;
        }
    }*/

}
