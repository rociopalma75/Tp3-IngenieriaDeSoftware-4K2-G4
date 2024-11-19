Feature: Agregar Nuevo Diagnostico

    Esta feature describe el comportamiento al agregar un nuevo diagnostico a la historia clinica del paciente.

    Scenario: El Medico Agrega un Nuevo Diagnostico a la Lista de Diagnosticos
        Given el listado de diagnosticos del paciente con dni "44105560"
            | Diagnostico   |
            | Dengue        |
            | Covid 19      |
            | Diabetes      |
        When el medico agrega el diagnostico "Coartacion Aortica"
        Then el diagnostico es incorporado en la lista de diagnosticos perteneciente a la historia clinica del paciente