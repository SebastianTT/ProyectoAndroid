package com.example.parcial.ui.gallery.models;

public class Agenda {
    private String NombreCampus;
    public  Agenda(){

    }

    public String getNombreCampus() {
        return NombreCampus;
    }

    public void setNombreCampus(String nombreCampus) {
        NombreCampus = nombreCampus;
    }

    public Agenda(String NombreCampus){
        this.NombreCampus = NombreCampus;
    }

}
