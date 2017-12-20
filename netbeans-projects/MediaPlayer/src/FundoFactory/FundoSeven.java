package FundoFactory;

import Cores.Cores;
import Cores.Verde;
import Fundo.Fundo;
import Fundo.Seven;

public class FundoSeven implements FundoFactory {

    private static FundoSeven fundoSeven;

    private FundoSeven() {
    }

    public static FundoSeven getInstance() {
        if (fundoSeven == null) {
            fundoSeven = new FundoSeven();
        }
        return fundoSeven;
    }

    public Fundo getFundo() {
        return new Seven();
    }

    public Cores getCores() {
        return new Verde();
    }
}
