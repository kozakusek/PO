package Main;

import Urzędowe.KomisjaWyborcza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Program wywołujemy podając jako argument ścieżkę do pliku z wejsciem.
// Wypisze on rozwiązania na wyjście standardowe dla kolejnych trzech metod
// liczenia głosów i zakończy się kodem 0.
// Jeżeli liczba argumentów będzie różna od 1, albo plik nie będzie istniał
// program zakończy się kodem 1 i wypisze stosowny komunikat na stderr.
// W pliku wejściowym zakładamy poprawność podanych danych.
public class Main {

    public static void main(String[] args){
        File plik;
        Scanner sc;

        // Sprawdzenie ilości argumentów
        if (args.length != 1) {
            System.err.println("Zła liczba argumentów");
            System.exit(1);
        }
        plik = new File(args[0]);

        // Na wypadek podania nieistniejącego pliku przechwytujemy wyjątek.
        try {
            // 1 - metoda D'Hondta
            // 2 - metoda Sainte-Laguë
            // 3 - metoda Here'a-Niemeyera
            // losujMetodę z klasy KomisjaWyborcza, wywołana z innym argumentem,
            // wybierze losową metodę spośród tych trzech
            for (int i = 1; i <= 3; i++) {
                sc = new Scanner(plik);
                
                KomisjaWyborcza komisjaWyborcza = new KomisjaWyborcza(sc);

                komisjaWyborcza.przygotujWybory();

                komisjaWyborcza.losujMetodę(i);

                komisjaWyborcza.przeprowadźKampanie();

                komisjaWyborcza.przeprowadźWybory();

                komisjaWyborcza.policzGłosy();

                komisjaWyborcza.ogłośWyniki();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Podany plik nie istnieje");
            System.exit(1);
        }
    }
}
