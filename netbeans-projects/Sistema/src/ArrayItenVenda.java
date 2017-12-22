
import java.io.*;

public class ArrayItenVenda implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Descricao, Modelo;
    private int CodIten, CodVenda, CodProduto, Codigo;
    private float Total;

    ArrayItenVenda(String nome, String descricao, String modelo, int coditem, int codvenda, int codProd, int codigo, float total) {

        this.Nome = nome;
        this.Descricao = descricao;
        this.Modelo = modelo;
        this.CodIten = coditem;
        this.CodVenda = codvenda;
        this.CodProduto = codProd;
        this.Codigo = codigo;
        this.Total = total;
    }

    ArrayItenVenda() {
    }

    public int getCodIten() {
        return CodIten;
    }

    public void setCodIten(int CodIten) {
        this.CodIten = CodIten;
    }

    public int getCodProduto() {
        return CodProduto;
    }

    public void setCodProduto(int CodProduto) {
        this.CodProduto = CodProduto;
    }

    public int getCodVenda() {
        return CodVenda;
    }

    public void setCodVenda(int CodVenda) {
        this.CodVenda = CodVenda;
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
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
}
