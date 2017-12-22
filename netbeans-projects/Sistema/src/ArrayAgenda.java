
import java.io.*;

public class ArrayAgenda implements Serializable {//Serializable Trabalha com arquivo

    private String Data, Nome, Descricao, Hora;
    private int Codigo;

    ArrayAgenda(String data, String nome, String descricao, String hora, int codigo) {

        this.Data = data;
        this.Nome = nome;
        this.Descricao = descricao;
        this.Hora = hora;
        this.Codigo = codigo;
    }

    ArrayAgenda() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
