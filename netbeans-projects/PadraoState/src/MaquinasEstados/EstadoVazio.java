package MaquinasEstados;

public class EstadoVazio implements Estado {

    private MaquinaGoma maquinaGoma;

    public EstadoVazio(MaquinaGoma maquinaGoma) {
        this.maquinaGoma = maquinaGoma;
    }

    public void inserirMoeda() {
        System.out.println("Você não pode inserir moeda, a máquina é vendida para fora");
    }

    public void ejetarMoeda() {
        System.out.println("Você não pode ejetar, você não inseriu uma moeda ainda");
    }

    public void acionarAlavanca() {
        System.out.println("Você acionou a alavanca, mas não há gomas");
    }

    public void entregar() {
        System.out.println("Nenhuma goma entrege");
    }
}
