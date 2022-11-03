package com.example.unparche.entidades;

import java.util.ArrayList;

public class Usuario {

    private String nick, nombre, apellido, edad, descripcion;
    private ArrayList<String> amigos, actividades, sitios, eventos;

    public Usuario(){
        nick="";
        nombre="";
        apellido="";
        edad="";
        descripcion="";
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String nick, String nombre){
        this.nick=nick;
        this.nombre=nombre;
        apellido="";
        edad="";
        descripcion="";
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String nick, String nombre, String apellido, String edad, String descripcion){
        this.nick=nick;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.descripcion=descripcion;
        amigos=new ArrayList<>();
        actividades=new ArrayList<>();
        sitios=new ArrayList<>();
        eventos=new ArrayList<>();
    }

    public Usuario(String nick, String nombre, String apellido, String edad, String descripcion, ArrayList<String> amigos, ArrayList<String> actividades, ArrayList<String> sitios, ArrayList<String> eventos){
        this.nick=nick;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.descripcion=descripcion;
        this.amigos=amigos;
        this.actividades=actividades;
        this.sitios=sitios;
        this.eventos=eventos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
        nick="";
        nombre="";
        apellido="";
        edad="";
        descripcion="";
        if(amigos.size()!=0) amigos.clear();
        if(actividades.size()!=0) actividades.clear();
        if(sitios.size()!=0) sitios.clear();
        if(eventos.size()!=0) eventos.clear();
    }
}
