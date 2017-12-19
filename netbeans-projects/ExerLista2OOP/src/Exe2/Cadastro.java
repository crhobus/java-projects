package Exe2;

import java.util.ArrayList;
import java.util.List;

public class Cadastro {

    private List<Jogador> lista = new ArrayList<Jogador>();

    public void addJogador(Jogador jogador) {
        lista.add(jogador);
    }

    public String getQtdadeCadas() {
        return "Quantidade de jogadores cadastrados: " + lista.size();
    }

    public boolean isVazio() {
        return lista.isEmpty();
    }

    public String getMaiorAltura() {
        double maiorAltura = -1;
        for (Jogador jogador : lista) {
            if (jogador.getAltura() > maiorAltura) {
                maiorAltura = jogador.getAltura();
            }
        }
        return "Altura do maior jogador: " + maiorAltura;
    }

    public String getMaiorIdade() {
        int maiorIdade = -1;
        String nome = "";
        for (Jogador jogador : lista) {
            if (jogador.getIdade() > maiorIdade) {
                maiorIdade = jogador.getIdade();
                nome = jogador.getNome();
            }
        }
        return "Jogador mais velho: " + nome + ", com a idade " + maiorIdade;
    }

    public String getMaiorPeso() {
        double maiorPeso = -1;
        String nome = "";
        for (Jogador jogador : lista) {
            if (jogador.getPeso() > maiorPeso) {
                maiorPeso = jogador.getPeso();
                nome = jogador.getNome();
            }
        }
        return "Jogador mais pesado: " + nome + ", com a idade " + maiorPeso;
    }

    public String getMediaAltura() {
        double soma = 0;
        for (Jogador jogador : lista) {
            soma += jogador.getAltura();
        }
        return "Média das alturas jogadores: " + (soma / lista.size());
    }
}
