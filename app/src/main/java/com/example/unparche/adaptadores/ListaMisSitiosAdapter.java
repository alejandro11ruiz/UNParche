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
import com.example.unparche.interfaces.sitio.VerSitioActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMisSitiosAdapter extends RecyclerView.Adapter<ListaMisSitiosAdapter.SitioViewHolder> {


    ArrayList<String> listaSitios;
    ArrayList<String> listaDir;
    ArrayList<String> listaCiu;
    ArrayList<String> listakey;
    boolean edit;

    ArrayList<String> listaOriginal;

    public ListaMisSitiosAdapter(ArrayList<String> listaSitios, ArrayList<String> listaDir,ArrayList<String> listaCiu,ArrayList<String> listakey, boolean edit){
        this.listaSitios = listaSitios;
        this.listaDir = listaDir;
        this.listaCiu = listaCiu;
        this.listakey = listakey;
        this.edit = edit;
    }
    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mis_sitios, null,false);
        return new SitioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SitioViewHolder holder, int position) {
        holder.viewNombre.setText(listaSitios.get(position));
        holder.viewDireccion.setText(listaDir.get(position));
        holder.viewCiudad.setText(listaCiu.get(position));
    }

/*
    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            listaSitios.clear();
            listaSitios.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<String> coleccion = listaSitios.stream().filter(i -> i.toLowerCase()
                        .contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaSitios.clear();
                listaSitios.addAll(coleccion);
            } else {
                for (String c:listaOriginal){
                    if(c.toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaSitios.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
*/

    @Override
    public int getItemCount() {
        return listaSitios.size();
    }

    public class SitioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDireccion, viewCiudad;

        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre=itemView.findViewById(R.id.viewNombreL);
            viewDireccion=itemView.findViewById(R.id.viewDireccionL);
            viewCiudad=itemView.findViewById(R.id.viewCiudadL);

            if (edit){
                itemView.setOnClickListener(view -> {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerSitioActivity.class);
                    intent.putExtra("ID", listakey.get(getAdapterPosition()));
                    context.startActivity(intent);
                });}
        }
    }
}