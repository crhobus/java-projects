package Exe4AgenciaEmpregoPadraoObserver;

public class PublicacaoSite implements VagaListener {

    public void novaVaga(Vaga vaga) {
        System.out.printf("Publicação no site, na area de %s, na empresa %s, com salario %.2f\n", vaga.getArea(), vaga.getEmpresa(), vaga.getSalario());
    }
}
