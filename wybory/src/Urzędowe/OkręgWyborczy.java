package Urzędowe;

import Ludzie.*;
import Partyjne.Partia;

import java.util.Arrays;
import java.util.Vector;

// Klasa reprezentująca okręg wyborczy w Bajtocji.
// Sortowalna po liczbie mieszkańców.
public class OkręgWyborczy implements Comparable<OkręgWyborczy>{

    // Numer okręgu (po scaleniu)
    private int numer;
    // Wektor kandydatów w danym okręgu. Posortowani partiami.
    private Vector<Kandydat> kandydaci;
    // Wektor wyborców w danym okręgu. Kolejność jak na wejściu.
    private Vector<Wyborca> wyborcy;
    // Miejsce zapisywania indeksu wybranego przez i-tego wyborcę kandydata.
    private int[] głosyWyborców;
    // Miejsca zapisywania sumy głosów na i-tego kandydata.
    private int[] głosyNaKandydatów;
    // Miejsce zapisywania głosów otrzymanych przez i-tą partię.
    private int[] głosyNaPartie;
    // Miejsce do wpisania liczby mandatów otrzmanych przez kolejne partie.
    private int[] mandatyNaPartie;

    // Konstruktor okręgu wyborczego identyfikującego go numerem.
    public OkręgWyborczy(int numer) {
        this.numer = numer;
        kandydaci = new Vector<Kandydat>();
        wyborcy = new Vector<Wyborca>();
    }

    // Dostając zgodną z poleceniem linię opisującą kandydata oraz inne
    // potrzebne dane (mapowanie numerów okręgów, liczbę cech kandydata
    // oraz liczbę mandatów w okręgu przed ewentualnym złączeniem) tworzy,
    // inicjalizuje oraz dodaje nowego kandydata do wektora kandydatów.
    public void dodajKandydata(String opisKandydata, int[] mapowanie,
                               int liczbaCech, int bazowaLiczbaMandatów) {
        String[] parametry = opisKandydata.split(" ");
        String imię = parametry[0];
        String nazwisko = parametry[1];
        int numerOkręgu = Integer.decode(parametry[2]);
        String partia = parametry[3];
        int pozycja = Integer.decode(parametry[4]);
        int[] wektorWartościCech = new int[liczbaCech];

        Kandydat nowyKandydat = new Kandydat(imię, nazwisko, partia, liczbaCech);
        if (numerOkręgu == mapowanie[numerOkręgu])
            nowyKandydat.zmieńNumerNaLiście(pozycja);
        else
            nowyKandydat.zmieńNumerNaLiście(pozycja + bazowaLiczbaMandatów);

        for (int i = 0; i < liczbaCech; i++) {
            wektorWartościCech[i] = Integer.decode(parametry[5 + i]);
        }

        nowyKandydat.modyfikujWagiCechOWektor(wektorWartościCech);
        kandydaci.add(nowyKandydat);
    }

    // Dostając zgodną z poleceniem linię opisującą wyborcę oraz inne
    // potrzebne dane (mapowanie numerów okręgów, liczbę wag cech
    // tworzy, inicjalizuje oraz dodaje nowego wyborcę do wektorawyborców.
    public void dodajWyborcę(String opisWyborcy, int[] mapowanie,
                             int liczbaCech, int  bazowaLiczbaMandatów) {
        String[] parametry = opisWyborcy.split(" ");
        String imię = parametry[0];
        String nazwisko = parametry[1];
        int numerOkręgu = Integer.decode(parametry[2]);
        int typWyborcy = Integer.decode(parametry[3]);
        Wyborca nowyWyborca = null;
        int[] wektorWag;
        int pozycja;

        switch(typWyborcy) {
            case 1:
                nowyWyborca = new WyborcaŻelaznyPartyjny(imię,
                                                         nazwisko,
                                                         parametry[4]);
                break;
            case 2:
                pozycja = Integer.decode(parametry[5]);
                if (numerOkręgu != mapowanie[numerOkręgu])
                    pozycja += bazowaLiczbaMandatów;

                nowyWyborca = new WyborcaŻelaznyKandydata(imię,
                                                          nazwisko,
                                                          parametry[4],
                                                          pozycja);
                break;
            case 3:
                nowyWyborca = new WyborcaMinimalizującyWszechstronny(imię,
                                                                     nazwisko,
                                                                     Integer.decode(parametry[4]));
                break;
            case 4:
                nowyWyborca = new WyborcaMaksymalizującyWszechstronny(imię,
                                                                      nazwisko,
                                                                      Integer.decode(parametry[4]));
                break;
            case 5:
                wektorWag = new int[liczbaCech];
                for (int i = 0; i < liczbaCech; i++) {
                    wektorWag[i] = Integer.decode(parametry[4 + i]);
                }
                nowyWyborca = new WyborcaWybitnieWszechstronny(imię,
                                                               nazwisko,
                                                               wektorWag);
                break;
            case 6:
                nowyWyborca = new WyborcaMinimalizującyPartyjny(imię,
                                                                nazwisko,
                                                                Integer.decode(parametry[4]),
                                                                parametry[5]);
                break;
            case 7:
                nowyWyborca = new WyborcaMaksymalizującyPartyjny(imię,
                                                                 nazwisko,
                                                                 Integer.decode(parametry[4]),
                                                                 parametry[5]);
                break;
            case 8:
                wektorWag = new int[liczbaCech];
                for (int i = 0; i < liczbaCech; i++) {
                    wektorWag[i] = Integer.decode(parametry[4 + i]);
                }
                nowyWyborca = new WyborcaPartyjnieWszechstronny(imię,
                                                                nazwisko,
                                                                wektorWag,
                                                                parametry[parametry.length - 1]);
                break;
        }

        wyborcy.add(nowyWyborca);
    }

