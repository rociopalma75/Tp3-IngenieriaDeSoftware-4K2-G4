package com.example.clinicaBDD.dominio;

public class Evolucion {
    private String informe;
    private Doctor doctor;

    public Evolucion(Doctor doctor, String informe){
        this.informe = informe;
        this.doctor = doctor;
    }

    public boolean tiene(Doctor doctor, String informe){
        return this.informe.equals(informe) && this.doctor.equals(doctor);
    }

}
