package Exe4AgenciaEmpregoPadraoObserver;

import java.util.ArrayList;
import java.util.List;

public class Agencia {

    private List<VagaListener> listaVagaListener = new ArrayList<VagaListener>();
    private List<Vaga> ListaVaga = new ArrayList<Vaga>();

    public void novaVaga(String area, String empresa, float salario) {
        Vaga vaga = new Vaga(area, empresa, salario);
        ListaVaga.add(vaga);
        notifica(vaga);
    }

    public void notifica(Vaga vaga) {
        for (VagaListener listener : listaVagaListener) {
            listener.novaVaga(vaga);
        }
    }

    public void addPublicacao(VagaListener vagaListener) {
        listaVagaListener.add(vagaListener);
    }

    public void removePublicacao(VagaListener vagaListener) {
        listaVagaListener.remove(vagaListener);
    }
}
