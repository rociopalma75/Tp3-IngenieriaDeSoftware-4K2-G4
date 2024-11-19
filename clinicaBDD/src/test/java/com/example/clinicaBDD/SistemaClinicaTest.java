package com.example.clinicaBDD;


import com.example.clinicaBDD.app.SistemaClinica;
import com.example.clinicaBDD.dominio.Diagnostico;
import com.example.clinicaBDD.dominio.Doctor;
import com.example.clinicaBDD.dominio.Paciente;
import com.example.clinicaBDD.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SistemaClinicaTest {
    private SistemaClinica sistemaClinica;
    private RepositorioPaciente repositorioPaciente;
    private List<String> diagnosticos = new ArrayList<>();
    private String dniPaciente = "12345678";
    private String nombrePaciente = "Sabrina";

    @BeforeEach
    public void SetUp(){
        this.repositorioPaciente = mock(RepositorioPaciente.class);
        this.sistemaClinica = new SistemaClinica(repositorioPaciente);
        this.diagnosticos.add("Dengue");
        this.diagnosticos.add("Covid");
        this.diagnosticos.add("Gripe");
    }

    @Test
    public void verificarCrearPacienteConDatosValidos(){
        Paciente pacienteSimulado = new Paciente(dniPaciente, nombrePaciente); //Paciente esperado
        when(repositorioPaciente.crearPaciente(dniPaciente, nombrePaciente)).thenReturn(pacienteSimulado);

        Paciente pacienteResultado = sistemaClinica.crearPaciente(dniPaciente, nombrePaciente);

        assertEquals(pacienteSimulado.getDni(), pacienteResultado.getDni());
        assertEquals(pacienteSimulado.getNombre(), pacienteResultado.getNombre());
        assertEquals(pacienteSimulado.getHistoriaClinica(), pacienteResultado.getHistoriaClinica());

        verify(repositorioPaciente, times(1)).crearPaciente(dniPaciente, nombrePaciente);
    }

    @Test
    public void verificarAgregarUnNuevoDiagnosticoALaListaDeDiagnosticosDeLaHistoriaClinicaDelPaciente(){
        String nuevoDiagnostico = "Gripe2";
        Paciente pacienteSimulado = new Paciente(dniPaciente,nombrePaciente, diagnosticos);
        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(pacienteSimulado));

        Paciente pacienteResultado = sistemaClinica.agregarNuevoDiagnostico(nuevoDiagnostico, dniPaciente);

        assertThat(pacienteResultado.obtenerDiagnosticos().stream().anyMatch(d -> d.tieneNombre(nuevoDiagnostico))).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }

    @Test
    public void verificarAgregarNuevaEvolucionAUnDiagnosticoExistente(){
        String informe = "Nuevo cuadro de fiebre";
        Doctor doctor = new Doctor("Lourdes");
        String diagnosticoElegido = "Dengue";
        Paciente pacienteSimulado = new Paciente(dniPaciente, "Rocio", diagnosticos);
        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(pacienteSimulado));

        Paciente pacienteResultante = sistemaClinica.agregarEvolucion(doctor, dniPaciente,diagnosticoElegido,informe);

        assertThat(pacienteResultante.buscarDiagnostico(diagnosticoElegido).tieneEvolucion(doctor, informe)).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }
}