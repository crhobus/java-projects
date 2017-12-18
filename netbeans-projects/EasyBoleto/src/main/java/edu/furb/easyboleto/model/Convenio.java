package edu.furb.easyboleto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "convenio")
@TableGenerator(name = "convenio_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "convenioseq", valueColumnName = "valor", allocationSize = 1)
public class Convenio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "convenio_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_convenio")
    private long cdConvenio;
    @Column(name = "descricao", length = 4000, nullable = false)
    private String descricao;
    @Column(name = "carteira", nullable = false)
    private int carteira;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_banco", nullable = false)
    private Banco banco;

    public long getCdConvenio() {
        return cdConvenio;
    }

    public void setCdConvenio(long cdConvenio) {
        this.cdConvenio = cdConvenio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCarteira() {
        return carteira;
    }

    public void setCarteira(int carteira) {
        this.carteira = carteira;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.cdConvenio ^ (this.cdConvenio >>> 32));
        hash = 29 * hash + Objects.hashCode(this.descricao);
        hash = 29 * hash + this.carteira;
        hash = 29 * hash + Objects.hashCode(this.banco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Convenio other = (Convenio) obj;
        if (this.cdConvenio != other.cdConvenio) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (this.carteira != other.carteira) {
            return false;
        }
        if (!Objects.equals(this.banco, other.banco)) {
            return false;
        }
        return true;
    }
}
