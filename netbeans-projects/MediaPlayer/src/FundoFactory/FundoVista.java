package FundoFactory;

import Cores.Azul;
import Cores.Cores;
import Fundo.Fundo;
import Fundo.Vista;

public class FundoVista implements FundoFactory {

    private static FundoVista fundoVista;

    private FundoVista() {
    }

    public static FundoVista getInstance() {
        if (fundoVista == null) {
            fundoVista = new FundoVista();
        }
        return fundoVista;
    }

    public Fundo getFundo() {
        return new Vista();
    }

    public Cores getCores() {
        return new Azul();
    }
}
