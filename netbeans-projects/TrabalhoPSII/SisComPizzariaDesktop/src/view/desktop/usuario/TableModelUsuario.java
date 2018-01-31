package view.desktop.usuario;

import control.funcoes.Funcoes;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Usuario;
import view.componentes.table.TableModel;

public class TableModelUsuario extends TableModel {

    private List<Usuario> usuarios;

    public TableModelUsuario(UsuarioSis usuarioSis) {
        try {
            this.usuarios = usuarioSis.getServidor().getUsuarioAction().getUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch (coluna) {
            case 0:
                return usuario.getCdUsuario();
            case 1:
                return Funcoes.validaString(usuario.getNmUsuario());
            case 2:
                return Funcoes.validaString(usuario.getDsEmail());
            case 3:
                return usuario.getDtAtualizacao();
            default:
                return usuario.getTipoUsuario().getCdTipoUsuario();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Seq.";
            case 1:
                return "Usuario";
            case 2:
                return "Email";
            case 3:
                return "Dt Atualização";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Date.class;
            default:
                return Integer.class;
        }
    }

    public Usuario getUsuario(int linha) {
        return usuarios.get(linha);
    }
}
