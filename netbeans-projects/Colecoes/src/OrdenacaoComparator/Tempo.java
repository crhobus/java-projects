package OrdenacaoComparator;

public class Tempo {

    private int hora;
    private int minuto;
    private int segundo;

    public Tempo() {
        this(0, 0, 0);
    }

    public Tempo(int hora) {
        this(hora, 0, 0);
    }

    public Tempo(int hora, int minuto) {
        this(hora, minuto, 0);
    }

    public Tempo(int hora, int minuto, int segundo) {
        setTempo(hora, minuto, segundo);
    }

    public Tempo(Tempo tempo) {
        this(tempo.getHora(), tempo.getMinuto(), tempo.getSegundo());
    }

    private void setTempo(int hora, int minuto, int segundo) {
        setHora(hora);
        setMinuto(minuto);
        setSegundo(segundo);
    }

    private void setHora(int hora) {
        this.hora = ((hora >= 0 && hora < 24) ? hora : 0);
    }

    public int getHora() {
        return hora;
    }

    private void setMinuto(int minuto) {
        this.minuto = ((minuto >= 0 && minuto < 60) ? minuto : 0);
    }

    public int getMinuto() {
        return minuto;
    }

    private void setSegundo(int segundo) {
        this.segundo = ((segundo >= 0 && segundo < 60) ? segundo : 0);
    }

    public int getSegundo() {
        return segundo;
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", getHora(), getMinuto(), getSegundo());
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d %s", ((getHora() == 0 || getHora() == 12) ? 12 : getHora() % 12), getMinuto(), getSegundo(), (getHora() < 12 ? "AM" : "PM"));
    }
}
