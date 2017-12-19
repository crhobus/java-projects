package LeituraFuncionario;

import java.util.Date;

public class Funcionario {

    private String nome, sobrenome, apelido, banco, agencia, conta, contaFGTS, cpf, rg, carteiraHabilitacao,
            carteiraTrabalho, certificadoReservista, tituloEleitor, nomePai, nomeMae, pais, rua, bairro, cep,
            complemento, telefoneResidencial, telefoneComercial, telefoneCelular, chefe, cargo;
    private char sexo;
    private int estadoCivil, digitoConta, centroCusto, cracha, estabilidade, digitoCarteiraTrabalho,
            documentoEstrangeiro, descontoINSS, dependentesIR, classeContribuicaoIR, horasSemanais, inscricaoINSS,
            pis, numeroContrato, tipoContrato;
    private Date dataNascimento, dataContratacao, dataPagamento;
    private double salario, saldoFGTS;
    private boolean insentoIR, contribuicaoSindical;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCarteiraHabilitacao() {
        return carteiraHabilitacao;
    }

    public void setCarteiraHabilitacao(String carteiraHabilitacao) {
        this.carteiraHabilitacao = carteiraHabilitacao;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public int getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(int centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCertificadoReservista() {
        return certificadoReservista;
    }

    public void setCertificadoReservista(String certificadoReservista) {
        this.certificadoReservista = certificadoReservista;
    }

    public String getChefe() {
        return chefe;
    }

    public void setChefe(String chefe) {
        this.chefe = chefe;
    }

    public int getClasseContribuicaoIR() {
        return classeContribuicaoIR;
    }

    public void setClasseContribuicaoIR(int classeContribuicaoIR) {
        this.classeContribuicaoIR = classeContribuicaoIR;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaFGTS() {
        return contaFGTS;
    }

    public void setContaFGTS(String contaFGTS) {
        this.contaFGTS = contaFGTS;
    }

    public boolean isContribuicaoSindical() {
        return contribuicaoSindical;
    }

    public void setContribuicaoSindical(boolean contribuicaoSindical) {
        this.contribuicaoSindical = contribuicaoSindical;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCracha() {
        return cracha;
    }

    public void setCracha(int cracha) {
        this.cracha = cracha;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getDependentesIR() {
        return dependentesIR;
    }

    public void setDependentesIR(int dependentesIR) {
        this.dependentesIR = dependentesIR;
    }

    public int getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(int descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public int getDigitoCarteiraTrabalho() {
        return digitoCarteiraTrabalho;
    }

    public void setDigitoCarteiraTrabalho(int digitoCarteiraTrabalho) {
        this.digitoCarteiraTrabalho = digitoCarteiraTrabalho;
    }

    public int getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(int digitoConta) {
        this.digitoConta = digitoConta;
    }

    public int getDocumentoEstrangeiro() {
        return documentoEstrangeiro;
    }

    public void setDocumentoEstrangeiro(int documentoEstrangeiro) {
        this.documentoEstrangeiro = documentoEstrangeiro;
    }

    public int getEstabilidade() {
        return estabilidade;
    }

    public void setEstabilidade(int estabilidade) {
        this.estabilidade = estabilidade;
    }

    public int getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public int getInscricaoINSS() {
        return inscricaoINSS;
    }

    public void setInscricaoINSS(int inscricaoINSS) {
        this.inscricaoINSS = inscricaoINSS;
    }

    public boolean isInsentoIR() {
        return insentoIR;
    }

    public void setInsentoIR(boolean insentoIR) {
        this.insentoIR = insentoIR;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public int getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(int numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPis() {
        return pis;
    }

    public void setPis(int pis) {
        this.pis = pis;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSaldoFGTS() {
        return saldoFGTS;
    }

    public void setSaldoFGTS(double saldoFGTS) {
        this.saldoFGTS = saldoFGTS;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public int getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(int tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }
}
