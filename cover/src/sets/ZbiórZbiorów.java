package sets;

import java.util.HashSet;
import java.util.function.Predicate;

class ZbiórZbiorów extends Zbiór {

    private HashSet<Zbiór> podzbiory;
    private ZbiórZElementów zElementówPodzbiór;

    ZbiórZbiorów() {
        podzbiory = new HashSet<Zbiór>(4);
        zElementówPodzbiór = new ZbiórZElementów();
    }

    void dodajPodzbiór() {
        var nowy = new ZbiórPusty();
        podzbiory.add(nowy);
    }

    void dodajPodzbiór(Integer a, Integer b) {
        var nowy = new ZbiórNieskończony(a, b);
        podzbiory.add(nowy);
    }

    void dodajPodzbiór(Integer a, Integer b, Integer c) {
        var nowy = new ZbiórSkończony(a, b, c);
        podzbiory.add(nowy);
    }

    void dodajPodzbiór(Integer a) {
        zElementówPodzbiór.dodajElement(a);
    }

    @Override
    boolean zawieraElement(Integer n) {
        for (Zbiór z : podzbiory) {
            if (z.zawieraElement(n))
                return true;
        }

        return zElementówPodzbiór.zawieraElement(n);
    }

    @Override
    Predicate podajPredykat() {
        return i -> zawieraElement((Integer) i);
    }
}
