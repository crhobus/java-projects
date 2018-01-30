package Principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import jomp.compiler.*;

public class ConversorJomp2 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O programa encontrou um problema e presisa ser fechado", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".java";
            }

            @Override
            public boolean accept(File arq) {
                if (arq.getAbsolutePath().endsWith(".java") || arq.isDirectory()) {
                    return true;
                }
                return false;
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showDialog(null, "Conveter...");
        if (result == JFileChooser.APPROVE_OPTION) {
            String nomeArq = fileChooser.getSelectedFile().getPath();
            System.out.println("Arquivo de origem - " + nomeArq);
            File antigoArq = new File(nomeArq);
            nomeArq = nomeArq.substring(0, nomeArq.indexOf('.')) + "_jomp";
            File novoArq = new File(nomeArq + ".jomp");
            try {
                copyFile(antigoArq, novoArq);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // compila arquivo .jomp
            String[] s = new String[1];
            s[0] = nomeArq;
            Jomp.main(s);
            System.out.println("Arquivo de destino - " + nomeArq + ".java");
        }
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }
}
