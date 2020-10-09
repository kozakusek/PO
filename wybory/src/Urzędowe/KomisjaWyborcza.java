package Urzędowe;

import Partyjne.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class KomisjaWyborcza {

    // tablica okręgów wyborczych posortowana względem ilości mieszkańców
    private OkręgWyborczy[] okręgiWyborcze;
    // tabelka funkcji opisująca numer okręgu po scaleniu
    private int[] mapowanieOkręgów;
    // nr okręgu -> liczba wyborców przed połączeniem
    private int[] liczbyWyborców;
    // tablica partii uczestniczących w wyborach
    private Partia[] partie;
    // wybrana metoda liczenia głosów
    private MetodaLiczeniaGłosów licznikGłosów;
    // skaner zczytujący parametry wejścia opisane w poleceniu
    private Scanner wejścieParametrów;
    // możliwe działania dla partii w kampanii, posortowane po koszcie
    private Działanie[] działania;
    // liczba mandatów jakie kolejne partie uzyskały w wyborach
    private int[] wyniki;

    // Inicjalizuje KomisjęWyborczą podając  jej wejście dla danych
    // potrzebnych do rozpoczęcia wyborów.
    public KomisjaWyborcza(Scanner sc) {
        wejścieParametrów = sc;
    }

    // Zwraca najmniejszy możliwy koszt działania w kampanii
    // korzystając z posortowania działań i okręgów
    private int najmniejszyKoszt() {
        return działania[0].podajKoszt() * okręgiWyborcze[0].podajLiczbęWyborców();
    }

    // Dla modyfikatorów 1,2,3 ustala ją odgórnie
    // W innym wypadku losuje jedną z trzech dostępnych metod
    public void losujMetodę(int modyfikator) {
        Random r = new Random();

        if (modyfikator < 1 || 3 < modyfikator)
            modyfikator = (r.nextInt(3)) + 1;

        switch(modyfikator) {
            case 1:
                licznikGłosów = new MetodaDHondta();
                break;
            case 2:
                licznikGłosów = new MetodaSainteLaguë();
                break;
            case 3:
                licznikGłosów = new MetodaHareaNiemeyera();
                break;
        }

    }

    // Tworzy partie zależnie od podanych parametrów i zwraca je w tablicy
    private Partia[] utwórzPartie(int l, String[] nazwy, int[] budżety, TypPartii[] typy) {
        Partia[] partie = new Partia[l];

        for (int i = 0; i < l; i++) {
            switch (typy[i]) {
                case R:
                    partie[i] = new PartiaZRozmachem(nazwy[i], budżety[i]);
                    break;
                case S:
                    partie[i] = new PartiaSkromna(nazwy[i], budżety[i]);
                    break;
                case W:
                    partie[i] = new PartiaWłasna(nazwy[i], budżety[i]);
                    break;
                case Z:
                    partie[i] = new PartiaZachłanna(nazwy[i], budżety[i]);
                    break;
            }
        }

        return partie;
    }


    // Tworzy, inizjalizuje i łączy okręgi wyborcze
    // Sortuje je pod względem ilości mieszkańców
    private void utwórzOkręgiWyborcze(int n, int c) {
        liczbyWyborców = new int[n + 1];
        int k = 0;

        for (int i = 1; i <= n; i++) {
            liczbyWyborców[i] = wejścieParametrów.nextInt();
            if (i == mapowanieOkręgów[i]) {
                okręgiWyborcze[k] = new OkręgWyborczy(mapowanieOkręgów[i]);
                k++;
            }
        }

        wejścieParametrów.nextLine();
        k = -1;
        for (int i = 1; i <= n; i++) {
            if (i == mapowanieOkręgów[i])
                k++;

            for (int j = 0; j < (liczbyWyborców[i] / 10) * partie.length; j++) {
                okręgiWyborcze[k].dodajKandydata(wejścieParametrów.nextLine(),
                                                 mapowanieOkręgów, c,
                                                 liczbyWyborców[i] / 10);
            }

            if (i != mapowanieOkręgów[i])
                okręgiWyborcze[k].posortujKandydatów(partie);

        }

        k = -1;
        for (int i = 1; i <= n; i++) {
            if (i == mapowanieOkręgów[i])
                k++;

            for (int j = 0; j < liczbyWyborców[i]; j++)
            okręgiWyborcze[k].dodajWyborcę(wejścieParametrów.nextLine(),
                                           mapowanieOkręgów, c,
                                          liczbyWyborców[i] / 10);
        }

        Arrays.sort(okręgiWyborcze, OkręgWyborczy::compareTo);
    }

    // Przetwarza wejście, tworząc partie, okręgi i działania
    // Działania sortuje po koszcie ich wykonania
    public void przygotujWybory() {
        int n = wejścieParametrów.nextInt(); // liczba podstawowych okręgów wyborczych
        int p = wejścieParametrów.nextInt(); // liczba partii
        int d = wejścieParametrów.nextInt(); // liczba możliwych działań
        int c = wejścieParametrów.nextInt(); // liczba cech kandydatów
        int s = wejścieParametrów.nextInt(); // lcizba okręgów, które należy scalić

        mapowanieOkręgów = new int[n + 1];
        Arrays.setAll(mapowanieOkręgów, i -> i);
        int numer;
        String[] temp;
        for (int i = 0; i < s; i++) {
            temp = wejścieParametrów.next().split("\\D");
            numer = Integer.decode(temp[1]);
            mapowanieOkręgów[numer + 1] = numer;
        }

        String[] nazwyPartii = new String[p];
        int[] budżetyPartii = new int[p];
        TypPartii[] typyPartii = new TypPartii[p];
        for (int i = 0; i < p; i++) {
            nazwyPartii[i] = wejścieParametrów.next();
        }
        for (int i = 0; i < p; i++) {
            budżetyPartii[i] = wejścieParametrów.nextInt();
        }
        for (int i = 0; i < p; i++) {
            typyPartii[i] = TypPartii.valueOf(wejścieParametrów.next());
        }
        partie = utwórzPartie(p, nazwyPartii, budżetyPartii, typyPartii);

        okręgiWyborcze = new OkręgWyborczy[n - s];
        utwórzOkręgiWyborcze(n, c);

        działania = new Działanie[d];
        int[] wagi = new int[c];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < c; j++) {
                wagi[j] = wejścieParametrów.nextInt();
            }
            działania[i] = new Działanie(wagi);
        }
        Arrays.sort(działania, Działanie::compareTo);

    }

    // Daje możliwość każdej z partii wykonania kampanii.
    // Partie dostają dostęp do okręgów wyborczych i dozwolonych działań.
    // Zwracają okręgi już zmodyfikowane.
    // Korzysta ze znajomości najmniejszego kosztu działania.
    public void przeprowadźKampanie() {
        for (Partia p : partie) {
            while (p.podajBudżet() >= najmniejszyKoszt()) {
                okręgiWyborcze = p.wykonajDziałanie(okręgiWyborcze, działania);
            }
        }
    }

    // Każy okręg przeprowadza głosowanie, mając dostęp do kolejności partii.
    public void przeprowadźWybory() {
        for (OkręgWyborczy o : okręgiWyborcze) {
            o.przeprowadźGłosowanie(partie);
        }
    }

    // Wykorzystując wylosowaną metodę liczenia głosów, przelicza je
    // na mandaty w każdym z okręgów. Następnie zlizcza mandaty ze
    // wszystkich okręgów.
    public void policzGłosy() {
        wyniki = new int[partie.length];
        okręgiWyborcze = licznikGłosów.policzGłosy(okręgiWyborcze, partie.length);

        for (OkręgWyborczy o : okręgiWyborcze) {
            for (int i = 0; i < wyniki.length; i++) {
                wyniki[i] += o.ileMandatówOtrzymałaPartia(i);
            }
        }
    }

    // Wypisuje wyniki wyborów zgodnie z opisem z polecenia.
    // Metoda liczenia głosów, głosy wyborców, wyniki kandydatów
    // oraz wyniki poszczególnych partii w okręgach i ogólnie.
    public void ogłośWyniki() {
        licznikGłosów.przedstawSię();
        for (OkręgWyborczy o : okręgiWyborcze) {
            System.out.println();
            System.out.println("Nr Okręgu: " + o.podajNumerOkręgu());
            o.wypiszGłosyWyborców();
            o.wypiszGłosyNaKandydatów();
            o.wypiszLiczbyMandatów(partie);
        }
        for (int i = 0; i < partie.length; i++) {
            System.out.println(partie[i].podajNazwę() + " " + wyniki[i]);
        }
        System.out.println();
    }

}
