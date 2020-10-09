package Partyjne;

import Urzędowe.OkręgWyborczy;

// Partia wybierająca najtańsze działanie,
// czyli w najmniejszym okręgu i o najmniejszym koszcie.
// W segregtorze działań przechowuje więc tylko jedno,
// a jego indeks to zawsze 0;
// Koszt działania jest stały.
public class PartiaSkromna extends Partia {

    private int kosztDziałania;

    public PartiaSkromna(String nazwa, int budżet) {
        super(nazwa, budżet);
    }

    @Override
    protected void inicjalizujSegregatorDziałań(OkręgWyborczy[] okręgi, Działanie[] działania) {
        kosztDziałania = działania[0].podajKoszt() * okręgi[0].podajLiczbęWyborców();
        segregatorDziałań.add(new OpisDziałania(kosztDziałania,0, 0));
    }

    @Override
    protected void ustalBieżącyIndeks() {
        bieżącyIndeks = 0;
    }

    @Override
    protected int kosztDziałania() {
        return kosztDziałania;
    }
}
