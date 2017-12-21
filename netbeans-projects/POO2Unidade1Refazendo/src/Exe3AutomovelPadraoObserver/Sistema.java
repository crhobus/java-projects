package Exe3AutomovelPadraoObserver;

public class Sistema {

    public static void main(String[] args) {
        Automovel automovel = new Automovel(60);
        Velocidade veloTartCorps = new MostradorVeloTartarugaCorps();
        Velocidade veloChinaInc = new MostradorChinaInc();
        Combustivel nivelCombChinaInc = new MostradorChinaInc();
        Oleo nivelOleoChinaInc = new MostradorChinaInc();
        Oleo nivelOleoPowerOilsa = new NivelOleoPowerOilsa();
        automovel.setMostraVelocidade(veloTartCorps);
        automovel.setMostraOleo(nivelOleoPowerOilsa);
        automovel.encherTanqueCombustivel();
        automovel.encherOleo();
        automovel.acelerar();
        automovel.frear();
        automovel.setMostraOleo(nivelOleoChinaInc);
        automovel.acelerar();
        automovel.frear();
        automovel.setMostraCombustivel(nivelCombChinaInc);
        automovel.acelerar();
        automovel.frear();
        automovel.setMostraVelocidade(veloChinaInc);
        automovel.acelerar();
        automovel.frear();
    }
}
