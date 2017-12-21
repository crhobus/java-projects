package Combo;

import Bebida.Agua;
import Bebida.Bebida;
import PratoPrincipal.Macarrao;
import PratoPrincipal.PratoPrincipal;
import Salada.Salada;
import Salada.Tomate;
import Sobremesa.Gelatina;
import Sobremesa.Sobremesa;

public class Combo4 implements ComboFactory {

    private static Combo4 combo4;

    private Combo4() {
    }

    public static Combo4 getInstance() {
        if (combo4 == null) {
            combo4 = new Combo4();
        }
        return combo4;
    }

    public Bebida getBebida() {
        return new Agua();
    }

    public PratoPrincipal getPratoPricipal() {
        return new Macarrao();
    }

    public Salada getSalada() {
        return new Tomate();
    }

    public Sobremesa getSobremesa() {
        return new Gelatina();
    }

    @Override
    public String toString() {
        return "Combo 4";
    }
}
