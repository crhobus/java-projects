
import java.io.*;

public class ArrayCidade implements Serializable {//Serializable Trabalha com arquivo

    private String Cidade, Estado;
    private int Codigo;

    ArrayCidade(String cidade, String estado, int codigo) {

        this.Cidade = cidade;
        this.Estado = estado;
        this.Codigo = codigo;
    }

    ArrayCidade() {
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}
