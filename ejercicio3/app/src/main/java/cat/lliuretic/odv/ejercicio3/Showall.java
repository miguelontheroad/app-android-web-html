package cat.lliuretic.odv.ejercicio3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static cat.lliuretic.odv.ejercicio3.MainActivity.milistadodefichero;

public class Showall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall);
        Intent i = this.getIntent();

        ListView tvResultado = (ListView) this.findViewById(R.id.tvResultado);

        ArrayAdapter<Persona> adaptem =new ArrayAdapter<Persona>(this, android.R.layout.simple_list_item_1, milistadodefichero);

        tvResultado.setAdapter(adaptem);
    }

    public void cerrar(View v){
        this.finish();
    }
}
