package ArquivoTexto;

import java.io.*;
import java.io.IOException;

public class ArquivoTexto {

    public static void main(String[] args) throws IOException {
        String palavra;
        int i, letra;
        File arquivo = new File("MerArq.txt");
        FileWriter sai = new FileWriter(arquivo);
        FileReader entra = new FileReader(arquivo);
        for (i = 0; i < 11; i++) {
            palavra = "linha " + i + " do texto\n";
            sai.write(palavra);
        }
        sai.close();
        System.out.println("Arquivo Gerado e fechado");
        System.out.println("Arquivo Aberto e Agora para Leitura");
        System.out.println("Veja o Conteudo: ");
        letra = entra.read();
        while (letra != -1) {
            System.out.print((char) letra);
            letra = entra.read();
        }
        entra.close();
    }
}
