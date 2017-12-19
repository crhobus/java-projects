package UsuarioDAO;

import java.util.List;

import Modelo.Usuario;

public interface UsuarioDAO {

    public boolean setUsuario(Usuario usuario) throws Exception;

    public Usuario getUsuario(String nome) throws Exception;

    public boolean removeUsu(String nome) throws Exception;

    public List<Usuario> listUsuario() throws Exception;

    public boolean arqUsuVazio() throws Exception;

    public int getProxCodUsu() throws Exception;
}
