package Ludzie;

import Partyjne.Partia;

import java.util.List;
import java.util.Random;
import java.util.Vector;

//  Wyborca wybierający z pośród wszystkich kandydatów jednej partii
// tego z maksymalną sumą ważoną cech odpowiadjącą wagom.
public class WyborcaPartyjnieWszechstronny extends  WyborcaWybitnieWszechstronny {

    private String ulubionaPartia;

    public WyborcaPartyjnieWszechstronny(String imię, String nazwisko, int[] wektorWag, String partia) {
        super(imię, nazwisko, wektorWag);
        ulubionaPartia = partia;
    }


    private int znajdźNumerPartii(Partia[] listaPartii) {
        int numerPartii = 0;

        for (int i = 0; i < listaPartii.length; i++) {
            if (listaPartii[i].podajNazwę().equals(ulubionaPartia)) {
                numerPartii = i;
                i = listaPartii.length;
            }
        }

        return numerPartii;
    }

    @Override
    public int oddajGłos(Vector<Kandydat> listaKandydatów, Partia[] listaPartii) {
        int numerPartii = znajdźNumerPartii(listaPartii);
        int liczbaMandatów = listaKandydatów.size() / listaPartii.length;
        int a = numerPartii * liczbaMandatów;
        int b = (numerPartii + 1) * liczbaMandatów;
        int[] sumyWażone = new int[liczbaMandatów];
        Random r = new Random();

        for (int i = 0; i < b - a; i++) {
            sumyWażone[i] = znajdźSumęWażoną(listaKandydatów.get(a + i));
        }

        List<Integer> wybraniKandydaci = wybórKandydatów(sumyWażone);

        return a + wybraniKandydaci.get(r.nextInt(wybraniKandydaci.size()));
    }

}
