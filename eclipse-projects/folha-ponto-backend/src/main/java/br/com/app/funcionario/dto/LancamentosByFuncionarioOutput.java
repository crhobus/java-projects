package br.com.app.funcionario.dto;

import java.io.Serializable;
import java.util.List;

import br.com.app.lancamento.dto.LancamentoFuncOutput;

public class LancamentosByFuncionarioOutput implements Serializable {

    private static final long serialVersionUID = 1L;

    private long funcionarioId;

    private String nome;

    private String cpf;

    private String rg;

    private String email;

    private Long empresaId;

    private String nomeEmpresa;

    private String cnpjEmpresa;

    List<LancamentoFuncOutput> lancamentosFunc;

    public long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(long funcionarioId) {
        this.funcionarioId = funcionarioId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public List<LancamentoFuncOutput> getLancamentosFunc() {
        return lancamentosFunc;
    }

    public void setLancamentosFunc(List<LancamentoFuncOutput> lancamentosFunc) {
        this.lancamentosFunc = lancamentosFunc;
    }

}
