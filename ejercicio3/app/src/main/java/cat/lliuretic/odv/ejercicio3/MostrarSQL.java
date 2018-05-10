package cat.lliuretic.odv.ejercicio3;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MostrarSQL extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mostrar_sql);
            DBpersonas adp = new DBpersonas(this);
            Cursor c =adp.recuperarPerson();
            String[] columnas = new String[]{"nombre","email","edad"};
            int[] vistas = new int[]{R.id.nombrex, R.id.emailx, R.id.edadx};

            SimpleCursorAdapter sc = new SimpleCursorAdapter(
                    this,
                    R.layout.listado,
                    c,
                    columnas,
                    vistas,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
            );

            ListView lista = (ListView) findViewById(R.id.listaSQL);
            lista.setAdapter(sc);
            adp.close();


        }

    public void VolverInicio(View v){
        Intent i= new Intent(this, MainActivity.class);
        this.startActivity(i);
    }


}
