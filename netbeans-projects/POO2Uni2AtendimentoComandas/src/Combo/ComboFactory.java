package Combo;

import Bebida.Bebida;
import PratoPrincipal.PratoPrincipal;
import Salada.Salada;
import Sobremesa.Sobremesa;

public interface ComboFactory {

    public Bebida getBebida();

    public PratoPrincipal getPratoPricipal();

    public Salada getSalada();

    public Sobremesa getSobremesa();
}
