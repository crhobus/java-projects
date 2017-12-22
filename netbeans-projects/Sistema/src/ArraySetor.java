
import java.io.*;

public class ArraySetor implements Serializable {//Serializable Trabalha com arquivo

    private String Nome;
    private int Codigo;

    ArraySetor(int codigo, String nome) {

        this.Codigo = codigo;
        this.Nome = nome;
    }

    ArraySetor() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
