package com.example.clinicaBDD.app;

import com.example.clinicaBDD.dominio.Doctor;
import com.example.clinicaBDD.dominio.Paciente;
import com.example.clinicaBDD.repositorio.RepositorioPaciente;

public class SistemaClinica {
    private RepositorioPaciente repositorioPaciente;
    public SistemaClinica(RepositorioPaciente repositorioPaciente){
        this.repositorioPaciente = repositorioPaciente;
    }
    public Paciente agregarEvolucion(Doctor doctor,String dniPaciente, String diagnosticoElegido, String informe) {

        Paciente paciente = repositorioPaciente.buscarPaciente(dniPaciente).orElseThrow(()-> new RuntimeException("Paciente inexistente"));

        paciente.agregarEvolucion(diagnosticoElegido, doctor, informe);
        repositorioPaciente.actualizarPaciente(paciente);
        return paciente;
    }

    public Paciente agregarNuevoDiagnostico(String nuevoDiagnostico, String dniPaciente) {
        Paciente paciente = repositorioPaciente.buscarPaciente(dniPaciente).orElseThrow(() -> new RuntimeException("Paciente inexistente"));

        paciente.agregarNuevoDiagnostico(nuevoDiagnostico);

        repositorioPaciente.actualizarPaciente(paciente);
        return paciente;
    }

    public Paciente crearPaciente(String dniPaciente, String nombrePaciente){
        Paciente paciente = repositorioPaciente.crearPaciente(dniPaciente, nombrePaciente);
        return paciente;
    }
}
