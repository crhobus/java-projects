package Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.Produto;
import Principal.DBDAO;

public class ProdutoDAO extends DBDAO {

    private Connection con;

    public ProdutoDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertProduto(Produto produto) throws SQLException {
        if (isProdutoCadastrado(produto.getCodProduto())) {
            return updateProduto(produto);
        }
        PreparedStatement stm = null;
        try {
            int[] codTabelas = insertAux(produto);
            stm = con.prepareStatement("INSERT INTO TBPRODUTO (CODPRODUTO, CODNOMEPROD, "
                    + "CODMODELO, CODMARCA, DATACADAS, ULTALTERACAO, DESCRICAO, IDENTIFICACAO, COR, UNIDADE, MESESGARANTIA, "
                    + "QUANTIDADE, PRECOCOMPRA, PERCENTUALLUCRO, IPI, DESCONTOS, OBSERVACOES, ASCESSORIOS) VALUES (?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, produto.getCodProduto());
            stm.setInt(2, codTabelas[0]);//codNomeProduto
            stm.setInt(3, codTabelas[2]);//codModelo
            stm.setInt(4, codTabelas[1]);//codMarca
            stm.setTimestamp(5, new Timestamp(produto.getDataCadastro().getTime()));
            stm.setTimestamp(6, new Timestamp(produto.getUltAlteracao().getTime()));
            stm.setString(7, produto.getDescricao());
            stm.setString(8, produto.getIdentificacao());
            stm.setString(9, produto.getCor());
            stm.setString(10, produto.getUnidade());
            stm.setInt(11, produto.getMesesGarantia());
            stm.setInt(12, produto.getQuantidade());
            stm.setDouble(13, produto.getPrecoCompra());
            stm.setDouble(14, produto.getPercentualLucro());
            stm.setDouble(15, produto.getIpi());
            stm.setDouble(16, produto.getDescontos());
            stm.setString(17, produto.getObservacoes());
            stm.setString(18, produto.getAscessorios());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int[] insertAux(Produto produto) throws SQLException {
        try {
            int[] codTabelas = new int[3];//0 = codNomeProduto, 1 = codMarca, 2 = codModelo
            codTabelas[0] = getCadastrado("SELECT CODNOMEPROD FROM TBNOMEPROD WHERE LOWER(NOMEPROD) = '"
                    + produto.getProduto().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se nome do produto está cadastrado
            if (codTabelas[0] == -1) {
                codTabelas[0] = insertNomeProd(produto);
            }
            codTabelas[1] = getCadastrado("SELECT CODMARCA FROM TBMARCA WHERE LOWER(MARCA) = '"
                    + produto.getMarca().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se marca está cadastrada
            if (codTabelas[1] == -1) {
                codTabelas[1] = insertMarcaProd(produto);
            }
            codTabelas[2] = getCadastrado("SELECT CODMODELO FROM TBMODELO WHERE LOWER(MODELO) = '"
                    + produto.getModelo().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se modelo está cadastrado
            if (codTabelas[2] == -1) {
                codTabelas[2] = insertModeloProd(produto);
            }
            return codTabelas;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o produto no sistema");
        }
    }

    private int insertNomeProd(Produto produto) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBNOMEPROD (CODNOMEPROD, "
                    + "NOMEPROD) VALUES (?, ?)");
            int proxCodNomeProd = getProxCod(
                    "SELECT MAX(CODNOMEPROD) FROM TBNOMEPROD", "Erro na leitura de produtos no sistema");// obtem próximo codigo da tabela nome produto
            stm.setInt(1, proxCodNomeProd);
            stm.setString(2, produto.getProduto());
            stm.execute();
            return proxCodNomeProd;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updateNomeProd(int codProduto, String produto) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codNomeProduto = getCadastrado("SELECT CODNOMEPROD FROM TBPRODUTO WHERE CODPRODUTO = "
                    + codProduto, "Erro na leitura do produto no sistema");
            stm = con.prepareStatement("UPDATE TBNOMEPROD SET NOMEPROD = ?"
                    + "WHERE CODNOMEPROD = ?");
            stm.setString(1, produto);
            stm.setInt(2, codNomeProduto);
            stm.execute();
            return codNomeProduto;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertMarcaProd(Produto produto) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBMARCA (CODMARCA, "
                    + "MARCA) VALUES (?, ?)");
            int proxCodMarca = getProxCod("SELECT MAX(CODMARCA) FROM TBMARCA",
                    "Erro na leitura de produtos no sistema");// obtem próximo codigo da tabela de marcas
            stm.setInt(1, proxCodMarca);
            stm.setString(2, produto.getMarca());
            stm.execute();
            return proxCodMarca;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updateMarcaProd(int codProduto, String marca) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codMarcaProduto = getCadastrado("SELECT CODMARCA FROM TBPRODUTO WHERE CODPRODUTO = "
                    + codProduto, "Erro na leitura do produto no sistema");
            stm = con.prepareStatement("UPDATE TBMARCA SET MARCA = ?"
                    + "WHERE CODMARCA = ?");
            stm.setString(1, marca);
            stm.setInt(2, codMarcaProduto);
            stm.execute();
            return codMarcaProduto;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int insertModeloProd(Produto produto) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBMODELO (CODMODELO, "
                    + "MODELO) VALUES (?, ?)");
            int proxCodModelo = getProxCod("SELECT MAX(CODMODELO) FROM TBMODELO",
                    "Erro na leitura de produtos no sistema");// obtem próximo codigo da tabela de modelo
            stm.setInt(1, proxCodModelo);
            stm.setString(2, produto.getModelo());
            stm.execute();
            return proxCodModelo;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int updateModeloProd(int codProduto, String modelo) throws SQLException {
        PreparedStatement stm = null;
        try {
            int codModeloProduto = getCadastrado("SELECT CODMODELO FROM TBPRODUTO WHERE CODPRODUTO = "
                    + codProduto, "Erro na leitura do produto no sistema");
            stm = con.prepareStatement("UPDATE TBMODELO SET MODELO = ?"
                    + "WHERE CODMODELO = ?");
            stm.setString(1, modelo);
            stm.setInt(2, codModeloProduto);
            stm.execute();
            return codModeloProduto;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateProduto(Produto produto) throws SQLException {
        PreparedStatement stm = null;
        try {
            DadosControle[] controleTabela = updateAux(produto);
            stm = con.prepareStatement("UPDATE TBPRODUTO SET CODNOMEPROD = ?, "
                    + "CODMODELO = ?, CODMARCA = ?, DATACADAS = ?, ULTALTERACAO = ?, DESCRICAO = ?, "
                    + "IDENTIFICACAO = ?, COR = ?, UNIDADE = ?, MESESGARANTIA = ?, QUANTIDADE = ?, "
                    + "PRECOCOMPRA = ?, PERCENTUALLUCRO = ?, IPI = ?, DESCONTOS = ?, OBSERVACOES = ?, "
                    + "ASCESSORIOS = ? WHERE CODPRODUTO = ?");
            stm.setInt(1, controleTabela[0].getCodNovo());//codNomeProduto
            stm.setInt(2, controleTabela[2].getCodNovo());//codModelo
            stm.setInt(3, controleTabela[1].getCodNovo());//codMarca 
            stm.setTimestamp(4, new Timestamp(produto.getDataCadastro().getTime()));
            stm.setTimestamp(5, new Timestamp(produto.getUltAlteracao().getTime()));
            stm.setString(6, produto.getDescricao());
            stm.setString(7, produto.getIdentificacao());
            stm.setString(8, produto.getCor());
            stm.setString(9, produto.getUnidade());
            stm.setInt(10, produto.getMesesGarantia());
            stm.setInt(11, produto.getQuantidade());
            stm.setDouble(12, produto.getPrecoCompra());
            stm.setDouble(13, produto.getPercentualLucro());
            stm.setDouble(14, produto.getIpi());
            stm.setDouble(15, produto.getDescontos());
            stm.setString(16, produto.getObservacoes());
            stm.setString(17, produto.getAscessorios());
            stm.setInt(18, produto.getCodProduto());
            stm.execute();
            if (controleTabela[0].getCodAntigo() != -1 && controleTabela[0].getQtdade() == 1) {
                try {
                    con.prepareStatement("DELETE FROM TBNOMEPROD WHERE CODNOMEPROD = "
                            + controleTabela[0].getCodAntigo()).executeUpdate();
                } catch (SQLException ex) {
                }
            }
            if (controleTabela[1].getCodAntigo() != -1 && controleTabela[1].getQtdade() == 1) {
                try {
                    con.prepareStatement("DELETE FROM TBMARCA WHERE CODMARCA = "
                            + controleTabela[1].getCodAntigo()).executeUpdate();
                } catch (SQLException ex) {
                }
            }
            if (controleTabela[2].getCodAntigo() != -1 && controleTabela[2].getQtdade() == 1) {
                try {
                    con.prepareStatement("DELETE FROM TBMODELO WHERE CODMODELO = "
                            + controleTabela[2].getCodAntigo()).executeUpdate();
                } catch (SQLException ex) {
                }
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private DadosControle[] updateAux(Produto produto) throws SQLException {
        try {
            DadosControle[] controleTabela = {new DadosControle(), new DadosControle(), new DadosControle()};//nomeProduto, marca, modelo
            int codNomeProd = getCadastrado("SELECT CODNOMEPROD FROM TBNOMEPROD WHERE LOWER(NOMEPROD) = '"
                    + produto.getProduto().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se nome do produto está cadastrado
            controleTabela[0].setQtdade(getCadastrado("SELECT COUNT(CODNOMEPROD) FROM TBPRODUTO WHERE CODNOMEPROD = "
                    + "(SELECT CODNOMEPROD FROM TBPRODUTO WHERE CODPRODUTO = " + produto.getCodProduto() + ")",
                    "Erro na leitura do produto no sistema"));// verifica quantos produtos com esse nome estão cadastrados
            controleTabela[0].setCodAntigo(-1);
            if (codNomeProd != -1) {
                controleTabela[0].setCodAntigo(getCadastrado("SELECT CODNOMEPROD FROM TBPRODUTO "
                        + "WHERE CODPRODUTO = " + produto.getCodProduto(), "Erro na leitura do produto no sistema"));
                controleTabela[0].setCodNovo(codNomeProd);
            } else {
                if (controleTabela[0].getQtdade() == 1) {
                    controleTabela[0].setCodNovo(updateNomeProd(produto.getCodProduto(), produto.getProduto()));
                } else {
                    controleTabela[0].setCodNovo(insertNomeProd(produto));
                }
            }
            int codMarca = getCadastrado("SELECT CODMARCA FROM TBMARCA WHERE LOWER(MARCA) = '"
                    + produto.getMarca().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se marca está cadastrada
            controleTabela[1].setQtdade(getCadastrado("SELECT COUNT(CODMARCA) FROM TBPRODUTO WHERE CODMARCA = "
                    + "(SELECT CODMARCA FROM TBPRODUTO WHERE CODPRODUTO = " + produto.getCodProduto() + ")",
                    "Erro na leitura do produto no sistema"));// verifica quantos produtos com essa marca estão cadastrados
            controleTabela[1].setCodAntigo(-1);
            if (codMarca != -1) {
                controleTabela[1].setCodAntigo(getCadastrado("SELECT CODMARCA FROM TBPRODUTO "
                        + "WHERE CODPRODUTO = " + produto.getCodProduto(), "Erro na leitura do produto no sistema"));
                controleTabela[1].setCodNovo(codMarca);
            } else {
                if (controleTabela[1].getQtdade() == 1) {
                    controleTabela[1].setCodNovo(updateMarcaProd(produto.getCodProduto(), produto.getMarca()));
                } else {
                    controleTabela[1].setCodNovo(insertMarcaProd(produto));
                }
            }
            int codModelo = getCadastrado("SELECT CODMODELO FROM TBMODELO WHERE LOWER(MODELO) = '"
                    + produto.getModelo().toLowerCase() + "'", "Erro na leitura do produto no sistema");// verifica se modelo está cadastrado
            controleTabela[2].setQtdade(getCadastrado("SELECT COUNT(CODMODELO) FROM TBPRODUTO WHERE CODMODELO = "
                    + "(SELECT CODMODELO FROM TBPRODUTO WHERE CODPRODUTO = " + produto.getCodProduto() + ")",
                    "Erro na leitura do produto no sistema"));// verifica quantos produtos com esse modelo estão cadastrados
            controleTabela[2].setCodAntigo(-1);
            if (codModelo != -1) {
                controleTabela[2].setCodAntigo(getCadastrado("SELECT CODMODELO FROM TBPRODUTO "
                        + "WHERE CODPRODUTO = " + produto.getCodProduto(), "Erro na leitura do produto no sistema"));
                controleTabela[2].setCodNovo(codModelo);
            } else {
                if (controleTabela[2].getQtdade() == 1) {
                    controleTabela[2].setCodNovo(updateModeloProd(produto.getCodProduto(), produto.getModelo()));
                } else {
                    controleTabela[2].setCodNovo(insertModeloProd(produto));
                }
            }
            return controleTabela;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados do produto no sistema");
        }
    }

    public int getQtdadeProd(int codProd) throws SQLException {
        return getCadastrado("SELECT QUANTIDADE FROM TBPRODUTO WHERE CODPRODUTO = "
                + codProd, "Erro na leitura do produto no sistema");
    }

    public void updateQtdadeProduto(int codProd, int novaQtdade) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBPRODUTO SET QUANTIDADE = ? WHERE CODPRODUTO = ?");
            stm.setInt(1, novaQtdade);
            stm.setInt(2, codProd);
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar a quantidade do produto no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isProdutoCadastrado(int codProd) throws SQLException {
        return isCadastrado("SELECT CODPRODUTO FROM TBPRODUTO WHERE CODPRODUTO = "
                + codProd, "Erro na leitura do produto no sistema");
    }

    public int getProdCadastrado(String nomeProduto, String marca, String modelo) throws SQLException {
        return getCadastrado("SELECT P.CODPRODUTO FROM TBPRODUTO P "
                + "INNER JOIN TBNOMEPROD NP ON P.CODNOMEPROD = NP.CODNOMEPROD "
                + "INNER JOIN TBMARCA MA ON P.CODMARCA = MA.CODMARCA "
                + "INNER JOIN TBMODELO MO ON P.CODMODELO = MO.CODMODELO "
                + "WHERE LOWER(NP.NOMEPROD) = '" + nomeProduto.toLowerCase()
                + "' AND " + "LOWER(MA.MARCA) = '" + marca.toLowerCase()
                + "' AND " + "LOWER(MO.MODELO) = '" + modelo.toLowerCase()
                + "'", "Erro na leitura do produto no sistema");
    }

    public int getProdCadastrado(String identificacao) throws SQLException {
        return getCadastrado("SELECT CODPRODUTO FROM TBPRODUTO WHERE LOWER(IDENTIFICACAO) = '"
                + identificacao.toLowerCase() + "'", "Erro na leitura do produto no sistema");
    }

    public boolean isProdVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBPRODUTO",
                "Erro na leitura de produtos no sistema");
    }

    public int getProxCodProd() throws SQLException {
        return getProxCod("SELECT MAX(CODPRODUTO) FROM TBPRODUTO",
                "Erro na leitura de produtos no sistema");
    }

    public boolean deleteProd(int codProd) throws SQLException {
        int codNomeProduto = getCadastrado("SELECT NP.CODNOMEPROD FROM TBPRODUTO P "
                + "INNER JOIN TBNOMEPROD NP ON P.CODNOMEPROD = NP.CODNOMEPROD "
                + "WHERE P.CODPRODUTO = " + codProd, "Erro na leitura do produto no sistema");
        int codMarcaProduto = getCadastrado("SELECT MA.CODMARCA FROM TBPRODUTO P "
                + "INNER JOIN TBMARCA MA ON P.CODMARCA = MA.CODMARCA "
                + "WHERE P.CODPRODUTO = " + codProd, "Erro na leitura do produto no sistema");
        int codModeloProduto = getCadastrado("SELECT MO.CODMODELO FROM TBPRODUTO P "
                + "INNER JOIN TBMODELO MO ON P.CODMODELO = MO.CODMODELO "
                + "WHERE P.CODPRODUTO = " + codProd, "Erro na leitura do produto no sistema");
        delete("DELETE FROM TBPRODUTO WHERE CODPRODUTO = " + codProd,
                "Não foi possível excluir o produto do sistema");
        int qtdade = getCadastrado("SELECT COUNT(*) FROM TBPRODUTO WHERE CODNOMEPROD = "
                + codNomeProduto, "Erro na leitura do produto no sistema");
        if (qtdade == 0) {
            delete("DELETE FROM TBNOMEPROD WHERE CODNOMEPROD = " + codNomeProduto,
                    "Não foi possível excluir o produto do sistema");
        }
        qtdade = getCadastrado("SELECT COUNT(*) FROM TBPRODUTO WHERE CODMARCA = "
                + codMarcaProduto, "Erro na leitura do produto no sistema");
        if (qtdade == 0) {
            delete("DELETE FROM TBMARCA WHERE CODMARCA = " + codMarcaProduto,
                    "Não foi possível excluir o produto do sistema");
        }
        qtdade = getCadastrado("SELECT COUNT(*) FROM TBPRODUTO WHERE CODMODELO = "
                + codModeloProduto, "Erro na leitura do produto no sistema");
        if (qtdade == 0) {
            delete("DELETE FROM TBMODELO WHERE CODMODELO = " + codModeloProduto,
                    "Não foi possível excluir o produto do sistema");
        }
        return true;
    }

    public List<Produto> listProd() throws SQLException {
        return getLista("SELECT P.CODPRODUTO, P.DATACADAS, P.ULTALTERACAO, NP.NOMEPROD, "
                + "MA.MARCA, MO.MODELO, P.DESCRICAO, P.IDENTIFICACAO, P.COR, P.UNIDADE, "
                + "P.MESESGARANTIA, P.QUANTIDADE, P.PRECOCOMPRA, P.PERCENTUALLUCRO, "
                + "P.IPI, P.DESCONTOS, P.OBSERVACOES, P.ASCESSORIOS FROM TBPRODUTO P "
                + "INNER JOIN TBNOMEPROD NP ON P.CODNOMEPROD = NP.CODNOMEPROD "
                + "INNER JOIN TBMARCA MA ON P.CODMARCA = MA.CODMARCA "
                + "INNER JOIN TBMODELO MO ON P.CODMODELO = MO.CODMODELO "
                + "ORDER BY P.CODPRODUTO");
    }

    private List<Produto> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Produto> lista = new ArrayList<Produto>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getProdAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de produtos no sistema");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    private Produto getProdAux(ResultSet rs) throws SQLException {
        try {
            Produto produto = new Produto();
            produto.setCodProduto(rs.getInt(1));
            produto.setDataCadastro(rs.getTimestamp(2));
            produto.setUltAlteracao(rs.getTimestamp(3));
            produto.setProduto(rs.getString(4));
            produto.setMarca(rs.getString(5));
            produto.setModelo(rs.getString(6));
            produto.setDescricao(rs.getString(7));
            produto.setIdentificacao(rs.getString(8));
            produto.setCor(rs.getString(9));
            produto.setUnidade(rs.getString(10));
            produto.setMesesGarantia(rs.getInt(11));
            produto.setQuantidade(rs.getInt(12));
            produto.setPrecoCompra(rs.getDouble(13));
            produto.setPercentualLucro(rs.getDouble(14));
            produto.setIpi(rs.getDouble(15));
            produto.setDescontos(rs.getDouble(16));
            produto.setObservacoes(rs.getString(17));
            produto.setAscessorios(rs.getString(18));
            return produto;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura do produto no sistema");
        }
    }

    public List<Produto> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("P.CODPRODUTO = " + n));
            case 1:
                return getLista(getSql("LOWER(NP.NOMEPROD) like '%" + s.toLowerCase() + "%'"));
            case 2:
                return getLista(getSql("LOWER(MA.MARCA) like '%" + s.toLowerCase() + "%'"));
            case 3:
                return getLista(getSql("LOWER(MO.MODELO) like '%" + s.toLowerCase() + "%'"));
            case 4:
                return getLista(getSql("LOWER(P.DESCRICAO) like '%" + s.toLowerCase() + "%'"));
            case 5:
                return getLista(getSql("LOWER(P.IDENTIFICACAO) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("P.QUANTIDADE = " + n));
        }
    }

    private String getSql(String condicao) {
        return "SELECT P.CODPRODUTO, P.DATACADAS, P.ULTALTERACAO, NP.NOMEPROD, "
                + "MA.MARCA, MO.MODELO, P.DESCRICAO, P.IDENTIFICACAO, P.COR, P.UNIDADE, "
                + "P.MESESGARANTIA, P.QUANTIDADE, P.PRECOCOMPRA, P.PERCENTUALLUCRO, "
                + "P.IPI, P.DESCONTOS, P.OBSERVACOES, P.ASCESSORIOS FROM TBPRODUTO P "
                + "INNER JOIN TBNOMEPROD NP ON P.CODNOMEPROD = NP.CODNOMEPROD "
                + "INNER JOIN TBMARCA MA ON P.CODMARCA = MA.CODMARCA "
                + "INNER JOIN TBMODELO MO ON P.CODMODELO = MO.CODMODELO "
                + "WHERE " + condicao + " ORDER BY P.CODPRODUTO";
    }
}
