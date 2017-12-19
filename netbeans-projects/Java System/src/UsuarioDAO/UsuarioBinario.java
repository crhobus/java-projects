package UsuarioDAO;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAOFactory.LeituraBytes;
import Modelo.Usuario;

public class UsuarioBinario extends LeituraBytes implements UsuarioDAO {

    @Override
    public int getProxCodUsu() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 46);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()));
                    if ("".equals(nomeLido)) {
                        arq.close();
                        return i;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de usuario");
            }
        }
        return 1;
    }

    @Override
    public boolean removeUsu(String nome) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 46);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()));
                    if (nome.equals(nomeLido)) {
                        arq.seek(i * 46);
                        arq.write(Arrays.copyOf("".getBytes(), 46));
                        arq.close();
                        return true;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro ao remover um usuario do arquivo");
            }
        }
        return false;
    }

    @Override
    public boolean setUsuario(Usuario usuario) throws Exception {
        File diretorio = new File("Binario");
        boolean result = diretorio.mkdir();
        if (result || diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                arq.seek(usuario.getCodigo() * 46);
                arq.write(Arrays.copyOf(usuario.getNome().getBytes(), 15));
                arq.writeInt(usuario.getCodigo());
                arq.write(Arrays.copyOf(usuario.getSenha().getBytes(), 8));
                arq.writeInt(usuario.getPermissao());
                arq.write(Arrays.copyOf(SimpleDateFormat.getInstance().format(usuario.getData()).getBytes(), 15));
                arq.close();
                return true;
            } catch (Exception ex) {
                throw new Exception("Erro na gravação do arquivo de usuario");
            }
        }
        return false;
    }

    @Override
    public Usuario getUsuario(String nome) throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 46);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()));
                    if (nomeLido.equals(nome)) {
                        Usuario usuario = new Usuario();
                        usuario.setNome(nomeLido);
                        usuario.setCodigo(arq.readInt());
                        usuario.setSenha(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
                        usuario.setPermissao(arq.readInt());
                        usuario.setData(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        arq.close();
                        return usuario;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de usuario");
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listUsuario() throws Exception {
        List<Usuario> lista = new ArrayList<Usuario>();
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 46);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()));
                    if (!"".equals(nomeLido)) {
                        Usuario usuario = new Usuario();
                        usuario.setNome(nomeLido);
                        usuario.setCodigo(arq.readInt());
                        usuario.setSenha(new String(Arrays.copyOf(recuperaTam(arq, 8), getTam())));
                        usuario.setPermissao(arq.readInt());
                        usuario.setData(SimpleDateFormat.getInstance().parse(new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()))));
                        lista.add(usuario);
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de usuario");
            }
        }
        return lista;
    }

    @Override
    public boolean arqUsuVazio() throws Exception {
        File diretorio = new File("Binario");
        if (diretorio.exists()) {
            try {
                RandomAccessFile arq = new RandomAccessFile(new File(diretorio, "Usuario.jsy"), "rw");
                String nomeLido;
                for (int i = 1; i < arq.length(); i++) {
                    arq.seek(i * 46);
                    nomeLido = new String(Arrays.copyOf(recuperaTam(arq, 15), getTam()));
                    if (!"".equals(nomeLido)) {
                        arq.close();
                        return false;
                    }
                }
                arq.close();
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de usuario");
            }
        }
        return true;
    }
}
