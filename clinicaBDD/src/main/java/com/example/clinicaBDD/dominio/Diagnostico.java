package com.example.clinicaBDD.dominio;

import java.util.ArrayList;
import java.util.List;

public class Diagnostico {
    private String nombre;
    private List<Evolucion> evoluciones;

    public String getNombre(){return nombre;}
    public Diagnostico(String nombre){
        this.nombre = nombre;
        this.evoluciones = new ArrayList<>();
    }

    public boolean tieneNombre(String nombre){
        return this.nombre.equals(nombre);
    }

    public boolean tieneEvolucion(Doctor doctor, String informe){
        return evoluciones.stream()
                .anyMatch(evolucion -> evolucion.tiene(doctor, informe));
        //Busca si alguna evolucion tiene el doctor y el informe que yo le paso
    }

    public void agregarEvolucion(Doctor doctor, String informe) {
        Evolucion evolucion = new Evolucion(doctor, informe);
        evoluciones.add(evolucion);
    }
}
