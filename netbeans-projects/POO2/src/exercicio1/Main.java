package exercicio1;

public class Main {

    public static void main(String[] args) {
        Automovel a1 = new Automovel(50);

        MostradorCombustivel mc = new ChinaInc();
        MostradorVelocidade mv = new TartarugaCorps();
        MostradorOleo mo = new PowerOil();

        a1.setMostraC(mc);
        a1.setMostraV(mv);
        a1.setMostraO(mo);

        a1.encherTanqueCombustivel();
        a1.acelerar();
        a1.frear();
        a1.acelerar();
        a1.acelerar();
        a1.encherOleo();

        MostradorVelocidade mostraVel = new MostraVelocidade();
        MostradorOleo mostraOleo = new MostraOleo();
        a1.setMostraV(mostraVel);
        a1.setMostraO(mostraOleo);

        a1.acelerar();
        a1.frear();
    }
}
