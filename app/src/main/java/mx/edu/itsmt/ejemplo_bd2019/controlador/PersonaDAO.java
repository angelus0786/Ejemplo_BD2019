package mx.edu.itsmt.ejemplo_bd2019.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mx.edu.itsmt.ejemplo_bd2019.BD.BasedeDatos;
import mx.edu.itsmt.ejemplo_bd2019.modelo.Persona;

public class PersonaDAO {

    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase database;
    Context context;

    public PersonaDAO(Context context) {
        this.context = context;
        sqLiteOpenHelper = new BasedeDatos(context);

    }

    public  void openBD()throws SQLException {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public  void closeBD() {
        sqLiteOpenHelper.close();
        database.close();
    }

    public long insertar(Persona p) {
        long i=0;
        try{
            ContentValues conten = new ContentValues();
            conten.put("nombre",p.getNombre());
            conten.put("apellidos",p.getApellidos());
            conten.put("edad",p.getEdad());
            conten.put("fechanac",p.getFechaNac());
            i = database.insert(BasedeDatos.TABLE_PERSONA,
                    null,conten);
        }
        catch(SQLException e){
            System.out.println("Error SQL"+e.getMessage());
            e.printStackTrace();
        }
        catch (Exception ex){
            System.out.println("Error "+ex.getMessage());
            ex.printStackTrace();
        }

        return i;
    }//fin insertar

    public long modificar(Persona p,int clave) {
        long estado=0;
        try{
            ContentValues valores =new ContentValues();
            valores.put("nombre",p.getNombre());
            valores.put("apellidos",p.getApellidos());
            valores.put("edad",p.getEdad());
            valores.put("fechanac",p.getFechaNac());
            estado = database.update(BasedeDatos.TABLE_PERSONA,valores,
                    "id="+clave,null);
        }catch (Exception e){
            estado = 0;
        }
        return estado;
    }
    public Persona buscarPersona(int id) {
        Persona persona= new Persona();;
        String sql="Select * from persona where id="+id+";";
        Cursor cursor = database.rawQuery(sql,null);

        try{

            while (cursor.moveToNext()){
                persona.setNombre(cursor.getString(1));
                persona.setApellidos(cursor.getString(2));
                persona.setEdad(cursor.getInt(3));
                persona.setFechaNac(cursor.getString(4));
            }
            cursor.close();

        }
        catch(SQLException e){
            System.out.println("Error SQL"+e.getMessage());
            e.printStackTrace();
        }
        catch (Exception ex){
            System.out.println("Error "+ex.getMessage());
            ex.printStackTrace();
        }

        return persona;

    }//fin buscar

    public ArrayList<Persona> listaPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
        String sql= "select * from persona";
        Persona persona;
        Cursor c =database.rawQuery(sql,null);
        while (c.moveToNext()){
            persona = new Persona();
            persona.setNombre(c.getString(1));
            persona.setApellidos(c.getString(2));
            persona.setEdad(c.getInt(3));
            persona.setFechaNac(c.getString(4));
            lista.add(persona);
        }
        c.close();
        return lista;
    }

}
