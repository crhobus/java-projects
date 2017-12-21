package exercicio1;

public class Automovel {

    private int velocidade = 0;
    private double oleo;
    private int combustivel;
    private int tanqueCheio;
    private MostradorCombustivel mostraC;
    private MostradorVelocidade mostraV;
    private MostradorOleo mostraO;

    public Automovel(int tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
        this.oleo = 15;
        this.mostraC = new MostraCombustivel();
        this.mostraO = new MostraOleo();
        this.mostraV = new MostraVelocidade();
    }

    public void acelerar() {
        if (combustivel >= 15) {
            velocidade += 10;
            combustivel -= 15;
            mostraV.mostraVelocidade(velocidade);
            mostraC.mostraCombustivel(combustivel, combustivel);
        }
    }

    public void frear() {
        if (velocidade >= 10 && oleo >= 0.5) {
            velocidade -= 10;
            oleo -= 0.5;
            mostraV.mostraVelocidade(velocidade);
            mostraO.mostraOleo(oleo, 15);
        }
    }

    public void encherTanqueCombustivel() {
        this.combustivel = this.tanqueCheio;
        mostraC.mostraCombustivel(combustivel, combustivel);
    }

    public void encherOleo() {
        this.oleo = 15;
        mostraO.mostraOleo(oleo, 15);
    }

    public void setMostraC(MostradorCombustivel mostraC) {
        this.mostraC = mostraC;
    }

    public void setMostraV(MostradorVelocidade mostraV) {
        this.mostraV = mostraV;
    }

    public void setMostraO(MostradorOleo mostraO) {
        this.mostraO = mostraO;
    }
}
