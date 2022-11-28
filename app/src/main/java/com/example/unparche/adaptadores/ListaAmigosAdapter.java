package com.example.unparche.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;

import java.util.ArrayList;

public class ListaAmigosAdapter extends RecyclerView.Adapter<ListaAmigosAdapter.UsuarioViewHolder>{

    ArrayList<String> listaKeys;
    ArrayList<String> listaNombres;
    ArrayList<String> listaApellidos;

    public ListaAmigosAdapter(ArrayList<String> listaKeys, ArrayList<String> listaNombres, ArrayList<String> listaApellidos){
        this.listaKeys=listaKeys;
        this.listaNombres=listaNombres;
        this.listaApellidos=listaApellidos;
    }

    /*
    Importa el layout para la visualizacion de cada objeto individual en el recycler view
    */
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_amigos,null,false);
            return new UsuarioViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.tvNombre.setText(listaNombres.get(position));
        holder.tvApellido.setText(listaApellidos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaKeys.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvApellido;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvApellido=itemView.findViewById(R.id.tvApellido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
