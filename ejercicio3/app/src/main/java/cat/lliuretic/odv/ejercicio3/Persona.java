package cat.lliuretic.odv.ejercicio3;

/**
 * Created by usuari on 13/03/2018.
 */

public class Persona {

    String nombre, email;
    int edad;

    Persona(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " " + edad + " " + email;
    }

    public String toFile()  {
        return nombre + " / " + edad + " / " + email;
    }
}
