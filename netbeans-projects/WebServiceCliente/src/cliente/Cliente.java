package cliente;

import java.util.List;
import webservice.Servico;
import webservice.ServicoService;

public class Cliente {

    public static void main(String[] args) {
        ServicoService servicoService = new ServicoService();

        Servico proxy = servicoService.getServicoPort();

        String nm = proxy.getHelloWord("Caio Renan Hobus");
        double soma = proxy.soma(10, 10.5);
        List<String> cidades = proxy.getListaCidades();

        System.out.println("1. " + nm);
        System.out.println("2. " + soma);
        System.out.println("3.cidades");
        for (String cidade : cidades) {
            System.out.println(cidade);
        }
        System.out.println("mapa");
    }
}
