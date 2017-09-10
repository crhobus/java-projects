package AssistenciaTecRandom;

import java.util.*;

public class ClienteControl {

    private List<Cliente> lista = new ArrayList<Cliente>();
    private int codigo, numero;
    private String nome, endereco, bairro, cidade, estado, cep, fone, celular, solicitante, rg, cpf;
    private ArquivoTexto arquivo;

    public ClienteControl() {
        arquivo = new ArquivoTexto();
        if (arquivo.verificaArq() == true) {
            lista = arquivo.listarTodos();
        }
    }

    public void adicionar(int codigo, int numero, String nome, String endereco, String bairro, String cidade,
            String estado, String cep, String fone, String celular, String solicitante, String rg, String cpf) {
        Cliente cliente = new Cliente();
        cliente.setCodigo(codigo);
        cliente.setNumero(numero);
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setBairro(bairro);
        cliente.setCidade(cidade);
        cliente.setEstado(estado);
        cliente.setCep(cep);
        cliente.setFone(fone);
        cliente.setCelular(celular);
        cliente.setSolicitante(solicitante);
        cliente.setRg(rg);
        cliente.setCpf(cpf);
        lista.add(cliente);
        arquivo.gravarCliente(cliente);
    }

    public int tamanho() {
        return lista.size();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Cliente cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getNome();
            case 2:
                return cadas.getEndereco();
            case 3:
                return cadas.getBairro();
            case 4:
                return cadas.getNumero();
            case 5:
                return cadas.getCidade();
            case 6:
                return cadas.getEstado();
            case 7:
                return cadas.getCep();
            case 8:
                return cadas.getFone();
            case 9:
                return cadas.getCelular();
            case 10:
                return cadas.getSolicitante();
            case 11:
                return cadas.getRg();
            default:
                return cadas.getCpf();
        }
    }

    public int ultimoCadasCod() {
        int maior = -1;
        if (vazio() == true) {
            maior = 0;
        } else {
            for (int i = 0; i < lista.size(); i++) {
                Cliente aux = lista.get(i);
                if (aux.getCodigo() > maior) {
                    maior = aux.getCodigo();
                }
            }
        }
        return maior;
    }

    public boolean vazio() {
        if (lista.isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean clienteCadasCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public Cliente clienteCadasNome(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                nome = aux.getNome();
                return aux;
            }
        }
        return null;
    }

    public void removerCod(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            if (codigo == (aux.getCodigo())) {
                lista.remove(aux);
                arquivo.excluirLinha(codigo);
            }
        }
    }

    public boolean recuperar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente aux = lista.get(i);
            //Cliente aux = arquivo.lerClente(codigo);
            if (aux == null) {
                return false;
            }
            if (codigo == aux.getCodigo()) {
                this.codigo = aux.getCodigo();
                numero = aux.getNumero();
                nome = aux.getNome();
                endereco = aux.getEndereco();
                bairro = aux.getBairro();
                cidade = aux.getCidade();
                estado = aux.getEstado();
                cep = aux.getCep();
                fone = aux.getFone();
                celular = aux.getCelular();
                solicitante = aux.getSolicitante();
                rg = aux.getRg();
                cpf = aux.getCpf();
                return true;
            }
        }
        return false;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCelular() {
        return celular;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEstado() {
        return estado;
    }

    public String getFone() {
        return fone;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getRg() {
        return rg;
    }

    public String getSolicitante() {
        return solicitante;
    }
}
