package Ludzie;

import Partyjne.Partia;

import java.util.List;
import java.util.Random;
import java.util.Vector;

// Wyborca minimalizujący wartość pewnej cechy wśród wszystkich kandydatów.
public class WyborcaMinimalizującyWszechstronny extends WyborcaOptymalizujący {

    public WyborcaMinimalizującyWszechstronny(String imię, String nazwisko, int cecha) {
        super(imię, nazwisko, cecha);

    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        List<Integer> wybraniKandydaci = kandydaciMinimalni(listaKandydatów);
        Random r = new Random();

        return wybraniKandydaci.get(r.nextInt(wybraniKandydaci.size()));
    }
}
