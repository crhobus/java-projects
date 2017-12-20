package Regiao;

import Frete.Frete;
import Frete.FreteCentroOeste;
import Imposto.Imposto;
import Imposto.ImpostoCentroOeste;

public class CentroOesteFactory implements RegiaoFactory {

    public Imposto getImposto() {
        return new ImpostoCentroOeste();
    }

    public Frete getFrete() {
        return new FreteCentroOeste();
    }

    @Override
    public String toString() {
        return "Centro Oeste";
    }
}
