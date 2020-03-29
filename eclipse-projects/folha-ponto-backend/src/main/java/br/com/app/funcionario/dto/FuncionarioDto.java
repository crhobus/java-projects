package br.com.app.funcionario.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

public class FuncionarioDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "Nome do funcionário não pode ser vazio")
    @Length(min = 5, max = 100, message = "Nome do funcionário deve conter entre 5 e 100 caracteres")
    private String nome;

    @NotNull(message = "CPF do funcionário não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "RG do funcionário não pode ser vazio")
    @Pattern(regexp = "\\d{9}", message = "RG do funcionário deve conter 9 dígitos")
    private String rg;

    @NotNull(message = "Valor hora do funcionário não pode ser vazio")
    @Positive(message = "Valor hora do funcionário deve ser maior que zero")
    @Digits(integer = 5, fraction = 2, message = "Valor hora do funcionário inválido")
    private BigDecimal valorHora;

    @NotNull(message = "Quantidade de horas trabalhadas por dia do funcionário não pode ser vazio")
    @Digits(integer = 2, fraction = 2, message = "Quantidade de horas trabalhadas por dia do funcionário inválido")
    @Range(min = 1, max = 10, message = "Quantidade de horas trabalhadas por dia do funcionário deve ser entre 1 e 10 horas")
    private BigDecimal qtHorasTrabalhoDia;

    @NotNull(message = "Quantidade de horas de almoço por dia do funcionário não pode ser vazio")
    @Digits(integer = 1, fraction = 2, message = "Quantidade de horas de almoço por dia do funcionário inválido")
    @Range(min = 1, max = 2, message = "Quantidade de horas de almoço por dia do funcionário deve ser entre 1 e 2 horas")
    private BigDecimal qtHorasAlmoco;

    @NotNull(message = "ID da empresa do funcionário não pode ser vazio")
    private Long empresaId;

    @NotEmpty(message = "Email do funcionário não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Senha do funcionário não pode ser vazio")
    @Size(min = 6, message = "Senha do funcionário deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "A situação do funcionário deve ser informada")
    private SituacaoUserEnum situacao;

    @NotNull(message = "O perfil do funcionário deve ser informado")
    private PerfilEnum perfil;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getQtHorasTrabalhoDia() {
        return qtHorasTrabalhoDia;
    }

    public void setQtHorasTrabalhoDia(BigDecimal qtHorasTrabalhoDia) {
        this.qtHorasTrabalhoDia = qtHorasTrabalhoDia;
    }

    public BigDecimal getQtHorasAlmoco() {
        return qtHorasAlmoco;
    }

    public void setQtHorasAlmoco(BigDecimal qtHorasAlmoco) {
        this.qtHorasAlmoco = qtHorasAlmoco;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public SituacaoUserEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = SituacaoUserEnum.valueOf(situacao);
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = PerfilEnum.valueOf(perfil);
    }

}
