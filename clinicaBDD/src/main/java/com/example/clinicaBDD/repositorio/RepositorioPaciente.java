package com.example.clinicaBDD.repositorio;

import com.example.clinicaBDD.dominio.Paciente;

import java.util.Optional;

public interface RepositorioPaciente {
    Optional<Paciente> buscarPaciente(String dniPaciente);
    void actualizarPaciente(Paciente paciente);
    Paciente crearPaciente(String dniPaciente, String nombrePaciente);
}
