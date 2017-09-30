package JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JFileChooserArq extends JFrame {

    private JTextArea taArea;

    public JFileChooserArq() {
        super("Arquivos");
        Container tela = getContentPane();
        taArea = new JTextArea();
        taArea.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));//Borda em volta no JTextArea
        taArea.setEditable(false);
        JScrollPane rolagem = new JScrollPane(taArea);
        tela.add(rolagem, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        DadosArquivo();
    }

    private File getFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//Para indicar que arquivos e diretorios podem ser selecionados
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.exit(1);
        }
        File nomeArq = fileChooser.getSelectedFile();//Obtém o arquivo selecionado
        if (nomeArq == null || nomeArq.getName().equals("")) {//Exibe erro se for invalido
            JOptionPane.showMessageDialog(this, "Nome do arquivo inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return nomeArq;
    }

    private void DadosArquivo() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
        File nome = getFile();//cria o objeto file com base na entrada do usuario
        if (nome.exists()) {//veriafica se o nome existe
            Date ultimaMod = new Date(nome.lastModified());
            taArea.setText(String.format("%s%s\n%s\n%s\n%s\n%s\n%s\n%s%s%s%s\n%s%s\n%s%s\n%s%s\n%s%s",
                    nome.getName(), " exixte",
                    (nome.isFile() ? "é um arquivo" : "não é um arquivo"),
                    (nome.canRead() ? "é legivel" : "não é legivel"),
                    (nome.canWrite() ? "é gravavel" : "não é gravaavel"),
                    (nome.isDirectory() ? "é um diretório" : "não é um diretório"),
                    (nome.isAbsolute() ? "é um caminho absoluto" : "não é uma caminho absoluto"),
                    "Ultima modificação: ", data.format((Date) ultimaMod), " as ", hora.format((Date) ultimaMod),
                    "Tamanho em bytes: ", nome.length(),
                    "Caminho: ", nome.getPath(), "Caminho absoluto: ", nome.getAbsolutePath(),
                    "Principal: ", nome.getParent()));

            if (nome.isDirectory()) {
                String diretorio[] = nome.list();
                taArea.append("\nConteúdo do diretório:\n");
                for (String nomeDiretorio : diretorio) {
                    taArea.append(nomeDiretorio + "\n");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, nome + " não exixte", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
