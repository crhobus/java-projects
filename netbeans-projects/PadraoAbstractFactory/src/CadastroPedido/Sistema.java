package CadastroPedido;

import java.util.Date;

public class Sistema {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Caio", Regiao.SUL);
        Cliente cliente2 = new Cliente("Hobus", Regiao.NORTE);

        Produto produto1 = new Produto("Notbook", 5.56, 2450.76);
        Produto produto2 = new Produto("Computador", 13, 2000);
        Produto produto3 = new Produto("Monitor LCD", 9, 1200.87);
        Produto produto4 = new Produto("Teclado", 0.76, 56);
        Produto produto5 = new Produto("Mouse", 0.12, 15.98);

        Pedido pedido1 = new Pedido(cliente1, new Date());

        ItemPedido item1 = new ItemPedido();
        item1.setProduto(produto1);
        item1.setQtdade(1);
        pedido1.addItem(item1);

        item1 = new ItemPedido();
        item1.setProduto(produto2);
        item1.setQtdade(3);
        pedido1.addItem(item1);

        item1 = new ItemPedido();
        item1.setProduto(produto3);
        item1.setQtdade(2);
        pedido1.addItem(item1);

        item1 = new ItemPedido();
        item1.setProduto(produto4);
        item1.setQtdade(5);
        pedido1.addItem(item1);

        item1 = new ItemPedido();
        item1.setProduto(produto5);
        item1.setQtdade(2);
        pedido1.addItem(item1);

        pedido1.imprime();

        System.out.println();

        Pedido pedido2 = new Pedido(cliente2, new Date());

        ItemPedido item2 = new ItemPedido();
        item2.setProduto(produto4);
        item2.setQtdade(40);
        pedido2.addItem(item2);

        item2 = new ItemPedido();
        item2.setProduto(produto5);
        item2.setQtdade(3);
        pedido2.addItem(item2);

        pedido2.imprime();
    }
}
