package Exe4AgenciaEmpregoPadraoObserver;

public class PublicacaoRevista implements VagaListener {

    public void novaVaga(Vaga vaga) {
        if (vaga.getSalario() > 5000) {
            System.out.printf("Publicação na revista, na area de %s, na empresa %s, com salario %.2f\n", vaga.getArea(), vaga.getEmpresa(), vaga.getSalario());
        }
    }
}
