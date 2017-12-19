package Exe23;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    private List<Produto> lista = new ArrayList<Produto>();
    private double contaFinal, valor;

    public void addProduto(Produto produto) {
        lista.add(produto);
    }

    public String getProdutos() {
        String str = "Código - Produto - Preço";
        for (Produto prod : lista) {
            str += prod.getCodigo() + " - " + prod.getProduto() + " - "
                    + prod.getPreco() + "\n";
        }
        return str;
    }

    public Produto getProduto(int codigo) {
        for (Produto prod : lista) {
            if (prod.getCodigo() == codigo) {
                valor = prod.getPreco();
                return prod;
            }
        }
        return null;
    }

    public void setQtdade(int qtdade) {
        contaFinal += qtdade * valor;
    }

    public double getContaFinal() {
        return contaFinal;
    }

    public boolean pagarConta(double valPago) {
        if (valPago < contaFinal) {
            System.out.println("Entre com o pagamento correto");
            return false;
        } else {
            System.out.println("Troco: R$ " + (valPago - contaFinal));
            return true;
        }
    }
}
