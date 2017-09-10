package ObjectInputOutputStream;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static void main(String[] args) {
        ArquivoSequencial arq = new ArquivoSequencial();
        arq.addRegistros();
        List<Conta> leituraConta = new ArrayList<Conta>();
        leituraConta = arq.lerRegistros();
        System.out.println("Total de registros: " + leituraConta.size());
        for(int i = 0; i < leituraConta.size(); i++){
            System.out.println("Conta: " + leituraConta.get(i).getConta());
            System.out.println("Nome: " + leituraConta.get(i).getNome());
            System.out.println("Sobrenome: " + leituraConta.get(i).getSobrenome());
            System.out.println("Saldo: " + leituraConta.get(i).getSaldo());
            System.out.println();
        }
    }
}
