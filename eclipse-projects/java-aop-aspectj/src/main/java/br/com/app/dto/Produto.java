package br.com.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Código não pode ser nulo")
    private Integer codigo;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Preco não pode ser nulo")
    private BigDecimal preco;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((preco == null) ? 0 : preco.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Produto other = (Produto) obj;
        if (codigo == null) {
            if (other.codigo != null) return false;
        } else if (!codigo.equals(other.codigo)) return false;
        if (nome == null) {
            if (other.nome != null) return false;
        } else if (!nome.equals(other.nome)) return false;
        if (preco == null) {
            if (other.preco != null) return false;
        } else if (!preco.equals(other.preco)) return false;
        return true;
    }

}
