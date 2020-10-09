package Ludzie;

// Klasa reprezentujaca obywateli w bajtocjii
public abstract class ObywatelBajtocji {

    protected String imię;
    protected String nazwisko;
    protected int[] wagiCech;

    public ObywatelBajtocji(String imię, String nazwisko, int liczbaCech) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        wagiCech = new int[liczbaCech];
    }

    public String podajImię() {
        return imię;
    }

    public String podajNazwisko() {
        return nazwisko;
    }


    public int podajWagęCechy(int i) {
        return (0 <= i - 1 && i - 1 < wagiCech.length) ? wagiCech[i - 1] : 0;
    }

    public void modyfikujWagiCechOWektor(int[] wektorWag) {
        for (int i = 0; i < wagiCech.length; i++) {
            wagiCech[i] += wektorWag[i];
            // Kontrolujemy poprawność zakresu wagi: [-100, 100]
            if (wagiCech[i] < 0)
                wagiCech[i] = Math.max(wagiCech[i], -100);
            else
                wagiCech[i] = Math.min(wagiCech[i], 100);
        }
    }

}
