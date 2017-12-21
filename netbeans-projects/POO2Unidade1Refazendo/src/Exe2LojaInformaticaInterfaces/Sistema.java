package Exe2LojaInformaticaInterfaces;

public class Sistema {

    public static void main(String[] args) {
        Loja loja = new Loja();
        Vendas vendas = new Vendas();
        vendas.setNomeProd("Notebook 7");
        vendas.setPreco(2476.89);
        vendas.setDescontos(3);
        vendas.setQtdade(3);
        loja.addReceita(vendas);

        vendas = new Vendas();
        vendas.setNomeProd("Notebook 13");
        vendas.setPreco(2000.99);
        vendas.setDescontos(2.9);
        vendas.setQtdade(2);
        loja.addReceita(vendas);

        vendas = new Vendas();
        vendas.setNomeProd("Teclado");
        vendas.setPreco(22);
        vendas.setDescontos(0);
        vendas.setQtdade(1);
        loja.addReceita(vendas);

        vendas = new Vendas();
        vendas.setNomeProd("Impressora");
        vendas.setPreco(230);
        vendas.setDescontos(1.6);
        vendas.setQtdade(2);
        loja.addReceita(vendas);

        vendas = new Vendas();
        vendas.setNomeProd("iMac Apple");
        vendas.setPreco(2897.78);
        vendas.setDescontos(1);
        vendas.setQtdade(1);
        loja.addReceita(vendas);

        Servico servico = new Servico();
        servico.setNomeServico("Manutenção em computador");
        servico.setValorServico(80);
        loja.addReceita(servico);

        DespesaContas despesaContas = new DespesaContas();
        despesaContas.setNomeDespesa("Conta Luz");
        despesaContas.setValorDespesa(230.76);
        loja.addDespesa(despesaContas);

        despesaContas = new DespesaContas();
        despesaContas.setNomeDespesa("Conta água");
        despesaContas.setValorDespesa(85.38);
        loja.addDespesa(despesaContas);

        despesaContas = new DespesaContas();
        despesaContas.setNomeDespesa("Conta telefone");
        despesaContas.setValorDespesa(98.26);
        loja.addDespesa(despesaContas);

        despesaContas = new DespesaContas();
        despesaContas.setNomeDespesa("Aluguel");
        despesaContas.setValorDespesa(1300.87);
        loja.addDespesa(despesaContas);

        DespesaSalarios despesasSalarios = new DespesaSalarios();
        despesasSalarios.setNome("João Silva");
        despesasSalarios.setSalario(2000);
        loja.addDespesa(despesasSalarios);

        despesasSalarios = new DespesaSalarios();
        despesasSalarios.setNome("Joana Souza");
        despesasSalarios.setSalario(2000);
        loja.addDespesa(despesasSalarios);

        DespesaEntrega despesaFrete = new DespesaEntrega();
        despesaFrete.setEntrega("Indaial");
        despesaFrete.setKm(18);
        loja.addDespesa(despesaFrete);

        loja.mostrar();
        System.out.printf("Total de Rendimentos: %.2f\n", loja.getTotalRendimentos());
        System.out.printf("Total de Despesas: %.2f\n\n", loja.getTotalDespesas());
        System.out.printf("Lucro: %.2f\n", loja.getlucroLoja());
    }
}
