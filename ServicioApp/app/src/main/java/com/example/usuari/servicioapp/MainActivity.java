package com.example.usuari.servicioapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private static ProgressBar pb;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            pb =(ProgressBar)findViewById(R.id.pbProgreso);
            //valor máximo a alcanzar en la barra de progreso
            pb.setMax(100);
        }
        public void Iniciar(View v){
            startService(new Intent(this, ProgresoService.class));
        }
        public void auxiliar(View v){
            //muestra un mensaje cualquiera
            Toast.makeText(this, "Sigue funcionando", Toast.LENGTH_LONG).show();
        }


        //Nos falta crear el manejador (Handler) para
        // actualizar la interfaz gráfica de la actividad principal

        public static Handler manejador = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                //el valor del segundo parámetro es recogido
                //mediante el atributo arg1
                int valor = msg.arg1;
                //una vez tenemos el valor
                // actualizamos el estado de la barra de progreso
                pb.setProgress(valor);
            }
        };
}
