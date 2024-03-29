package Regiao;

import Frete.Frete;
import Frete.FreteSul;
import Imposto.Imposto;
import Imposto.ImpostoSul;

public class SuldesteFactory implements RegiaoFactory {

    public Imposto getImposto() {
        return new ImpostoSul();
    }

    public Frete getFrete() {
        return new FreteSul();
    }

    @Override
    public String toString() {
        return "Suldeste";
    }
}
