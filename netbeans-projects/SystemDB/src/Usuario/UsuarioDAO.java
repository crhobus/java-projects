package Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Departamento;
import Modelo.Setor;
import Modelo.Usuario;
import Principal.DBDAO;

public class UsuarioDAO extends DBDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertUsuario(Usuario usuario) throws SQLException {
        if (isUsuarioCadastrado(usuario.getCodUsuario())) {
            return updateUsuario(usuario);
        }
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBUSUARIO (CODUSUARIO, DATACADAS, ULTALTERACAO, "
                    + "USUARIO, PERMISSAO, SENHA, CODFUNC) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, usuario.getCodUsuario());
            stm.setTimestamp(2, new Timestamp(usuario.getDataCadastro().getTime()));
            stm.setTimestamp(3, new Timestamp(usuario.getUltAlteracao().getTime()));
            stm.setString(4, usuario.getUsuario());
            stm.setInt(5, usuario.getPermissao());
            stm.setBytes(6, usuario.getSenha());
            stm.setInt(7, usuario.getCodFunc());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o usuário no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateUsuario(Usuario usuario) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBUSUARIO SET DATACADAS = ?, ULTALTERACAO = ?, "
                    + "USUARIO = ?, PERMISSAO = ?, SENHA = ?, CODFUNC = ? WHERE CODUSUARIO = ?");
            stm.setTimestamp(1, new Timestamp(usuario.getDataCadastro().getTime()));
            stm.setTimestamp(2, new Timestamp(usuario.getUltAlteracao().getTime()));
            stm.setString(3, usuario.getUsuario());
            stm.setInt(4, usuario.getPermissao());
            stm.setBytes(5, usuario.getSenha());
            stm.setInt(6, usuario.getCodFunc());
            stm.setInt(7, usuario.getCodUsuario());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do usuário no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isUsuarioCadastrado(int codUsuario) throws SQLException {
        return isCadastrado("SELECT CODUSUARIO FROM TBUSUARIO WHERE CODUSUARIO = "
                + codUsuario, "Erro na leitura do usuário no sistema");
    }

    public int getUsuarioCadastrado(String usuario, int codFunc) throws SQLException {
        return getCadastrado("SELECT CODUSUARIO FROM TBUSUARIO WHERE USUARIO = '" + usuario
                + "' AND CODFUNC = " + codFunc, "Erro na leitura do usuário no sistema");
    }

    public int getJaCadastrado(String usuario, int codFunc) throws SQLException {
        return getCadastrado("SELECT CODUSUARIO FROM TBUSUARIO WHERE USUARIO = '" + usuario
                + "' OR CODFUNC = " + codFunc, "Erro na leitura do usuário no sistema");
    }

    public boolean isUsuarioVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBUSUARIO",
                "Erro na leitura de usuários no sistema");
    }

    public int getProxCodUsuario() throws SQLException {
        return getProxCod("SELECT MAX(CODUSUARIO) FROM TBUSUARIO",
                "Erro na leitura de usuários no sistema");
    }

    public boolean deleteUsuario(int codUsuario) throws SQLException {
        return delete("DELETE FROM TBUSUARIO WHERE CODUSUARIO = " + codUsuario,
                "Não foi possível excluir o usuário do sistema");
    }

    public List<Usuario> listUsuario() throws SQLException {
        return getLista("SELECT U.CODUSUARIO, U.DATACADAS, U.ULTALTERACAO, F.CODFUNC, P.NOME, S.SETOR, "
                + "D.DEPARTAMENTO, F.CARGO, U.PERMISSAO, U.USUARIO, U.SENHA FROM TBUSUARIO U "
                + "INNER JOIN TBFUNCIONARIO F ON U.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBSETOR S ON F.CODSETOR = S.CODSETOR "
                + "INNER JOIN TBDEPARTAMENTO D ON F.CODDEPARTAMENTO = D.CODDEPARTAMENTO "
                + "ORDER BY U.CODUSUARIO");
    }

    private List<Usuario> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Usuario> lista = new ArrayList<Usuario>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getUsuarioAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de usuários no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    public Usuario getUsuario(String usuario) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT USUARIO, SENHA, PERMISSAO FROM TBUSUARIO "
                    + "WHERE USUARIO = '" + usuario + "'").executeQuery();
            if (rs.next()) {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("USUARIO"));
                usu.setSenha(rs.getBytes("SENHA"));
                usu.setPermissao(rs.getInt("PERMISSAO"));
                return usu;
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do usuário no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Usuario getUsuarioAux(ResultSet rs) throws SQLException {
        try {
            Usuario usuario = new Usuario();
            usuario.setCodUsuario(rs.getInt(1));
            usuario.setDataCadastro(rs.getTimestamp(2));
            usuario.setUltAlteracao(rs.getTimestamp(3));
            usuario.setCodFunc(rs.getInt(4));
            usuario.setNome(rs.getString(5));
            Setor setor = new Setor();
            setor.setSetor(rs.getString(6));
            usuario.setSetor(setor);
            Departamento departamento = new Departamento();
            departamento.setDepartamento(rs.getBytes(7));
            usuario.setDepartamento(departamento);
            usuario.setCargo(rs.getString(8));
            usuario.setPermissao(rs.getInt(9));
            usuario.setUsuario(rs.getString(10));
            usuario.setSenha(rs.getBytes(11));
            return usuario;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do usuário no sistema");
        }
    }

    public List<Usuario> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("U.CODUSUARIO = " + n));
            case 1:
                return getLista(getSql("LOWER(U.USUARIO) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("LOWER(P.NOME) like '%" + s.toLowerCase() + "%'"));
            case 3:
                return getLista(getSql("LOWER(F.CARGO) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("U.PERMISSAO = " + n));
        }
    }

    private String getSql(String condicao) {
        return "SELECT U.CODUSUARIO, U.DATACADAS, U.ULTALTERACAO, F.CODFUNC, P.NOME, S.SETOR, "
                + "D.DEPARTAMENTO, F.CARGO, U.PERMISSAO, U.USUARIO, U.SENHA FROM TBUSUARIO U "
                + "INNER JOIN TBFUNCIONARIO F ON U.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA P ON F.CODPESSOA = P.CODPESSOA "
                + "INNER JOIN TBSETOR S ON F.CODSETOR = S.CODSETOR "
                + "INNER JOIN TBDEPARTAMENTO D ON F.CODDEPARTAMENTO = D.CODDEPARTAMENTO "
                + "WHERE " + condicao + " ORDER BY U.CODUSUARIO";
    }
}
