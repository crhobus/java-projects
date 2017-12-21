package Exe1Automovel;

public class Sistema {

    public static void main(String[] args) {

        Automovel auto = new Automovel(800);

        Mostrador tartaruga = new Tartaruga();
        Mostrador china = new China();
        Mostrador power = new PowerOil();

        auto.setMostradorVelocidade(tartaruga);
        auto.setMostradorOleo(power);

        auto.encherTanqueCombustivel();
        auto.encherOleo();

        auto.acelerar();
        auto.frear();

        auto.setMostradorOleo(china);

        auto.acelerar();
        auto.frear();

        auto.setMostradorCombustivel(china);

        auto.acelerar();
        auto.frear();

        auto.setMostradorVelocidade(china);

        auto.acelerar();
        auto.frear();
    }
}
