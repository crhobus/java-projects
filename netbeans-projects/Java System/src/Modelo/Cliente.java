package Modelo;

import java.util.Date;

public class Cliente {

    private String nome, rg, cpf, sexo, estadoCivil, tipoPessoa, profissao,
            cep, endereco, bairro, complemento, referencia, cidade, estado,
            email, fone, celular1, celular2, descricao, empresa, foneEmpresa,
            fax, msn, skype, naturalidade, nomePai, rgPai, cpfPai,
            estadoCivilPai, tipoPessoaPai, profissaoPai, contato1Pai,
            contato2Pai, empresaPai, foneEmpresaPai, nomeMae, rgMae, cpfMae,
            estadoCivilMae, tipoPessoaMae, profissaoMae, contato1Mae,
            contato2Mae, empresaMae, foneEmpresaMae, opRealizada;
    private int codigo, numero, idade, anoTempoTrab, mesTempoTrab,
            anoTempoTrabPai, mesTempoTrabPai, anoTempoTrabMae, mesTempoTrabMae;
    private Date dataNasc, dataCadastro, ultAlteracao;
    private double renda, rendaPai, credito, debito, rendaMae;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFoneEmpresa() {
        return foneEmpresa;
    }

    public void setFoneEmpresa(String foneEmpresa) {
        this.foneEmpresa = foneEmpresa;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getEstadoCivilPai() {
        return estadoCivilPai;
    }

    public void setEstadoCivilPai(String estadoCivilPai) {
        this.estadoCivilPai = estadoCivilPai;
    }

    public String getTipoPessoaPai() {
        return tipoPessoaPai;
    }

    public void setTipoPessoaPai(String tipoPessoaPai) {
        this.tipoPessoaPai = tipoPessoaPai;
    }

    public String getProfissaoPai() {
        return profissaoPai;
    }

    public void setProfissaoPai(String profissaoPai) {
        this.profissaoPai = profissaoPai;
    }

    public String getContato1Pai() {
        return contato1Pai;
    }

    public void setContato1Pai(String contato1Pai) {
        this.contato1Pai = contato1Pai;
    }

    public String getContato2Pai() {
        return contato2Pai;
    }

    public void setContato2Pai(String contato2Pai) {
        this.contato2Pai = contato2Pai;
    }

    public String getEmpresaPai() {
        return empresaPai;
    }

    public void setEmpresaPai(String empresaPai) {
        this.empresaPai = empresaPai;
    }

    public String getFoneEmpresaPai() {
        return foneEmpresaPai;
    }

    public void setFoneEmpresaPai(String foneEmpresaPai) {
        this.foneEmpresaPai = foneEmpresaPai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getRgMae() {
        return rgMae;
    }

    public void setRgMae(String rgMae) {
        this.rgMae = rgMae;
    }

    public String getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(String cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getEstadoCivilMae() {
        return estadoCivilMae;
    }

    public void setEstadoCivilMae(String estadoCivilMae) {
        this.estadoCivilMae = estadoCivilMae;
    }

    public String getTipoPessoaMae() {
        return tipoPessoaMae;
    }

    public void setTipoPessoaMae(String tipoPessoaMae) {
        this.tipoPessoaMae = tipoPessoaMae;
    }

    public String getProfissaoMae() {
        return profissaoMae;
    }

    public void setProfissaoMae(String profissaoMae) {
        this.profissaoMae = profissaoMae;
    }

    public String getContato1Mae() {
        return contato1Mae;
    }

    public void setContato1Mae(String contato1Mae) {
        this.contato1Mae = contato1Mae;
    }

    public String getContato2Mae() {
        return contato2Mae;
    }

    public void setContato2Mae(String contato2Mae) {
        this.contato2Mae = contato2Mae;
    }

    public String getEmpresaMae() {
        return empresaMae;
    }

    public void setEmpresaMae(String empresaMae) {
        this.empresaMae = empresaMae;
    }

    public String getFoneEmpresaMae() {
        return foneEmpresaMae;
    }

    public void setFoneEmpresaMae(String foneEmpresaMae) {
        this.foneEmpresaMae = foneEmpresaMae;
    }

    public String getOpRealizada() {
        return opRealizada;
    }

    public void setOpRealizada(String opRealizada) {
        this.opRealizada = opRealizada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getAnoTempoTrab() {
        return anoTempoTrab;
    }

    public void setAnoTempoTrab(int anoTempoTrab) {
        this.anoTempoTrab = anoTempoTrab;
    }

    public int getMesTempoTrab() {
        return mesTempoTrab;
    }

    public void setMesTempoTrab(int mesTempoTrab) {
        this.mesTempoTrab = mesTempoTrab;
    }

    public int getAnoTempoTrabPai() {
        return anoTempoTrabPai;
    }

    public void setAnoTempoTrabPai(int anoTempoTrabPai) {
        this.anoTempoTrabPai = anoTempoTrabPai;
    }

    public int getMesTempoTrabPai() {
        return mesTempoTrabPai;
    }

    public void setMesTempoTrabPai(int mesTempoTrabPai) {
        this.mesTempoTrabPai = mesTempoTrabPai;
    }

    public int getAnoTempoTrabMae() {
        return anoTempoTrabMae;
    }

    public void setAnoTempoTrabMae(int anoTempoTrabMae) {
        this.anoTempoTrabMae = anoTempoTrabMae;
    }

    public int getMesTempoTrabMae() {
        return mesTempoTrabMae;
    }

    public void setMesTempoTrabMae(int mesTempoTrabMae) {
        this.mesTempoTrabMae = mesTempoTrabMae;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getUltAlteracao() {
        return ultAlteracao;
    }

    public void setUltAlteracao(Date ultAlteracao) {
        this.ultAlteracao = ultAlteracao;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public double getRendaPai() {
        return rendaPai;
    }

    public void setRendaPai(double rendaPai) {
        this.rendaPai = rendaPai;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getRendaMae() {
        return rendaMae;
    }

    public void setRendaMae(double rendaMae) {
        this.rendaMae = rendaMae;
    }
}
