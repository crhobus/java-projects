package Exe18;

import java.util.ArrayList;
import java.util.List;

public class Controle {

    private List<Produto> lista = new ArrayList<Produto>();

    public void addProduto(Produto produto) {
        lista.add(produto);
    }

    public String getProdutos() {
        String str = "Produto - Valor\n";
        for (Produto produto : lista) {
            str += produto.getProduto() + " - " + produto.getValor() + "\n";
        }
        return str;
    }

    public String getPagamento(String produto, String formaPagto) {
        for (Produto prod : lista) {
            if (prod.getProduto().equalsIgnoreCase(produto)) {
                if (formaPagto.equalsIgnoreCase("A Vista")) {
                    return "Produto: " + prod.getProduto() + ", Valor: " + prod.getValor();
                }
                if (formaPagto.equalsIgnoreCase("A Prazo 30 dias")) {
                    return "Produto: " + prod.getProduto() + ", Valor: " + (prod.getValor() * 1.1);
                }
                if (formaPagto.equalsIgnoreCase("A Prazo 60 dias")) {
                    return "Produto: " + prod.getProduto() + ", Valor: " + (prod.getValor() * 1.2);
                }
                return "Forma Pagamento inválida, entre novamente";
            }
        }
        return "Produto não encontrado";
    }
}
