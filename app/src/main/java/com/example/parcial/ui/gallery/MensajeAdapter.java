package com.example.parcial.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;

import java.util.ArrayList;

public class MensajeAdapter extends RecyclerView.Adapter {
    private  int resource;
    private ArrayList<Mensaje> mensajeList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mensajetexto;
        public View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.mensajetexto = itemView.findViewById(R.id.valores);
        }
    }
    public MensajeAdapter(ArrayList<Mensaje> mensajeList, int resource){
        this.mensajeList = mensajeList;
        this.resource = resource;
    }
    private  int reso;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(reso, parent, attachToRoot(false));
        return new ViewHolder(view);

    }

    private boolean attachToRoot(boolean b) {
        return false;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
Mensaje mensaje = mensajeList.get(position);
    }

    @Override
    public int getItemCount() {

        return mensajeList.size();
    }
}
