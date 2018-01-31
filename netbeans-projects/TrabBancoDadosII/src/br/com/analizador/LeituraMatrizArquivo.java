package br.com.analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeituraMatrizArquivo {

    public int[][] getLeituraMatriz(int linha, int coluna, String nomeArquivo) {
        int[][] matriz = new int[linha][coluna];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("arquivosAnalizador/" + nomeArquivo + ".txt")));
            String vetorLinha[];
            for (int i = 0; i < linha; i++) {
                vetorLinha = reader.readLine().replace("{", "").replace("}", "").replace(" ", "").split(",");
                for (int y = 0; y < coluna; y++) {
                    matriz[i][y] = Integer.parseInt(vetorLinha[y]);
                }
            }
        } catch (Exception ex) {
            System.out.println("Erro ao carregar a matriz " + nomeArquivo + " para a memória");
            System.exit(0);
        }
        return matriz;
    }
}
