package mx.edu.itsmt.ejemplo_bd2019.fragmentos;


import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.edu.itsmt.ejemplo_bd2019.R;
import mx.edu.itsmt.ejemplo_bd2019.controlador.MyAdapter;
import mx.edu.itsmt.ejemplo_bd2019.controlador.PersonaDAO;
import mx.edu.itsmt.ejemplo_bd2019.modelo.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListarFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Persona> listaNombres;
    Persona persona;
    PersonaDAO personaDAO;

    public ListarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        persona =new Persona();
        personaDAO = new PersonaDAO(getActivity());
        recyclerView = view.findViewById(R.id.myrecycleyview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        personaDAO.openBD();
        this.listaNombres = personaDAO.listaPersonas();
        mAdapter = new MyAdapter(listaNombres,
                R.layout.item, new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String nombre, int posicion) {
                Toast.makeText(getActivity(),"Click en:"+nombre,
                        Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return view;
    }

}
