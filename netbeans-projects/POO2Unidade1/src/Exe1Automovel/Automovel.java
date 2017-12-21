package Exe1Automovel;

public class Automovel {

    private int velocidade = 0;
    private int combustivel = 0;
    private int tanqueCheio = 0;
    private Mostrador mostradorVelocidade, mostradorOleo, mostradorCombustivel;
    private double oleo = 0;

    public Automovel(int tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
    }

    public void acelerar() {
        if (combustivel >= 15) {
            velocidade += 10;
            combustivel -= 15;
            mostradorVelocidade.mostraVelocidade(velocidade);
            if (mostradorCombustivel != null) {
                mostradorCombustivel.mostraCombustivel(tanqueCheio, combustivel);
            }
        }
    }

    public void frear() {
        if (velocidade >= 10 && oleo >= 0.5) {
            velocidade -= 10;
            oleo -= 0.5;
            mostradorOleo.mostraOleo(oleo);
        }
    }

    public void encherTanqueCombustivel() {
        this.combustivel = this.tanqueCheio;
    }

    public void encherOleo() {
        this.oleo = 15;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    public Mostrador getMostradorVelocidade() {
        return mostradorVelocidade;
    }

    public void setMostradorVelocidade(Mostrador mostradorVelocidade) {
        this.mostradorVelocidade = mostradorVelocidade;
    }

    public Mostrador getMostradorOleo() {
        return mostradorOleo;
    }

    public void setMostradorOleo(Mostrador mostradorOleo) {
        this.mostradorOleo = mostradorOleo;
    }

    public Mostrador getMostradorCombustivel() {
        return mostradorCombustivel;
    }

    public void setMostradorCombustivel(Mostrador mostradorCombustivel) {
        this.mostradorCombustivel = mostradorCombustivel;
    }
}
