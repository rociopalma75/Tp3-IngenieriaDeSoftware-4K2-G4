package com.example.clinicaBDD.dominio;

import jdk.jshell.Diag;

import java.util.List;
import java.util.stream.Collectors;

public class HistoriaClinica {
    private List<Diagnostico> diagnosticos;

    public List<Diagnostico> getDiagnosticos(){ return diagnosticos; }
    public HistoriaClinica(List<String> diagnosticos){
        this.diagnosticos = diagnosticos.stream()
                .map(Diagnostico::new) //(nombreDiagnostico -> new Diagnostico(nombreDiagnostico)
                .collect(Collectors.toList());
    }

    public Diagnostico buscarDiagnostico(String nombreDiagnostico){
        Diagnostico diagnosticoEncontrado = this.diagnosticos.stream()
                .filter(diagnostico -> diagnostico.tieneNombre(nombreDiagnostico))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Diagnostico no encontrado"));
        return diagnosticoEncontrado;
    }

    public void agregarEvolucion(String diagnosticoElegido, Doctor doctor, String informe) {
        Diagnostico diagnostico = buscarDiagnostico(diagnosticoElegido);
        diagnostico.agregarEvolucion(doctor, informe);
    }

    public void agregarNuevoDiagnostico(String diagnosticoNuevo){
        this.diagnosticos.add(new Diagnostico(diagnosticoNuevo));
    }
}
