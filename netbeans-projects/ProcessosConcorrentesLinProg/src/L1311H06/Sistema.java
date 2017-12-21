package L1311H06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.UIManager;

/**
 *
 * @author Caio
 */
public class Sistema {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.exit(1);
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("L1311H06 - Caio Renan Hobus");
        int qtProdutores = getEntrada(buffer, "Informe nro. de produtores: ");
        int tamArmazem = getEntrada(buffer, "Informe nro. de áreas para armazenar produtos enviados pelo prod.: ");
        int qtConsumidores = getEntrada(buffer, "Informe nro. de consumidores: ");
        Principal principal = new Principal(qtProdutores, tamArmazem, qtConsumidores);
        principal.ativar();

        Produtor[] produtores = principal.getProdutores();
        Consumidor[] consumidores = principal.getConsumidores();

        for (Consumidor consumidor : consumidores) {
            try {
                consumidor.join();
            } catch (InterruptedException ex) {}
        }

        System.out.println("");
        System.out.println("-------------------------------------------------------x--Informações Finais--x-----------------------------------------------------------------");
        System.out.println("");

        for (Produtor produtor : produtores) {
            System.out.println("Produtos manuseados pelo PRODUTOR 1." + produtor.getIdentificador() + "..........: " + produtor.getQtProduzido());
        }
        for (Consumidor consumidor : consumidores) {
            System.out.println("Produtos manuseados pelo CONSUMIDOR 2." + consumidor.getIdentificador() + "........: " + consumidor.getQtConsumido());
        }
    }

    private static int getEntrada(BufferedReader buffer, String msg) {
        int qtd;
        while (true) {
            try {
                System.out.print(msg);
                qtd = Integer.parseInt(buffer.readLine());
                if (qtd <= 0) {
                    System.out.println("Valor inválido, entre novamente");
                } else {
                    return qtd;
                }
            } catch (Exception ex) {
                System.out.println("Valor inválido, entre novamente");
            }
        }
    }
}
