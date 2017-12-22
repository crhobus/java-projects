
import java.io.*;

public class ArrayItemPedido implements Serializable {//Serializable Trabalha com arquivo

    private String nome, Descricao, Modelo;
    private int CodItem, CodPedido, CodProduto, Codigo;

    ArrayItemPedido(String nome, String descricao, String modelo, int coditem, int codpedido, int codproduto, int codigo) {

        this.nome = nome;
        this.Descricao = descricao;
        this.Modelo = modelo;
        this.CodItem = coditem;
        this.CodPedido = codpedido;
        this.CodProduto = codproduto;
        this.Codigo = codigo;
    }

    ArrayItemPedido() {
    }

    public int getCodItem() {
        return CodItem;
    }

    public void setCodItem(int CodItem) {
        this.CodItem = CodItem;
    }

    public int getCodPedido() {
        return CodPedido;
    }

    public void setCodPedido(int CodPedido) {
        this.CodPedido = CodPedido;
    }

    public int getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(int CodProduto) {
        this.CodProduto = CodProduto;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }
}
