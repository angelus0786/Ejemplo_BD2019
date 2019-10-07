package mx.edu.itsmt.ejemplo_bd2019.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BasedeDatos extends SQLiteOpenHelper {

    public static String NAME_BD="proyecto";
    public static String TABLE_PERSONA="persona";
    public static int VERSION=1;
    public static String consulta=
            "CREATE TABLE "+TABLE_PERSONA +"("+
                    "id integer primary key autoincrement,"+
                    "nombre text not null,apellidos text not null,"+
                    "edad integer not  null,fechanac text not null);";

    Context context;
    public BasedeDatos(@Nullable Context context) {
        super(context, NAME_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(consulta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_PERSONA);
        onCreate(db);
    }
}
