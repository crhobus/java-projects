package Exe7;

import java.util.ArrayList;
import java.util.List;

public class Controle {

    private List<Palavra> palavras = new ArrayList<Palavra>();

    public void addPalavras(Palavra palavra) {
        palavras.add(palavra);
    }

    public String getPalavras(int tipo) {
        String str = "";
        for (Palavra palavra : palavras) {
            if (tipo == 1) {
                str += palavra.getPortugues() + " ";
            } else {
                str += palavra.getIngles() + " ";
            }
        }
        return str;
    }

    public String getPortugues(String palavraIng) {
        for (Palavra palavra : palavras) {
            if (palavra.getIngles().equalsIgnoreCase(palavraIng)) {
                return palavra.getPortugues();
            }
        }
        return "Palavra não encontrada";
    }

    public String getIngles(String palavraPort) {
        for (Palavra palavra : palavras) {
            if (palavra.getPortugues().equalsIgnoreCase(palavraPort)) {
                return palavra.getIngles();
            }
        }
        return "Palavra não encontrada";
    }
}
