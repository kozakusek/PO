package tasks;

import sets.KontenerNaZbiory;
import sets.ZbiórPokrywany;

import java.util.Arrays;
import java.util.Comparator;

class HeurystykaZachłanna {


    static void pokryj(Integer maks, KontenerNaZbiory zbiory) {
        if (zbiory.ileZbiorów() == 0) {
            System.out.println(0);
            return;
        }

        var doPokrycia = new ZbiórPokrywany(maks);
        var wyjście = new String[zbiory.ileZbiorów()];
        int c = 0;

        Arrays.fill(wyjście, " ");

        Integer najlepszyZbiór = zbiory.największePrzecięcie(doPokrycia);
        while (najlepszyZbiór != null && !doPokrycia.jestPusty()) {
            doPokrycia.usuńPrzecięcie(zbiory.podajPredykat(najlepszyZbiór));
            wyjście[c] = Integer.toString(najlepszyZbiór);
            c++;
            najlepszyZbiór = zbiory.największePrzecięcie(doPokrycia);
        }

        var komparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(" "))
                    return 1;
                else if (o2.equals(" "))
                    return -1;
                else
                    return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
            }
        };

        if (doPokrycia.jestPusty()) {
            Arrays.sort(wyjście, komparator);
            System.out.println(String.join(" ", wyjście).trim());
        } else {
            System.out.println(0);
        }
    }
}