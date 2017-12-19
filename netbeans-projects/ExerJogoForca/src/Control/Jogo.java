package Control;

import javax.swing.JOptionPane;

public class Jogo {

    private String palavra, palavraAux, termino;

    public Jogo(String palavra) {
        this.palavra = palavra;
        this.palavraAux = "";
        for (int i = 0; i < palavra.length(); i++) {
            palavraAux += "x";
        }
    }

    public String getTermino() {
        return termino;
    }

    public String getJogar(char letra) {
        boolean encontrouLetra = false;// flag letra digitada encontrada
        int[] indice = new int[palavra.length()];// vet com os índices a ser trocado na palavra aux
        char[] vet = palavra.toCharArray();
        for (int i = 0; i < vet.length; i++) {// verifica letra a letra
            if (vet[i] == letra) {
                indice[i] = 1;// seta a posição a ser trocada
                encontrouLetra = true;// encontrou uma letra
            }
        }
        if (!encontrouLetra) {
            JOptionPane.showMessageDialog(null, "Letra não encontrada", "Letra", JOptionPane.INFORMATION_MESSAGE);
            return palavraAux;
        } else {
            char[] c = palavraAux.toCharArray();
            palavraAux = "";
            for (int i = 0; i < c.length; i++) {// atualiza palavra com a letra encontrada
                if (indice[i] == 1) {
                    palavraAux += letra;// seta a letra na posição encontrada
                } else {
                    palavraAux += c[i];
                }
            }
            if (!palavraAux.contains("x")) {
                termino = "Você acertou";
            }
            JOptionPane.showMessageDialog(null, "Letra encontrada", "Letra", JOptionPane.INFORMATION_MESSAGE);
            return palavraAux;
        }
    }
}
