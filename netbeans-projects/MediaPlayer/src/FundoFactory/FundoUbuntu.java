package FundoFactory;

import Cores.Amarelo;
import Cores.Cores;
import Fundo.Fundo;
import Fundo.Ubuntu;

public class FundoUbuntu implements FundoFactory {

    private static FundoUbuntu fundoUbuntu;

    private FundoUbuntu() {
    }

    public static FundoUbuntu getInstance() {
        if (fundoUbuntu == null) {
            fundoUbuntu = new FundoUbuntu();
        }
        return fundoUbuntu;
    }

    public Fundo getFundo() {
        return new Ubuntu();
    }

    public Cores getCores() {
        return new Amarelo();
    }
}
