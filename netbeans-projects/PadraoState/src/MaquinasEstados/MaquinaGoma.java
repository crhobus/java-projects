package MaquinasEstados;

public class MaquinaGoma {

    private Estado estadoVazio, estadoSemMoeda, estadoTemMoeda, estadoVendido, estado = estadoVazio;
    private int cont = 0;

    public MaquinaGoma(int numeroGomas) {
        estadoVazio = new EstadoVazio(this);
        estadoSemMoeda = new EstadoSemMoeda(this);
        estadoTemMoeda = new EstadoTemMoeda(this);
        estadoVendido = new EstadoVendido(this);
        this.cont = numeroGomas;
        if (numeroGomas > 0) {
            estado = estadoSemMoeda;
        }
    }

    public void inserirMoeda() {
        estado.inserirMoeda();
    }

    public void ejetarMoeda() {
        estado.ejetarMoeda();
    }

    public void acionarAlavanca() {
        estado.acionarAlavanca();
        estado.entregar();
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void lancamentoBala() {
        System.out.println("Fornecendo uma goma ao cliente");
        if (cont != 0) {
            cont--;
        }
    }

    public int getCont() {
        return cont;
    }

    public Estado getEstadoSemMoeda() {
        return estadoSemMoeda;
    }

    public Estado getEstadoTemMoeda() {
        return estadoTemMoeda;
    }

    public Estado getEstadoVazio() {
        return estadoVazio;
    }

    public Estado getEstadoVendido() {
        return estadoVendido;
    }

    @Override
    public String toString() {
        return "Maquina de goma";
    }
}
