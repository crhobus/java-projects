package DataInputOutputStream;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class ArquivoDados {

    public void gravar(List<Cliente> lista) {
        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream("ClientesData.dat"));
            try {
                output.writeInt(lista.size());
                for (Cliente clie : lista) {
                    output.writeInt(clie.getCodigo());
                    output.writeUTF(clie.getNome());
                    output.writeUTF(clie.getRg());
                    output.writeUTF(clie.getCpf());
                    output.writeUTF(clie.getProfissao());
                    output.writeUTF(clie.getEmpresa());
                    output.writeUTF(clie.getFoneEmpresa());
                    output.writeUTF(clie.getSexo());
                    output.writeUTF(clie.getCep());
                    output.writeUTF(clie.getEndereco());
                    output.writeUTF(clie.getBairro());
                    output.writeUTF(clie.getComplemento());
                    output.writeInt(clie.getNumero());
                    output.writeUTF(clie.getCidade());
                    output.writeUTF(clie.getEstado());
                    output.writeUTF(clie.getEmail());
                    output.writeUTF(clie.getFone());
                    output.writeUTF(clie.getCelular());
                    output.writeUTF(clie.getDescricao());
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo clientesData.dat para gravação", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            output.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Cliente> ler() {
        List<Cliente> lista = new ArrayList<Cliente>();
        DataInputStream input = null;
        File arq = new File("ClientesData.dat");
        if (arq.exists() == true) {
            try {
                input = new DataInputStream(new FileInputStream("ClientesData.dat"));
                try {
                    int qtdadeRet = input.readInt();
                    for (int i = 0; i < qtdadeRet; i++) {
                        Cliente cliente = new Cliente();
                        cliente.setCodigo(input.readInt());
                        cliente.setNome(input.readUTF());
                        cliente.setRg(input.readUTF());
                        cliente.setCpf(input.readUTF());
                        cliente.setProfissao(input.readUTF());
                        cliente.setEmpresa(input.readUTF());
                        cliente.setFoneEmpresa(input.readUTF());
                        cliente.setSexo(input.readUTF());
                        cliente.setCep(input.readUTF());
                        cliente.setEndereco(input.readUTF());
                        cliente.setBairro(input.readUTF());
                        cliente.setComplemento(input.readUTF());
                        cliente.setNumero(input.readInt());
                        cliente.setCidade(input.readUTF());
                        cliente.setEstado(input.readUTF());
                        cliente.setEmail(input.readUTF());
                        cliente.setFone(input.readUTF());
                        cliente.setCelular(input.readUTF());
                        cliente.setDescricao(input.readUTF());
                        lista.add(cliente);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler os dados do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo ClientesData.dat para leitura", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            try {
                input.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return lista;
    }
}
