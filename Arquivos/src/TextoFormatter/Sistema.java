package TextoFormatter;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static void main(String[] args) {
        ArquivoTexto arqTxt = new ArquivoTexto();
        arqTxt.addRegistros();
        List<Conta> listaConta = new ArrayList<Conta>();
        listaConta = arqTxt.lerRegistros();
        for (int i = 0; i < listaConta.size(); i++) {
            System.out.printf("%-10d%-12s%-12s%10.2f\n", listaConta.get(i).getConta(), listaConta.get(i).getNome(),
                    listaConta.get(i).getSobrenome(), listaConta.get(i).getSaldo());
        }
    }
}
