package mx.edu.itsmt.ejemplo_bd2019;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import mx.edu.itsmt.ejemplo_bd2019.fragmentos.ConsultaFragment;
import mx.edu.itsmt.ejemplo_bd2019.fragmentos.ListarFragment;
import mx.edu.itsmt.ejemplo_bd2019.fragmentos.RegistroFragment;

public class ContenedorActivity extends AppCompatActivity {

    Fragment misFragmetos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);

        misFragmetos = new Fragment[3];
        misFragmetos[0]=new RegistroFragment();
        misFragmetos[1]=new ConsultaFragment();
        misFragmetos[2]=new ListarFragment();

        Bundle intent = getIntent().getExtras();
        int boton = intent.getInt("boton");

        FragmentManager miManejador = getFragmentManager();
        FragmentTransaction miTransaction = miManejador.beginTransaction();
        switch (boton){
            case 1:
                miTransaction.replace(R.id.contenedor,misFragmetos[0]);
                miTransaction.commit();
                break;
            case 2:
                miTransaction.replace(R.id.contenedor,misFragmetos[1]);
                miTransaction.commit();
                break;
            case 3:
                miTransaction.replace(R.id.contenedor,misFragmetos[2]);
                miTransaction.commit();
                break;
            case 4:
              //  miTransaction.replace(R.id.contenedorDetalle,misFragmetos[3]);
                //miTransaction.commit();
                break;
        }//fin switch
    }
}
