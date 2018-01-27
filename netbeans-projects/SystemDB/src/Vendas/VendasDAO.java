package Vendas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.Vendas;
import Principal.DBDAO;

public class VendasDAO extends DBDAO {

    private Connection con;

    public VendasDAO(Connection con) {
        super(con);
        this.con = con;
    }

    public boolean insertVenda(Vendas vendas) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO TBVENDAS (NUMVENDA, CODFUNC, CODCLIE, DATAEMISSAO, SITUACAO, "
                    + "TIPO, SUBTOTAL, DESCONTOS, CONDPAGTO, FORMAPAGTO, JUROS, DATAENTREGA, FRETE, PARCELASRES, "
                    + "DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, vendas.getNumVenda());
            stm.setInt(2, vendas.getFuncionario().getCodFunc());
            if (vendas.getCliente() == null) {
                stm.setNull(3, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(3, vendas.getCliente().getCodClie());
            }
            stm.setDate(4, new Date(vendas.getDataEmissao().getTime()));
            stm.setString(5, vendas.getSituacao());
            stm.setString(6, vendas.getTipo());
            if (vendas.getSubTotal() == -1) {
                stm.setNull(7, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(7, vendas.getSubTotal());
            }
            if (vendas.getDescontos() == -1) {
                stm.setNull(8, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(8, vendas.getDescontos());
            }
            stm.setString(9, vendas.getCondPagto());
            stm.setString(10, vendas.getFormaPagto());
            if (vendas.getJuros() == -1) {
                stm.setNull(11, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(11, vendas.getJuros());
            }
            if (vendas.getDataEntrega() == null) {
                stm.setDate(12, null);// seta campo como null
            } else {
                stm.setDate(12, new Date(vendas.getDataEntrega().getTime()));
            }
            if (vendas.getFrete() == -1) {
                stm.setNull(13, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(13, vendas.getFrete());
            }
            if (vendas.getParcelasRes() == -1) {
                stm.setNull(14, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(14, vendas.getParcelasRes());
            }
            stm.setString(15, vendas.getDescricao());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar a venda no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean updateVenda(Vendas vendas) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBVENDAS SET CODFUNC = ?, CODCLIE = ?, DATAEMISSAO = ?, "
                    + "SITUACAO = ?, TIPO = ?, SUBTOTAL = ?, DESCONTOS = ?, CONDPAGTO = ?, FORMAPAGTO = ?, "
                    + "JUROS = ?, DATAENTREGA = ?, FRETE = ?, PARCELASRES = ?, DESCRICAO = ? WHERE NUMVENDA = ?");
            stm.setInt(1, vendas.getFuncionario().getCodFunc());
            if (vendas.getCliente() == null) {
                stm.setNull(2, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(2, vendas.getCliente().getCodClie());
            }
            stm.setDate(3, new Date(vendas.getDataEmissao().getTime()));
            stm.setString(4, vendas.getSituacao());
            stm.setString(5, vendas.getTipo());
            stm.setDouble(6, vendas.getSubTotal());
            if (vendas.getDescontos() == -1) {
                stm.setNull(7, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(7, vendas.getDescontos());
            }
            stm.setString(8, vendas.getCondPagto());
            stm.setString(9, vendas.getFormaPagto());
            if (vendas.getJuros() == -1) {
                stm.setNull(10, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(10, vendas.getJuros());
            }
            if (vendas.getDataEntrega() == null) {
                stm.setDate(11, null);// seta campo como null
            } else {
                stm.setDate(11, new Date(vendas.getDataEntrega().getTime()));
            }
            if (vendas.getFrete() == -1) {
                stm.setNull(12, Types.BIGINT);// seta campo como null
            } else {
                stm.setDouble(12, vendas.getFrete());
            }
            if (vendas.getParcelasRes() == -1) {
                stm.setNull(13, Types.BIGINT);// seta campo como null
            } else {
                stm.setInt(13, vendas.getParcelasRes());
            }
            stm.setString(14, vendas.getDescricao());
            stm.setInt(15, vendas.getNumVenda());
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da venda no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void updateSusbTotalVenda(double subTotal, int codVenda) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBVENDAS SET SUBTOTAL = ? WHERE NUMVENDA = ?");
            stm.setDouble(1, subTotal);
            stm.setInt(2, codVenda);
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da venda no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void updateParcelasResVenda(int parcelasRes, int codVenda, String situacao) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE TBVENDAS SET SITUACAO = ?, PARCELASRES = ? WHERE NUMVENDA = ?");
            stm.setString(1, situacao);
            stm.setInt(2, parcelasRes);
            stm.setInt(3, codVenda);
            stm.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar os dados da venda no sistema");
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    public boolean isVendaVazio() throws SQLException {
        return isVazio("SELECT COUNT(*) FROM TBVENDAS",
                "Erro na leitura de vendas no sistema");
    }

    public int getProxCodVenda() throws SQLException {
        return getProxCod("SELECT MAX(NUMVENDA) FROM TBVENDAS",
                "Erro na leitura de vendas no sistema");
    }

    public List<Vendas> listVendas() throws SQLException {
        return getLista("SELECT V.NUMVENDA, V.DATAEMISSAO, V.SITUACAO, V.CODFUNC, PF.NOME, V.TIPO, "
                + "V.SUBTOTAL, V.DESCONTOS, V.CONDPAGTO, V.FORMAPAGTO, V.JUROS, V.CODCLIE, "
                + "PC.NOME, PC.CPFCNPJ, EN.ENDERECO, B.BAIRRO, PC.NUMERO, CI.CIDADE, ES.ESTADO, "
                + "PC.FONE, V.DATAENTREGA, V.FRETE, V.PARCELASRES, V.DESCRICAO FROM TBVENDAS V "
                + "INNER JOIN TBFUNCIONARIO F ON V.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "LEFT JOIN TBCLIENTE C ON V.CODCLIE = C.CODCLIE "
                + "LEFT JOIN TBPESSOA PC ON C.CODPESSOA = PC.CODPESSOA "
                + "LEFT JOIN TBENDERECO_HAS_TBBAI_CEP EB ON PC.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "LEFT JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "LEFT JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "LEFT JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "LEFT JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "LEFT JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "LEFT JOIN TBCIDADE CI ON CE.CODCIDADE = CI.CODCIDADE "
                + "LEFT JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO ORDER BY V.NUMVENDA");
    }

    private List<Vendas> getLista(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            List<Vendas> lista = new ArrayList<Vendas>();
            rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(getVendaAux(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de vendas no sistema");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    private Vendas getVendaAux(ResultSet rs) throws SQLException {
        try {
            String s;
            Vendas vendas = new Vendas();
            vendas.setNumVenda(rs.getInt(1));
            vendas.setDataEmissao(rs.getDate(2));
            vendas.setSituacao(rs.getString(3));
            Funcionario funcionario = new Funcionario();
            funcionario.setCodFunc(rs.getInt(4));
            funcionario.setNome(rs.getString(5));
            vendas.setFuncionario(funcionario);
            vendas.setTipo(rs.getString(6));
            vendas.setSubTotal(rs.getDouble(7));
            s = rs.getString(8);
            if (s != null) {
                vendas.setDescontos(Double.parseDouble(s));
            } else {
                vendas.setDescontos(-1);
            }
            s = null;
            vendas.setCondPagto(rs.getString(9));
            vendas.setFormaPagto(rs.getString(10));
            s = rs.getString(11);
            if (s != null) {
                vendas.setJuros(Double.parseDouble(s));
            } else {
                vendas.setJuros(-1);
            }
            s = null;
            s = rs.getString(12);
            Cliente cliente = new Cliente();
            if (s != null) {
                cliente.setCodClie(Integer.parseInt(s));
                cliente.setNome(rs.getString(13));
                cliente.setCpfCnpj(rs.getString(14));
                cliente.setEndereco(rs.getString(15));
                cliente.setBairro(rs.getString(16));
                cliente.setNumero(rs.getInt(17));
                cliente.setCidade(rs.getString(18));
                cliente.setEstado(rs.getString(19));
                cliente.setFone(rs.getString(20));
            } else {
                cliente.setCodClie(-1);
            }
            vendas.setCliente(cliente);
            s = null;
            vendas.setDataEntrega(rs.getDate(21));
            s = rs.getString(22);
            if (s != null) {
                vendas.setFrete(Double.parseDouble(s));
            } else {
                vendas.setFrete(-1);
            }
            s = null;
            s = rs.getString(23);
            if (s != null) {
                vendas.setParcelasRes(Integer.parseInt(s));
            } else {
                vendas.setParcelasRes(-1);
            }
            vendas.setDescricao(rs.getString(24));
            return vendas;
        } catch (SQLException ex) {
            throw new SQLException("Erro na leitura de vendas no sistema");
        }
    }

    public List<Vendas> getLista(final int coluna, final String s, final int n) throws SQLException {
        switch (coluna) {
            case 0:
                return getLista(getSql("V.NUMVENDA = " + n));
            case 1:
                return getLista(getSql("V.DATAEMISSAO = '" + s + "'"));
            case 2:
                return getLista(getSql("LOWER(V.SITUACAO) like '%" + s.toLowerCase() + "%'"));
            case 3:
                return getLista(getSql("LOWER(PF.NOME) like '%" + s.toLowerCase() + "%'"));
            case 4:
                return getLista(getSql("LOWER(V.TIPO) like '%" + s.toLowerCase() + "%'"));
            case 5:
                return getLista(getSql("LOWER(PC.NOME) like '%" + s.toLowerCase() + "%'"));
            default:
                return getLista(getSql("PC.CPFCNPJ = '" + s.toLowerCase() + "'"));
        }
    }

    private String getSql(String condicao) {
        return "SELECT V.NUMVENDA, V.DATAEMISSAO, V.SITUACAO, V.CODFUNC, PF.NOME, V.TIPO, "
                + "V.SUBTOTAL, V.DESCONTOS, V.CONDPAGTO, V.FORMAPAGTO, V.JUROS, V.CODCLIE, "
                + "PC.NOME, PC.CPFCNPJ, EN.ENDERECO, B.BAIRRO, PC.NUMERO, CI.CIDADE, ES.ESTADO, "
                + "PC.FONE, V.DATAENTREGA, V.FRETE, V.PARCELASRES, V.DESCRICAO FROM TBVENDAS V "
                + "INNER JOIN TBFUNCIONARIO F ON V.CODFUNC = F.CODFUNC "
                + "INNER JOIN TBPESSOA PF ON F.CODPESSOA = PF.CODPESSOA "
                + "LEFT JOIN TBCLIENTE C ON V.CODCLIE = C.CODCLIE "
                + "LEFT JOIN TBPESSOA PC ON C.CODPESSOA = PC.CODPESSOA "
                + "LEFT JOIN TBENDERECO_HAS_TBBAI_CEP EB ON PC.COD_ENDERECO_BAI_CEP = EB.COD_ENDERECO_BAI_CEP "
                + "LEFT JOIN TBENDERECO EN ON EB.CODENDERECO = EN.CODENDERECO "
                + "LEFT JOIN TBBAIRRO_HAS_TBCEP BC ON EB.COD_BAIRRO_CEP = BC.COD_BAIRRO_CEP "
                + "LEFT JOIN TBBAIRRO B ON BC.CODBAIRRO = B.CODBAIRRO "
                + "LEFT JOIN TBCEP CEP ON BC.CODCEP = CEP.CODCEP "
                + "LEFT JOIN TBCIDADE_HAS_TBESTADO CE ON CEP.COD_CIDADE_ESTADO = CE.COD_CIDADE_ESTADO "
                + "LEFT JOIN TBCIDADE CI ON CE.CODCIDADE = CI.CODCIDADE "
                + "LEFT JOIN TBESTADO ES ON CE.CODESTADO = ES.CODESTADO "
                + "WHERE " + condicao + " ORDER BY V.NUMVENDA";
    }
}
