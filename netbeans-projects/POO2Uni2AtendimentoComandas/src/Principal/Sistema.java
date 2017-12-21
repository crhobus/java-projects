package Principal;

import Combo.Combo1;
import Combo.Combo2;
import Combo.Combo3;
import Combo.Combo4;
import Combo.ComboFactory;

public class Sistema {

    public static void main(String[] args) {
        ComboFactory x = getComboFactory(Restaurante.COMBO1);
        System.out.print(x + ": ");
        System.out.print("Bebida: " + x.getBebida().getBebida() + ", ");
        System.out.print("Prato Principal: " + x.getPratoPricipal().getPratoPrincipal() + ", ");
        System.out.print("Salada: " + x.getSalada().getSalada() + ", ");
        System.out.print("Sobremesa: " + x.getSobremesa().getSobremesa() + "\n\n");

        x = getComboFactory(Restaurante.COMBO2);
        System.out.print(x + ": ");
        System.out.print("Bebida: " + x.getBebida().getBebida() + ", ");
        System.out.print("Prato Principal: " + x.getPratoPricipal().getPratoPrincipal() + ", ");
        System.out.print("Salada: " + x.getSalada().getSalada() + ", ");
        System.out.print("Sobremesa: " + x.getSobremesa().getSobremesa() + "\n\n");

        x = getComboFactory(Restaurante.COMBO3);
        System.out.print(x + ": ");
        System.out.print("Bebida: " + x.getBebida().getBebida() + ", ");
        System.out.print("Prato Principal: " + x.getPratoPricipal().getPratoPrincipal() + ", ");
        System.out.print("Salada: " + x.getSalada().getSalada() + ", ");
        System.out.print("Sobremesa: " + x.getSobremesa().getSobremesa() + "\n\n");

        x = getComboFactory(Restaurante.COMBO4);
        System.out.print(x + ": ");
        System.out.print("Bebida: " + x.getBebida().getBebida() + ", ");
        System.out.print("Prato Principal: " + x.getPratoPricipal().getPratoPrincipal() + ", ");
        System.out.print("Salada: " + x.getSalada().getSalada() + ", ");
        System.out.print("Sobremesa: " + x.getSobremesa().getSobremesa() + "\n");
    }

    private static ComboFactory getComboFactory(Restaurante restaurante) {
        switch (restaurante) {
            case COMBO1:
                return Combo1.getInstance();
            case COMBO2:
                return Combo2.getInstance();
            case COMBO3:
                return Combo3.getInstance();
            case COMBO4:
                return Combo4.getInstance();
            default:
                return null;
        }
    }
}
