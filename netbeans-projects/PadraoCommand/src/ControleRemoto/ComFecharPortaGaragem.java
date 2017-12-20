package ControleRemoto;

public class ComFecharPortaGaragem implements Comando {

    private PortaGaragem portaGaragem;

    public ComFecharPortaGaragem(PortaGaragem portaGaragem) {
        this.portaGaragem = portaGaragem;
    }

    public void executar() {
        portaGaragem.fechar();
    }
}
