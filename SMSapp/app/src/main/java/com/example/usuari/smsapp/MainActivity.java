package com.example.usuari.smsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> misSMS = new ArrayList<String>();
    ListView lvSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSMS = (ListView)this.findViewById(R.id.lvSMS);
    }

    public void refrescar(View v){
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, misSMS);
        lvSMS.setAdapter(adp);
    }

}
