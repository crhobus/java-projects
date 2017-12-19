package VendedorDAO;

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
import Modelo.Vendedor;

public class VendedorTexto extends ArqTextoGenerico implements VendedorDAO {

    @Override
    public boolean arqVenVazio() throws Exception {
        return arqVazio("Texto/Vendedor");
    }

    @Override
    public int getProxCodVen() throws Exception {
        return getProxCod("Texto/Vendedor", "vendedor");
    }

    @Override
    public Vendedor getVendedor(String cpf) throws Exception {
        File diretorio = new File("Texto/Vendedor");
        if (diretorio.exists()) {
            File arq = new File(diretorio, cpf + ".txt");
            if (arq.exists()) {
                try {
                    return leituraVendedor(arq);
                } catch (Exception ex) {
                    throw new Exception("Erro na leitura do arquivo de vendedor\no arquivo " + cpf + ".txt está corrompido");
                }
            }
        }
        return null;
    }

    @Override
    public List<Vendedor> listVendedor() throws Exception {
        List<Vendedor> lista = new ArrayList<Vendedor>();
        File diretorio = new File("Texto/Vendedor");
        if (diretorio.exists()) {
            File[] arqs = diretorio.listFiles();
            try {
                for (int i = 0; i < arqs.length; i++) {
                    lista.add(leituraVendedor(new File(diretorio, arqs[i].getName())));
                }
            } catch (Exception ex) {
                throw new Exception("Erro na leitura do arquivo de vendedor\n há um ou mais arquivos corrompidos");
            }
        }
        return lista;
    }

    private Vendedor leituraVendedor(File arq) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(arq));
        Vendedor vendedor = new Vendedor();
        vendedor.setCodigo(Integer.parseInt(in.readLine()));
        vendedor.setCpf(in.readLine());
        vendedor.setCelularEmpresa(in.readLine());
        vendedor.setEmailVendedor(in.readLine());
        vendedor.setDescricao(in.readLine());
        vendedor.setHomePage(in.readLine());
        vendedor.setDataCadas(SimpleDateFormat.getInstance().parse(in.readLine()));
        vendedor.setUltAlteracao(SimpleDateFormat.getInstance().parse(in.readLine()));
        vendedor.setComissao(Double.parseDouble(in.readLine()));
        vendedor.setSalarioLiquido(Double.parseDouble(in.readLine()));
        String outConsideracoes = "", linha = "";
        while (linha != null) {
            linha = in.readLine();
            if (linha != null) {
                outConsideracoes += linha + "\n";
            }
        }
        vendedor.setOutasConsideracoes(outConsideracoes);
        in.close();
        return vendedor;
    }

    @Override
    public boolean removeVendedor(String cpf) throws Exception {
        return remove("Texto/Vendedor", cpf);
    }

    @Override
    public boolean setVendedor(Vendedor vendedor) throws Exception {
        List<Vendedor> lista = listVendedor();
        for (Vendedor ven : lista) {
            if (ven.getCodigo() == vendedor.getCodigo()) {
                removeVendedor(ven.getCpf());
            }
        }
        File diretorio = new File("Texto");
        if (diretorio.mkdir() || diretorio.exists()) {
            File subDiretorio = new File(diretorio, "Vendedor");
            if (subDiretorio.mkdir() || subDiretorio.exists()) {
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(subDiretorio, vendedor.getCpf() + ".txt"))));
                    out.println(vendedor.getCodigo());
                    out.println(vendedor.getCpf());
                    out.println(vendedor.getCelularEmpresa());
                    out.println(vendedor.getEmailVendedor());
                    out.println(vendedor.getDescricao());
                    out.println(vendedor.getHomePage());
                    out.println(SimpleDateFormat.getInstance().format(vendedor.getDataCadas()));
                    out.println(SimpleDateFormat.getInstance().format(vendedor.getUltAlteracao()));
                    out.println(vendedor.getComissao());
                    out.println(vendedor.getSalarioLiquido());
                    String[] outConsideracoes = vendedor.getOutasConsideracoes().split("\n");
                    for (int i = 0; i < outConsideracoes.length; i++) {
                        out.println(outConsideracoes[i]);
                    }
                    out.close();
                    return true;
                } catch (Exception e) {
                    throw new Exception("Erro na gravação do arquivo de vendedor");
                }
            }
        }
        return false;
    }
}
