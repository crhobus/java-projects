package MaquinaGomaRemota;

public class EstadoTemMoeda implements Estado {

    private transient MaquinaGoma maquinaGoma;

    public EstadoTemMoeda(MaquinaGoma maquinaGoma) {
        this.maquinaGoma = maquinaGoma;
    }

    public void inserirMoeda() {
        System.out.println("Você não pode inserir mais uma moeda");
    }

    public void ejetarMoeda() {
        System.out.println("Retornando a moeda");
        maquinaGoma.setEstado(maquinaGoma.getEstadoSemMoeda());
    }

    public void acionarAlavanca() {
        System.out.println("Você acionou a alavanca...");
        maquinaGoma.setEstado(maquinaGoma.getEstadoVendido());
    }

    public void entregar() {
        System.out.println("Nenhuma goma entrege");
    }
}
