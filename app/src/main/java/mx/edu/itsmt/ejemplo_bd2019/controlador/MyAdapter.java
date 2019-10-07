package mx.edu.itsmt.ejemplo_bd2019.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.edu.itsmt.ejemplo_bd2019.R;
import mx.edu.itsmt.ejemplo_bd2019.modelo.Persona;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Persona> nombres;
    private int layout;
    private onItemClickListener onItemClickListener;


    public MyAdapter(ArrayList<Persona> nombres, int layout, MyAdapter.onItemClickListener onItemClickListener) {
        this.nombres = nombres;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layout,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(nombres.get(i).getNombre()+" "
                +nombres.get(i).getApellidos(),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return nombres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre =itemView.findViewById(R.id.tvnombre);
        }

        public  void bind(final String nombre, final onItemClickListener listener) {
            this.nombre.setText(nombre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(nombre,getAdapterPosition());
                }
            });

        }
    }

    public interface onItemClickListener{
        void onItemClick(String nombre,int posicion);
    }//fin interface


}
