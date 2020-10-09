package sets;

import java.util.function.Predicate;

class ZbiórNieskończony extends Zbiór {

    private Integer pierwszyElement;
    private Integer różnica;

    ZbiórNieskończony(Integer a, Integer b) {
        pierwszyElement = a;
        różnica = -b;
    }

    @Override
    boolean zawieraElement(Integer n) {
        if (n < pierwszyElement)
            return false;
        else
            return ((n - pierwszyElement) % różnica) == 0;
    }

    @Override
    Predicate podajPredykat() {
        return i -> zawieraElement((Integer) i);
    }
}