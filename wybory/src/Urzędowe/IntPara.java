package Urzędowe;


// Prosta klasa przechowująca parę Integerów.
// Sortowalna malejąco leksykograficznie.
public class IntPara implements Comparable<IntPara> {
    public Integer pierwszy;
    public Integer drugi;

    public IntPara(Integer pierwszy, Integer drugi) {
        this.pierwszy = pierwszy;
        this.drugi = drugi;
    }

    @Override
    public int compareTo(IntPara para) {
        if (pierwszy > para.pierwszy)
            return -1;
        else if (pierwszy < para.pierwszy)
            return 1;
        else
            return - drugi.compareTo(para.drugi);
    }
}
