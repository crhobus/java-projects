package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Conexao;

// Classe que conecta as conexões ao banco de dados
public class ConexaoDAO extends DBDAO {

    private Connection con;// Conexão com o banco de dados

    public ConexaoDAO(Connection con) {
        super(con);// Passa a conexão para super classe
        this.con = con;
    }

    // Insere a conexão no banco de dados
    public void insertConexao(Conexao conexao) throws Exception {
        // Verifica se conexão já cadastrada
        if (isCadastrado("SELECT CODCONEXAO FROM TBCONEXAO WHERE CODCONEXAO = 1",
                "Erro na leitura da conexao no cliente de e-mail")) {
            updateConexao(conexao);// Se estiver sobrescreve
            return;
        }
        // Insere novo
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBCONEXAO (CODCONEXAO, TIPO, NOME, SERVPOP3IMAP, USUARIO, SENHA, SERVSMTP, "
                    + "EMAIL, PORTAPOP3IMAP, PORTASMTP, AUTENTICACAO, CONSEGURANCA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, 1);
            stm.setString(2, conexao.getTipo());
            stm.setString(3, conexao.getNome());
            stm.setString(4, conexao.getServidorPop3Imap());
            stm.setString(5, conexao.getUsuario());
            stm.setString(6, conexao.getSenha());
            stm.setString(7, conexao.getServidorSmtp());
            stm.setString(8, conexao.getEmail());
            stm.setInt(9, conexao.getPortaPop3Imap());
            stm.setInt(10, conexao.getPortaSmtp());
            if (conexao.isAutenticacao()) {
                stm.setString(11, "V");
            } else {
                stm.setString(11, "F");
            }
            if (conexao.isConSegura()) {
                stm.setString(12, "V");
            } else {
                stm.setString(12, "F");
            }
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a conexão no cliente de e-mail");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Sobrescreve a conexao no banco de dados
    private void updateConexao(Conexao conexao) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBCONEXAO SET TIPO = ?, NOME = ?, SERVPOP3IMAP = ?, USUARIO = ?, SENHA = ?, "
                    + "SERVSMTP = ?, EMAIL = ?, PORTAPOP3IMAP = ?, PORTASMTP = ?, AUTENTICACAO = ?, CONSEGURANCA = ? WHERE CODCONEXAO = ?");
            stm.setString(1, conexao.getTipo());
            stm.setString(2, conexao.getNome());
            stm.setString(3, conexao.getServidorPop3Imap());
            stm.setString(4, conexao.getUsuario());
            stm.setString(5, conexao.getSenha());
            stm.setString(6, conexao.getServidorSmtp());
            stm.setString(7, conexao.getEmail());
            stm.setInt(8, conexao.getPortaPop3Imap());
            stm.setInt(9, conexao.getPortaSmtp());
            if (conexao.isAutenticacao()) {
                stm.setString(10, "V");
            } else {
                stm.setString(10, "F");
            }
            if (conexao.isConSegura()) {
                stm.setString(11, "V");
            } else {
                stm.setString(11, "F");
            }
            stm.setInt(12, 1);
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da conexão no cliente de e-mail");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    // Obtém a conexão do banco de dados
    public Conexao getConexao() throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement("SELECT * FROM TBCONEXAO").executeQuery();
            if (rs.next()) {
                Conexao conexao = new Conexao();
                conexao.setTipo(rs.getString(2));
                conexao.setNome(rs.getString(3));
                conexao.setServidorPop3Imap(rs.getString(4));
                conexao.setUsuario(rs.getString(5));
                conexao.setSenha(rs.getString(6));
                conexao.setServidorSmtp(rs.getString(7));
                conexao.setEmail(rs.getString(8));
                conexao.setPortaPop3Imap(rs.getInt(9));
                conexao.setPortaSmtp(rs.getInt(10));
                String s = rs.getString(11);
                if (s.equals("V")) {
                    conexao.setAutenticacao(true);
                }
                s = rs.getString(12);
                if (s.equals("V")) {
                    conexao.setConSegura(true);
                }
                return conexao;
            }
            return null;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura da conexão no cliente de e-mail");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }
}
