package MaquinaGomaRemota;

public class EstadoSemMoeda implements Estado {

    private transient MaquinaGoma maquinaGoma;

    public EstadoSemMoeda(MaquinaGoma maquinaGoma) {
        this.maquinaGoma = maquinaGoma;
    }

    public void inserirMoeda() {
        System.out.println("Você inseriu uma moeda");
        maquinaGoma.setEstado(maquinaGoma.getEstadoTemMoeda());
    }

    public void ejetarMoeda() {
        System.out.println("Você não inseriu nenhuma moeda");
    }

    public void acionarAlavanca() {
        System.out.println("Você acionou a alavanca mas não há nenhuma moeda");
    }

    public void entregar() {
        System.out.println("Você precisa pagar primeiro");
    }
}
