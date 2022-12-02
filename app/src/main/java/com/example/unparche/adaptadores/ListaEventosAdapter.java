package com.example.unparche.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unparche.R;
import com.example.unparche.interfaces.evento.VerEventosActivity;

import java.util.ArrayList;

public class ListaEventosAdapter extends RecyclerView.Adapter<ListaEventosAdapter.UsuarioViewHolder>{

    ArrayList<String> listaKeys;
    ArrayList<String> listaNombres;
    ArrayList<String> listaHora;
    String IDsit;
    boolean edit;

    public ListaEventosAdapter(ArrayList<String> listaKeys, ArrayList<String> listaNombres, ArrayList<String> listaApellidos, boolean edit, String IDsit){
        this.listaKeys=listaKeys;
        this.listaNombres=listaNombres;
        this.listaHora=listaApellidos;
        this.edit=edit;
        this.IDsit=IDsit;
    }

    /*
    Importa el layout para la visualizacion de cada objeto individual en el recycler view
    */
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_eventos,null,false);
            return new UsuarioViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.tvNombre.setText(listaNombres.get(position));
        holder.tvHora.setText(listaHora.get(position));
    }

    @Override
    public int getItemCount() {
        return listaKeys.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvHora;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvHora=itemView.findViewById(R.id.tvHora);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, VerEventosActivity.class);
                intent.putExtra("ID", listaKeys.get(getAdapterPosition()));
                intent.putExtra("Sitios", edit);
                intent.putExtra("IDsit", IDsit);
                context.startActivity(intent);
            });

        }
    }
}
