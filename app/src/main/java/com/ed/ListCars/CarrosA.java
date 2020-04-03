package com.ed.ListCars;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarrosA extends RecyclerView.Adapter<CarrosA.ViewHolderPelicula> {

    private ArrayList<Carros> listaCarros;

    public CarrosA(ArrayList<Carros> listaCarros) {
        this.listaCarros = listaCarros;
    }

    @NonNull
    @Override
    public CarrosA.ViewHolderPelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pelicula_layout, null, false);
        return new ViewHolderPelicula(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrosA.ViewHolderPelicula holder, int position) {
        holder.txtMarca.setText(listaCarros.get(position).getMarca());
        holder.txtPlaca.setText(listaCarros.get(position).getPlaca());
        holder.txtTransmision.setText(listaCarros.get(position).getTransmision());
        holder.txtfecha.setText(listaCarros.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaCarros.size();
    }

    public class ViewHolderPelicula extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView txtMarca, txtPlaca,  txtfecha, txtTransmision;

        public ViewHolderPelicula(View itemView) {
            super(itemView);
            txtMarca = itemView.findViewById(R.id.idtxtNombre);
            txtPlaca = itemView.findViewById(R.id.idtxtDirector);
            txtTransmision =itemView.findViewById(R.id.idtxtIdioma);
            txtfecha=itemView.findViewById(R.id.idtexFecha);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
