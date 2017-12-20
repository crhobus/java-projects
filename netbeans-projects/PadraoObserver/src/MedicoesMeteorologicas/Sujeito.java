package MedicoesMeteorologicas;

import java.util.ArrayList;
import java.util.List;

public class Sujeito {

    private List<PrevisaoTempoListener> listeners = new ArrayList<PrevisaoTempoListener>();

    public void addListener(PrevisaoTempoListener l) {
        listeners.add(l);
    }

    public void removeListener(PrevisaoTempoListener l) {
        listeners.remove(l);
    }

    protected void notifica() {
        for (PrevisaoTempoListener e : listeners) {
            e.tempoMudou();
        }
    }
}
