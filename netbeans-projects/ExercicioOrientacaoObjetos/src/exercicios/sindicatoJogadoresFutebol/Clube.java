package exercicios.sindicatoJogadoresFutebol;

import java.util.ArrayList;
import java.util.Iterator;

public class Clube {

    private String nome, cidade;
    private ArrayList<Jogador> jogadores = new ArrayList();

    public void setNome(String str) {
        nome = str;
    }

    public void setCidade(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("Nome de cidade inválido");
        }
        cidade = str;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void addJogador(Jogador pernaDePau) {
        jogadores.add(pernaDePau);
        pernaDePau.setClube(this);
    }

    public int getQtdeJogadores() {
        return jogadores.size();
    }

    public float getMediaSalarial() {
        return (this.getTotalSalariosAtuais() / jogadores.size());
    }

    public float getTotalSalariosAtuais() {
        float total = 0;

        for (Jogador j : jogadores) {
            total += j.getSalarioAtual();
        }
        return total;
    }

    public float getTotalSalariosAtuais(char situacao) {
        float total = 0;

        for (Jogador j : jogadores) {
            if (j.getSituacao() == situacao) {
                total += j.getSalarioAtual();
            }
        }
        return total;
    }

    public float getTotalSalariosNovos() {
        float total = 0;

        for (Jogador j : jogadores) {
            total += j.getNovoSalario();
        }
        return total;
    }

    public float getTotalSalariosNovos(char situacao) {
        float total = 0;

        for (Jogador j : jogadores) {
            if (j.getSituacao() == situacao) {
                total += j.getNovoSalario();
            }
        }
        return total;
    }

    public Iterator<Jogador> getJogadores() {
        return jogadores.iterator();
    }

    public String toString() {
        return this.nome;
    }
}
