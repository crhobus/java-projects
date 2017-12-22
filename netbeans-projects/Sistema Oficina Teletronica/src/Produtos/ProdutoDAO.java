package Produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Produto;

public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }

    public boolean insertProduto(Produto produto) throws Exception {
        try {
            if (con.prepareStatement("SELECT * FROM TBPRODUTO WHERE CODIGO = " + produto.getCodigo()).executeQuery().next()) {
                return updateProduto(produto);
            }
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBPRODUTO (CODIGO, NUMEROSERIE, QUANTIDADE, "
                    + " MESGARANTIA, NOME, MARCA, MODELO, DESCRICAO, ACESSORIOS, COR, DATACADASTRO, ULTALTERACAO, PRECOUNITCOMPRA, "
                    + "PRECOUNITVENDA, PERCENTUALLUCRO, IMPOSTOSUNIT, VALORTOTAL, DESCONTOS) VALUES (?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, produto.getCodigo());
            stm.setInt(2, produto.getNumeroSerie());
            stm.setInt(3, produto.getQuantidade());
            stm.setInt(4, produto.getMesGarantia());
            stm.setString(5, produto.getNome());
            stm.setString(6, produto.getMarca());
            stm.setString(7, produto.getModelo());
            stm.setString(8, produto.getDescricao());
            stm.setString(9, produto.getAcessorios());
            stm.setString(10, produto.getCor());
            stm.setTimestamp(11, new Timestamp(produto.getDataCadastro().getTime()));
            stm.setTimestamp(12, new Timestamp(produto.getUltAlteracao().getTime()));
            stm.setDouble(13, produto.getPrecoUnitCompra());
            stm.setDouble(14, produto.getPrecoUnitVenda());
            stm.setDouble(15, produto.getPercentualLucro());
            stm.setDouble(16, produto.getImpostosUnit());
            stm.setDouble(17, produto.getValorTotal());
            stm.setDouble(18, produto.getDescontos());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação do produto no banco de dados");
        }
    }

    public boolean updateProduto(Produto produto) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBPRODUTO SET NUMEROSERIE = ?, QUANTIDADE = ?, "
                    + "MESGARANTIA = ?, NOME = ?, MARCA = ?, MODELO = ?, DESCRICAO = ?, ACESSORIOS = ?, COR = ?, "
                    + "DATACADASTRO = ?, ULTALTERACAO = ?, PRECOUNITCOMPRA = ?, PRECOUNITVENDA = ?, PERCENTUALLUCRO = ?, "
                    + "IMPOSTOSUNIT = ?, VALORTOTAL = ?, DESCONTOS = ? WHERE CODIGO = ?");
            stm.setInt(1, produto.getNumeroSerie());
            stm.setInt(2, produto.getQuantidade());
            stm.setInt(3, produto.getMesGarantia());
            stm.setString(4, produto.getNome());
            stm.setString(5, produto.getMarca());
            stm.setString(6, produto.getModelo());
            stm.setString(7, produto.getDescricao());
            stm.setString(8, produto.getAcessorios());
            stm.setString(9, produto.getCor());
            stm.setTimestamp(10, new Timestamp(produto.getDataCadastro().getTime()));
            stm.setTimestamp(11, new Timestamp(produto.getUltAlteracao().getTime()));
            stm.setDouble(12, produto.getPrecoUnitCompra());
            stm.setDouble(13, produto.getPrecoUnitVenda());
            stm.setDouble(14, produto.getPercentualLucro());
            stm.setDouble(15, produto.getImpostosUnit());
            stm.setDouble(16, produto.getValorTotal());
            stm.setDouble(17, produto.getDescontos());
            stm.setInt(18, produto.getCodigo());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao regravar o pruduto no banco de dados");
        }
    }

    public boolean deleteProduto(String nome) throws Exception {
        try {
            return con.prepareStatement("DELETE FROM TBPRODUTO WHERE NOME = " + "'" + nome + "'").executeUpdate() == 1;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um produto do banco de dados");
        }
    }

    public Produto selectProduto(String nome) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBPRODUTO WHERE NOME = '" + nome + "'").executeQuery();
            if (rs.next()) {
                return getProduto(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do produto do banco de dados");
        }
    }

    public List<Produto> listProdutos() throws Exception {
        try {
            List<Produto> lista = new ArrayList<Produto>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBPRODUTO ORDER BY CODIGO").executeQuery();
            while (rs.next()) {
                lista.add(getProduto(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de produtos do banco de dados");
        }
    }

    private Produto getProduto(ResultSet rs) throws Exception {
        try {
            Produto produto = new Produto();
            produto.setCodigo(rs.getInt("CODIGO"));
            produto.setNumeroSerie(rs.getInt("NUMEROSERIE"));
            produto.setQuantidade(rs.getInt("QUANTIDADE"));
            produto.setMesGarantia(rs.getInt("MESGARANTIA"));
            produto.setNome(rs.getString("NOME"));
            produto.setMarca(rs.getString("MARCA"));
            produto.setModelo(rs.getString("MODELO"));
            produto.setDescricao(rs.getString("DESCRICAO"));
            produto.setAcessorios(rs.getString("ACESSORIOS"));
            produto.setCor(rs.getString("COR"));
            produto.setDataCadastro(rs.getTimestamp("DATACADASTRO"));
            produto.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            produto.setPrecoUnitCompra(rs.getDouble("PRECOUNITCOMPRA"));
            produto.setPrecoUnitVenda(rs.getDouble("PRECOUNITVENDA"));
            produto.setPercentualLucro(rs.getDouble("PERCENTUALLUCRO"));
            produto.setImpostosUnit(rs.getDouble("IMPOSTOSUNIT"));
            produto.setValorTotal(rs.getDouble("VALORTOTAL"));
            produto.setDescontos(rs.getDouble("DESCONTOS"));
            return produto;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura do produto do banco de dados");
        }
    }

    public boolean isProdVazio() throws Exception {
        try {
            return !con.prepareStatement("SELECT * FROM TBPRODUTO").executeQuery().next();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de produtos do banco de dados");
        }
    }

    public int getProxCodProd() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBPRODUTO ORDER BY CODIGO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("CODIGO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de produtos do banco de dados");
        }
    }
}
