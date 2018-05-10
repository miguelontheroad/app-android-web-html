package cat.lliuretic.odv.ejercicio3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Findpersona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpersona);
    }
    public void cerrar(View v){
        this.finish();
    }

    public void buscar(View v){
        String resultado = "";


        if (i > 0)
            tv.setText(resultado);
        else tv.setText("No se ha encontrado ningun resultado");
    }


    public Persona recuperarPersonaPorNombre(String nombre) {
        TextView tv = (TextView) this.findViewById(R.id.tvEncontrado);
        float valor = 0.0f;
        Persona per = null;
        Cursor c = db.query(TABLA, new String[]{"id",
                        "email","edad"},"nombre =?",new String[]{nombre},
                null, null, null);
//debe desplazarlo al siguiente registro para
//apuntar al primero
        if (c.moveToNext()) {
            per = new Persona();
            per.setNombre(c.getString(1));
            per.setEmail(c.getString(2));
            per.setEdad(c.getInt(2));
        }
        EditText edt = (EditText) this.findViewById(R.id.edtBuscar);
        String s = edt.getText().toString();

            if (per.nombre == s) {
                return per;
            }else{tv.setText("No se ha encontrado ningun resultado");
            }



    }






}
