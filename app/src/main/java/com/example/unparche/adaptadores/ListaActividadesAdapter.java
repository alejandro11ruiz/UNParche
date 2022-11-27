package com.example.unparche.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import com.example.unparche.R;
import com.example.unparche.interfaces.intermedios.PreUsuarioActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaActividadesAdapter extends RecyclerView.Adapter<ListaActividadesAdapter.ActividadViewHolder>{

    ArrayList<String> listaKeys;

    public ListaActividadesAdapter(ArrayList<String> listaKeys){
        this.listaKeys=listaKeys;
    }

    /*
    Importa el layout para la visualizacion de cada objeto individual en el recycler view
    */
    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_actividad,null,false);
        return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        holder.tvNombre.setText(listaKeys.get(position));
    }

    @Override
    public int getItemCount() {
        return listaKeys.size();
    }

    public class ActividadViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre;

        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre=itemView.findViewById(R.id.tvNombre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

        }
    }

}
