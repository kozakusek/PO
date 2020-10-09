package tasks;

import sets.KontenerNaZbiory;
import sets.ZbiórPokrywany;

import java.util.Collections;
import java.util.Vector;

class WektorPomocniczy extends Vector<String> {

    boolean pokryty;
}

class AlgorytmDokładny {

    private static WektorPomocniczy krótszy(WektorPomocniczy w, WektorPomocniczy v) {
        if (w.pokryty && !v.pokryty)
            return w;
        else if (!w.pokryty && v.pokryty)
            return v;
        else if (!w.pokryty && !v.pokryty)
            return new WektorPomocniczy();
        else if (w.size() <= v.size())
            return w;
        else
            return v;
    }

    private static WektorPomocniczy pokrywanieRekurencyjne(ZbiórPokrywany p,
                                                           KontenerNaZbiory zbiory,
                                                           int nr) {
        if (p.jestPusty()) {
            var wynik = new WektorPomocniczy();
            wynik.pokryty = true;

            return wynik;
        } else {
            if (zbiory.ileZbiorów() > nr) {
                var zawieraIty = pokrywanieRekurencyjne(p.kopia().usuńPrzecięcie(zbiory.podajPredykat(nr)),
                        zbiory, nr + 1);
                zawieraIty.add(Integer.toString(nr));
                var nieZawieraItego = pokrywanieRekurencyjne(p, zbiory, nr + 1);

                return krótszy(zawieraIty, nieZawieraItego);
            } else {
                var wynik = new WektorPomocniczy();
                p.usuńPrzecięcie(zbiory.podajPredykat(nr));

                if (p.jestPusty()) {
                    wynik.pokryty = true;
                    wynik.add(Integer.toString(nr));
                }

                return wynik;
            }
        }
    }

    static void pokryj(Integer maks, KontenerNaZbiory zbiory) {
        if (zbiory.ileZbiorów() == 0) {
            System.out.println(0);
            return;
        }

        var wynik = new Vector<String>();
        var doPokrycia = new ZbiórPokrywany(maks);

        wynik = pokrywanieRekurencyjne(doPokrycia, zbiory, 1);

        if (wynik.isEmpty()) {
            System.out.println(0);
        } else {
            Collections.reverse(wynik);
            System.out.println(String.join(" ", wynik).trim());
        }
    }
}