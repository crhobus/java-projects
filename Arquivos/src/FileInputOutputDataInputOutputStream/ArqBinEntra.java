package FileInputOutputDataInputOutputStream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ArqBinEntra {

    public static void main(String[] args) throws IOException {
        FileInputStream arqEntra = new FileInputStream("arqmeu.dat");//Encontra arquivo que ja esta criado
        DataInputStream entra = new DataInputStream(arqEntra);//Leitor do Arquivo
        float precoLido;
        int produtoLido, i;
        for (i = 0; i < 4; i++) {//Lendo o arquivo encontrado
            produtoLido = entra.readInt();//leitura do produto
            precoLido = entra.readFloat();//leitura do preço
            System.out.print("\nProduto " + produtoLido);//le cada produto e mostra
            System.out.print(" Tem preco = " + precoLido);//le cada item de preço e mostra
        }
        entra.close();//fecha o arquivo
        System.out.println("\nArquivo Fechado");
    }
}
