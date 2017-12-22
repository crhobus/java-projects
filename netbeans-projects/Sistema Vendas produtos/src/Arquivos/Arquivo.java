package Arquivos;

import java.io.*;
import java.util.*;

public class Arquivo {

    public void arquivo(ArrayList lista, String nome) throws Exception {
        try {
            FileOutputStream arqDados = new FileOutputStream("C:/Users/Caio/Documents/NetBeansProjects/Sistema Vendas produtos/Dados/" + nome + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(arqDados);
            out.writeObject(lista);
            out.close();
        } catch (Exception ex) {
        }
    }
}
