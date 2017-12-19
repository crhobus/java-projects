package Exe8;

import java.util.ArrayList;
import java.util.List;

public class Controle {

    private List<Produto> lista = new ArrayList<Produto>();

    public void addProduto(Produto produto) {
        lista.add(produto);
    }

    public String getProdutos() {
        String str = "Código - Produto - Marca - Quantidade - Valor(Unitário)\n";
        for (Produto produto : lista) {
            str += produto.getCodigo() + " - " + produto.getProduto() + " - " + produto.getMarca()
                    + " - " + produto.getQuantidade() + " - " + produto.getValor() + "\n";
        }
        return str;
    }

    public String getProduto(int codigo) {
        String str = "";
        for (Produto produto : lista) {
            if (produto.getCodigo() == codigo) {
                str += "Código: " + produto.getCodigo()
                        + "\nProduto: " + produto.getProduto()
                        + "\nMarca: " + produto.getMarca()
                        + "\nQuantidade: " + produto.getQuantidade()
                        + "\nValor(Unitário): " + produto.getValor() + "\n";
            }
        }
        return str;
    }
}
