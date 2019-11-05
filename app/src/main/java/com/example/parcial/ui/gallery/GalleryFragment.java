package com.example.parcial.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    DatabaseReference ref;
    private GalleryViewModel galleryViewModel;
    private TextView vis;
    public List<String> lista = new ArrayList<String>();

    public MensajeAdapter mensajeAdapter;
    private RecyclerView rcv;
    private ArrayList<String> men = new ArrayList<>();

    private ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        vis = root.findViewById(R.id.valores);
        rcv = root.findViewById(R.id.listaa);
        listView = root.findViewById(R.id.listica);



           ref = FirebaseDatabase.getInstance().getReference("agenda");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot db : dataSnapshot.getChildren()) {
                            String mensaje = db.child("espacio").child("campus").child("nombre_campus").getValue().toString();
                            men.add(mensaje);

                        }
                        System.out.println(men);
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
}