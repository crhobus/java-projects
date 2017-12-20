package ControleRemoto;

public class Sistema {

    public static void main(String[] args) {
        //SimplesControleRemoto remoto = new SimplesControleRemoto();
        ControleRemoto remoto = new ControleRemoto();

        Luz luz = new Luz();
        PortaGaragem portaGaragem = new PortaGaragem();
        AparelhoSom aparelhoSom = new AparelhoSom();

        ComandoLigarLuz ligarLuz = new ComandoLigarLuz(luz);
        ComDesLuz desligarLuz = new ComDesLuz(luz);

        ComAbrirPortaGaragem abrirPortaGaragem = new ComAbrirPortaGaragem(portaGaragem);
        ComFecharPortaGaragem fecharPortaGaragem = new ComFecharPortaGaragem(portaGaragem);

        ComLigarApaSom ligarApaSom = new ComLigarApaSom(aparelhoSom);
        ComDesApaSom deslApaSom = new ComDesApaSom(aparelhoSom);
        ComColCDApaSom colCDApaSom = new ComColCDApaSom(aparelhoSom);
        ComRetCDApaSom retCDApaSom = new ComRetCDApaSom(aparelhoSom);
        ComTocarMusCDApaSom tocarMusCDApaSom = new ComTocarMusCDApaSom(aparelhoSom);
        ComPararMusCDApaSom pararTocarMusCDApaSom = new ComPararMusCDApaSom(aparelhoSom);
        ComLigarRadioApaSom ligarRadioApaSom = new ComLigarRadioApaSom(aparelhoSom);
        ComDesRadioApaSom desligarRarioApaSom = new ComDesRadioApaSom(aparelhoSom);
        ComAumVolApaSom aumentarVolApaSom = new ComAumVolApaSom(aparelhoSom);
        ComDimVolApaSom diminuirVolApaSom = new ComDimVolApaSom(aparelhoSom);

        remoto.setComando(0, ligarLuz, desligarLuz);
        remoto.setComando(1, abrirPortaGaragem, fecharPortaGaragem);
        remoto.setComando(2, ligarApaSom, deslApaSom);
        remoto.setComando(3, colCDApaSom, retCDApaSom);
        remoto.setComando(4, tocarMusCDApaSom, pararTocarMusCDApaSom);
        remoto.setComando(5, ligarRadioApaSom, desligarRarioApaSom);
        remoto.setComando(6, aumentarVolApaSom, diminuirVolApaSom);

        remoto.botaoPressionadoLigar(0);
        remoto.botaoPressionadoDesligar(0);
        remoto.botaoPressionadoLigar(1);
        remoto.botaoPressionadoDesligar(1);
        remoto.botaoPressionadoLigar(2);
        //remoto.botaoPressionadoDesligar(2);
        remoto.botaoPressionadoLigar(3);
        //remoto.botaoPressionadoDesligar(3);
        remoto.botaoPressionadoLigar(4);
        //remoto.botaoPressionadoDesligar(4);
        //remoto.botaoPressionadoLigar(5);
        //remoto.botaoPressionadoDesligar(5);
        remoto.botaoPressionadoLigar(6);
        remoto.botaoPressionadoLigar(6);
        remoto.botaoPressionadoLigar(6);
        remoto.botaoPressionadoLigar(6);
        remoto.botaoPressionadoDesligar(6);

        /*remoto.setComando(ligarLuz);
        remoto.botaoFoiPrecionado();
        remoto.setComando(abrirPortaGaragem);
        remoto.botaoFoiPrecionado();*/
    }
}
