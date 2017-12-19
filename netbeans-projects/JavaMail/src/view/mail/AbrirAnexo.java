package view.mail;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import view.PanelComponentes;
import control.DiretorioMsgsDAO;

public class AbrirAnexo extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JComboBox cbAnexo;
    private DiretorioMsgsDAO diretorioMsgsDAO;

    public AbrirAnexo(Frame principal, String[] anexos, DiretorioMsgsDAO diretorioMsgsDAO) {
        super(principal, true);// Chama o superconstrutor, especificando que essa caixa de diálogo é modal
        this.diretorioMsgsDAO = diretorioMsgsDAO;
        initComponents(principal, anexos);
    }

    // Cria os componentes gráficos
    private void initComponents(Frame principal, String[] anexos) {
        this.setTitle("Abrir anexo");
        this.setLayout(null);

        PanelComponentes panel = new PanelComponentes(5, 5, 300, 57);
        this.add(panel);

        cbAnexo = panel.addJComboBox(anexos, 20, 15, 260, 26);
        cbAnexo.setSelectedItem(null);
        cbAnexo.addActionListener(this);

        this.setSize(315, 94);
        this.setResizable(false);
        this.setLocationRelativeTo(principal);// Centraliza a caixa de diálogo na aplicação
    }

    // Trata eventos
    @Override
    public void actionPerformed(ActionEvent evento) {
        // Abre o anexo
        try {
            diretorioMsgsDAO.abrirAnexo((String) cbAnexo.getSelectedItem());
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
