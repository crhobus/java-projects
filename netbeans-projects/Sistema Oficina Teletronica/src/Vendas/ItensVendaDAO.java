package Vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensVenda;

public class ItensVendaDAO {

    private Connection con;

    public ItensVendaDAO(Connection con) {
        this.con = con;
    }

    public boolean insertItensVenda(ItensVenda itensVenda) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBITENSVENDA (CODIGO, NUMVENDA, "
                    + "UNIDADE, NOMEPROD, DESCRICAO, VALORUNIT, VALORTOTAL) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, itensVenda.getCodigo());
            stm.setInt(2, itensVenda.getCodVenda());
            stm.setInt(3, itensVenda.getUnidade());
            stm.setString(4, itensVenda.getNomeProd());
            stm.setString(5, itensVenda.getDescricao());
            stm.setDouble(6, itensVenda.getValorUnit());
            stm.setDouble(7, itensVenda.getValorTotal());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do item da venda no banco de dados");
        }
    }

    public List<ItensVenda> listItensVenda(int numeroVen) throws Exception {
        try {
            List<ItensVenda> lista = new ArrayList<ItensVenda>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBITENSVENDA WHERE NUMVENDA = " + numeroVen + " ORDER BY CODIGO").executeQuery();
            while (rs.next()) {
                ItensVenda itensVenda = new ItensVenda();
                itensVenda.setCodigo(rs.getInt("CODIGO"));
                itensVenda.setCodVenda(rs.getInt("NUMVENDA"));
                itensVenda.setUnidade(rs.getInt("UNIDADE"));
                itensVenda.setNomeProd(rs.getString("NOMEPROD"));
                itensVenda.setDescricao(rs.getString("DESCRICAO"));
                itensVenda.setValorUnit(rs.getDouble("VALORUNIT"));
                itensVenda.setValorTotal(rs.getDouble("VALORTOTAL"));
                lista.add(itensVenda);
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de itens de venda do banco de dados");
        }
    }

    public int getProxCodItemVenda() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBITENSVENDA ORDER BY CODIGO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("CODIGO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de itens dae venda do banco de dados");
        }
    }

    public int getQtdadeItensVenda(int numeroVen) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT COUNT(NUMVENDA) as NUMVENDA FROM TBITENSVENDA WHERE NUMVENDA = " + numeroVen).executeQuery();
            if (rs.next()) {
                return rs.getInt("NUMVENDA");
            }
            return 0;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de itens de venda do banco de dados");
        }
    }
}
