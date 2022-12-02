package com.example.unparche.entidades;

public class Evento {


    private String ID;
    private String nombre;
    private String idsitio;
    private String sitio;
    private String hora;
    private String actividad;

    public Evento(){
        ID="null";
        nombre="null";
        hora="null";
        idsitio="null";
        sitio="null";
        actividad="null";
    }

    public Evento(String ID, String nombre, String hora, String idsitio, String sitio, String actividad){
        this.ID=ID;
        this.nombre=nombre;
        this.hora=hora;
        this.idsitio=idsitio;
        this.sitio=sitio;
        this.actividad= actividad;
    }



    public  String getID() {return  ID; }

    public void setID(String ID) {this.ID = ID; }

    public  String getNombre() {return  nombre; }

    public void setNombre(String nombre) {this.nombre = nombre; }

    public  String getHora() {return  hora; }

    public void setHora(String hora) {this.hora = hora; }

    public  String getIdsitio() {return  idsitio; }

    public void setIdsitio(String idsitio) {this.idsitio = idsitio; }

    public  String getSitio() {return  sitio; }

    public void setSitio(String sitio) {this.sitio = sitio; }

    public  String getActividad() {return actividad; }

    public void setActividad(String actividad) {this.actividad = actividad; }

    }
