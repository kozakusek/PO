package Partyjne;

import Ludzie.Kandydat;
import Urzędowe.OkręgWyborczy;

// Partia starająca sie maksymalizować zmiany przy wyborze działań.
// Kluczem w segregatorze jest to o ile zmenia się suma ważona
// wszystkich kandydatów z partii w okręgu.
public class PartiaZachłanna extends Partia {

    protected int[] kosztaDziałań;

    public PartiaZachłanna(String nazwa, int budżet) {
        super(nazwa, budżet);
    }

    protected int oIleZmieniaSumę(OkręgWyborczy o, Działanie d) {
        int zmiana = 0;
        int[] wektorDziałania = d.podajWektor();

        for (Kandydat k : o.udostępnijKandydatów()) {
            for (int i = 0; i < wektorDziałania.length; i++) {
                if (k.podajPartię().equals(nazwa)) {
                    zmiana += (wektorDziałania[i] * k.podajWagęCechy(i));
                }
            }
        }

        return zmiana;
    }

    @Override
    protected void inicjalizujSegregatorDziałań(OkręgWyborczy[] okręgi, Działanie[] działania) {
        int zmiana;

        for (int i = 0; i < okręgi.length; i++) {
            for (int j = 0; j < działania.length; j++) {
                zmiana = oIleZmieniaSumę(okręgi[i], działania[j]);
                segregatorDziałań.add(new OpisDziałania(zmiana, i, j));
            }
        }

        segregatorDziałań.sort(OpisDziałania::compareTo);
        kosztaDziałań = new int[okręgi.length * działania.length];

        for (int i = 0; i < kosztaDziałań.length; i++) {
            kosztaDziałań[i] = okręgi[segregatorDziałań.get(i).okręg].podajLiczbęWyborców();
            kosztaDziałań[i] *= działania[segregatorDziałań.get(i).działanie].podajKoszt();
        }
    }

    @Override
    protected int kosztDziałania() {
        return kosztaDziałań[bieżącyIndeks];
    }

    @Override
    protected void ustalBieżącyIndeks() {
        bieżącyIndeks = 0;

        while (kosztDziałania() > budżet)
            bieżącyIndeks++;

    }


}
