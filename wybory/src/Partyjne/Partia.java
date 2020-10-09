package Partyjne;

import Ludzie.Wyborca;
import Urzędowe.OkręgWyborczy;

import java.util.ArrayList;
import java.util.Vector;

// Klasa ogólna reprezentująca partię w wyborach.
public abstract class Partia {

    // Nazwa partii.
    protected String nazwa;
    // Budżet partii w kampanii.
    protected int budżet;
    // Lista wykorzystywania do wartościowania działań w trakcie kampanii.
    protected ArrayList<OpisDziałania> segregatorDziałań;
    // Indeks aktualnie wybranego działania w segregatorze Działań.
    protected int bieżącyIndeks;

    public Partia(String nazwa, int budżet) {
        this.nazwa = nazwa;
        this.budżet = budżet;
        bieżącyIndeks = 0;
        segregatorDziałań = new ArrayList<>();
    }

    public int podajBudżet() {
        return budżet;
    }

    public String podajNazwę() {
        return nazwa;
    }

    // Dostając w czasie kampanii dostę do okręgów oraz działań,
    // tworzy segregator działań zależnie od partyjnej strategii.
    protected abstract void inicjalizujSegregatorDziałań(OkręgWyborczy[] okręgi, Działanie[] działania);

    // Zleżnie od budżetu i strategii partii ustala indeks działania,
    // które będzie wykonane następne.
    protected abstract void ustalBieżącyIndeks();

    // Zwraca koszt działania wskazywanego przez bieżącyIndeks.
    protected abstract int kosztDziałania();

    // Wywołana po raz pierwszy tworzy i inicjalizuje segregatorDziałań.
    // Wykonuje jedno wybrane przez pratię zadanie, w wybanym przezeń okręgu.
    // Zmniejsza odpowiednio budżet partii.
    public OkręgWyborczy[] wykonajDziałanie(OkręgWyborczy[] okręgi, Działanie[] działania) {
        if (segregatorDziałań.isEmpty())
            inicjalizujSegregatorDziałań(okręgi, działania);

        ustalBieżącyIndeks();

        int numerOkręgu = segregatorDziałań.get(bieżącyIndeks).okręg;
        int numerDziałania = segregatorDziałań.get(bieżącyIndeks).działanie;

        Vector<Wyborca> wyborcy = okręgi[numerOkręgu].udostępnijWyborców();

        for (Wyborca w : wyborcy) {
            w.poddajSięDziałaniu(działania[numerDziałania]);
        }

        budżet -= kosztDziałania();
        okręgi[numerOkręgu].zaktualizujWyborców(wyborcy);

        return okręgi;
    }
}
