package model.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    // Código
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_produto", columnDefinition = "number(10)")
    private int cdProduto;
    // Descriçao
    @Column(name = "ds_produto", nullable = false, columnDefinition = "varchar2(50)")
    private String dsProduto;
    // Preço
    @Column(name = "vl_produto", nullable = false, columnDefinition = "number(10,2)")
    private double vlProduto;
    // Marca
    @Column(name = "ds_marca", nullable = false, columnDefinition = "varchar2(150)")
    private String dsMarca;
    // Estoque
    @Column(name = "vl_estoque", nullable = false, columnDefinition = "number(10,2)")
    private double vlEstoque;
    // Observação
    @Column(name = "ds_observacao", nullable = false, columnDefinition = "varchar2(150)")
    private String dsObservacao;
    // Tipo
    @Column(name = "ds_tipo", nullable = false, columnDefinition = "varchar2(50)")
    private String dsTipo;
    // Validade
    @Column(name = "dt_validade", nullable = false, columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private Date dtValidade;
    // cADASTRO
    @Column(name = "dt_cadastro", nullable = false, columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;

    public int getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(int cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getDsMarca() {
        return dsMarca;
    }

    public void setDsMarca(String dsMarca) {
        this.dsMarca = dsMarca;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public String getDsTipo() {
        return dsTipo;
    }

    public void setDsTipo(String dsTipo) {
        this.dsTipo = dsTipo;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public double getVlEstoque() {
        return vlEstoque;
    }

    public void setVlEstoque(double vlEstoque) {
        this.vlEstoque = vlEstoque;
    }

    public double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
}
