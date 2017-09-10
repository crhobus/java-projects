package AssistenciaTecRandom;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ArquivoTexto {

    public void gravarCliente(Cliente cliente) {
        try {
            FileWriter arquivo = new FileWriter("Cliente.txt", true);
            PrintWriter escreve = new PrintWriter(arquivo);
            escreve.write(cliente.getCodigo() + "#");
            escreve.write(cliente.getNumero() + "#");
            escreve.write(cliente.getNome() + "#");
            escreve.write(cliente.getEndereco() + "#");
            escreve.write(cliente.getBairro() + "#");
            escreve.write(cliente.getCidade() + "#");
            escreve.write(cliente.getEstado() + "#");
            escreve.write(cliente.getCep() + "#");
            escreve.write(cliente.getFone() + "#");
            escreve.write(cliente.getCelular() + "#");
            escreve.write(cliente.getSolicitante() + "#");
            escreve.write(cliente.getRg() + "#");
            escreve.println(cliente.getCpf());
            escreve.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Cliente lerCliente(int linha) {
        String s;
        Cliente cliente = null;
        try {
            FileReader arquivo = new FileReader("Cliente.txt");
            BufferedReader ler = new BufferedReader(arquivo);
            int i = 0;
            do {
                try {
                    s = ler.readLine();
                    if (s == null) {
                        break;
                    } else {
                        if (i == linha) {
                            cliente = new Cliente();
                            String vetor[] = s.split("#");
                            cliente.setCodigo(Integer.parseInt(vetor[0]));
                            cliente.setNumero(Integer.parseInt(vetor[1]));
                            cliente.setNome(vetor[2]);
                            cliente.setEndereco(vetor[3]);
                            cliente.setBairro(vetor[4]);
                            cliente.setCidade(vetor[5]);
                            cliente.setEstado(vetor[6]);
                            cliente.setCep(vetor[7]);
                            cliente.setFone(vetor[8]);
                            cliente.setCelular(vetor[9]);
                            cliente.setSolicitante(vetor[10]);
                            cliente.setRg(vetor[11]);
                            cliente.setCpf(vetor[12]);
                            ler.close();
                            return cliente;
                        }
                    }
                    i++;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Linha não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } while (true);
            try {
                ler.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return cliente;
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<Cliente>();
        Cliente cliente;
        int i = 0;
        while ((cliente = lerCliente(i)) != null) {
            lista.add(cliente);
            i++;
        }
        return lista;
    }

    public boolean verificaArq() {
        File arq = new File("Cliente.txt");
        if (arq.exists() == true) {
            return true;
        } else {
            return false;
        }
    }

    public void excluirLinha(int linha) {

        try {
            FileWriter arquivo = new FileWriter("Cliente.txt", true);
            PrintWriter escreve = new PrintWriter(arquivo);

            int i = 0;
            do {
                if (i == linha) {
                    //escreve.write("\naprendendo \njava");
                    //escreve.flush();
                    escreve.println();
                    //escreve.write("wwwwwwwwwwwwwwwwwwwwww");
                    break;
                }
                i++;
            } while (true);
            escreve.close();
        } catch (IOException ex) {
            Logger.getLogger(ArquivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
