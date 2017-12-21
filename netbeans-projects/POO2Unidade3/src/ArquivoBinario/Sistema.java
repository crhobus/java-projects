package ArquivoBinario;

import java.util.*;
import javax.swing.*;

public class Sistema {

    public static void main(String[] args) {
        Retangulo retangulo = new Retangulo();
        retangulo.setNome("HMK");
        retangulo.setAltura(21);
        retangulo.setLargura(8);
        try {
            RetanguloDAO retanguloDAO = new RetanguloDAO("arquivo");
            retanguloDAO.gravar(retangulo);
            System.out.println("Abaixo todos os retangulos cadastrados no arquivo de acesso aleatório");
            int y = 0;
            while (y < retanguloDAO.qtdadeRetangulo()) {
                System.out.println("Nome: " + retanguloDAO.ler(y).getNome());
                System.out.println("Altura: " + retanguloDAO.ler(y).getAltura());
                System.out.println("Largura: " + retanguloDAO.ler(y).getLargura());
                System.out.println();
                y++;
            }
            int n = 4698;
            System.out.println("Todos os Retangulos que possuem a area " + n);
            List<Retangulo> lista2 = new ArrayList<Retangulo>();
            lista2 = retanguloDAO.getRetanguloArea(n);
            for (int i = 0; i < lista2.size(); i++) {
                Retangulo retangulo2 = lista2.get(i);
                System.out.println("Nome: " + retangulo2.getNome());
                System.out.println("Altura: " + retangulo2.getAltura());
                System.out.println("Largura: " + retangulo2.getLargura());
                System.out.println();
            }
            System.out.println();
            System.out.println("Quantidade de retangulos cadastrados: " + retanguloDAO.qtdadeRetangulo());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
