package com.example.usuari.timerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServicioClock extends Service {

    Timer timer;

    public ServicioClock() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        iniciarServicio();
        //Con el return informamos al SO de como comportarse si el servicio se destruye
        // por falta de memoria y se intenta lanzar de nuevo (sticky es el más usual)
        return START_STICKY;
    }

    private void iniciarServicio() {
        timer = new Timer();
        //después de crear una instancia de Timer
        //hacemos un schedule de nuestra tarea cada x milisegundos
        timer.schedule(new Mitimer(), 0, 500);

    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private class Mitimer extends TimerTask {

        @Override
        public void run() {
            //código que queremos que se repita
            Date d = new Date();
            DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
            //Damos formato a la fecha obtenida
            String resultado = df.format(d);
            //Enviamos un mensaje al hilo principal para actualizar la hora
            MainActivity.manejador.obtainMessage(0,0,0,resultado).sendToTarget();
        }
    }
}
