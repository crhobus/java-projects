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
@Table(name = "banco")
@TableGenerator(name = "banco_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "bancoseq", valueColumnName = "valor", allocationSize = 1)
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "banco_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_banco")
    private long cdBanco;
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "dv", length = 1, nullable = false)
    private String dv;
    @Column(name = "nome", length = 256, nullable = false)
    private String nome;

    public long getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(long cdBanco) {
        this.cdBanco = cdBanco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.cdBanco ^ (this.cdBanco >>> 32));
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.dv);
        hash = 29 * hash + Objects.hashCode(this.nome);
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
        final Banco other = (Banco) obj;
        if (this.cdBanco != other.cdBanco) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.dv, other.dv)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
}
