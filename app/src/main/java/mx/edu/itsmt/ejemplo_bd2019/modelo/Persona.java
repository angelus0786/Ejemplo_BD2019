package mx.edu.itsmt.ejemplo_bd2019.modelo;

public class Persona {

    String nombre;
    String apellidos;
    int edad;
    String fechaNac;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, int edad, String fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.fechaNac = fechaNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
}
