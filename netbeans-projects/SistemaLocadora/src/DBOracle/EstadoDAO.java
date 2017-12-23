package DBOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Estado;

public class EstadoDAO extends DBDAO {

    private Connection con;

    public EstadoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertEstado(Estado estado) throws Exception {
        try {
            if (isEstadoCadastrado(estado.getCodigo())) {
                throw new Exception("Não é possível sobrescrever este estado");
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBESTADO "
                    + "(CODESTADO, ESTADO) VALUES (?, ?)");
            stm.setInt(1, estado.getCodigo());
            stm.setString(2, estado.getEstado());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o estado no sistema");
        }
    }

    public int getEstadoCadastrado(String estado) throws SQLException {
        return getCadastrado("SELECT CODESTADO FROM TBESTADO WHERE LOWER(ESTADO) = '"
                + estado.toLowerCase() + "'", "Erro na leitura do estado no sistema");
    }

    public boolean isEstadoCadastrado(int codEstado) throws SQLException {
        return isCadastrado("SELECT CODESTADO FROM TBESTADO WHERE CODESTADO = "
                + codEstado, "Erro na leitura do estado no sistema");
    }

    public boolean isEstadoVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBESTADO",
                "Erro na leitura de estados no sistema");
    }

    public int getProxCodEstado() throws SQLException {
        return getProxCod("SELECT MAX(CODESTADO) FROM TBESTADO",
                "Erro na leitura de estados no sistema");
    }

    public List<Estado> listEstados() throws SQLException {
        return getLista("SELECT * FROM TBESTADO ORDER BY CODESTADO");
    }

    private List<Estado> getLista(String sql) throws SQLException {
        try {
            List<Estado> lista = new ArrayList<Estado>();
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getEstadoAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de estados no sistema");
        }
    }

    private Estado getEstadoAux(ResultSet rs) throws SQLException {
        try {
            Estado estado = new Estado();
            estado.setCodigo(rs.getInt(1));
            estado.setEstado(rs.getString(2));
            return estado;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do estado no sistema");
        }
    }

    public List<Estado> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista("SELECT * FROM TBESTADO WHERE CODESTADO = " + n);
            default:
                return getLista("SELECT * FROM TBESTADO WHERE LOWER(ESTADO) like '%"
                        + s.toLowerCase() + "%' ORDER BY CODESTADO");
        }
    }
}
