package Partyjne;

import Ludzie.Kandydat;
import Ludzie.Wyborca;
import Ludzie.WyborcaWybitnieWszechstronny;
import Urzędowe.OkręgWyborczy;

// Ponieważ kandydaci są z jednej partii to prawdopodobnie mają podobne poglądy.
// Możemy więc uśrednić ich cech.
// Następnie będziemy wykonywać działanie,
// które najbardziej zwiększy sumę dla średniego kandydata.
// Wiemy dodatkowo, że kandydat niewszechtronny ma wagi cech równe 0.
// Skorzsytamy z tego redukując rozmiar zmiany.
// Jeżeli działanie zmniejsza wagę danej cechy, która jest ujemna
// u kandydata to dobrze. Partia o tym wie.
// Poza tym interesuje nas tylko wpływanie na wyborców wszechstronnych,
// którzy jeszcze nie preferują naszej partii.
public class PartiaWłasna extends PartiaZachłanna {

    private int[] średniWektorKandydata;

    public PartiaWłasna(String nazwa, int budżet) {
        super(nazwa, budżet);
    }

    @Override
    protected int oIleZmieniaSumę(OkręgWyborczy o, Działanie d) {
        int zmiana = 0;
        int[] wektorDziałania = d.podajWektor();
        int liczbaKandydatów = 0;
        WyborcaWybitnieWszechstronny egz = new WyborcaWybitnieWszechstronny("","", wektorDziałania);

        średniWektorKandydata = new int[d.podajWektor().length];

        for (Kandydat k : o.udostępnijKandydatów()) {
            if (k.podajPartię().equals(nazwa)) {
                for (int i = 0; i < średniWektorKandydata.length; i++) {
                    średniWektorKandydata[i] += k.podajWagęCechy(i);
                }
                liczbaKandydatów++;
            }
        }

        for (int i = 0; i < średniWektorKandydata.length; i++) {
            średniWektorKandydata[i] /= liczbaKandydatów;
        }

        for (Wyborca w : o.udostępnijWyborców()) {
            for (int i = 0; i < wektorDziałania.length; i++) {
                if (w.getClass() == egz.getClass())
                    zmiana += w.podajWagęCechy(i) * wektorDziałania[i] * średniWektorKandydata[i];
            }
        }

        return zmiana;
    }

}
