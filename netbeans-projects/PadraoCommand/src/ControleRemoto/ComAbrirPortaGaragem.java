package ControleRemoto;

public class ComAbrirPortaGaragem implements Comando {

    private PortaGaragem portaGaragem;

    public ComAbrirPortaGaragem(PortaGaragem portaGaragem) {
        this.portaGaragem = portaGaragem;
    }

    public void executar() {
        portaGaragem.abrir();
    }
}
