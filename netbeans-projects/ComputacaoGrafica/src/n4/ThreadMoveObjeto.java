package n4;

public class ThreadMoveObjeto extends Thread {

    private Desenhar desenhar;

    public ThreadMoveObjeto(Desenhar desenhar) {
        this.desenhar = desenhar;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(500);
                if (((desenhar.getNovoTipoObjeto() == 2
                        && desenhar.getPosicaoPz() == 1.5)
                        || (desenhar.getNovoTipoObjeto() == 1
                        && desenhar.getPosicaoPz() == 0.5)
                        || (desenhar.getNovoTipoObjeto() == 3
                        && desenhar.getPosicaoPz() == 2.5))
                        || desenhar.getMundo().verificaPosicaoOcupada(desenhar.getPosicaoPx(), (desenhar.getPosicaoPz() - 1), desenhar.getTipoObjetoEmCena())) {
                    desenhar.proximoObj();
                    break;
                } else if (!desenhar.getMundo().verificaPosicaoOcupada(desenhar.getPosicaoPx(), (desenhar.getPosicaoPz() - 1), desenhar.getTipoObjetoEmCena())) {
                    desenhar.setPosicaoPz(desenhar.getPosicaoPz() - 1);
                }
            } catch (InterruptedException ex) {}
        }
    }
}
