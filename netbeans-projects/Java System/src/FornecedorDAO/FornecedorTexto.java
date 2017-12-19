package FornecedorDAO;

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
import Modelo.Fornecedor;

public class FornecedorTexto extends ArqTextoGenerico implements FornecedorDAO {

    @Override
    public boolean arqFornVazio() throws Exception {
        return arqVazio("Texto/Fornecedor");
    }

    @Override
    public Fornecedor getFornecedor(String nome) throws Exception {
        File diretorio = new File("Texto/Fornecedor");
        if (diretorio.exists()) {
            File arq = new File(diretorio, nome + ".txt");
            if (arq.exists()) {
                try {
                    return leituraFornecedor(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de fornecedor\no arquivo " + nome + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    private Fornecedor leituraFornecedor(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(Integer.parseInt(in.readLine()));
        fornecedor.setDigitoConta(Integer.parseInt(in.readLine()));
        fornecedor.setNumero(Integer.parseInt(in.readLine()));
        fornecedor.setNome(in.readLine());
        fornecedor.setSigla(in.readLine());
        fornecedor.setNomeFantasia(in.readLine());
        fornecedor.setInscricaoEstadual(in.readLine());
        fornecedor.setCnpj(in.readLine());
        fornecedor.setInscricaoMunicipal(in.readLine());
        fornecedor.setTipoPessoa(in.readLine());
        fornecedor.setContribuinte(in.readLine());
        fornecedor.setExportador(in.readLine());
        fornecedor.setTipoFornecedor(in.readLine());
        fornecedor.setEmpresa(in.readLine());
        fornecedor.setFoneEmpresa(in.readLine());
        fornecedor.setBanco(in.readLine());
        fornecedor.setAgencia(in.readLine());
        fornecedor.setConta(in.readLine());
        fornecedor.setCaixaPostal(in.readLine());
        fornecedor.setEndereco(in.readLine());
        fornecedor.setBairro(in.readLine());
        fornecedor.setCidade(in.readLine());
        fornecedor.setEstado(in.readLine());
        fornecedor.setRegiao(in.readLine());
        fornecedor.setPais(in.readLine());
        fornecedor.setCep(in.readLine());
        fornecedor.setReferencia(in.readLine());
        fornecedor.setFone(in.readLine());
        fornecedor.setCelular1(in.readLine());
        fornecedor.setCelular2(in.readLine());
        fornecedor.setFax(in.readLine());
        fornecedor.setEmail(in.readLine());
        fornecedor.setMsn(in.readLine());
        fornecedor.setSkype(in.readLine());
        fornecedor.setDescricao(in.readLine());
        fornecedor.setHomePage(in.readLine());
        fornecedor.setDataCadas(SimpleDateFormat.getInstance().parse(in.readLine()));
        fornecedor.setUltAlteracao(SimpleDateFormat.getInstance().parse(in.readLine()));
        fornecedor.setComissao(Double.parseDouble(in.readLine()));
        fornecedor.setCompraMinima(Double.parseDouble(in.readLine()));
        fornecedor.setCompraMaxima(Double.parseDouble(in.readLine()));
        fornecedor.setValorFrete(Double.parseDouble(in.readLine()));
        fornecedor.setIcms(Double.parseDouble(in.readLine()));
        fornecedor.setCofins(Double.parseDouble(in.readLine()));
        fornecedor.setIpi(Double.parseDouble(in.readLine()));
        fornecedor.setJuros(Double.parseDouble(in.readLine()));
        fornecedor.setDescontos(Double.parseDouble(in.readLine()));
        String observacoes = "", linha = "";
        while (linha != null) {
            linha = in.readLine();
            if (linha != null) {
                observacoes += linha + "\n";
            }
        }
        fornecedor.setObservacoes(observacoes);
        in.close();
        return fornecedor;
    }

    @Override
    public int getProxCodForn() throws Exception {
        return getProxCod("Texto/Fornecedor", "fornecedor");
    }

    @Override
    public List<Fornecedor> listFornecedor() throws Exception {
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        File diretorio = new File("Texto/Fornecedor");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraFornecedor(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de fornecedor\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    @Override
    public boolean removeFornecedor(String nome) throws Exception {
        return remove("Texto/Fornecedor", nome);
    }

    @Override
    public boolean setFornecedor(Fornecedor fornecedor) throws Exception {
        List<Fornecedor> lista = listFornecedor();
        for (Fornecedor forn : lista) {
            if (forn.getCodigo() == fornecedor.getCodigo()) {
                removeFornecedor(forn.getNome());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Fornecedor");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, fornecedor.getNome() + ".txt"))));
                    out.println(fornecedor.getCodigo());
                    out.println(fornecedor.getDigitoConta());
                    out.println(fornecedor.getNumero());
                    out.println(fornecedor.getNome());
                    out.println(fornecedor.getSigla());
                    out.println(fornecedor.getNomeFantasia());
                    out.println(fornecedor.getInscricaoEstadual());
                    out.println(fornecedor.getCnpj());
                    out.println(fornecedor.getInscricaoMunicipal());
                    out.println(fornecedor.getTipoPessoa());
                    out.println(fornecedor.getContribuinte());
                    out.println(fornecedor.getExportador());
                    out.println(fornecedor.getTipoFornecedor());
                    out.println(fornecedor.getEmpresa());
                    out.println(fornecedor.getFoneEmpresa());
                    out.println(fornecedor.getBanco());
                    out.println(fornecedor.getAgencia());
                    out.println(fornecedor.getConta());
                    out.println(fornecedor.getCaixaPostal());
                    out.println(fornecedor.getEndereco());
                    out.println(fornecedor.getBairro());
                    out.println(fornecedor.getCidade());
                    out.println(fornecedor.getEstado());
                    out.println(fornecedor.getRegiao());
                    out.println(fornecedor.getPais());
                    out.println(fornecedor.getCep());
                    out.println(fornecedor.getReferencia());
                    out.println(fornecedor.getFone());
                    out.println(fornecedor.getCelular1());
                    out.println(fornecedor.getCelular2());
                    out.println(fornecedor.getFax());
                    out.println(fornecedor.getEmail());
                    out.println(fornecedor.getMsn());
                    out.println(fornecedor.getSkype());
                    out.println(fornecedor.getDescricao());
                    out.println(fornecedor.getHomePage());
                    out.println(SimpleDateFormat.getInstance().format(fornecedor.getDataCadas()));
                    out.println(SimpleDateFormat.getInstance().format(fornecedor.getUltAlteracao()));
                    out.println(fornecedor.getComissao());
                    out.println(fornecedor.getCompraMinima());
                    out.println(fornecedor.getCompraMaxima());
                    out.println(fornecedor.getValorFrete());
                    out.println(fornecedor.getIcms());
                    out.println(fornecedor.getCofins());
                    out.println(fornecedor.getIpi());
                    out.println(fornecedor.getJuros());
                    out.println(fornecedor.getDescontos());
                    String[] observacoes = fornecedor.getObservacoes().split("\n");
                    for (int i = 0; i < observacoes.length; i++) {
                        out.println(observacoes[i]);
                    }
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de fornecedor");
                }
            }
        }
        return false;
    }
}
