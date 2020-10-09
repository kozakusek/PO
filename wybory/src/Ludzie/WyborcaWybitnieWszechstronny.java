package Ludzie;

import Partyjne.Działanie;
import Partyjne.Partia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

// Wyborca wybierający z pośród wszystkich kandydatów tego
// z maksymalną sumą ważoną cech odpowiadjącą wagom.
public class WyborcaWybitnieWszechstronny extends Wyborca {


    public WyborcaWybitnieWszechstronny(String imię, String nazwisko, int[] wektorWag) {
        super(imię, nazwisko, wektorWag.length);
        wagiCech = wektorWag;
    }

    // Wylicza sumę ważoną dla danego kandydata.
    protected int znajdźSumęWażoną(Kandydat k) {
        int suma = 0;

        for (int i = 0; i < wagiCech.length; i++) {
            suma += wagiCech[i] * k.podajWagęCechy(i);
        }

        return suma;
    }

    // Wybiera kandydatów z maksymalną sumą ważoną.
    protected List<Integer> wybórKandydatów(int[] sumyWażone) {
        List<Integer> listaWybranych = new ArrayList<>();
        int maksimum = sumyWażone[0];

        for (int i : sumyWażone) {
            maksimum = Math.max(maksimum, i);
        }

        for (int i = 0; i < sumyWażone.length; i++) {
            if (sumyWażone[i] == maksimum)
                listaWybranych.add(i);
        }

        return listaWybranych;
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int[] sumyWażone = new int[listaKandydatów.size()];
        List<Integer> wybraniKandydaci;
        Random r = new Random();

        for (int i = 0; i < listaKandydatów.size(); i++) {
            sumyWażone[i] = znajdźSumęWażoną(listaKandydatów.get(i));
        }

        wybraniKandydaci = wybórKandydatów(sumyWażone);

        return wybraniKandydaci.get(r.nextInt(wybraniKandydaci.size()));
    }

    @Override
    public void poddajSięDziałaniu(Działanie d) {
        int[] wektorZmiany = d.podajWektor();

        modyfikujWagiCechOWektor(wektorZmiany);
    }

}
