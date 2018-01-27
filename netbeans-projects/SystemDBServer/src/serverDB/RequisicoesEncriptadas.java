package serverDB;

import Modelo.Usuario;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class RequisicoesEncriptadas extends Thread {

    private Connection con;
    private SSLServerSocket sslServerSocket;

    public RequisicoesEncriptadas(Connection con) {
        this.con = con;
    }

    @Override
    public void run() {
        if (conectar()) {
            receberRequisicao();
        }
    }

    public boolean conectar() {
        try {
            SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sslServerSocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);

            String[] enabledCipherSuites = {"SSL_DH_anon_WITH_RC4_128_MD5"};
            sslServerSocket.setEnabledCipherSuites(enabledCipherSuites);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void receberRequisicao() {
        try {
            Object obj;
            Usuario u;
            ObjectInputStream objIn;
            SSLSocket sslsocket = null;
            while ((sslsocket = (SSLSocket) sslServerSocket.accept()) != null) {

                objIn = new ObjectInputStream(sslsocket.getInputStream());

                obj = objIn.readObject();
                if (obj instanceof Usuario) {
                    u = (Usuario) obj;
                    System.out.println("\n-------------------------------------------------------------------------------");
                    System.out.println("Nome: " + u.getCodUsuario());
                    System.out.println("Usuário: " + u.getUsuario());
                    insertUsuario(u);
                }
                objIn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean insertUsuario(Usuario usuario) throws SQLException {
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
            ex.printStackTrace();
            throw new SQLException("Erro ao salvar o usuário no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private boolean updateUsuario(Usuario usuario) throws SQLException {
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

    private boolean isUsuarioCadastrado(int codUsuario) throws SQLException {
        return isCadastrado("SELECT CODUSUARIO FROM TBUSUARIO WHERE CODUSUARIO = "
                + codUsuario, "Erro na leitura do usuário no sistema");
    }

    private boolean isCadastrado(String sql, String msg) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new SQLException(msg);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }
}
