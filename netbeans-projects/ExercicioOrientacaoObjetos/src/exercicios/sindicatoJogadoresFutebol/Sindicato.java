package exercicios.sindicatoJogadoresFutebol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Sindicato {

    private HashMap<String, Clube> clubes = new HashMap();

    public void insereClube(Clube c) {
        clubes.put(c.getNome().toLowerCase(), c);
    }

    public Clube buscaClube(String nome) {
        return clubes.get(nome.toLowerCase());
    }

    public float getTotalSalariosAtuais() {
        Collection<Clube> c = clubes.values();
        float total = 0;

        for (Clube club : c) {
            total += club.getTotalSalariosAtuais();
        }
        return total;
    }

    public float getTotalSalariosAtuais(char situacao) {
        Collection<Clube> c = clubes.values();
        float total = 0;

        for (Clube club : c) {
            total += club.getTotalSalariosAtuais(situacao);
        }
        return total;
    }

    public float getTotalSalariosNovos() {
        Collection<Clube> c = clubes.values();
        float total = 0;

        for (Clube club : c) {
            total += club.getTotalSalariosNovos();
        }
        return total;
    }

    public float getTotalSalariosNovos(char situacao) {
        Collection<Clube> c = clubes.values();
        float total = 0;

        for (Clube club : c) {
            total += club.getTotalSalariosNovos(situacao);
        }
        return total;
    }

    public Jogador getJogadorMenorSalario() {
        Collection<Clube> c = clubes.values();
        Jogador atual, menor = null;

        for (Clube club : c) {
            Iterator<Jogador> it = club.getJogadores();
            while (it.hasNext()) {
                atual = it.next();
                if (atual.menorSalario(menor)) {
                    menor = atual;
                }
            }
        }

        return menor;
    }

    public float getMediaGeralNovosSalarios() {
        Collection<Clube> c = clubes.values();
        float total = 0;
        int qtd = 0;
        Jogador atual;

        for (Clube club : c) {
            Iterator<Jogador> it = club.getJogadores();
            while (it.hasNext()) {
                atual = it.next();
                total += atual.getNovoSalario();
                qtd++;
            }
        }

        return total / qtd;
    }

    public List<Jogador> getJogadoresAcimaMediaSalarial() {
        Collection<Clube> c = clubes.values();
        Jogador atual;
        float media = this.getMediaGeralNovosSalarios();
        ArrayList<Jogador> lista = new ArrayList();

        for (Clube club : c) {
            Iterator<Jogador> it = club.getJogadores();
            while (it.hasNext()) {
                atual = it.next();
                if (atual.getNovoSalario() > media) {
                    lista.add(atual);
                }
            }
        }

        return lista;
    }

}
