package com.example.clinicaBDD.dominio;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String dni;
    private String nombre;
    private HistoriaClinica historiaClinica;

    public String getDni(){ return dni; }
    public String getNombre() { return nombre; }

    public HistoriaClinica getHistoriaClinica(){return historiaClinica;}

    public Paciente(String dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
        this.historiaClinica = new HistoriaClinica(new ArrayList<>());
    }

    public Paciente(String dni, String nombre, List<String> diagnosticosPreexistentes){
        this.dni = dni;
        this.nombre = nombre;
        this.historiaClinica = new HistoriaClinica(diagnosticosPreexistentes);
    }

    public Diagnostico buscarDiagnostico(String nombreDiagnostico){
        return this.historiaClinica.buscarDiagnostico(nombreDiagnostico);
    }

    public void agregarEvolucion(String diagnosticoElegido, Doctor doctor, String informe){
        this.historiaClinica.agregarEvolucion(diagnosticoElegido, doctor, informe);
    }

    public void agregarNuevoDiagnostico(String diagnosticoNuevo){
        this.historiaClinica.agregarNuevoDiagnostico(diagnosticoNuevo);
    }

    public List<Diagnostico> obtenerDiagnosticos(){
        return this.historiaClinica.getDiagnosticos();
    }
}
