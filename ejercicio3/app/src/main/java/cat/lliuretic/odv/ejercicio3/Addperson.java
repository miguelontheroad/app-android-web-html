package cat.lliuretic.odv.ejercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static cat.lliuretic.odv.ejercicio3.MainActivity.milistadodefichero;


public class Addperson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperson);
    }

    public void cerrar (View v){
        this.finish();
    }

/*
    public void saveandclose(View v) throws FileNotFoundException {
        EditText edtNom = (EditText) this.findViewById(R.id.edtNombre);
        EditText edtMa = (EditText) this.findViewById(R.id.edtMail);
        EditText edtEd = (EditText) this.findViewById(R.id.edtEdad);

       Persona p = new Persona();
        p.setNombre(edtNom.getText().toString());
        p.setEmail(edtMa.getText().toString());
        p.setEdad(Integer.parseInt(edtEd.getText().toString()));
        milistadodefichero.add(p);

        FileOutputStream filete = this.openFileOutput("Nombres.txt", MODE_APPEND);

        PrintStream outer = new PrintStream(filete);
        outer.println(p.toFile());
        outer.flush();
        outer.close();


        Toast.makeText(this, "Persona guardada", Toast.LENGTH_LONG).show();
        this.finish();
    }

 */


    public void saveandclose(View v){
        String nombre = ((EditText) findViewById(R.id.edtNombre)).getText().toString();
        String email = ((EditText) findViewById(R.id.edtMail)).getText().toString();
        String edad =((EditText)findViewById(R.id.edtEdad)).getText().toString();
        Double dblEdad = Double.parseDouble(edad);
        DBpersonas adp = new DBpersonas(this);
        adp.altaPersona(nombre, email,dblEdad);
        adp.close();
        this.finish();
    }

}
