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
@Table(name = "pagador")
@TableGenerator(name = "pagador_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "pagadorseq", valueColumnName = "valor", allocationSize = 1)
public class Pagador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "pagador_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_sequencia")
    private long nrSequencia;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_seq_pessoa", nullable = false)
    private Pessoa pessoa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_conta", nullable = false)
    private Beneficiario beneficiario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_banco", nullable = false)
    private Banco banco;

    public Pagador() {
        this.pessoa = new Pessoa();
    }

    public long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 53 * hash + Objects.hashCode(this.pessoa);
        hash = 53 * hash + Objects.hashCode(this.beneficiario);
        hash = 53 * hash + Objects.hashCode(this.banco);
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
        final Pagador other = (Pagador) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.beneficiario, other.beneficiario)) {
            return false;
        }
        if (!Objects.equals(this.banco, other.banco)) {
            return false;
        }
        return true;
    }
}
