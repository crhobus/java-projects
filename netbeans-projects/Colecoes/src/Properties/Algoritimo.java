package Properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Algoritimo {

    private Properties tabela;

    public Algoritimo() {
        tabela = new Properties();
        tabela.setProperty("Cor", "Azul");//Armazena um valor na chave especificada
        tabela.setProperty("Cor2", "Verde");
        tabela.setProperty("Largura", "200");
        System.out.println("Após a definição de Properties");
        listaProperties();
        tabela.setProperty("Cor", "Vermelho");
        System.out.println("Após substituir as Properties");
        listaProperties();
        salvarProperties();
        tabela.clear();
        System.out.println("Após limpar as Properties");
        listaProperties();
        carregaProperties();
        Object valor = tabela.getProperty("Cor");//Localiza o valor pela chave
        if (valor != null) {
            System.out.printf("Valor das cores da propriedade é %s\n", valor);
        } else {
            System.out.println("A cor da propriedade não esta na tabela");
        }
    }

    private void salvarProperties() {
        try {
            FileOutputStream output = new FileOutputStream("Properties.dat");
            tabela.store(output, "Propriedades da amostra");//salva o conteúdo do objeto properties no objeto OutputStream e o segundo argumento, uma String é uma descrição do objeto properties
            output.close();
            System.out.println("Após salvar as propriedades");
            listaProperties();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void carregaProperties() {
        try {
            FileInputStream input = new FileInputStream("Properties.dat");
            tabela.load(input);
            input.close();
            System.out.println("Após carregar as propriedades");
            listaProperties();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void listaProperties() {
        Set<Object> chaves = tabela.keySet();
        for (Object chave : chaves) {
            System.out.printf("%s\t%s\n", chave, tabela.getProperty((String) chave));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Algoritimo();
    }
}
