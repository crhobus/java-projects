package edu.furb.easyboleto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "pessoa")
@TableGenerator(name = "pessoa_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "pessoaseq", valueColumnName = "valor", allocationSize = 1)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.TABLE)
    @Column(name = "nr_sequencia")
    private long nrSequencia;
    @Column(name = "nome", length = 256, nullable = false)
    private String nome;
    @Column(name = "tipo_pessoa", length = 2, nullable = false)
    private String tipoPessoa;
    @Column(name = "cpf", length = 14)
    private String cpf;
    @Column(name = "cnpj", length = 18)
    private String cnpj;
    @Column(name = "fone", length = 14)
    private String fone;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "endereco", length = 60, nullable = false)
    private String endereco;
    @Column(name = "bairro", length = 40, nullable = false)
    private String bairro;
    @Column(name = "numero", nullable = false)
    private int numero;
    @Column(name = "complemento", length = 15, nullable = false)
    private String complemento;
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    @Column(name = "cidade", length = 40, nullable = false)
    private String cidade;
    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    public long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.nrSequencia ^ (this.nrSequencia >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.tipoPessoa);
        hash = 67 * hash + Objects.hashCode(this.cpf);
        hash = 67 * hash + Objects.hashCode(this.cnpj);
        hash = 67 * hash + Objects.hashCode(this.fone);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.endereco);
        hash = 67 * hash + Objects.hashCode(this.bairro);
        hash = 67 * hash + this.numero;
        hash = 67 * hash + Objects.hashCode(this.complemento);
        hash = 67 * hash + Objects.hashCode(this.cep);
        hash = 67 * hash + Objects.hashCode(this.cidade);
        hash = 67 * hash + Objects.hashCode(this.uf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.nrSequencia != other.nrSequencia) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.tipoPessoa, other.tipoPessoa)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.fone, other.fone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        return true;
    }
}
