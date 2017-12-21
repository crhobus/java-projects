package CaioRenanHobus.Controle;

import CaioRenanHobus.Modelo.*;
import java.util.*;

public class ProdutoControl {

    private ArrayList<Produto> lista = new ArrayList<Produto>();

    public ProdutoControl() {
        adicionar(-1);
    }

    public int tamanho() {
        return lista.size();
    }

    public void adicionar(int linha) {
        Produto cadas = new Produto();
        cadas.setNome(insere());
        cadas.setQuantidade(1);
        cadas.setUnidade("un");
        cadas.setAdicionar("+");
        cadas.setRemover("-");
        lista.add(linha + 1, cadas);
    }

    public void remover(int linha) {
        lista.remove(linha);
    }

    public Object conteudoLinha(int linha, int coluna) {
        Produto cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getNome();
            case 1:
                return cadas.getQuantidade();
            case 2:
                return cadas.getUnidade();
            case 3:
                return cadas.getAdicionar();
            default:
                return cadas.getRemover();
        }
    }

    public void alteraCelula(Object valor, int linha, int coluna) {
        if (linha < lista.size()) {
            Produto cadas = lista.get(linha);
            switch (coluna) {
                case 0:
                    cadas.setNome((String) valor);
                    break;
                case 1:
                    String qtdade = (String) valor;
                    cadas.setQuantidade(Double.parseDouble(qtdade));
                    break;
                case 2:
                    cadas.setUnidade((String) valor);
                    break;
            }
        }
    }

    public boolean existe(String nome) {
        for (int i = 0; i < lista.size(); i++) {
            Produto aux = lista.get(i);
            if (nome.equalsIgnoreCase(aux.getNome())) {
                return true;
            }
        }
        return false;
    }

    private String insere() {
        int cont = 1;
        String nome = "Padrão";
        while (existe(nome) == true) {
            cont++;
            nome = "Padrão" + cont;
        }
        return nome;
    }
}
