package Ludzie;

import java.util.ArrayList;
import java.util.List;

// Klasa ogólna reprezentująca wyborców maksymalizujących
// lub minializujących wartość wybranej cechy.
// Udostępnia podklasom metody szukające odpowiednich dla nich
// kandydatów w zbiorze, a także wprowadza indeks wybranej cechy.
public abstract class WyborcaOptymalizujący extends Wyborca {

    protected int najistotniejszaCecha;

    public WyborcaOptymalizujący(String imię, String nazwisko, int cecha) {
        super(imię, nazwisko, 0);
        najistotniejszaCecha = cecha;
    }

    // wybiera tych spośród kandydatów,
    // którzy mają najniższy poziom wybranej cechy
    protected List<Integer> kandydaciMinimalni(List<Kandydat> kandydaci) {
        List<Integer> listaWybranychKandydatów = new ArrayList<>();
        int minimum = kandydaci.get(0).podajWagęCechy(najistotniejszaCecha);

        for (Kandydat k : kandydaci) {
            minimum = Math.min(minimum, k.podajWagęCechy(najistotniejszaCecha));
        }

        for (int i = 0; i < kandydaci.size(); i++) {
            if (kandydaci.get(i).podajWagęCechy(najistotniejszaCecha) == minimum)
                listaWybranychKandydatów.add(i);
        }

        return listaWybranychKandydatów;
    }

    // wybiera tyvh spośród kandydatów,
    // którzy mają najwyższy poziom wybranej cechy
    protected List<Integer> kandydaciMaksymalni(List<Kandydat> kandydaci) {
        List<Integer> listaWybranychKandydatów = new ArrayList<>();
        int maksimum = kandydaci.get(0).podajWagęCechy(najistotniejszaCecha);

        for (Kandydat k : kandydaci) {
            maksimum = Math.max(maksimum, k.podajWagęCechy(najistotniejszaCecha));
        }

        for (int i = 0; i < kandydaci.size(); i++) {
            if (kandydaci.get(i).podajWagęCechy(najistotniejszaCecha) == maksimum)
                listaWybranychKandydatów.add(i);
        }
        return listaWybranychKandydatów;
    }
}
