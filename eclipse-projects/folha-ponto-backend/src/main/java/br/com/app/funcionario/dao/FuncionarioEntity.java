package br.com.app.funcionario.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.infra.database.EntityBase;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.usuario.dao.UsuarioEntity;

@Entity
@Table(name = "funcionario")
public class FuncionarioEntity extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(name = "rg", length = 9, unique = true, nullable = false)
    private String rg;

    @Column(name = "valor_hora", nullable = false)
    private BigDecimal valorHora;

    @Column(name = "qt_horas_trabalho_dia", nullable = false)
    private double qtHorasTrabalhoDia;

    @Column(name = "qt_horas_almoco", nullable = false)
    private double qtHorasAlmoco;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "funcionario_empresa_fk"), referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresa;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "funcionario_usuario_fk"), referencedColumnName = "id", nullable = false, unique = true)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LancamentoEntity> lancamentos;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
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

    public double getQtHorasTrabalhoDia() {
        return qtHorasTrabalhoDia;
    }

    public void setQtHorasTrabalhoDia(double qtHorasTrabalhoDia) {
        this.qtHorasTrabalhoDia = qtHorasTrabalhoDia;
    }

    public double getQtHorasAlmoco() {
        return qtHorasAlmoco;
    }

    public void setQtHorasAlmoco(double qtHorasAlmoco) {
        this.qtHorasAlmoco = qtHorasAlmoco;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<LancamentoEntity> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<LancamentoEntity> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public void addLancamento(LancamentoEntity lancamento) {
        if (lancamentos == null) {
            lancamentos = new ArrayList<>();
        }
        lancamentos.add(lancamento);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + ((lancamentos == null) ? 0 : lancamentos.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        long temp;
        temp = Double.doubleToLongBits(qtHorasAlmoco);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(qtHorasTrabalhoDia);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((rg == null) ? 0 : rg.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = prime * result + ((valorHora == null) ? 0 : valorHora.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FuncionarioEntity other = (FuncionarioEntity) obj;
        if (cpf == null) {
            if (other.cpf != null) return false;
        } else if (!cpf.equals(other.cpf)) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (empresa == null) {
            if (other.empresa != null) return false;
        } else if (!empresa.equals(other.empresa)) return false;
        if (getId() != other.getId()) return false;
        if (lancamentos == null) {
            if (other.lancamentos != null) return false;
        } else if (!lancamentos.equals(other.lancamentos)) return false;
        if (nome == null) {
            if (other.nome != null) return false;
        } else if (!nome.equals(other.nome)) return false;
        if (Double.doubleToLongBits(qtHorasAlmoco) != Double.doubleToLongBits(other.qtHorasAlmoco)) return false;
        if (Double.doubleToLongBits(qtHorasTrabalhoDia) != Double.doubleToLongBits(other.qtHorasTrabalhoDia)) return false;
        if (rg == null) {
            if (other.rg != null) return false;
        } else if (!rg.equals(other.rg)) return false;
        if (usuario == null) {
            if (other.usuario != null) return false;
        } else if (!usuario.equals(other.usuario)) return false;
        if (valorHora == null) {
            if (other.valorHora != null) return false;
        } else if (!valorHora.equals(other.valorHora)) return false;
        return true;
    }

}
