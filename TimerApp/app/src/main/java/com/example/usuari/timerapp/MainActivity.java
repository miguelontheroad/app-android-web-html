package com.example.usuari.timerapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static TextView tvTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTiempo = (TextView)this.findViewById(R.id.tvTiempo);
    }

    @Override
    protected void onResume() {
        startService(new Intent(this, ServicioClock.class));
        super.onResume();
    }


    @Override
    protected void onPause() {
        //paramos el servicio si la app esté en pausa
        stopService(new Intent(this, ServicioClock.class));
        super.onPause();
    }

    //Creamos el manejador que sera el que gestiona mensajes que recibe el servicio

    public static Handler manejador = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //recuperamos el contenido del mensaje del servicio
            //para imprimirlo por pantalla
            String resultado = (String) msg.obj;
            tvTiempo.setText(resultado);
        }
    };
}
