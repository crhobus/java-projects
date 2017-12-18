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
@Table(name = "convenio_beneficiario")
@TableGenerator(name = "convenio_beneficiario_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "conveniobeneficiarioseq", valueColumnName = "valor", allocationSize = 1)
public class ConvenioBeneficiario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "convenio_beneficiario_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_sequencia")
    private long nrSequencia;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_banco", nullable = false)
    private Banco banco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_convenio", nullable = false)
    private Convenio convenio;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nr_conta", nullable = false)
    private Beneficiario beneficiario;

    public long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 41 * hash + Objects.hashCode(this.banco);
        hash = 41 * hash + Objects.hashCode(this.convenio);
        hash = 41 * hash + Objects.hashCode(this.beneficiario);
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
        final ConvenioBeneficiario other = (ConvenioBeneficiario) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (!Objects.equals(this.banco, other.banco)) {
            return false;
        }
        if (!Objects.equals(this.convenio, other.convenio)) {
            return false;
        }
        if (!Objects.equals(this.beneficiario, other.beneficiario)) {
            return false;
        }
        return true;
    }
}
