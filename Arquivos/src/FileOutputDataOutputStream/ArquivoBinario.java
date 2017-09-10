package FileOutputDataOutputStream;

import java.io.*;
import java.io.IOException;

public class ArquivoBinario {

    public static void main(String[] args) throws IOException {
        int i;
        FileOutputStream arqMeu = new FileOutputStream("DadosMeuh.dat");
        DataOutputStream sai = new DataOutputStream(arqMeu);
        String[] cor = {"Azul", "Branco", "Verde", "Bege"};
        int[] num = {1, 2, 3, 4};
        for (i = 0; i < 4; i++) {
            sai.writeChars(cor[i]);
            sai.writeInt(num[i]);
        }
        sai.close();
        System.out.println("Arquivo Binario Gerado e fechado");
    }
}
