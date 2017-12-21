package exercicio2;

import java.util.Date;
import java.util.List;

public class Pedido {

    private List<Produto> arrayProdutos;
    private Cliente cliente;
    private Date dataEmissao;

    public List<Produto> getArrayProdutos() {
        return arrayProdutos;
    }

    public void setArrayProdutos(List<Produto> arrayProdutos) {
        this.arrayProdutos = arrayProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void imprime() throws Exception {
        for (Produto p : arrayProdutos) {
            System.out.println("Frete: " + cliente.getEndereco().getFrete().getValorFrete(cliente.getEndereco(), p.getPeso(), p.getPreco()));
            System.out.println("Imposto: " + cliente.getEndereco().getImposto().getValorImposto(cliente.getEndereco(), p.getPreco()));
        }
    }
}
