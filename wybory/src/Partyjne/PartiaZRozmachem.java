package Partyjne;

import Urzędowe.OkręgWyborczy;

// Partia wybierająca najdroższe dostępne działanie.
// Kluczem w segregatorze jest koszt działania.
public class PartiaZRozmachem extends Partia {

    public PartiaZRozmachem(String nazwa, int budżet) {
        super(nazwa, budżet);
    }

    @Override
    protected void inicjalizujSegregatorDziałań(OkręgWyborczy[] okręgi, Działanie[] działania) {
        int koszt;

        for (int i = 0; i < okręgi.length; i++) {
            for (int j = 0; j < działania.length; j++) {
                koszt = okręgi[i].podajLiczbęWyborców() * działania[j].podajKoszt();
                segregatorDziałań.add(new OpisDziałania(koszt, i, j));
            }
        }

        segregatorDziałań.sort(OpisDziałania::compareTo);
    }

    @Override
    protected void ustalBieżącyIndeks() {
        while (budżet < segregatorDziałań.get(bieżącyIndeks).klucz)
            bieżącyIndeks++;
    }

    @Override
    protected int kosztDziałania() {
        return segregatorDziałań.get(bieżącyIndeks).klucz;
    }

}
