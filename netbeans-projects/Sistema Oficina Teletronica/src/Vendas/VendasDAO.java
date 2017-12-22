package Vendas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Venda;

public class VendasDAO {

    private Connection con;

    public VendasDAO(Connection con) {
        this.con = con;
    }

    public boolean insertVenda(Venda venda) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBVENDA (NUMERO, PARCELASRES, SITUACAO, "
                    + "VENDEDOR, EMPRESA, FONEEMPRESA, CNPJ, IE, TIPO, CNPJFORNECEDOR, CONDPAGTO, FORMAPAGTO, "
                    + "CPFCNPJCLIE, DESCRICAO, SUBTOTAL, DESCONTOS, TOTAL, JUROS, PARCELASMES, FRETE, "
                    + "VALORRESTANTE, DATAEMISSAO, DATAENTREGA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, venda.getNumeroVen());
            stm.setInt(2, venda.getParcelasRes());
            stm.setString(3, venda.getSituacao());
            stm.setString(4, venda.getVendedor());
            stm.setString(5, venda.getEmpresa());
            stm.setString(6, venda.getFoneEmpresa());
            stm.setString(7, venda.getCnpj());
            stm.setString(8, venda.getIe());
            stm.setString(9, venda.getTipo());
            stm.setString(10, venda.getCnpjFornecedor());
            stm.setString(11, venda.getCondPagto());
            stm.setString(12, venda.getFormaPagto());
            stm.setString(13, venda.getCpfCnpjClie());
            stm.setString(14, venda.getDescricao());
            stm.setDouble(15, venda.getSubTotal());
            stm.setDouble(16, venda.getDescontos());
            stm.setDouble(17, venda.getTotal());
            stm.setDouble(18, venda.getJuros());
            stm.setDouble(19, venda.getParcelasMes());
            stm.setDouble(20, venda.getFrete());
            stm.setDouble(21, venda.getValorRestante());
            stm.setTimestamp(22, new Timestamp(venda.getDataEmissao().getTime()));
            stm.setDate(23, new Date(venda.getDataEntrega().getTime()));
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação da venda no banco de dados");
        }
    }

    public boolean updateVenda(Venda venda) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBVENDA SET PARCELASRES = ?, SITUACAO = ?, "
                    + "VENDEDOR = ?, EMPRESA = ?, FONEEMPRESA = ?, CNPJ = ?, IE = ?, TIPO = ?, CNPJFORNECEDOR = ?, "
                    + "CONDPAGTO = ?, FORMAPAGTO = ?, CPFCNPJCLIE = ?, DESCRICAO = ?, SUBTOTAL = ?, DESCONTOS = ?, "
                    + "TOTAL = ?, JUROS = ?, PARCELASMES = ?, FRETE = ?, VALORRESTANTE = ?, DATAEMISSAO = ?, "
                    + "DATAENTREGA = ? WHERE NUMERO = ?");
            stm.setInt(1, venda.getParcelasRes());
            stm.setString(2, venda.getSituacao());
            stm.setString(3, venda.getVendedor());
            stm.setString(4, venda.getEmpresa());
            stm.setString(5, venda.getFoneEmpresa());
            stm.setString(6, venda.getCnpj());
            stm.setString(7, venda.getIe());
            stm.setString(8, venda.getTipo());
            stm.setString(9, venda.getCnpjFornecedor());
            stm.setString(10, venda.getCondPagto());
            stm.setString(11, venda.getFormaPagto());
            stm.setString(12, venda.getCpfCnpjClie());
            stm.setString(13, venda.getDescricao());
            stm.setDouble(14, venda.getSubTotal());
            stm.setDouble(15, venda.getDescontos());
            stm.setDouble(16, venda.getTotal());
            stm.setDouble(17, venda.getJuros());
            stm.setDouble(18, venda.getParcelasMes());
            stm.setDouble(19, venda.getFrete());
            stm.setDouble(20, venda.getValorRestante());
            stm.setTimestamp(21, new Timestamp(venda.getDataEmissao().getTime()));
            stm.setDate(22, new Date(venda.getDataEntrega().getTime()));
            stm.setInt(23, venda.getNumeroVen());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao regravar a venda no banco de dados");
        }
    }

    public Venda selectVenda(int numero) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVENDA WHERE NUMERO = " + numero).executeQuery();
            if (rs.next()) {
                return getVenda(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura da venda do banco de dados");
        }
    }

    public List<Venda> listVendas() throws Exception {
        try {
            List<Venda> lista = new ArrayList<Venda>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVENDA ORDER BY NUMERO").executeQuery();
            while (rs.next()) {
                lista.add(getVenda(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de vendas do banco de dados");
        }
    }

    private Venda getVenda(ResultSet rs) throws Exception {
        try {
            Venda venda = new Venda();
            venda.setNumeroVen(rs.getInt("NUMERO"));
            venda.setParcelasRes(rs.getInt("PARCELASRES"));
            venda.setSituacao(rs.getString("SITUACAO"));
            venda.setVendedor(rs.getString("VENDEDOR"));
            venda.setEmpresa(rs.getString("EMPRESA"));
            venda.setFoneEmpresa(rs.getString("FONEEMPRESA"));
            venda.setCnpj(rs.getString("CNPJ"));
            venda.setIe(rs.getString("IE"));
            venda.setTipo(rs.getString("TIPO"));
            venda.setCnpjFornecedor(rs.getString("CNPJFORNECEDOR"));
            venda.setCondPagto(rs.getString("CONDPAGTO"));
            venda.setFormaPagto(rs.getString("FORMAPAGTO"));
            venda.setCpfCnpjClie(rs.getString("CPFCNPJCLIE"));
            venda.setDescricao(rs.getString("DESCRICAO"));
            venda.setSubTotal(rs.getDouble("SUBTOTAL"));
            venda.setDescontos(rs.getDouble("DESCONTOS"));
            venda.setTotal(rs.getDouble("TOTAL"));
            venda.setJuros(rs.getDouble("JUROS"));
            venda.setParcelasMes(rs.getDouble("PARCELASMES"));
            venda.setFrete(rs.getDouble("FRETE"));
            venda.setValorRestante(rs.getDouble("VALORRESTANTE"));
            venda.setDataEmissao(rs.getTimestamp("DATAEMISSAO"));
            venda.setDataEntrega(rs.getDate("DATAENTREGA"));
            return venda;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura da venda do banco de dados");
        }
    }

    public boolean isVendaVazio() throws Exception {
        try {
            return !con.prepareStatement("SELECT * FROM TBVENDA").executeQuery().next();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de vendas do banco de dados");
        }
    }

    public int getProxCodVenda() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVENDA ORDER BY NUMERO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("NUMERO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de vendas do banco de dados");
        }
    }

    public boolean pagarVenda(int numero, double valorRestante, int parcelasRestante, String situacao) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBVENDA SET PARCELASRES = ?, SITUACAO = ?, "
                    + "VALORRESTANTE = ? WHERE NUMERO = ?");
            stm.setInt(1, parcelasRestante);
            stm.setString(2, situacao);
            stm.setDouble(3, valorRestante);
            stm.setInt(4, numero);
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao pagar a parcela, problemas no banco de dados");
        }
    }

    public List<Venda> listaVendaData(int mes, int ano) throws Exception {
        try {
            List<Venda> lista = new ArrayList<Venda>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVENDA WHERE EXTRACT (MONTH FROM DATAEMISSAO) = " + mes + " AND EXTRACT (YEAR FROM DATAEMISSAO) = " + ano + " ORDER BY NUMERO").executeQuery();
            while (rs.next()) {
                lista.add(getVenda(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de vendas do banco de dados");
        }
    }
}
