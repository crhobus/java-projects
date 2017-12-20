package Regiao;

import Frete.Frete;
import Imposto.Imposto;

public interface RegiaoFactory {

    public Imposto getImposto();

    public Frete getFrete();
}
