package com.example.parcial.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parcial.MainActivity;
import com.example.parcial.Publicidad;
import com.example.parcial.R;
import com.example.parcial.ui.slideshow.SlideshowFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class HomeFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener  {
    private GoogleApiClient googleApiClient;
    private HomeViewModel homeViewModel;
    Button prueba;
    public static final int codigo =777;
    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(mContext).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        Button google = view.findViewById(R.id.google);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,codigo);
            }
        });


        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==codigo){
            GoogleSignInResult result =Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handlesign(result);
        }
    }

    private void handlesign(GoogleSignInResult result){
        if(result.isSuccess()){
            goMainScreen();
        }else {

            Toast.makeText(mContext, "No se pudo iniciar la sesión", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {

        Intent intent = new Intent(getActivity(), Publicidad.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}