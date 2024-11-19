package com.example.clinicaBDD;

import com.example.clinicaBDD.app.SistemaClinica;
import com.example.clinicaBDD.dominio.Diagnostico;
import com.example.clinicaBDD.dominio.Doctor;
import com.example.clinicaBDD.dominio.Paciente;
import com.example.clinicaBDD.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class EvolucionTextoSimpleStepDefinitions {
    private Doctor doctor;
    private String dniPaciente;
    private String informe;
    private String diagnosticoElegido;
    private Paciente pacienteResultante;
    //Sistema clinica -> Servicio -> Controlador de caso de uso -> Controlador de Fachada
    private SistemaClinica sistemaClinica;
    //repositorio -> base de datos -> simular una db -> mockear una db
    private RepositorioPaciente repositorioPaciente;

    @Before
    public void setUp(){
        this.doctor = null;
        this.dniPaciente = null;
        this.diagnosticoElegido = null;
        this.pacienteResultante = null;
        this.repositorioPaciente = mock(RepositorioPaciente.class);
        sistemaClinica = new SistemaClinica(repositorioPaciente);
    }

    @Given("el medico {string} ha iniciado sesion")
    public void elMedicoHaIniciadoSesion(String nombreDoctor) {
        doctor = new Doctor(nombreDoctor);
    }

    @And("ha buscado la historia clinica del paciente {string} que posee los siguientes diagnosticos.")
    public void haBuscadoLaHistoriaClinicaDelPacienteQuePoseeLosSiguientesDiagnosticos(String dniPaciente, List<String> diagnosticos) {
        this.dniPaciente = dniPaciente;
        Paciente paciente = new Paciente(dniPaciente, "Juan Doe", diagnosticos);

        when(repositorioPaciente.buscarPaciente(dniPaciente)).thenReturn(Optional.of(paciente));
    }

    @When("el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico {string} que dice {string}")
    public void elDoctorEscribeParaElPacientePreviamenteBuscadoUnInformeSobreElDiagnosticoQueDice(String nombreDiagnostico, String informe) {
        this.diagnosticoElegido = nombreDiagnostico;
        this.informe = informe;
    }

    @And("el medico guarda la evolucion")
    public void elMedicoGuardaLaEvolucion() {
        pacienteResultante = sistemaClinica.agregarEvolucion(doctor,dniPaciente,diagnosticoElegido,informe);
    }

    @Then("se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el medico.")
    public void seRegistraLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoLaDescripcionYElMedico() {
        Diagnostico diagnostico = pacienteResultante.buscarDiagnostico(diagnosticoElegido);
        assertThat(diagnostico.tieneEvolucion(doctor, informe)).isTrue();
        verify(repositorioPaciente, times(1)).actualizarPaciente(any());
    }
}
