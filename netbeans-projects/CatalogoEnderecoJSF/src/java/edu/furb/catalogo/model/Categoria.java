package edu.furb.catalogo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Caio
 */
@Entity
@Table(name = "categoria")
@TableGenerator(name = "categoria_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "categoriaseq", valueColumnName = "valor", allocationSize = 1)
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "categoria_seq", strategy = GenerationType.TABLE)
    @Column(name = "codigo", columnDefinition = "number(10)")
    private long codigo;
    @Column(name = "nome", columnDefinition = "varchar2(50)", nullable = false)
    private String nome;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nome);
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
        final Categoria other = (Categoria) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
}
