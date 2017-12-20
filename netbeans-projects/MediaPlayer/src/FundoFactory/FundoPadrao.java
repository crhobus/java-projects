package FundoFactory;

import Cores.CorPadrao;
import Cores.Cores;
import Fundo.Fundo;
import Fundo.Padrao;

public class FundoPadrao implements FundoFactory {

    private static FundoPadrao fundoPadrao;

    private FundoPadrao() {
    }

    public static FundoPadrao getInstance() {
        if (fundoPadrao == null) {
            fundoPadrao = new FundoPadrao();
        }
        return fundoPadrao;
    }

    public Fundo getFundo() {
        return new Padrao();
    }

    public Cores getCores() {
        return new CorPadrao();
    }
}
