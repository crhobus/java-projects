package br.com.app.rastreioevtspessoafisica.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RendaPessoaFisicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal renda;

    private Integer idade;

    private String dataRenda;

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getDataRenda() {
        return dataRenda;
    }

    public void setDataRenda(String dataRenda) {
        this.dataRenda = dataRenda;
    }

}
