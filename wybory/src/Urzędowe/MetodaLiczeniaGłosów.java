package Urzędowe;

// Klasa ogólna metod liczących głosy
public abstract class MetodaLiczeniaGłosów {

    // Dokonuje przeliczenia głosów na mandaty. Uzależniona od wybranej metody.
    // Korzysta z tablicy głosów na poszczególne partie, liczby partii oraz limitu mandatów.
    protected abstract int[] głosyNaMandaty(int[] głosyOkręgu, int p, int liczbaMandatów);

    // Wpisuje do poszczególnych okręgów wyborczych wyniki przeliczenia głosów na mandaty.
    // Działa tak samo niezależnie od wybranej metody liczenia głosów.
    // Wykorzystuje implementowaną prze pod kalsy metodę głosyNaMandaty();
    public OkręgWyborczy[] policzGłosy(OkręgWyborczy[] okręgiWyborcze, int p) {
        int[] głosyOkręgu;
        int[] rozkładMandatówWOkręgu;

        for (int i = 0; i < okręgiWyborcze.length; i++) {
            głosyOkręgu = okręgiWyborcze[i].podajGłosyNaPartie();
            rozkładMandatówWOkręgu = głosyNaMandaty(głosyOkręgu, p,
                    okręgiWyborcze[i].podajLiczbęMandatów());

            okręgiWyborcze[i].ustalWyniki(rozkładMandatówWOkręgu);
        }

        return okręgiWyborcze;
    }

    // Wypisuje w linii nazwę metody na wyjście standardowe.
    public abstract void przedstawSię();

}
