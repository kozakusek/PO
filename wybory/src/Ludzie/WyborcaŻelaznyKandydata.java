package Ludzie;

import Partyjne.Partia;

import java.util.Vector;

// Wyborca oddający głos zawsze na tego samego kandydata.
public class WyborcaŻelaznyKandydata extends WyborcaŻelaznyPartyjny {

    private int numerUlubionegoKandydata;

    public WyborcaŻelaznyKandydata(String imię, String nazwisko, String partia, int numer) {
        super(imię, nazwisko, partia);
        numerUlubionegoKandydata = numer;
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int numerPartii = znajdźNumerPartii(listaPartii);
        int liczbaMandatów = listaKandydatów.size() / listaPartii.length;
        int głos = 0;

        for (int i = numerPartii * liczbaMandatów; i < (numerPartii + 1) * liczbaMandatów; i++) {
            if (numerUlubionegoKandydata == listaKandydatów.get(i).podajNumerNaLiście()) {
                głos = i;
                i = (numerPartii + 1) * liczbaMandatów;
            }
        }

        return głos;
    }
}