    // Zwraca lizcbę wyborców okręgu wyborczym.
    public int podajLiczbęWyborców() {
        return wyborcy.size();
    }

    // Zwraca liczbę dostępnych do zdobycia mandatów w okręgu wyborczym.
    public int podajLiczbęMandatów() {
        return wyborcy.size() / 10;
    }

    // Każdy wyborca w okręgu oddaje głos podając indeks wybranego kandydata.
    // Następnie zwiekszana jest liczba głosów odpowiedniego kandydata, partii,
    // a sam indeks jest zapisywany w tablicy.
    // Metoda tworzy potrzebne tablice.
    public void przeprowadźGłosowanie(Partia[] partie) {
        int idKandydata;
        głosyNaKandydatów = new int[kandydaci.size()];
        głosyWyborców = new int[wyborcy.size()];
        głosyNaPartie = new int[partie.length];
        mandatyNaPartie = new int[partie.length];

        for ( int i = 0; i < wyborcy.size(); i++) {
            idKandydata = wyborcy.get(i).oddajGłos(kandydaci, partie);
            głosyWyborców[i] = idKandydata;
            głosyNaKandydatów[idKandydata]++;
            głosyNaPartie[idKandydata / podajLiczbęMandatów()]++;
        }
    }

    // Podaje numer okręgu (w przyapdku okręgu scalonego mniejszy).
    public int podajNumerOkręgu() {
        return numer;
    }

    // Wypisuje na wyjście standardowe jakie głosy oddawali wyborcy w okręgu.
    // Robi to w formacie imię i nazwisko wyborcy imię i nazwisko kandydata.
    public void wypiszGłosyWyborców() {
        for (int i = 0; i < wyborcy.size(); i++) {
            System.out.print(wyborcy.get(i).podajImię() + " ");
            System.out.print(wyborcy.get(i).podajNazwisko() + " ");
            System.out.print(kandydaci.get(głosyWyborców[i]).podajImię() + " ");
            System.out.println(kandydaci.get(głosyWyborców[i]).podajNazwisko());
        }
    }

    // Wypisuje kolejnych kandydatów oraz liczbę głosów jakie uzyskali
    public void wypiszGłosyNaKandydatów() {
        Kandydat k;
        for (int i = 0; i < kandydaci.size(); i++) {
            k = kandydaci.elementAt(i);
            System.out.print(k.podajImię() + " " + k.podajNazwisko() + " ");
            System.out.print(k.podajPartię() + " " + k.podajNumerNaLiście() + " ");
            System.out.println(głosyNaKandydatów[i]);
        }
    }

    // Wypisuje nazwy partii z podanej tablicy oraz liczbe głosów na
    // daną partię w tym okręgu.
    // Zakłada, że tablica mandatyNaPartie została już utworzona.
    public void wypiszLiczbyMandatów(Partia[] partie) {
        for (int i = 0; i < partie.length; i++) {
            System.out.println(partie[i].podajNazwę() + " " + mandatyNaPartie[i]);
        }
    }

    // Zwraca kopię tablicy opisujacej liczbę głosów oddanych na poszcególne partie.
    public int[] podajGłosyNaPartie() {
        return Arrays.copyOf(głosyNaPartie, głosyNaPartie.length);
    }

    // Dostając nume partii (zakładając jego poprawność) wypisuje liczbę
    // oddanych na nią głosów w okręgu.
    public int ileMandatówOtrzymałaPartia(int numer) {
        return mandatyNaPartie[numer];
    }

    // Dostając tablicę z przeliczoną ilością mandatów
    // przypisuje ją do tablicy mandatyNaPartie.
    public void ustalWyniki(int[] mandatyNaPartie) {
        this.mandatyNaPartie = mandatyNaPartie;
    }

    // Daje możliwość dostępu do wyborców z okręgu.
    public Vector<Wyborca> udostępnijWyborców() {
        return wyborcy;
    }

    // Daje możliwość dostępu do kandydatów w okręgu.
    public Vector<Kandydat> udostępnijKandydatów() {
        return kandydaci;
    }

    // Aktualizuje poglądy wyborców podczas kampanii.
    public void zaktualizujWyborców(Vector<Wyborca> wyborcyUpdated) {
        wyborcy = wyborcyUpdated;
    }

    // Sortuje kandydatów zgodnie z partiami.
    public void posortujKandydatów(Partia[] partie) {
        Vector<Kandydat> posortowani = new Vector<>();

        for (int i = 0; i < partie.length; i++) {
            for (int j = 0; j < kandydaci.size(); j++) {
                if (kandydaci.get(j).podajPartię().equals(partie[i].podajNazwę())) {
                    posortowani.add(kandydaci.get(j));
                }
            }
        }

        kandydaci = posortowani;
    }

    @Override
    public int compareTo(OkręgWyborczy okręgWyborczy) {
        return (podajLiczbęWyborców() - okręgWyborczy.podajLiczbęWyborców());
    }
}
