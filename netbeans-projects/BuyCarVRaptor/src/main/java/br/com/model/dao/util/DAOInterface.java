package br.com.model.dao.util;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    public void init() throws SQLException, ClassNotFoundException;

    public T insert(T object) throws SQLException;

    public T update(T object) throws SQLException;

    public void delete(long nrSequencia) throws SQLException;

    public T get(long nrSequencia) throws SQLException;

    public List<T> list(Operacao operacao) throws SQLException;

    public void destroy() throws SQLException;
}
