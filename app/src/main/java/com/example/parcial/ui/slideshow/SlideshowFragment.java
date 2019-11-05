package com.example.parcial.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parcial.R;
import com.example.parcial.maps;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private MapView myOpenMapView;
    private MapController myMapController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);


       View view = inflater.inflate(R.layout.fragment_slideshow,container,false);
       Button mosmap = view.findViewById(R.id.button3);
       mosmap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent in = new Intent(getActivity(),maps.class);
               in.putExtra("maps","maps");
               startActivity(in);
           }
       });

       return view;

    }
}