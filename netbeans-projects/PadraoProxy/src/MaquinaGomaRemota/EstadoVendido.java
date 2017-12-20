package MaquinaGomaRemota;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoVendido implements Estado {

    private transient MaquinaGoma maquinaGoma;

    public EstadoVendido(MaquinaGoma maquinaGoma) {
        this.maquinaGoma = maquinaGoma;
    }

    public void inserirMoeda() {
        System.out.println("Aguarde, já estamos dando-lhe uma bola de chiclete");
    }

    public void ejetarMoeda() {
        System.out.println("Desculpe, você já acionou a alavanca");
    }

    public void acionarAlavanca() {
        System.out.println("Passando por duas vezes não te outra goma");
    }

    public void entregar() {
        maquinaGoma.lancamentoBala();
        try {
            if (maquinaGoma.getCont() > 0) {
                maquinaGoma.setEstado(maquinaGoma.getEstadoSemMoeda());
            } else {
                System.out.println("Oops, out of gumballs!");
                maquinaGoma.setEstado(maquinaGoma.getEstadoVazio());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(EstadoVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
