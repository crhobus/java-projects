package MaquinasEstados;

public class EstadoVendido implements Estado {

    private MaquinaGoma maquinaGoma;

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
        if (maquinaGoma.getCont() > 0) {
            maquinaGoma.setEstado(maquinaGoma.getEstadoSemMoeda());
        } else {
            System.out.println("Oops, out of gumballs!");
            maquinaGoma.setEstado(maquinaGoma.getEstadoVazio());
        }
    }
}
