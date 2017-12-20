package ControleRemoto;

public class SimplesControleRemoto {

    private Comando slot;

    public SimplesControleRemoto() {
    }

    public void setComando(Comando comando) {
        this.slot = comando;
    }

    public void botaoFoiPrecionado() {
        slot.executar();
    }
}
