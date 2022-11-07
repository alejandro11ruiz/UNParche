package com.example.unparche.entidades;

import java.util.ArrayList;

public class Usuario {

    private String email, nombre, apellido, edad, descripcion;
    private ArrayList<String> amigos, actividades, sitios, eventos;

    public Usuario(){
        email="null";
        nombre="null";
        apellido="null";
        edad="null";
        descripcion="null";
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String email){
        this.email=email;
        nombre="null";
        apellido="null";
        edad="null";
        descripcion="null";
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String email, String nombre){
        this.email=email;
        this.nombre=nombre;
        apellido="null";
        edad="null";
        descripcion="null";
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String email, String nombre, String apellido, String edad, String descripcion){
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.descripcion=descripcion;
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String email, String nombre, String apellido, String edad, String descripcion, ArrayList<String> amigos, ArrayList<String> actividades, ArrayList<String> sitios, ArrayList<String> eventos){
        this.email=email;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.descripcion=descripcion;
        this.amigos=amigos;
        this.actividades=actividades;
        this.sitios=sitios;
        this.eventos=eventos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<String> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<String> actividades) {
        this.actividades = actividades;
    }

    public ArrayList<String> getSitios() {
        return sitios;
    }

    public void setSitios(ArrayList<String> sitios) {
        this.sitios = sitios;
    }

    public ArrayList<String> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<String> eventos) {
        this.eventos = eventos;
    }


    public void clearUser(){
        email="null";
        nombre="null";
        apellido="null";
        edad="null";
        descripcion="null";
        if(amigos.size()!=0) amigos.clear();
        if(actividades.size()!=0) actividades.clear();
        if(sitios.size()!=0) sitios.clear();
        if(eventos.size()!=0) eventos.clear();
    }
}
