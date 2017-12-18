package produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "codigo", nullable = false, columnDefinition = "number(10)")
    private int codigo;
    @Column(name = "nome", nullable = false, columnDefinition = "varchar2(50)")
    private String nome;
    @Column(name = "descricao", nullable = false, columnDefinition = "varchar2(80)")
    private String descricao;
    @Column(name = "preco", nullable = false, columnDefinition = "number(10,2)")
    private double preco;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
