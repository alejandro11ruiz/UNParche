package com.example.unparche.adaptadores;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.unparche.R;
import com.example.unparche.entidades.Sitio;
import com.example.unparche.interfaces.sitio.VerSitioActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMisSitiosAdapter extends RecyclerView.Adapter<ListaMisSitiosAdapter.SitioViewHolder> {


    ArrayList<Sitio> listaSitios;
    ArrayList<Sitio> listaOriginal;

    public ListaMisSitiosAdapter(ArrayList<Sitio> listaSitios){
        this.listaSitios = listaSitios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaSitios);
    }
    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mis_sitios, null,false);
        return new SitioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SitioViewHolder holder, int position) {
        holder.viewNombre.setText(listaSitios.get(position).getNombre());
        holder.viewTelefono.setText(listaSitios.get(position).getDireccion());
        holder.viewEmail.setText(listaSitios.get(position).getCiudad());
    }


    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            listaSitios.clear();
            listaSitios.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Sitio> coleccion = listaSitios.stream().filter(i -> i.getNombre().toLowerCase()
                        .contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaSitios.clear();
                listaSitios.addAll(coleccion);
            } else {
                for (Sitio c:listaOriginal){
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaSitios.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaSitios.size();
    }

    public class SitioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewEmail;

        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre=itemView.findViewById(R.id.viewNombre);
            viewTelefono=itemView.findViewById(R.id.viewDireccion);
            viewEmail=itemView.findViewById(R.id.viewCiudad);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, VerSitioActivity.class);
                intent.putExtra("ID", listaSitios.get(getAdapterPosition()).getID());
                context.startActivity(intent);
            });
        }
    }
}