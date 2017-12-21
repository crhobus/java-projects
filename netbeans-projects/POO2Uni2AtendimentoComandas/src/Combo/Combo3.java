package Combo;

import Bebida.Bebida;
import Bebida.Coca;
import PratoPrincipal.PratoPrincipal;
import PratoPrincipal.Risoto;
import Salada.Salada;
import Salada.Tomate;
import Sobremesa.Mousse;
import Sobremesa.Sobremesa;

public class Combo3 implements ComboFactory {

    private static Combo3 combo3;

    private Combo3() {
    }

    public static Combo3 getInstance() {
        if (combo3 == null) {
            combo3 = new Combo3();
        }
        return combo3;
    }

    public Bebida getBebida() {
        return new Coca();
    }

    public PratoPrincipal getPratoPricipal() {
        return new Risoto();
    }

    public Salada getSalada() {
        return new Tomate();
    }

    public Sobremesa getSobremesa() {
        return new Mousse();
    }

    @Override
    public String toString() {
        return "Combo 3";
    }
}
