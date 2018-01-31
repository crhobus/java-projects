package control.action.usuario;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.util.Date;
import java.util.List;
import model.dao.ServidorDAO;
import model.dao.UsuarioDAO;
import model.entidades.Funcao;
import model.entidades.Perfil;
import model.entidades.Usuario;

public class UsuarioAction {

    private UsuarioDAO usuarioDAO;
    private ServidorDAO servidorDAO;

    public UsuarioAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.usuarioDAO = new UsuarioDAO(servidorDAO);
    }

    public Usuario autenticacao(Dados dados) throws ExceptionError {
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.getUsuario(dados.getInfo("NM_USUARIO"));
        } catch (Exception ex) {
            throw new ExceptionError("NM_USUARIO", "Usuário inválido");
        }
        if (usuario == null) {
            throw new ExceptionError("NM_USUARIO", "Usuário inválido");
        }
        if (usuario.getNmUsuario().equals(dados.getInfo("NM_USUARIO"))) {
            if (usuario.getDsSenha().equals(dados.getInfo("DS_SENHA"))) {
                return usuario;
            } else {
                throw new ExceptionError("DS_SENHA", "Senha inválida");
            }
        } else {
            throw new ExceptionError("NM_USUARIO", "Usuário inválido");
        }
    }

    public List<Usuario> getUsuarios() throws ExceptionError {
        try {
            return usuarioDAO.getUsuarios();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os usuários do sistema."
                    + "\nErro no Servidor");
        }
    }

    public boolean alterarDadosUsuario(Dados dados, Usuario usuario) throws ExceptionInfo, ExceptionError {
        if (Integer.parseInt(dados.getInfo("NR_PERMISSAO_USUARIO_LOGADO")) == 1
                && !(dados.getInfo("NM_USUARIO_LOGADO")).equalsIgnoreCase(usuario.getNmUsuario())) {
            if ("".equals(dados.getInfo("DS_NOVA_SENHA"))) {
                throw new ExceptionInfo("DS_NOVA_SENHA", "Entre a nova senha");
            }
            usuario.setDsSenha(dados.getInfo("DS_NOVA_SENHA"));
        } else {
            if ("".equals(dados.getInfo("DS_SENHA_ATUAL"))) {
                throw new ExceptionInfo("DS_SENHA_ATUAL", "Entre com a senha atual");
            }
            if (!dados.getInfo("DS_SENHA_ATUAL").equals(usuario.getDsSenha())) {
                throw new ExceptionInfo("DS_SENHA_ATUAL", "Esta não é sua senha atual");
            }
            if (!"".equals(dados.getInfo("DS_NOVA_SENHA"))
                    || !"".equals(dados.getInfo("DS_CONFIRMA_SENHA"))) {
                if ("".equals(dados.getInfo("DS_NOVA_SENHA"))) {
                    throw new ExceptionInfo("DS_NOVA_SENHA", "Entre com a nova senha");
                }
                if ("".equals(dados.getInfo("DS_CONFIRMA_SENHA"))
                        || !dados.getInfo("DS_CONFIRMA_SENHA").equals(dados.getInfo("DS_NOVA_SENHA"))) {
                    throw new ExceptionInfo("DS_CONFIRMA_SENHA", "Confirme sua senha");
                }
            }
            if ("".equals(dados.getInfo("DS_EMAIL").trim())) {
                throw new ExceptionInfo("DS_EMAIL", "Entre com um endereço de email");
            }
            usuario.setDsEmail(dados.getInfo("DS_EMAIL"));
            usuario.setDtAtualizacao(new Date());
            if (!"".equals(dados.getInfo("DS_NOVA_SENHA"))) {
                usuario.setDsSenha(dados.getInfo("DS_NOVA_SENHA"));
            }
        }
        try {
            servidorDAO.salvar(usuario, false);
            return true;
        } catch (Exception ex) {
            throw new ExceptionError("Erro ao alterar os dados do usuário");
        }
    }

    public List<Funcao> getFuncoes(int cdUsuario) throws ExceptionError {
        try {
            return usuarioDAO.getFuncoes(cdUsuario);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os funções do sistema."
                    + "\nErro no Servidor");
        }
    }

    public void addPerfilUsuario(Usuario usuario, Funcao funcao) throws ExceptionError {
        Perfil perfil = new Perfil();
        perfil.setFuncao(funcao);
        perfil.setIeAlterarDados("N");
        try {
            usuarioDAO.addPerfilUsuario(usuario, perfil);
        } catch (Exception ex) {
            throw new ExceptionError(ex.getMessage());
        }
    }

    public void alterarPermissao(int indice, Usuario usuario, Perfil perfil) throws ExceptionError {
        try {
            usuario.getPerfis().set(indice, perfil);
            servidorDAO.salvar(usuario, false);
        } catch (Exception ex) {
            throw new ExceptionError("Erro ao alterar a permissão do usuário");
        }
    }

    public boolean removePerfilFuncao(Usuario usuario, Perfil perfil) throws ExceptionError {
        try {
            return usuarioDAO.removePerfilFuncao(usuario, perfil);
        } catch (Exception ex) {
            throw new ExceptionError(ex.getMessage());
        }
    }

    public boolean alterarSenhaUsuario(Dados dados, Usuario usuario) throws ExceptionError, ExceptionInfo {
        if ("".equals(dados.getInfo("DS_SENHA_ATUAL"))) {
            throw new ExceptionInfo("DS_SENHA_ATUAL", "Entre com a senha atual");
        }
        if (!dados.getInfo("DS_SENHA_ATUAL").equals(usuario.getDsSenha())) {
            throw new ExceptionInfo("DS_SENHA_ATUAL", "Esta não é sua senha atual");
        }
        if ("".equals(dados.getInfo("DS_NOVA_SENHA"))) {
            throw new ExceptionInfo("DS_NOVA_SENHA", "Entre com a nova senha");
        }
        if ("".equals(dados.getInfo("DS_CONFIRMA_SENHA"))
                || !dados.getInfo("DS_CONFIRMA_SENHA").equals(dados.getInfo("DS_NOVA_SENHA"))) {
            throw new ExceptionInfo("DS_CONFIRMA_SENHA", "Confirme sua senha");
        }
        usuario.setDtAtualizacao(new Date());
        usuario.setDsSenha(dados.getInfo("DS_NOVA_SENHA"));
        try {
            servidorDAO.salvar(usuario, false);
        } catch (Exception ex) {
            throw new ExceptionError("Erro ao alterar os dados do usuário");
        }
        return true;
    }
}
