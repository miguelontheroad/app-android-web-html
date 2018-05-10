package cat.lliuretic.odv.ejercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AltaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
    }

    public void AltaPersonas(View v) {
        String nombre =
                ((EditText) findViewById(R.id.edtNombre)).getText().toString();
        String mail =
                ((EditText) findViewById(R.id.edtMail)).getText().toString();
        Integer edad =
                Integer.parseInt(((EditText) findViewById(R.id.edtEdad)).getText().toString());
        DBpersonas adp = new DBpersonas(this);
        adp.altaPersona(nombre, mail, edad);
        adp.close();
        this.finish();
    }



}

