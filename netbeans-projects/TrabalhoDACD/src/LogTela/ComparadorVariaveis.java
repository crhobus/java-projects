package LogTela;

import java.util.Comparator;

public class ComparadorVariaveis implements Comparator<Expressao> {

    @Override
    public int compare(Expressao exp1, Expressao exp2) {
        return exp1.getVariavel().compareTo(exp2.getVariavel());
    }
}
