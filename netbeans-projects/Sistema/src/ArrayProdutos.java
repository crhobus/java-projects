
import java.io.*;

public class ArrayProdutos implements Serializable {//Serializable Trabalha com arquivo

    private String Nome, Descricao, Numero_Serie, Modelo;
    private int Codigo, Quantidade;
    private float Valor_Unitario, IPI, Descontos, Valor_Total;

    ArrayProdutos(String nome, String descricao, String numero_serie, String modelo, int codigo, int quantidade,
            float valor_unitario, float ipi, float descontos, float valor_total) {

        this.Nome = nome;
        this.Descricao = descricao;
        this.Numero_Serie = numero_serie;
        this.Modelo = modelo;
        this.Codigo = codigo;
        this.Quantidade = quantidade;
        this.Valor_Unitario = valor_unitario;
        this.IPI = ipi;
        this.Descontos = descontos;
        this.Valor_Total = valor_total;
    }

    ArrayProdutos() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public float getDescontos() {
        return Descontos;
    }

    public void setDescontos(float Descontos) {
        this.Descontos = Descontos;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public float getIPI() {
        return IPI;
    }

    public void setIPI(float IPI) {
        this.IPI = IPI;
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

    public String getNumero_Serie() {
        return Numero_Serie;
    }

    public void setNumero_Serie(String Numero_Serie) {
        this.Numero_Serie = Numero_Serie;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public float getValor_Total() {
        return Valor_Total;
    }

    public void setValor_Total(float Valor_Total) {
        this.Valor_Total = Valor_Total;
    }

    public float getValor_Unitario() {
        return Valor_Unitario;
    }

    public void setValor_Unitario(float Valor_Unitario) {
        this.Valor_Unitario = Valor_Unitario;
    }
}
