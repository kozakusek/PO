package Urzędowe;

import java.util.Vector;

public class MetodaHareaNiemeyera extends MetodaLiczeniaGłosów {

    // Przelicza głosy na mandaty metodą Here'a-Niemeyera
    protected int[] głosyNaMandaty(int[] głosyOkręgu, int p, int liczbaMandatów) {
        int[] mandaty = new int[p];
        Vector<IntPara> reszty = new Vector<>();

        for (int i = 0; i < p; i++) {
            mandaty[i] = głosyOkręgu[i] / 10;
            liczbaMandatów -= mandaty[i];
            reszty.add(new IntPara(głosyOkręgu[i] % 10, i));
        }

        reszty.sort(IntPara::compareTo);

        while (liczbaMandatów > 0) {
            mandaty[reszty.remove(0).drugi]++;
            liczbaMandatów--;
        }

        return mandaty;
    }

    @Override
    public void przedstawSię() {
        System.out.println("Metoda Hare'a-Niemeyera");
    }
}
