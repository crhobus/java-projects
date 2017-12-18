package edu.furb.easyboleto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "especie_documento")
@TableGenerator(name = "especie_documento_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "especiedocumentoseq", valueColumnName = "valor", allocationSize = 1)
public class EspecieDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "especie_documento_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_sequencia")
    private long nrSequencia;
    @Column(name = "codigo", length = 2, nullable = false, unique = true)
    private String codigo;
    @Column(name = "descricao", length = 60, nullable = false)
    private String descricao;
    @Column(name = "assinatura", length = 256, nullable = false)
    private byte[] assinatura;

    public long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(byte[] assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 13 * hash + Objects.hashCode(this.codigo);
        hash = 13 * hash + Objects.hashCode(this.descricao);
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
        final EspecieDocumento other = (EspecieDocumento) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }
}
