package com.example.parcial.ui.gallery;

import android.app.SearchManager;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;
import com.example.parcial.ui.gallery.Adapters.MensajeAdapter;
import com.example.parcial.ui.gallery.models.Agenda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements SearchView.OnQueryTextListener {
    DatabaseReference ref;
    private GalleryViewModel galleryViewModel;
    private TextView vis;

    public List<String> lista = new ArrayList<String>();
    String mensa;
    private ArrayList<String> men = new ArrayList<>();
    private MensajeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Agenda> agendalist = new ArrayList<>();
    private ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
           

            mRecyclerView = root.findViewById(R.id.recyclerviewmensajes);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
           ref = FirebaseDatabase.getInstance().getReference("agenda");

        setHasOptionsMenu(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        agendalist.clear();
                        for (DataSnapshot db : dataSnapshot.getChildren()) {

                            String mensaje = db.child("espacio").child("campus").child("nombre_campus").getValue().toString();
                            String descripcion = db.child("espacio").child("descripcion").getValue().toString();
                            String fecha = db.child("fecha").getValue().toString();
                            String trabajo = db.child("trabajo").child("nombre_trabajo").getValue().toString();
                            String horaini = db.child("hora_inicio").getValue().toString();
                            String horafin= db.child("hora_fin").getValue().toString();
                            String institucion= db.child("trabajo").child("sede").child("institucion").child("nombre_institucion").getValue().toString();
                            String semillero= db.child("trabajo").child("semillero").child("nombre_semillero").getValue().toString();

                            agendalist.add(new Agenda("NOMBRE DEL TRABAJO: "+" "+trabajo+"\n"+"SEMILLERO:"+" "+semillero+"\n"+"NOMBRE INSTITUCION:"+" "+institucion+"\n"+"NOMBRE DEL CAMPUS:"+" "+mensaje+"\n"+"DESCRIPCION:"+" "+descripcion+"\n"+ "FECHA: "+" "+fecha+"\n"
                                    + "HORA DE INICIO: "+" "+horaini+"\n"+ "HORA QUE FINALIZA: "+" "+horafin+"\n"
                            ));

                        }
                        mAdapter = new MensajeAdapter(agendalist,R.layout.mensaje_view);
                        mRecyclerView.setAdapter(mAdapter);
                        System.out.println("agendita completa"+agendalist);

                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", "Error!", databaseError.toException());
            }
        });


        System.out.println("reeeeeeeeeeef" +ref);






        return root;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.busca_menu, menu) ;


        MenuItem item = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener(){
            @Override
            public boolean onMenuItemActionExpand(MenuItem item){

                return true;
            }
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item){
                mAdapter.setFilter(agendalist);

                return true;
            }

        });


    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {

            ArrayList<Agenda> listafiltrada = filter(agendalist, s);
            mAdapter.setFilter(listafiltrada);

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private ArrayList<Agenda> filter(ArrayList<Agenda> agenda, String query){
        ArrayList<Agenda> listafiltrada = new ArrayList<>();
        try {
                query = query.toLowerCase();
                for (Agenda agend : agenda){
                    String agend2 = agend.getNombreCampus().toLowerCase();

                    if(agend2.contains(query)){
                        listafiltrada.add(agend);

                    }

                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listafiltrada;
    }
}