package tasks;

import sets.KontenerNaZbiory;

public class Zapytanie {

    public void wykonaj(KontenerNaZbiory zbiory, Integer a, Integer b) {
        switch (b) {
            case 1:
                AlgorytmDokładny.pokryj(-a, zbiory);
                break;
            case 2:
                HeurystykaZachłanna.pokryj(-a, zbiory);
                break;
            case 3:
                HeurystykaNaiwna.pokryj(-a, zbiory);
                break;
        }
    }
}