package cover;

import sets.KontenerNaZbiory;
import tasks.Zapytanie;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        var wejście = new Scanner(System.in);
        var dane = new Vector<Integer>(); // do trzymania zbioru, kończy się 0
        var zbiory = new KontenerNaZbiory();
        var zapytanie = new Zapytanie();
        int[] in = new int[2]; // do trzymana wartości z wejścia (a,b)

        while (wejście.hasNext()) {
            in[0] = wejście.nextInt();

            if (in[0] < 0) {
                in[1] = wejście.nextInt();
                zapytanie.wykonaj(zbiory, in[0], in[1]);
            } else {
                dane.add(in[0]);
                while (in[0] != 0) {
                    in[0] = wejście.nextInt();
                    dane.add(in[0]);
                }
                zbiory.dodaj(dane);
                dane.clear();
            }
        }
    }
}