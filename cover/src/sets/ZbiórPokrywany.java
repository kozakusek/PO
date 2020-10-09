package sets;

import java.util.Arrays;
import java.util.function.Predicate;

public class ZbiórPokrywany extends Zbiór {

    private boolean[] zbiór;
    private int liczbaElementów;

    public ZbiórPokrywany(Integer a) {
        liczbaElementów = a;
        zbiór = new boolean[a];
        Arrays.fill(zbiór, true);
    }

    private ZbiórPokrywany(ZbiórPokrywany zbiór) {
        this.zbiór = Arrays.copyOf(zbiór.zbiór, zbiór.zbiór.length);
        this.liczbaElementów = zbiór.liczbaElementów;
    }

    public boolean jestPusty() {
        return liczbaElementów == 0;
    }

    public ZbiórPokrywany kopia() {
        return new ZbiórPokrywany(this);
    }

    public boolean jestElement(Predicate p) {
        for (int i = 0; i < zbiór.length; i++) {
            if (zbiór[i] && p.test(i + 1))
                return true;
        }

        return false;
    }

    public ZbiórPokrywany usuńPrzecięcie(Predicate p) {
        for (int i = 0; i < zbiór.length; i++) {
            if (p.test(i + 1) && zbiór[i]) {
                zbiór[i] = false;
                liczbaElementów--;
            }

        }

        return this;
    }

    @Override
    public boolean zawieraElement(Integer n) {
        if (0 > n || n >= zbiór.length)
            return false;
        else
            return zbiór[n];
    }

    @Override
    Predicate podajPredykat() {
        return i -> zawieraElement((Integer) i);
    }

    int mocPrzecięcia(ZbiórZbiorów z) {
        int moc = 0;

        for (int i = 1; i <= zbiór.length; i++) {
            if (zbiór[i - 1] && z.zawieraElement(i))
                moc++;
        }

        return moc;
    }
}
