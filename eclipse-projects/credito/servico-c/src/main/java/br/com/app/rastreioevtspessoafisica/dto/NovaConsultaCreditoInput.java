package br.com.app.rastreioevtspessoafisica.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class NovaConsultaCreditoInput implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "CPF não pode ser vazio")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O local da última consulta de crédito não pode ser vazio")
    @Length(min = 1, max = 250, message = "Local da última consulta de crédito deve conter entre 1 e 250 caracteres")
    private String localUltimaConsultaCredito;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLocalUltimaConsultaCredito() {
        return localUltimaConsultaCredito;
    }

    public void setLocalUltimaConsultaCredito(String localUltimaConsultaCredito) {
        this.localUltimaConsultaCredito = localUltimaConsultaCredito;
    }

}
