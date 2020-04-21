package br.com.app.funcionario.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class AtualizarHorariosByEmpresaInput implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID da empresa não pode ser vazio")
    private Long empresaId;

    @NotNull(message = "Quantidade de horas trabalhadas por dia não pode ser vazio")
    @Digits(integer = 2, fraction = 2, message = "Quantidade de horas trabalhadas por dia inválido")
    @Range(min = 1, max = 10, message = "Quantidade de horas trabalhadas por dia deve ser entre 1 e 10 horas")
    private BigDecimal qtHorasTrabalhoDia;

    @NotNull(message = "Quantidade de horas de almoço por dia não pode ser vazio")
    @Digits(integer = 1, fraction = 2, message = "Quantidade de horas de almoço por dia inválido")
    @Range(min = 1, max = 2, message = "Quantidade de horas de almoço por dia deve ser entre 1 e 2 horas")
    private BigDecimal qtHorasAlmoco;

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
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

}
