package tasks;

import sets.KontenerNaZbiory;
import sets.ZbiórPokrywany;

import java.util.Arrays;

class HeurystykaNaiwna {

    static void pokryj(Integer maks, KontenerNaZbiory zbiory) {
        if (zbiory.ileZbiorów() == 0) {
            System.out.println(0);
            return;
        }

        var doPokrycia = new ZbiórPokrywany(maks);
        var wyjście = new String[zbiory.ileZbiorów()];
        int i = 1;
        int c = 0;

        Arrays.fill(wyjście, " ");

        while (i <= zbiory.ileZbiorów() && !doPokrycia.jestPusty()) {

            if (doPokrycia.jestElement(zbiory.podajPredykat(i))) {
                wyjście[c] = Integer.toString(i);
                doPokrycia.usuńPrzecięcie(zbiory.podajPredykat(i));
                c++;
            }

            i++;
        }

        if (doPokrycia.jestPusty()) {
            System.out.println(String.join(" ", wyjście).trim());
        } else {
            System.out.println(0);
        }
    }
}