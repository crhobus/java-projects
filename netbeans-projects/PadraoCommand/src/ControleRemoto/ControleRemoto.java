package ControleRemoto;

public class ControleRemoto {

    private Comando[] ligarComandos;
    private Comando[] desligarComandos;

    public ControleRemoto() {
        ligarComandos = new Comando[7];
        desligarComandos = new Comando[7];
        Comando semComando = new SemComando();
        for (int i = 0; i < 7; i++) {
            ligarComandos[i] = semComando;
            desligarComandos[i] = semComando;
        }
    }

    public void setComando(int slot, Comando ligarComando, Comando desligarComando) {
        ligarComandos[slot] = ligarComando;
        desligarComandos[slot] = desligarComando;
    }

    public void botaoPressionadoLigar(int slot) {
        ligarComandos[slot].executar();
    }

    public void botaoPressionadoDesligar(int slot) {
        desligarComandos[slot].executar();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n----- Controle Remoto -----\n");
        for (int i = 0; i < ligarComandos.length; i++) {
            stringBuffer.append("[Slot " + i + "] " + ligarComandos[i].getClass().getName() + "   " + desligarComandos[i].getClass().getName() + "\n");
        }
        return stringBuffer.toString();
    }
}
