package Ludzie;

import Partyjne.Partia;

// Klasa ogólna zawężająca zakres rozważanych przez
// WyborcęOptymalizującego kandydatów do jednej, określanej
// przez nazwę ulubionaPartia partii.
public abstract class WyborcaOptymalizującyPartyjny extends WyborcaOptymalizujący {

    protected String ulubionaPartia;

    public WyborcaOptymalizującyPartyjny(String imię, String nazwisko, int cecha, String partia) {
        super(imię, nazwisko, cecha);
        ulubionaPartia = partia;
    }

    // Zwraca numer ulubionej partii wyborcy na liscie wszystkich partii
    // w celu ułatwienia mu poszukiwania jej na liscie kandydatów.
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

}
