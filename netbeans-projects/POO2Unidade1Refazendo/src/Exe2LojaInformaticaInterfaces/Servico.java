package Exe2LojaInformaticaInterfaces;

public class Servico implements LojaReceita {

    private String nomeServico;
    private double valorServico;

    public String getNomeReceita() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getValorReceita() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }
}
