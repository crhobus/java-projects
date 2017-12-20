package Regiao;

import Frete.Frete;
import Frete.FreteNordeste;
import Imposto.Imposto;
import Imposto.ImpostoNordeste;

public class NordesteFactory implements RegiaoFactory {

    public Imposto getImposto() {
        return new ImpostoNordeste();
    }

    public Frete getFrete() {
        return new FreteNordeste();
    }

    @Override
    public String toString() {
        return "Nordeste";
    }
}
