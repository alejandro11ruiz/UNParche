package com.example.unparche.entidades;

import java.util.ArrayList;

public class Sitio {

    private String ID;
    private String nombre;
    private ArrayList<String> actividad;
    private String ciudad;
    private String direccion;
    private String coordenadaLat;
    private String coordenadaLon;

    public Sitio(){
        ID="null";
        nombre="null";
        actividad= new ArrayList<>();
        ciudad="null";
        direccion="null";
        coordenadaLon="null";
        coordenadaLat="null";
    }

    public Sitio(String ID, String nombre, String ciudad, String direccion, String coordenadaLon, String coordenadaLat){
        this.ID= ID;
        this.nombre=nombre;
        actividad= new ArrayList<>();
        this.ciudad=ciudad;
        this.direccion=direccion;
        this.coordenadaLon=coordenadaLon;
        this.coordenadaLat=coordenadaLat;
    }

    public Sitio(String ID, String nombre, String ciudad, String direccion, String coordenadaLon, String coordenadaLat, ArrayList<String> actividad){
        this.ID=ID;
        this.nombre=nombre;
        this.actividad= actividad;
        this.ciudad=ciudad;
        this.direccion=direccion;
        this.coordenadaLon=coordenadaLon;
        this.coordenadaLat=coordenadaLat;
    }


    public  String getID() {return  ID; }

    public void setID(String ID) {this.ID = ID; }

    public  String getNombre() {return  nombre; }

    public void setNombre(String nombre) {this.nombre = nombre; }

    public  ArrayList<String> getActividad() {return actividad; }

    public void setActividad(ArrayList<String> actividad) {this.actividad = actividad; }

    public  String getCiudad() {return  ciudad; }

    public void setCiudad(String ciudad) {this.ciudad = ciudad; }

    public  String getDireccion() {return  direccion; }

    public void setDireccion(String direccion) {this.direccion = direccion; }

    public  String getCoordenadaLat() {return  coordenadaLat; }

    public void setCoordenadaLat(String coordenadaLat) {this.coordenadaLat = coordenadaLat; }

    public  String getCoordenadaLon() {return  coordenadaLon; }

    public void setCoordenadaLon(String coordenadaLon) {this.coordenadaLon = coordenadaLon; }

}
