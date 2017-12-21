package exercicio2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Cliente c1 = new Cliente();
        c1.setEndereco(RegiaoSul.getInstancia());
        c1.setNome("Cliente 1");

        Cliente c2 = new Cliente();
        c1.setEndereco(RegiaoCentroOeste.getInstancia());
        c2.setNome("Cliente 2");


        Produto prod1 = new Produto();
        prod1.setPeso(15.0);
        prod1.setPreco(100.50);
        prod1.setQtdade(1);

        Produto prod2 = new Produto();
        prod2.setPeso(3.25);
        prod2.setPreco(19.35);
        prod2.setQtdade(1);

        List arrayProdutos = new ArrayList<Produto>();
        arrayProdutos.add(prod1);
        arrayProdutos.add(prod2);

        Pedido p1 = new Pedido();
        p1.setCliente(c1);
        p1.setDataEmissao(new Date());
        p1.setArrayProdutos(arrayProdutos);


        p1.imprime();
    }
}
