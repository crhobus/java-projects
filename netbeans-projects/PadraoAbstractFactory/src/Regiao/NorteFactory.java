package Regiao;

import Frete.Frete;
import Frete.FreteNorte;
import Imposto.Imposto;
import Imposto.ImpostoNorte;

public class NorteFactory implements RegiaoFactory {

    public Imposto getImposto() {
        return new ImpostoNorte();
    }

    public Frete getFrete() {
        return new FreteNorte();
    }

    @Override
    public String toString() {
        return "Norte";
    }
}
