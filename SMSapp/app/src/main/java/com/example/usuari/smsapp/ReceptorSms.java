package com.example.usuari.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsMessage;

public class ReceptorSms extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String origen = "";
        String textoSMS = "";
        //Los SMS se envian por trozos que forman un array, al conjunto de trozos
        //se le llama PDUS
        Object[] pdus = (Object[])intent.getExtras().get("pdus");
        //vamos recorriendo los trozos de mensaje
        for (int i = 0; i < pdus.length; i++) {
            //obtenemos el objeto SMS

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                SmsMessage sm = SmsMessage.createFromPdu((byte[])pdus[i],"3gpp");

                //Recuperamos el numero desde el que se ha enviado
                origen = sm.getOriginatingAddress();
                textoSMS+=sm.getMessageBody().toString();

            }

        }

        MainActivity.misSMS.add(origen+ ":" + textoSMS);
    }

}
