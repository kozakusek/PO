package Ludzie;

import Partyjne.Partia;

import java.util.List;
import java.util.Random;
import java.util.Vector;

// Wyborca minimalizujący wartość pewnej cechy wśród kandydatów ulubionej partii.
public class WyborcaMinimalizującyPartyjny extends WyborcaOptymalizującyPartyjny {

    public WyborcaMinimalizującyPartyjny(String imię, String nazwisko, int cecha, String partia) {
        super(imię, nazwisko, cecha, partia);
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int numerPartii = znajdźNumerPartii(listaPartii);
        int liczbaMandatów = listaKandydatów.size() / listaPartii.length;
        int a = numerPartii * liczbaMandatów;
        int b = (numerPartii + 1) * liczbaMandatów;

        List<Integer> wybraniKandydaci = kandydaciMinimalni(listaKandydatów.subList(a, b));
        Random r = new Random();

        return a + wybraniKandydaci.get(r.nextInt(wybraniKandydaci.size()));
    }
}
