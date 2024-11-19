package com.example.enunciadoTP3;

public class PiedraPapelTijera {
    public enum Jugada{
        PIEDRA,
        PAPEL,
        TIJERA
    }

    public String jugar(Jugada jugador1, Jugada jugador2){
        final String ganador1 = "Jugador 1";
        final String ganador2 = "Jugador 2";
        final String empate = "Empate";
        final String error = "Error";

        switch(jugador1){
            case PIEDRA ->{
                if(jugador2 == Jugada.PAPEL) return ganador2;
                if(jugador2 == Jugada.TIJERA) return ganador1;
                else return empate;
            }
            case PAPEL -> {
                if(jugador2 == Jugada.TIJERA) return ganador2;
                if(jugador2 == Jugada.PIEDRA) return ganador1;
                else return empate;
            }
            case TIJERA -> {
                if (jugador2 == Jugada.PIEDRA) return ganador2;
                if (jugador2 == Jugada.PAPEL) return ganador1;
                else return empate;
            }
            default -> {return error;}
        }
    }
}
