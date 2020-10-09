package sets;

import java.util.function.Predicate;

public class ZbiórPusty extends Zbiór {

    @Override
    boolean zawieraElement(Integer n) {
        return false;
    }

    @Override
    Predicate podajPredykat() {
        return i -> false;
    }
}
