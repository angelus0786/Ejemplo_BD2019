package mx.edu.itsmt.ejemplo_bd2019.fragmentos;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import mx.edu.itsmt.ejemplo_bd2019.R;
import mx.edu.itsmt.ejemplo_bd2019.controlador.PersonaDAO;
import mx.edu.itsmt.ejemplo_bd2019.modelo.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment
        implements View.OnClickListener {

    EditText nombre,apellidos,edad,fecha;
    Button guardar;
    Persona persona;
    PersonaDAO personaDAO;
    Calendar calendar= Calendar.getInstance();

    public RegistroFragment() {
        // Required empty public constructor

    }

    public  void setFechaNac() {
        final Date fe = new Date();
        calendar.setTime(fe);
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog f =
                new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker,
                                                  int year, int month, int day) {
                                fecha.setText(""+day+"/"+(month+1)+"/"+year);
                            }
                        },anio,mes,dia);
        f.show();
    }

    public void setGuardar() {
        if (nombre.getText().toString().isEmpty()||
            apellidos.getText().toString().isEmpty()||
             edad.getText().toString().isEmpty()||
             fecha.getText().toString().isEmpty()){
            Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
        }else{
         persona = new Persona();
         persona.setNombre(nombre.getText().toString());
         persona.setApellidos(apellidos.getText().toString());
         persona.setEdad(Integer.parseInt(edad.getText().toString()));
         persona.setFechaNac(fecha.getText().toString());

         personaDAO = new PersonaDAO(getActivity());
         personaDAO.openBD();
         if (personaDAO.insertar(persona)>0){
             Toast.makeText(getActivity(),"Registro Exitoso",Toast.LENGTH_LONG).show();
         }else{
             Toast.makeText(getActivity(),"Registro Invalido",Toast.LENGTH_LONG).show();
         }
        }
    }//fin guardar


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registro, container, false);
        nombre = v.findViewById(R.id.etnombre);
        apellidos = v.findViewById(R.id.etapellidos);
        edad = v.findViewById(R.id.etedad);
        fecha = v.findViewById(R.id.etfecha);
        guardar = v.findViewById(R.id.btguardar);
        guardar.setOnClickListener(this);
        fecha.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btguardar:
                    setGuardar();
                break;
            case R.id.etfecha:
                    setFechaNac();
                break;
        }
    }


}
