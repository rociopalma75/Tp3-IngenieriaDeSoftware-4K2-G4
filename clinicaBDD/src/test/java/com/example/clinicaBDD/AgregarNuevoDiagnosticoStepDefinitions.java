package com.example.clinicaBDD;

import com.example.clinicaBDD.app.SistemaClinica;
import com.example.clinicaBDD.dominio.Diagnostico;
import com.example.clinicaBDD.dominio.Paciente;
import com.example.clinicaBDD.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class AgregarNuevoDiagnosticoStepDefinitions {
    private RepositorioPaciente repositorioPaciente;
    private SistemaClinica sistemaClinica;
    private String dniPaciente;
    private Paciente pacienteResultante;
    private String nuevoDiagnostico;

    @Before
    public void SetUp(){
        this.dniPaciente = null;
        this.pacienteResultante = null;
        this.nuevoDiagnostico = null;
        this.repositorioPaciente = mock(RepositorioPaciente.class);
        this.sistemaClinica = new SistemaClinica(repositorioPaciente);
    }

    @Given("el listado de diagnosticos del paciente con dni {string}")
    public void elListadoDeDiagnosticosDelPacienteConDni(String dniPaciente, List<String> diagnosticos) {
        this.dniPaciente = dniPaciente;
        Paciente paciente = new Paciente(dniPaciente, "Rocio", diagnosticos);
        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(paciente));
    }

    @When("el medico agrega el diagnostico {string}")
    public void elMedicoAgregaElDiagnostico(String nuevoDiagnostico) {
        this.nuevoDiagnostico = nuevoDiagnostico;
        pacienteResultante = sistemaClinica.agregarNuevoDiagnostico(nuevoDiagnostico, dniPaciente);
    }


    @Then("el diagnostico es incorporado en la lista de diagnosticos perteneciente a la historia clinica del paciente")
    public void elDiagnosticoEsIncorporadoEnLaListaDeDiagnosticosPertenecienteALaHistoriaClinicaDelPaciente() {
        List<Diagnostico> diagnosticos = pacienteResultante.obtenerDiagnosticos();
        assertThat(diagnosticos.stream().anyMatch(d -> d.tieneNombre(nuevoDiagnostico))).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }
}
