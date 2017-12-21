package Exe4AgenciaEmpregoPadraoObserver;

public class Sistema {

    public static void main(String[] args) {
        Agencia agencia = new Agencia();
        PublicacaoEmail publicacaoEmail = new PublicacaoEmail();

        Candidato candidato1 = new Candidato();
        candidato1.setNome("Caio");
        candidato1.setEmail("caio@hotmail.com");
        candidato1.addArea("Programador Java");
        candidato1.addArea("programador Delphi");
        publicacaoEmail.addCandidato(candidato1);

        Candidato candidato2 = new Candidato();
        candidato2.setNome("João");
        candidato2.setEmail("joao@bol.com.br");
        candidato2.addArea("Logistica");
        candidato2.addArea("Técnico em informática");
        publicacaoEmail.addCandidato(candidato2);

        Candidato candidato3 = new Candidato();
        candidato3.setNome("Marta");
        candidato3.setEmail("marta@gmail.com.br");
        candidato3.addArea("Vendas");
        candidato3.addArea("Secretária");
        publicacaoEmail.addCandidato(candidato3);

        Candidato candidato4 = new Candidato();
        candidato4.setNome("Marlon");
        candidato4.setEmail("marlon@hotmail.com");
        candidato4.addArea("Programador java");
        candidato4.addArea("Desenvolvedor de software");
        publicacaoEmail.addCandidato(candidato4);

        agencia.addPublicacao(new PublicacaoSite());
        agencia.addPublicacao(new PublicacaoRevista());
        agencia.addPublicacao(publicacaoEmail);

        agencia.novaVaga("Secretaria", "h", (float) 800.00);
        agencia.novaVaga("Programador java", "Senior", 2000);
        agencia.novaVaga("Desenvolvedor de software", "T-System", (float) 7300.87);
        agencia.novaVaga("Analista de Sistema", "T-System", (float) 5300);
    }
}
