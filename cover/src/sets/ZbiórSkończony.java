package sets;

import java.util.function.Predicate;

class ZbiórSkończony extends Zbiór {

    private Integer pierwszyElement;
    private Integer różnica;
    private Integer ostatniElement;

    ZbiórSkończony(Integer a, Integer b, Integer c) {
        pierwszyElement = a;
        różnica = -b;
        if (-c >= a)
            ostatniElement = a - (((a + c) / różnica) * różnica);
        else
            ostatniElement = 0;
    }

    @Override
    boolean zawieraElement(Integer n) {
        if (n < pierwszyElement || ostatniElement < n)
            return false;
        else
            return (n - pierwszyElement) % różnica == 0;
    }

    @Override
    Predicate podajPredykat() {
        return i -> zawieraElement((Integer) i);
    }
}