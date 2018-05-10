package com.example.usuari.locationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuari on 26/03/2018.
 */

public class DBlocations {

    //Atributos
    private SQLiteDatabase db = null;
    private DatabaseHelper dbhelper = null;

    //Contexto
    Context context;

    public DBlocations(Context context){
        this.context = context;
        //Creamos una instancia de helper
        dbhelper = new DatabaseHelper(context);
        //Creamos un objeto sqlitedatabase para operar con la BD
        db = dbhelper.getWritableDatabase();
    }


    public void close(){
        dbhelper.close();
    }

/*
    public long AltaLocation(String altitud, String longuitud, String latitud){
        ContentValues initialValues = new ContentValues();
        initialValues.put("altitud", altitud);
        initialValues.put("longuitud", longuitud);
        initialValues.put("latitud", latitud);
        return db.insert("localizaciones",null, initialValues);
    }*/


    public long altaLocation(Double altitud, Double longuitud, Double latitud) {

        ContentValues initialValues = new ContentValues();
        initialValues.put("altitud", altitud);
        initialValues.put("longuitud", longuitud);
        initialValues.put("latitud", latitud);
        return db.insert("localizaciones",null, initialValues);
    }

    public Cursor recuperarLocations(){
        return db.query("localizaciones", new String[]{"_id","altitud","longuitud","latitud"},
                null, null, null, null, null);
    }



}
