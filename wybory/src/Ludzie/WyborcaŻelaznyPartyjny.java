package Ludzie;

import Partyjne.Partia;

import java.util.Random;
import java.util.Vector;

// Wyborca oddający głos na losowego kandydata z listy ulubionejPartii
public class WyborcaŻelaznyPartyjny extends Wyborca {

    protected String ulubionaPartia;

    protected int znajdźNumerPartii(Partia[] listaPartii) {
        int numerPartii = 0;

        for (int i = 0; i < listaPartii.length; i++) {
            if (listaPartii[i].podajNazwę().equals(ulubionaPartia)) {
                numerPartii = i;
                i = listaPartii.length;
            }
        }

        return numerPartii;
    }

    public WyborcaŻelaznyPartyjny(String imię, String nazwisko, String partia) {
        super(imię, nazwisko, 0);
        ulubionaPartia = partia;
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int numerPartii = znajdźNumerPartii(listaPartii);
        int liczbaMandatów = listaKandydatów.size() / listaPartii.length;
        Random r = new Random();

        return numerPartii * liczbaMandatów + (r.nextInt(liczbaMandatów));
    }
}
