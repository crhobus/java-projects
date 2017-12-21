package Combo;

import Bebida.Bebida;
import Bebida.Coca;
import PratoPrincipal.Feijao;
import PratoPrincipal.PratoPrincipal;
import Salada.Couve;
import Salada.Salada;
import Sobremesa.SaladaFrutas;
import Sobremesa.Sobremesa;

public class Combo1 implements ComboFactory {

    private static Combo1 combo1;

    private Combo1() {
    }

    public static Combo1 getInstance() {
        if (combo1 == null) {
            combo1 = new Combo1();
        }
        return combo1;
    }

    public Bebida getBebida() {
        return new Coca();
    }

    public PratoPrincipal getPratoPricipal() {
        return new Feijao();
    }

    public Salada getSalada() {
        return new Couve();
    }

    public Sobremesa getSobremesa() {
        return new SaladaFrutas();
    }

    @Override
    public String toString() {
        return "Combo 1";
    }
}
