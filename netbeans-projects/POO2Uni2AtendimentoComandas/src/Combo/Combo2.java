package Combo;

import Bebida.Bebida;
import Bebida.Suco;
import PratoPrincipal.Macarrao;
import PratoPrincipal.PratoPrincipal;
import Salada.Agriao;
import Salada.Salada;
import Sobremesa.Mousse;
import Sobremesa.Sobremesa;

public class Combo2 implements ComboFactory {

    private static Combo2 combo2;

    private Combo2() {
    }

    public static Combo2 getInstance() {
        if (combo2 == null) {
            combo2 = new Combo2();
        }
        return combo2;
    }

    public Bebida getBebida() {
        return new Suco();
    }

    public PratoPrincipal getPratoPricipal() {
        return new Macarrao();
    }

    public Salada getSalada() {
        return new Agriao();
    }

    public Sobremesa getSobremesa() {
        return new Mousse();
    }

    @Override
    public String toString() {
        return "Combo 2";
    }
}
