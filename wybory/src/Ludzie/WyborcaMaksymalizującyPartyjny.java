package Ludzie;

import Partyjne.Partia;

import java.util.List;
import java.util.Random;
import java.util.Vector;

// Wyborca maksymalizujący wartość pewnej cechy wśród kandydatów ulubionej partii.
public class WyborcaMaksymalizującyPartyjny extends WyborcaOptymalizującyPartyjny {

    public WyborcaMaksymalizującyPartyjny(String imię, String nazwisko, int cecha, String partia) {
        super(imię, nazwisko, cecha, partia);
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int numerPartii = znajdźNumerPartii(listaPartii);
        int liczbaMandatów = listaKandydatów.size() / listaPartii.length;
        int a = numerPartii * liczbaMandatów;
        int b = (numerPartii + 1) * liczbaMandatów;

        List<Integer> wybraniKandydaci = kandydaciMaksymalni(listaKandydatów.subList(a, b));
        Random r = new Random();

        return wybraniKandydaci.get(r.nextInt(wybraniKandydaci.size()));
    }

}
