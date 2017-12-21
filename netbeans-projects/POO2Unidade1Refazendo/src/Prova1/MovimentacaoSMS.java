package Prova1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimentacaoSMS implements Movimentacao {

    private SimpleDateFormat sDataf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sHoraf = new SimpleDateFormat("HH:mm");

    public void transacaoEfetivada(ContaCorrente contaCorrente, float valor) {
        Date data = new Date();
        System.out.println("SMS: ---- Cliente: " + contaCorrente.getNumeroSMS() + ". Moviventação realizada em : " + sDataf.format(data).toString() + " as " + sHoraf.format(data).toString() + ", do tipo: " + contaCorrente.getConta() + " com valor: RS " + valor + ", Saldo atual da conta: " + contaCorrente.getSaldo());
    }
}
