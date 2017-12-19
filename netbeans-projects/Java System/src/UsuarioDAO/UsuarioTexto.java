package UsuarioDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.ArqTextoGenerico;
import Modelo.Usuario;

public class UsuarioTexto extends ArqTextoGenerico implements UsuarioDAO {

    @Override
    public int getProxCodUsu() throws Exception {
        return getProxCod("Texto/Usuario", "usuario");
    }

    @Override
    public boolean removeUsu(String nome) throws Exception {
        return remove("Texto/Usuario", nome);
    }

    @Override
    public boolean setUsuario(Usuario usuario) throws Exception {
        List<Usuario> lista = listUsuario();
        for (Usuario usu : lista) {
            if (usu.getCodigo() == usuario.getCodigo()) {
                removeUsu(usu.getNome());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Usuario");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, usuario.getNome() + ".txt"))));
                    out.println(usuario.getCodigo());
                    out.println(usuario.getNome());
                    out.println(usuario.getSenha());
                    out.println(usuario.getPermissao());
                    out.println(SimpleDateFormat.getInstance().format(usuario.getData()));
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de usuario");
                }
            }
        }
        return false;
    }

    @Override
    public Usuario getUsuario(String nome) throws Exception {
        File diretorio = new File("Texto/Usuario");
        if (diretorio.exists()) {
            File arq = new File(diretorio, nome + ".txt");
            if (arq.exists()) {
                try {
                    return leituraUsuario(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de usuário\no arquivo " + nome + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listUsuario() throws Exception {
        List<Usuario> lista = new ArrayList<Usuario>();
        File diretorio = new File("Texto/Usuario");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraUsuario(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de usuário\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    private Usuario leituraUsuario(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Usuario usuario = new Usuario();
        usuario.setCodigo(Integer.parseInt(in.readLine()));
        usuario.setNome(in.readLine());
        usuario.setSenha(in.readLine());
        usuario.setPermissao(Integer.parseInt(in.readLine()));
        usuario.setData(SimpleDateFormat.getInstance().parse(in.readLine()));
        in.close();
        return usuario;
    }

    @Override
    public boolean arqUsuVazio() throws Exception {
        return arqVazio("Texto/Usuario");
    }
}
