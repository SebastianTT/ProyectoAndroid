package com.example.parcial.ui.gallery.Adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;
import com.example.parcial.ui.gallery.models.Agenda;

import java.util.ArrayList;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {
    private int resource;
    private ArrayList<Agenda> agendalist;
    private ArrayList<Agenda> listaagenda;

    public MensajeAdapter(ArrayList<Agenda> agendalist, int resource){
        this.agendalist = agendalist;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Agenda nombrecampus = agendalist.get(position);
                holder.textViewMensaje.setText(nombrecampus.getNombreCampus());
    }

    @Override
    public int getItemCount() {
        return agendalist.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewMensaje;
        public View view;
        public  ViewHolder(View view){
            super(view);
            this.view = view;
            this.textViewMensaje = (TextView) view.findViewById(R.id.TextViewMensaje);
        }
    }

    public void setFilter(ArrayList<Agenda> listaagenda){
        this.agendalist = new ArrayList<>();
        this.agendalist.addAll(listaagenda);
        notifyDataSetChanged();
    }
}
