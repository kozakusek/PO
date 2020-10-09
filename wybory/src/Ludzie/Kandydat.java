package Ludzie;

// Klasa reprezentujaca kandydata w bajtockich wyborach.
// Kandydat jest obywatelem identyfikującym się nazwą partii
// do której należy oraz numerem na liście w pewnym okręgu.
public class Kandydat extends ObywatelBajtocji {

    private String partia;
    private int numerNaLiście;

    public Kandydat(String imię, String nazwisko, String partia, int liczbaCech) {
        super(imię, nazwisko, liczbaCech);
        this.partia = partia;
    }

    public String podajPartię() {
        return partia;
    }

    public int podajNumerNaLiście() {
        return numerNaLiście;
    }

    public void zmieńNumerNaLiście(int nowyNumer) {
        numerNaLiście = nowyNumer;
    }

}
