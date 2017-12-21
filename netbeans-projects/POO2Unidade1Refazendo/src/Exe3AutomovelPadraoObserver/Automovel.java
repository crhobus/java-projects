package Exe3AutomovelPadraoObserver;

public class Automovel {

    private int velocidade = 0, combustivel = 0, tanqueCheio = 0;
    private double oleo = 0;
    private Velocidade mostraVelocidade;
    private Combustivel mostraCombustivel;
    private Oleo mostraOleo;

    public Automovel(int tanqueCheio) {
        this.tanqueCheio = tanqueCheio;
    }

    public void acelerar() {
        if (combustivel >= 15) {
            velocidade += 10;
            combustivel -= 15;
            notificaVelocidade();
            notificaCombustivel();
        }
    }

    public void frear() {
        if (velocidade >= 10 && oleo >= 0.5) {
            velocidade -= 10;
            oleo -= 0.5;
            notificaVelocidade();
            notificaOleo();
        }
    }

    public void encherTanqueCombustivel() {
        this.combustivel = this.tanqueCheio;
        notificaCombustivel();
    }

    public void encherOleo() {
        this.oleo = 15;
        mostraOleo.mostraOleo(oleo, 15);
    }

    public void notificaVelocidade() {
        if (mostraVelocidade != null) {
            mostraVelocidade.mostraVelocidade(velocidade);
        }
    }

    public void notificaCombustivel() {
        if (mostraCombustivel != null) {
            mostraCombustivel.mostraCombustivel(combustivel, tanqueCheio);
        }
    }

    public void notificaOleo() {
        if (mostraOleo != null) {
            mostraOleo.mostraOleo(oleo, 15);
        }
    }

    public void setMostraCombustivel(Combustivel mostraCombustivel) {
        this.mostraCombustivel = mostraCombustivel;
    }

    public void setMostraOleo(Oleo mostraOleo) {
        this.mostraOleo = mostraOleo;
    }

    public void setMostraVelocidade(Velocidade mostraVelocidade) {
        this.mostraVelocidade = mostraVelocidade;
    }
}
