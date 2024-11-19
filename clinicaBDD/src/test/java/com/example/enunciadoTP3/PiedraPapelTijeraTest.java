package com.example.enunciadoTP3;

import org.junit.jupiter.api.Test;
import com.example.enunciadoTP3.PiedraPapelTijera.Jugada;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

public class PiedraPapelTijeraTest {
    PiedraPapelTijera partida = new PiedraPapelTijera();
    final String ganador1 = "Jugador 1";
    final String ganador2 = "Jugador 2";
    final String empate = "Empate";
    final String error = "Error";

    @Test
    public void obtenerGanadorEntreJugadorPiedraYPapel(){
        Jugada jugadorPiedra = Jugada.PIEDRA;
        Jugada jugadorPapel = Jugada.PAPEL;

        String result = partida.jugar(jugadorPiedra, jugadorPapel);

        assertEquals(ganador2, result);
    }

    @Test
    public void obtenerGanadorEntreJugadorPiedraYTijera(){
        Jugada jugadorPiedra = Jugada.PIEDRA;
        Jugada jugadorTijera = Jugada.TIJERA;

        String result = partida.jugar(jugadorPiedra, jugadorTijera);

        assertEquals(ganador1, result);
    }

    @ParameterizedTest
    @EnumSource(Jugada.class)
    public void obtenerEmpateEntreMismosJugadores(Jugada jugada){
        Jugada jugador1 = jugada;
        Jugada jugador2 = jugada;

        String result = partida.jugar(jugador1, jugador2);

        assertEquals(empate, result);
    }

    @Test
    public void obtenerGanadorEntreJugadorPapelYTijera(){
        Jugada jugadorPapel = Jugada.PAPEL;
        Jugada jugadorTijera = Jugada.TIJERA;

        String result = partida.jugar(jugadorPapel, jugadorTijera);

        assertEquals(ganador2, result);
    }

    @Test
    public void obtenerGanadorEntreJugadorPapelYPiedra(){
        Jugada jugadorPapel = Jugada.PAPEL;
        Jugada jugadorPiedra = Jugada.PIEDRA;

        String result = partida.jugar(jugadorPapel, jugadorPiedra);

        assertEquals(ganador1, result);
    }

    @Test
    public void obtenerGanadorEntreJugadorTijeraYPiedra(){
        Jugada jugadorTijera = Jugada.TIJERA;
        Jugada jugadorPiedra = Jugada.PIEDRA;

        String result = partida.jugar(jugadorTijera, jugadorPiedra);

        assertEquals(ganador2, result);
    }

    @Test
    public void obtenerGanadorEntreJugadorTijeraYPapel(){
        Jugada jugadorTijera = Jugada.TIJERA;
        Jugada jugadorPapel = Jugada.PAPEL;

        String result = partida.jugar(jugadorTijera, jugadorPapel);

        assertEquals(ganador1, result);
    }
}
