package Ludzie;

import Partyjne.Działanie;
import Partyjne.Partia;

import java.util.Vector;

// Klasa ogólne opsiujaca wyborcę w Bajtocjii.
// Udostępnia możliwość oddaania głosu.
public abstract class Wyborca extends ObywatelBajtocji {

    public Wyborca(String imię, String nazwisko, int liczbaCech) {
        super(imię, nazwisko, liczbaCech);
    }

    // Przyjmuje listy kandydatów i partii
    // Zwraca indeks wybranego kandadata z listy
    public abstract int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii);

    public void poddajSięDziałaniu(Działanie d) {
        // Wyborcy podatni na działania partii nadpisują tę metodę.
    }

}
