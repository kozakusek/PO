package Partyjne;

// Klasa przechowywująca opis konkretnego działania w kampanii.
// Krotka (klucz, nr okręgu, nr działania).
// Interpretacja klucza zależy od strategii partii.
// Sortowalna po wartosci klucza malejąco.
public class OpisDziałania implements Comparable<OpisDziałania> {

    public Integer klucz;
    public Integer okręg;
    public Integer działanie;

    public OpisDziałania(Integer klucz, Integer okręg ,Integer działanie) {
        this.klucz = klucz;
        this.okręg = okręg;
        this.działanie = działanie;
    }

    @Override
    public int compareTo(OpisDziałania opisDziałania) {
        return - klucz.compareTo(opisDziałania.klucz) ;
    }
}
