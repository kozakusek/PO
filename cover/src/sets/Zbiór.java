package sets;

import java.util.function.Predicate;

abstract class Zbiór {

    abstract boolean zawieraElement(Integer n);

    abstract Predicate podajPredykat();
}
