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
public class ConsultaFragment extends Fragment implements View.OnClickListener {

    EditText id,nombre,apellidos,edad,fecha;
    Button buscar,modificar;
    Persona persona;
    PersonaDAO personaDAO;
    Calendar calendar= Calendar.getInstance();

    public ConsultaFragment() {
        // Required empty public constructor
    }

    public  void setConsultar() {
        try{
            personaDAO.openBD();
            if (!id.getText().toString().isEmpty()) {
                int i = Integer.parseInt(id.getText().toString());
                persona = personaDAO.buscarPersona(i);
                nombre.setText(persona.getNombre());
                apellidos.setText(persona.getApellidos());
                edad.setText(String.valueOf(persona.getEdad()));
                fecha.setText(persona.getFechaNac());
                personaDAO.closeBD();
            }
            else{
                Toast.makeText(getActivity(),"Colocar ID",Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }//fin getConsultar
    public  void setModificar() {
        try{
            if (id.getText().toString().isEmpty()||
                    nombre.getText().toString().isEmpty()||
                    apellidos.getText().toString().isEmpty()||
                    edad.getText().toString().isEmpty()||
                    fecha.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
            }else{
                persona = new Persona();
                int clave = Integer.parseInt(id.getText().toString());
                persona.setNombre(nombre.getText().toString());
                persona.setApellidos(apellidos.getText().toString());
                persona.setEdad(Integer.parseInt(edad.getText().toString()));
                persona.setFechaNac(fecha.getText().toString());

                personaDAO = new PersonaDAO(getActivity());
                personaDAO.openBD();
                if (personaDAO.modificar(persona,clave)>0){
                    Toast.makeText(getActivity(),"Actualización Exitoso",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"Actualización Invalido",Toast.LENGTH_LONG).show();
                }
            }
            personaDAO.closeBD();
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }//fin setModificar
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
    }//fin  setFechaNac

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_consulta, container, false);
        id = v.findViewById(R.id.etid);
        nombre = v.findViewById(R.id.etnombre);
        apellidos = v.findViewById(R.id.etapellidos);
        edad = v.findViewById(R.id.etedad);
        fecha = v.findViewById(R.id.etfecha);
        buscar = v.findViewById(R.id.btbuscar);
        modificar = v.findViewById(R.id.btmodificar);
        buscar.setOnClickListener(this);
        modificar.setOnClickListener(this);
        fecha.setOnClickListener(this);
        persona = new Persona();
        personaDAO = new PersonaDAO(getActivity());
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btbuscar:
                setConsultar();
                break;
            case R.id.btmodificar:
                setModificar();
                break;
            case R.id.etfecha:
                setFechaNac();
                break;
        }
    }//onClick
}
