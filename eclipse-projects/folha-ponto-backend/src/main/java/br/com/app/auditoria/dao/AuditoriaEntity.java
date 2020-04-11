package br.com.app.auditoria.dao;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.auditoria.dto.StatusEnum;
import br.com.app.infra.database.EntityBase;

@Entity
@Table(name = "auditoria")
@SequenceGenerator(name = "SEQUENCE_BASE", sequenceName = "auditoria_seq", allocationSize = 1)
public class AuditoriaEntity extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "data", nullable = false)
    private Instant data;

    @Column(name = "api", length = 100, nullable = false)
    private String api;

    @Column(name = "input_json")
    private String inputJson;

    @Column(name = "output_json")
    private String outputJson;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Instant dataAtualizacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = Instant.now();
        dataAtualizacao = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = Instant.now();
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getInputJson() {
        return inputJson;
    }

    public void setInputJson(String inputJson) {
        this.inputJson = inputJson;
    }

    public String getOutputJson() {
        return outputJson;
    }

    public void setOutputJson(String outputJson) {
        this.outputJson = outputJson;
    }

    public StatusEnum getStatus() {
        return StatusEnum.getKey(status);
    }

    public void setStatus(StatusEnum status) {
        this.status = status.getValue();
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((api == null) ? 0 : api.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((inputJson == null) ? 0 : inputJson.hashCode());
        result = prime * result + ((outputJson == null) ? 0 : outputJson.hashCode());
        result = prime * result + (int) (getId() ^ (getId() >>> 32));
        result = prime * result + status;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AuditoriaEntity other = (AuditoriaEntity) obj;
        if (api == null) {
            if (other.api != null) return false;
        } else if (!api.equals(other.api)) return false;
        if (data == null) {
            if (other.data != null) return false;
        } else if (!data.equals(other.data)) return false;
        if (dataAtualizacao == null) {
            if (other.dataAtualizacao != null) return false;
        } else if (!dataAtualizacao.equals(other.dataAtualizacao)) return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null) return false;
        } else if (!dataCriacao.equals(other.dataCriacao)) return false;
        if (inputJson == null) {
            if (other.inputJson != null) return false;
        } else if (!inputJson.equals(other.inputJson)) return false;
        if (outputJson == null) {
            if (other.outputJson != null) return false;
        } else if (!outputJson.equals(other.outputJson)) return false;
        if (getId() != other.getId()) return false;
        if (status != other.status) return false;
        return true;
    }

}
