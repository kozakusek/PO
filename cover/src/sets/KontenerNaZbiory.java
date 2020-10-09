package sets;

import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.Vector;
import java.util.function.Predicate;

public class KontenerNaZbiory {

    private Vector<ZbiórZbiorów> zbiory;

    public KontenerNaZbiory() {
        zbiory = new Vector<ZbiórZbiorów>();
    }

    public void dodaj(Vector<Integer> dane) {

        var nowy = new ZbiórZbiorów();
        int[] abc = new int[3];
        boolean początek = true;
        int i = 0;

        if (dane.get(0) == 0) {
            nowy.dodajPodzbiór();
        } else {
            for (int l : dane) {
                if (l > 0 && początek) {
                    początek = false;
                    abc[i] = l;
                    i++;
                } else if (l > 0) {
                    if (i == 1) {
                        nowy.dodajPodzbiór(abc[0]);
                    } else if (i == 2) {
                        nowy.dodajPodzbiór(abc[0], abc[1]);
                    } else {
                        nowy.dodajPodzbiór(abc[0], abc[1], abc[2]);
                    }
                    i = 0;
                    abc[i] = l;
                    i++;
                } else if (l < 0) {
                    abc[i] = l;
                    i++;
                }
            }

            if (i == 1) {
                nowy.dodajPodzbiór(abc[0]);
            } else if (i == 2) {
                nowy.dodajPodzbiór(abc[0], abc[1]);
            } else {
                nowy.dodajPodzbiór(abc[0], abc[1], abc[2]);
            }
        }

        zbiory.add(nowy);
    }

    public int ileZbiorów() {
        return zbiory.size();
    }

    public Predicate podajPredykat(int i) {
        return zbiory.get(i - 1).podajPredykat();
    }

    public Integer największePrzecięcie(ZbiórPokrywany z) {
        var mapa = new TreeMap<Integer, Integer>();

        for (int i = zbiory.size(); i >= 1; i--) {
            mapa.put(-z.mocPrzecięcia(zbiory.get(i - 1)), i);
        }

        try {
            if (mapa.firstKey() < 0)
                return mapa.get(mapa.firstKey());
            else
                return null;
        } catch (NoSuchElementException e) {
            return null;
        }

    }
}