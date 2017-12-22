package Arquivos;

import java.io.*;
import java.util.*;

public class LerArquivo {

    public ArrayList lerArquivo(String nome) throws Exception {
        ArrayList lista = new ArrayList();
        try {
            FileInputStream Arq = new FileInputStream("C:/Users/Caio/Documents/NetBeansProjects/Sistema Vendas produtos/Dados/" + nome + ".dat");
            ObjectInputStream entra = new ObjectInputStream(Arq);
            lista = (ArrayList) entra.readObject();
            entra.close();
        } catch (Exception ex) {
        }
        return lista;
    }
}
