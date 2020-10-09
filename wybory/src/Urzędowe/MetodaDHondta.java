package Urzędowe;

import java.util.Vector;

public class MetodaDHondta extends MetodaLiczeniaGłosów {

    // Przelicza głosy na mandaty metodą D'Hondta
    protected int[] głosyNaMandaty(int[] głosyOkręgu, int p, int liczbaMandatów) {
        int[] mandaty = new int[p];
        Vector<IntPara> ilorazy = new Vector<>();
        boolean wszystkieWyzerowane = false;
        int i = 1;

        while (!wszystkieWyzerowane) {
            wszystkieWyzerowane = true;
            for (int j = 0; j < p; j++) {
                ilorazy.add(new IntPara(głosyOkręgu[j] / i, j));
                if (głosyOkręgu[j] / i != 0)
                    wszystkieWyzerowane = false;
            }
            i++;
        }

        ilorazy.sort(IntPara::compareTo);

        for (int j = 0; j < liczbaMandatów; j++) {
            mandaty[ilorazy.get(j).drugi]++;
        }

        return mandaty;
    }


    @Override
    public void przedstawSię() {
        System.out.println("Metoda D'Hondta");
    }
}
