package br.com.app.funcionario.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.app.usuario.dto.PerfilEnum;

public class SalarioByFuncionarioOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;

    private String nome;

    private PerfilEnum perfil;

    private BigDecimal vlSalario;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    public BigDecimal getVlSalario() {
        return vlSalario;
    }

    public void setVlSalario(BigDecimal vlSalario) {
        this.vlSalario = vlSalario;
    }

}
