package com.example.usuari.servicioapp;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class ProgresoService extends Service {

    public ProgresoService() {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destruyendo servicio", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            //inicia la tarea asíncrona. Como no necesita pasar ningún
            // valor a la misma, se le manda un array de Void
        new CalculoTask().execute(new Void[]{null});

        //Con el return informamos al SO de como comportarse si el servicio se destruye
        // por falta de memoria y se intenta lanzar de nuevo (sticky es el más usual)
        return START_STICKY;
    }

    private class CalculoTask extends AsyncTask<Void, Integer, Long> {

            //Los parámetros que le pasamos a un AsyncTask son:
            //1-Void: Lo que le pasamos al crear la instancia
            //2-Integer: Si preguntamos el progreso, nos devuelve un campo de este tipo
            //3-Long: Lo que nos devuelve al finalizar, que recibimos en PostExecute

            @Override
            protected Long doInBackground(Void... voids) {
                //simula una tarea de larga duración en donde  se tarda mucho
                // en realizar la suma de los números de 1 a 100
                long result = 0;
                for (int i = 1; i <= 100; i++) {
                    result += i;
                    try {
                        //Pausamos la aplicación durante 100 milisegundos
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //con cada suma, se hace la llamada a este método
                    //que provocará la ejecución de onProgressUpdate
                    publishProgress(i);
                }
                return result;

            }
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "calculado" + result,
                    Toast.LENGTH_LONG).show();
        }


        protected void onProgressUpdate(Integer... values) {
            //enviamos un mensaje al Handler con el porcentaje
            //de calculo realizado. Se lo pasamos en el segundo parámetro
            MainActivity.manejador.obtainMessage(0, values[0], 0, null).sendToTarget();

        }
    }



}




