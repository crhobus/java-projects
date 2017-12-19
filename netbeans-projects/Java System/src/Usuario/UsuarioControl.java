package Usuario;

import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.Usuario;

public class UsuarioControl {

    private List<Usuario> listaUsuario;
    private DAOFactory daoFactory;

    public UsuarioControl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean setUsuario(Usuario usuario) throws Exception {
        if (daoFactory.createUsuarioDAO().setUsuario(usuario)) {
            return true;
        }
        return false;
    }

    public Usuario getUsuario(String nome) throws Exception {
        return daoFactory.createUsuarioDAO().getUsuario(nome);
    }

    public boolean removeUsu(String nome) throws Exception {
        if (daoFactory.createUsuarioDAO().removeUsu(nome)) {
            return true;
        }
        return false;
    }

    public boolean arqUsuVazio() throws Exception {
        return daoFactory.createUsuarioDAO().arqUsuVazio();
    }

    public int getProxCodUsu() throws Exception {
        return daoFactory.createUsuarioDAO().getProxCodUsu();
    }

    public int getQtdadeUsuCadas() {
        return listaUsuario.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Usuario usuario = listaUsuario.get(linha);
        switch (coluna) {
            case 0:
                return usuario.getCodigo();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getPermissao();
            case 3:
                return usuario.getData();
            default:
                return usuario.getData();
        }
    }

    public void listaTodos() throws Exception {
        listaUsuario = daoFactory.createUsuarioDAO().listUsuario();
    }
}
