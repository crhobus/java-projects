package HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public class Algoritimo {

    private Map<String, Integer> mapa;

    public Algoritimo() {
        mapa = new HashMap<String, Integer>();//Criação do HashMap vazio com a capacidade inicial padrão (16 elementos) e um fator de carga padrão (0,75). HashMap aceita dois argumentos o primeiro String é a chave e o segundo Integer é o tipo do de valor
        criarMapa();
        mostrarMapa();
        recuperarMapa();
        removeMapa();
        mostrarMapa();
    }

    private void criarMapa() {
        boolean repete = true;
        do {
            String palavra = JOptionPane.showInputDialog(null, "Entre com uma palavra");
            if (palavra == null) {
                repete = false;
            } else {
                StringTokenizer tokenizer = new StringTokenizer(palavra);//StringTokenizer divide a palavra em duas palavras utilizando o espaço em branco
                while (tokenizer.hasMoreTokens()) {//Enquanto houver mais palavra divideas
                    String s = tokenizer.nextToken().toLowerCase();//verifica o proximo tokem e converte em minusculo retornando uma string
                    if (mapa.containsKey(s)) {//verifica se a palavra esta no mapa
                        int cont = mapa.get(s);//se a palavra existir no mapa utiliza o metodo mapa.get(s) para obter o valor associado (a contagem) da chave no mapa
                        mapa.put(s, cont + 1);//incrementa esse valor e utiliza put para substituir o valor associano da chave no mapa
                    } else {
                        mapa.put(s, 1);//se não tiver cria uma nova entrada no mapa com a chave como String e um objeto Integer que contem 1 como valor
                    }
                }
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar novamente? ", "Entrada", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    repete = true;
                } else {
                    repete = false;
                }
            }
        } while (repete);
    }

    private void mostrarMapa() {
        Set<String> chaves = mapa.keySet();//keySet obtem um conjunto da chaves
        TreeSet<String> chaveOrdenada = new TreeSet<String>(chaves);//cria um TreeSet para ordenar as chaves
        System.out.println("Mapa contém:\nChave\t\tValor");
        for (String chave : chaveOrdenada) {
            System.out.printf("%-10s%15s\n", chave, mapa.get(chave));
        }
        System.out.printf("\nTamanho: %d\nVazio: %b\n", mapa.size(), mapa.isEmpty());
    }

    private void recuperarMapa() {
        boolean repete = true;
        do {
            String palavra = JOptionPane.showInputDialog(null, "Entre com a palavra");
            if (palavra == null) {
                repete = false;
            } else {
                if (mapa.containsKey(palavra)) {
                    JOptionPane.showMessageDialog(null, "A palavra está no mapa");
                } else {
                    JOptionPane.showMessageDialog(null, "A palavra não está no mapa");
                }
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja recuperar mais uma palavra? ", "Entrada", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    repete = true;
                } else {
                    repete = false;
                }
            }
        } while (repete);
    }

    private void removeMapa() {
        int resRepete = JOptionPane.showConfirmDialog(null, "Deseja remover uma palavra do mapa? ", "Entrada", JOptionPane.YES_NO_OPTION);
        if (resRepete == 0) {
            boolean repete = true;
            do {
                String palavra = JOptionPane.showInputDialog(null, "Entre com a palavra");
                if (palavra == null) {
                    repete = false;
                } else {
                    if (mapa.containsKey(palavra)) {
                        mapa.remove(palavra);
                        JOptionPane.showMessageDialog(null, "Palavra removida do mapa do mapa");
                    } else {
                        JOptionPane.showMessageDialog(null, "A palavra não encontrada no mapa");
                    }
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja remover mais uma palavra? ", "Saída", JOptionPane.YES_NO_OPTION);
                    if (resposta == 0) {
                        repete = true;
                    } else {
                        repete = false;
                    }
                }
            } while (repete);
            System.out.println("\n\n\n\n");
        }
    }

    public static void main(String[] args) {
        new Algoritimo();
    }
}
