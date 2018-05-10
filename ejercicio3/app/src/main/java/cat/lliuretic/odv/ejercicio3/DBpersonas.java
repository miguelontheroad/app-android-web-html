package cat.lliuretic.odv.ejercicio3;

/**
 * Created by usuari on 15/03/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;

public class DBpersonas {

    private static final String TABLA = "personas";
    //Atributos
    private SQLiteDatabase db = null;
    private DatabaseHelper dbhelper = null;

    //Contexto
    Context context;

    public DBpersonas(Context ctx) {
        this.context = ctx;
//crea una instancia del helper
        dbhelper = new DatabaseHelper(context);
//crea un objeto SQLiteDatabase para operar
//contra la base de datos
        db = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }

    public long altaPersona(String nombre, String email, double edad) {

        ContentValues initialValues = new ContentValues();
        initialValues.put("nombre", nombre);
        initialValues.put("email", email);
        initialValues.put("edad", edad);
        return db.insert(TABLA, null, initialValues);
    }

    public boolean borrarPersona(int id) {
//elimina el libro a partir del id
        return db.delete(TABLA, "_id =" + id,null)>0;
    }

    public Persona recuperarPersonaPorNombre(String nombre) {
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
        return per;
    }

    public Cursor recuperarPerson() {
//aunque no se utilice, se debe recuperar también
// el campo _id
        return db.query(TABLA, new String[]{"_id","nombre",
                "edad","email"},null, null, null, null, null);
    }

}

