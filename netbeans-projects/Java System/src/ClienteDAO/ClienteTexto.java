package ClienteDAO;

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
import Modelo.Cliente;

public class ClienteTexto extends ArqTextoGenerico implements ClienteDAO {

    @Override
    public int getProxCodClie() throws Exception {
        return getProxCod("Texto/Cliente", "cliente");
    }

    @Override
    public boolean removeCliente(String cpf) throws Exception {
        return remove("Texto/Cliente", cpf);
    }

    @Override
    public boolean setCliente(Cliente cliente) throws Exception {
        List<Cliente> lista = listCliente();
        for (Cliente clie : lista) {
            if (clie.getCodigo() == cliente.getCodigo()) {
                removeCliente(clie.getCpf());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Cliente");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, cliente.getCpf() + ".txt"))));
                    out.println(cliente.getCodigo());
                    out.println(cliente.getNome());
                    out.println(cliente.getRg());
                    out.println(cliente.getCpf());
                    out.println(cliente.getSexo());
                    out.println(cliente.getEstadoCivil());
                    out.println(cliente.getTipoPessoa());
                    out.println(cliente.getProfissao());
                    out.println(cliente.getCep());
                    out.println(cliente.getEndereco());
                    out.println(cliente.getBairro());
                    out.println(cliente.getComplemento());
                    out.println(cliente.getReferencia());
                    out.println(cliente.getCidade());
                    out.println(cliente.getEstado());
                    out.println(cliente.getEmail());
                    out.println(cliente.getFone());
                    out.println(cliente.getCelular1());
                    out.println(cliente.getCelular2());
                    out.println(cliente.getDescricao());
                    out.println(cliente.getEmpresa());
                    out.println(cliente.getFoneEmpresa());
                    out.println(cliente.getFax());
                    out.println(cliente.getMsn());
                    out.println(cliente.getSkype());
                    out.println(cliente.getNaturalidade());
                    out.println(cliente.getNomePai());
                    out.println(cliente.getRgPai());
                    out.println(cliente.getCpfPai());
                    out.println(cliente.getEstadoCivilPai());
                    out.println(cliente.getTipoPessoaPai());
                    out.println(cliente.getProfissaoPai());
                    out.println(cliente.getContato1Pai());
                    out.println(cliente.getContato2Pai());
                    out.println(cliente.getEmpresaPai());
                    out.println(cliente.getFoneEmpresaPai());
                    out.println(cliente.getNomeMae());
                    out.println(cliente.getRgMae());
                    out.println(cliente.getCpfMae());
                    out.println(cliente.getEstadoCivilMae());
                    out.println(cliente.getTipoPessoaMae());
                    out.println(cliente.getProfissaoMae());
                    out.println(cliente.getContato1Mae());
                    out.println(cliente.getContato2Mae());
                    out.println(cliente.getEmpresaMae());
                    out.println(cliente.getFoneEmpresaMae());
                    out.println(cliente.getOpRealizada());
                    out.println(cliente.getNumero());
                    out.println(cliente.getIdade());
                    out.println(cliente.getAnoTempoTrab());
                    out.println(cliente.getMesTempoTrab());
                    out.println(cliente.getAnoTempoTrabPai());
                    out.println(cliente.getMesTempoTrabPai());
                    out.println(cliente.getAnoTempoTrabMae());
                    out.println(cliente.getMesTempoTrabMae());
                    out.println(SimpleDateFormat.getInstance().format(cliente.getDataNasc()));
                    out.println(SimpleDateFormat.getInstance().format(cliente.getDataCadastro()));
                    out.println(SimpleDateFormat.getInstance().format(cliente.getUltAlteracao()));
                    out.println(cliente.getRenda());
                    out.println(cliente.getRendaPai());
                    out.println(cliente.getRendaMae());
                    out.println(cliente.getCredito());
                    out.println(cliente.getDebito());
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de cliente");
                }
            }
        }
        return false;
    }

    @Override
    public Cliente getCliente(String cpf) throws Exception {
        File diretorio = new File("Texto/Cliente");
        if (diretorio.exists()) {
            File arq = new File(diretorio, cpf + ".txt");
            if (arq.exists()) {
                try {
                    return leituraCliente(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de cliente\no arquivo " + cpf + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listCliente() throws Exception {
        List<Cliente> lista = new ArrayList<Cliente>();
        File diretorio = new File("Texto/Cliente");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraCliente(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de cliente\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    private Cliente leituraCliente(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Cliente cliente = new Cliente();
        cliente.setCodigo(Integer.parseInt(in.readLine()));
        cliente.setNome(in.readLine());
        cliente.setRg(in.readLine());
        cliente.setCpf(in.readLine());
        cliente.setSexo(in.readLine());
        cliente.setEstadoCivil(in.readLine());
        cliente.setTipoPessoa(in.readLine());
        cliente.setProfissao(in.readLine());
        cliente.setCep(in.readLine());
        cliente.setEndereco(in.readLine());
        cliente.setBairro(in.readLine());
        cliente.setComplemento(in.readLine());
        cliente.setReferencia(in.readLine());
        cliente.setCidade(in.readLine());
        cliente.setEstado(in.readLine());
        cliente.setEmail(in.readLine());
        cliente.setFone(in.readLine());
        cliente.setCelular1(in.readLine());
        cliente.setCelular2(in.readLine());
        cliente.setDescricao(in.readLine());
        cliente.setEmpresa(in.readLine());
        cliente.setFoneEmpresa(in.readLine());
        cliente.setFax(in.readLine());
        cliente.setMsn(in.readLine());
        cliente.setSkype(in.readLine());
        cliente.setNaturalidade(in.readLine());
        cliente.setNomePai(in.readLine());
        cliente.setRgPai(in.readLine());
        cliente.setCpfPai(in.readLine());
        cliente.setEstadoCivilPai(in.readLine());
        cliente.setTipoPessoaPai(in.readLine());
        cliente.setProfissaoPai(in.readLine());
        cliente.setContato1Pai(in.readLine());
        cliente.setContato2Pai(in.readLine());
        cliente.setEmpresaPai(in.readLine());
        cliente.setFoneEmpresaPai(in.readLine());
        cliente.setNomeMae(in.readLine());
        cliente.setRgMae(in.readLine());
        cliente.setCpfMae(in.readLine());
        cliente.setEstadoCivilMae(in.readLine());
        cliente.setTipoPessoaMae(in.readLine());
        cliente.setProfissaoMae(in.readLine());
        cliente.setContato1Mae(in.readLine());
        cliente.setContato2Mae(in.readLine());
        cliente.setEmpresaMae(in.readLine());
        cliente.setFoneEmpresaMae(in.readLine());
        cliente.setOpRealizada(in.readLine());
        cliente.setNumero(Integer.parseInt(in.readLine()));
        cliente.setIdade(Integer.parseInt(in.readLine()));
        cliente.setAnoTempoTrab(Integer.parseInt(in.readLine()));
        cliente.setMesTempoTrab(Integer.parseInt(in.readLine()));
        cliente.setAnoTempoTrabPai(Integer.parseInt(in.readLine()));
        cliente.setMesTempoTrabPai(Integer.parseInt(in.readLine()));
        cliente.setAnoTempoTrabMae(Integer.parseInt(in.readLine()));
        cliente.setMesTempoTrabMae(Integer.parseInt(in.readLine()));
        cliente.setDataNasc(SimpleDateFormat.getInstance().parse(in.readLine()));
        cliente.setDataCadastro(SimpleDateFormat.getInstance().parse(in.readLine()));
        cliente.setUltAlteracao(SimpleDateFormat.getInstance().parse(in.readLine()));
        cliente.setRenda(Double.parseDouble(in.readLine()));
        cliente.setRendaPai(Double.parseDouble(in.readLine()));
        cliente.setRendaMae(Double.parseDouble(in.readLine()));
        cliente.setCredito(Double.parseDouble(in.readLine()));
        cliente.setDebito(Double.parseDouble(in.readLine()));
        in.close();
        return cliente;
    }

    @Override
    public boolean arqClieVazio() throws Exception {
        return arqVazio("Texto/Cliente");
    }
}
