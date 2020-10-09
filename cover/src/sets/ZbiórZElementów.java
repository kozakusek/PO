package sets;

import java.util.HashSet;
import java.util.function.Predicate;

class ZbiórZElementów extends Zbiór {

    HashSet<Integer> zbiór;

    ZbiórZElementów() {
        zbiór = new HashSet<Integer>(4);
    }

    void dodajElement(Integer a) {
        zbiór.add(a);
    }

    @Override
    boolean zawieraElement(Integer n) {
        return zbiór.contains(n);
    }

    @Override
    Predicate podajPredykat() {
        return i -> zawieraElement((Integer) i);
    }
}