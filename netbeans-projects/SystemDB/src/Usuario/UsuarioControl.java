package Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modelo.Usuario;

public class UsuarioControl {

    private List<Usuario> listaUsuario;
    private UsuarioDAO usuarioDAO;

    public UsuarioControl(Connection con) {
        usuarioDAO = new UsuarioDAO(con);
    }

    public boolean insertUsuario(Usuario usuario) throws SQLException {
        return usuarioDAO.insertUsuario(usuario);
    }

    public boolean updateUsuario(Usuario usuario) throws SQLException {
        return usuarioDAO.updateUsuario(usuario);
    }

    public boolean isUsuarioCadastrado(int codUsuario) throws SQLException {
        return usuarioDAO.isUsuarioCadastrado(codUsuario);
    }

    public int getUsuarioCadastrado(String usuario, int codFunc) throws SQLException {
        return usuarioDAO.getUsuarioCadastrado(usuario, codFunc);
    }

    public int getJaCadastrado(String usuario, int codFunc) throws SQLException {
        return usuarioDAO.getJaCadastrado(usuario, codFunc);
    }

    public boolean isUsuarioVazio() throws SQLException {
        return usuarioDAO.isUsuarioVazio();
    }

    public int getProxCodUsuario() throws SQLException {
        return usuarioDAO.getProxCodUsuario();
    }

    public boolean deleteUsuario(int codUsuario) throws SQLException {
        return usuarioDAO.deleteUsuario(codUsuario);
    }

    public Usuario getUsuario(String usuario) throws SQLException {
        return usuarioDAO.getUsuario(usuario);
    }

    public int getQtdadeUsuarioCadas() {
        return listaUsuario.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Usuario usuario = listaUsuario.get(linha);
        switch (coluna) {
            case 0:
                return usuario.getCodUsuario();
            case 1:
                return usuario.getUsuario();
            case 2:
                return usuario.getNome();
            case 3:
                return usuario.getCargo();
            default:
                return usuario.getPermissao();
        }
    }

    public void limparLista() {
        listaUsuario.clear();
    }

    public void listarUsuario() throws SQLException {
        listaUsuario = usuarioDAO.listUsuario();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaUsuario = usuarioDAO.getLista(coluna, s, n);
    }

    public Usuario getListaPosicao(int posicao) {
        return listaUsuario.get(posicao);
    }
}
