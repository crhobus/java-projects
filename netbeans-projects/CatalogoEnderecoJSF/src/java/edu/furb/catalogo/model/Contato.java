package edu.furb.catalogo.model;

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

/**
 *
 * @author Caio
 */
@Entity
@Table(name = "contato")
@TableGenerator(name = "contato_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "contatoseq", valueColumnName = "valor", allocationSize = 1)
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "contato_seq", strategy = GenerationType.TABLE)
    @Column(name = "codigo", columnDefinition = "number(10)")
    private long codigo;
    @Column(name = "nome", columnDefinition = "varchar2(80)", nullable = false)
    private String nome;
    @Column(name = "telefone", columnDefinition = "varchar2(13)", nullable = false)
    private String telefone;
    @Column(name = "email", columnDefinition = "varchar2(120)", nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_categoria", columnDefinition = "number(10)", nullable = false)
    private Categoria categoria;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.telefone);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.categoria);
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
        final Contato other = (Contato) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
}
