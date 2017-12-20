package MaquinaGomaRemota;

import java.rmi.Naming;

public class Sistema {

    public static void main(String[] args) {
        /*MaquinaGomaRemota maquinaGomaRemota = null;
        int cont = 0;
        if (args.length < 2) {
        System.out.println("Maquina Goma <nome> <registro>");
        System.exit(1);
        }
        try {
        cont = Integer.parseInt(args[1]);
        MaquinaGoma maquinaGoma = new MaquinaGoma(args[0], cont);
        Naming.rebind("//" + args[0] + "maquinaGoma", maquinaGoma);
        } catch (Exception ex) {
        ex.printStackTrace();
        }*/

        String[] localizacao = {"rmi://santafe.mightygumball.com/gumballmachine", "rmi://boulder.mightygumball.com/gumballmachine", "rmi://seattle.mightygumball.com/gumballmachine"};
        MonitorGoma[] monitor = new MonitorGoma[localizacao.length];
        for (int i = 0; i < localizacao.length; i++) {
            try {
                MaquinaGomaRemota maquina = (MaquinaGomaRemota) Naming.lookup(localizacao[i]);
                monitor[i] = new MonitorGoma(maquina);
                System.out.println(monitor[i]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        for (int i = 0; i < monitor.length; i++) {
            monitor[i].relatorio();
        }
    }
}
