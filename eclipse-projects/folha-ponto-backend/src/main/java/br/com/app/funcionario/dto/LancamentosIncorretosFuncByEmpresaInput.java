package br.com.app.funcionario.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CNPJ;

public class LancamentosIncorretosFuncByEmpresaInput implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "CNPJ da empresa não pode ser vazio")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotNull(message = "Data de lançamento não pode ser vazio")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9])", message = "Data de lançamento inválida")
    private String data;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
