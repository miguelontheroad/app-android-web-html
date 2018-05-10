package cat.lliuretic.odv.ejercicio3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Persona> milistadodefichero = new ArrayList<Persona>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPerson(View v) {
        Intent i = new Intent(this, Addperson.class);
        this.startActivity(i);
    }



    public void showAll(View v) {
        Intent i = new Intent(this, Showall.class);
        String contenido;
        FileInputStream fileton = null;
        try {
            fileton = this.openFileInput("Nombres.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(fileton));
        try {
            while ((contenido = bf.readLine()) != null) {
                Persona p = new Persona();
                String[] leido = contenido.split(" / ");
                p.setNombre(leido[0]);
                p.setEdad(Integer.parseInt(leido[1]));
                p.setEmail(leido[2]);
                milistadodefichero.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String todos = "";
        for (Persona p : milistadodefichero) todos += p.toString();
        i.putExtra("todos", todos);
        Toast.makeText(getApplicationContext(), "Datos recuperados del fichero", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }



    public void addPersonSQL(View v) {
        Intent i = new Intent(this, DBpersonas.class);
        this.startActivity(i);
    }



    public void showAllSQL(View v) {
        Intent i = new Intent(this, MostrarSQL.class);
        this.startActivity(i);
    }



    public void findPerson(View v) {
        Intent i = new Intent(this, Findpersona.class);
        this.startActivity(i);
    }

}