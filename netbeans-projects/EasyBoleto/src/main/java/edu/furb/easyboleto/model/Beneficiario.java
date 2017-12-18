package edu.furb.easyboleto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
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
@Table(name = "beneficiario")
@TableGenerator(name = "beneficiario_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "beneficiarioseq", valueColumnName = "valor", allocationSize = 1)
public class Beneficiario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "beneficiario_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_conta")
    private long nrConta;
    @Column(name = "agencia", nullable = false)
    private int agencia;
    @Column(name = "dv", length = 1, nullable = false)
    private String dv;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_banco", nullable = false)
    private Banco banco;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_pessoa", nullable = false)
    private Pessoa pessoa;

    public Beneficiario() {
        this.pessoa = new Pessoa();
    }

    public long getNrConta() {
        return nrConta;
    }

    public void setNrConta(long nrConta) {
        this.nrConta = nrConta;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.nrConta ^ (this.nrConta >>> 32));
        hash = 31 * hash + this.agencia;
        hash = 31 * hash + Objects.hashCode(this.dv);
        hash = 31 * hash + Objects.hashCode(this.banco);
        hash = 31 * hash + Objects.hashCode(this.pessoa);
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
        final Beneficiario other = (Beneficiario) obj;
        if (this.nrConta != other.nrConta) {
            return false;
        }
        if (this.agencia != other.agencia) {
            return false;
        }
        if (!Objects.equals(this.dv, other.dv)) {
            return false;
        }
        if (!Objects.equals(this.banco, other.banco)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }
}
