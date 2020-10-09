package Partyjne;

import java.util.Arrays;

// Klasa reprezentująca działanie.
// Udostępnia możliwość podawania kosztu oraz wektora cech.
// Sortowalna po koszcie.
public class Działanie implements Comparable<Działanie>{

    private int[] wektorZmianWagCech;
    private int koszt;

    public Działanie(int[] wektorZmianWagCech) {
        this.wektorZmianWagCech = new int[wektorZmianWagCech.length];
        for (int i = 0; i < wektorZmianWagCech.length; i++) {
            this.wektorZmianWagCech[i] = wektorZmianWagCech[i];
            koszt += Math.abs(wektorZmianWagCech[i]);
        }
    }

    public int podajKoszt() {
        return koszt;
    }

    public int[] podajWektor() {
        return Arrays.copyOf(wektorZmianWagCech, wektorZmianWagCech.length);
    }

    @Override
    public int compareTo(Działanie działanie) {
        return (this.koszt - działanie.koszt);
    }
}
