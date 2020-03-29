package br.com.app.lancamento.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LancamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "Data de lançamento não pode ser vazio")
    @Pattern(regexp = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(19[0-9][0-9]|20[0-9][0-9]) (0[0-9]|1[0-9]|2[0-3]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]):(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])", message = "Data de lançamento inválida")
    private String data;

    @Size(max = 255, message = "A descrição do lançamento pode ter no máximo 255 caracteres")
    private String descricao;

    @Size(max = 150, message = "A localização do lançamento pode ter no máximo 150 caracteres")
    private String localizacao;

    @NotNull(message = "O tipo de lançamento deve ser informado")
    private TipoEnum tipo;

    @NotNull(message = "ID do funcionário não pode ser vazio")
    private Long funcionarioId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

}
