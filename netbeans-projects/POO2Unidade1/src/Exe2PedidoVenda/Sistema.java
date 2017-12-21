package Exe2PedidoVenda;

public class Sistema {

    public static void main(String[] args) {
        String NomeCliente = "Caio";
        String EnderecoCliente = "Rua Uberaba";
        String RegiaoCliente = "Norte";
        String NomeProduto = "Computador HP";
        int QuantidadeProduto = 7;
        float PesoProduto = 3;
        float ValorUnitarioProduto = 5000;
        String DataEmissaoPedido = "27/02/2010";
        String RegiaoPedido = "Suldeste";

        Fabrica fab = new Fabrica(DataEmissaoPedido, RegiaoPedido);
        Cliente cli = new Cliente(NomeCliente, EnderecoCliente, RegiaoCliente);
        Produtos pro = new Produtos(NomeProduto, QuantidadeProduto, PesoProduto, ValorUnitarioProduto);

        if (RegiaoCliente.equals("Norte")) {
            ImpostoNorte impNorte = new ImpostoNorte(ValorUnitarioProduto);
            FreteNorteNordeste freNorte = new FreteNorteNordeste(PesoProduto);
        }
        if (RegiaoCliente.equals("Nordeste")) {
            ImpostoNordeste impNordeste = new ImpostoNordeste(ValorUnitarioProduto);
            FreteNorteNordeste freNordeste = new FreteNorteNordeste(PesoProduto);
        }
        if (RegiaoCliente.equals("Centro Oeste")) {
            ImpostoCentroOeste impCenOeste = new ImpostoCentroOeste(ValorUnitarioProduto, RegiaoPedido);
            FreteCentroOeste freCenOeste = new FreteCentroOeste(ValorUnitarioProduto, RegiaoPedido);
        }
        if (RegiaoCliente.equals("Sul")) {
            ImpostoSul impSul = new ImpostoSul(ValorUnitarioProduto);
            FreteSul freSul = new FreteSul(RegiaoPedido, PesoProduto);
        }
        if (RegiaoCliente.equals("Suldeste")) {
            ImpostoSuldeste impSuldeste = new ImpostoSuldeste(ValorUnitarioProduto, RegiaoPedido);
            FreteSuldeste freSuldeste = new FreteSuldeste(RegiaoPedido, PesoProduto);
        }
    }
}
