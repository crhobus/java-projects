package FileInputOutputDataInputOutputStream;

import java.io.*;

public class ArqBin {

    public static void main(String[] args) throws IOException {
        FileOutputStream arqSai = new FileOutputStream("arqmeu.dat");//cria Arquivo
        DataOutputStream saida = new DataOutputStream(arqSai);//
        int produto[] = {111, 222, 333, 444}, i;
        float preco[] = {10.99f, 20.99f, 30.99f, 40.99f};
        for (i = 0; i < 4; i++) {
            saida.writeInt(produto[i]);//Escreve o produto no arquivo de dados
            saida.writeFloat(preco[i]);//Escreve o preço do produto, dados no arquivo
        }
        saida.close();//fecha o arquivo
        System.out.println("Arquivo Gerado e Fechado");
    }
}
