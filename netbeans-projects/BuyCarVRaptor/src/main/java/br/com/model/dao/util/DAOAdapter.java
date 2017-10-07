package br.com.model.dao.util;

public abstract class DAOAdapter<T> implements DAOInterface<T> {

    private Long nrSequencia;

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }
}
