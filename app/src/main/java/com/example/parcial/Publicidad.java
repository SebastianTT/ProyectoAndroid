package com.example.parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class Publicidad extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {
    private Button olvida;
    private  Button retroceder;
    private GoogleApiClient googleapiclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicidad);
        ImageView iv_background = findViewById(R.id.iv_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_background.getDrawable();
        animationDrawable.start();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleapiclient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        olvida = (Button) findViewById(R.id.revocar);
        retroceder = findViewById(R.id.salir);
        olvida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                revocar();

            }
        });

        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Publicidad.this,MainActivity.class);

                startActivity(in);

            }
        });


    }




    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void inicio(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void salir(){
        Auth.GoogleSignInApi.signOut(googleapiclient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    inicio();
                }else{
                    Toast.makeText(getApplicationContext(),"No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void revocar(){
        Auth.GoogleSignInApi.revokeAccess(googleapiclient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    inicio();
                }else{
                    Toast.makeText(getApplicationContext(),"No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
