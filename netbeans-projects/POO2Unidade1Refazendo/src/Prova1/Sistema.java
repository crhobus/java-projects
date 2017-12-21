package Prova1;

public class Sistema {

    public static void main(String[] args) {
        Banco banco = new Banco();
        MovimentacaoSMS movimentacaoSMS = new MovimentacaoSMS();
        MovimentacaoSite movimentacaoSite = new MovimentacaoSite();
        banco.addMovimentacao(movimentacaoSMS);
        banco.addMovimentacao(movimentacaoSite);
        ContaCorrente conta1 = new ContaCorrente("345", "542", "544", 0);
        ContaCorrente conta2 = new ContaCorrente("525", "785", "322", 0);
        ContaCorrente conta3 = new ContaCorrente("896", "963", "456", 0);
        ContaCorrente conta4 = new ContaCorrente("100", "145", "234", 0);
        ContaCorrente conta5 = new ContaCorrente("78", "852", "789", 0);
        banco.novaConnta(conta1);
        banco.novaConnta(conta2);
        banco.novaConnta(conta3);
        banco.novaConnta(conta4);
        banco.novaConnta(conta5);
        banco.creditar(conta1, 145);
        banco.creditar(conta1, 145);
        banco.creditar(conta1, 145);
        banco.creditar(conta2, 8500);
        banco.debitar(conta1, 100);
        banco.debitar(conta2, 12333);
        banco.creditar(conta1, (float) 45.90);
    }
}
