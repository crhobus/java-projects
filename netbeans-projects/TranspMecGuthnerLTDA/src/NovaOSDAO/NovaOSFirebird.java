package NovaOSDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.MetodosFirebirdGenerico;
import Modelo.OrdemServico;

public class NovaOSFirebird extends MetodosFirebirdGenerico implements NovaOSDAO {

    @Override
    public OrdemServico getOrdemServico(int numero, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO WHERE CODIGO = " + numero).executeQuery();
            if (rs.next()) {
                return leituraOrdemServico(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de ordem de serviço do banco de dados");
        }
    }

    private OrdemServico leituraOrdemServico(ResultSet rs) throws Exception {
        try {
            OrdemServico ordemServico = new OrdemServico();
            ordemServico.setCodigo(rs.getInt("CODIGO"));
            ordemServico.setNomeVendedor(rs.getString("NOMEVENDEDOR"));
            ordemServico.setSituacao(rs.getString("SITUACAO"));
            ordemServico.setPlacaVei(rs.getString("PLACAVEI"));
            ordemServico.setCondPagto(rs.getString("CONDPAGTO"));
            ordemServico.setFormaPagto(rs.getString("FORMAPAGTO"));
            ordemServico.setCpfCnpjClie(rs.getString("CPFCNPJCLIE"));
            ordemServico.setNomeOPRealizada(rs.getString("NOMEOPREALIZADA"));
            ordemServico.setHoraInicial(rs.getString("HORAINICIAL"));
            ordemServico.setHoraFinal(rs.getString("HORAFINAL"));
            ordemServico.setTempo(rs.getString("TEMPO"));
            ordemServico.setDescricao(rs.getString("DESCRICAO"));
            ordemServico.setDataGeracao(rs.getTimestamp("DATAGERACAO"));
            ordemServico.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            ordemServico.setData(rs.getDate("DATA"));
            ordemServico.setSubTotal(rs.getDouble("SUBTOTAL"));
            ordemServico.setDescontos(rs.getDouble("DESCONTOS"));
            ordemServico.setTotal(rs.getDouble("TOTAL"));
            ordemServico.setJuros(rs.getDouble("JUROS"));
            ordemServico.setParcelasMes(rs.getDouble("PARCELASMES"));
            ordemServico.setValorPorHora(rs.getDouble("VALORPORHORA"));
            return ordemServico;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de ordem de serviço do banco de dados");
        }
    }

    @Override
    public int getProxCodOS(Connection con) throws Exception {
        return getProxCod(con, "SELECT * FROM TBORDEMSERVICO", "ordem de serviço");
    }

    @Override
    public boolean isOSVazio(Connection con) throws Exception {
        return isVazio(con, "SELECT * FROM TBORDEMSERVICO", "ordem de serviço");
    }

    @Override
    public List<OrdemServico> listOrdemServicos(Connection con) throws Exception {
        try {
            List<OrdemServico> lista = new ArrayList<OrdemServico>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO").executeQuery();
            while (rs.next()) {
                lista.add(leituraOrdemServico(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de ordem de serviços do banco de dados");
        }
    }

    @Override
    public boolean setOrdemServico(OrdemServico ordemServico, Connection con) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBORDEMSERVICO (CODIGO, NOMEVENDEDOR, SITUACAO, "
                    + "PLACAVEI, CONDPAGTO, FORMAPAGTO, CPFCNPJCLIE, NOMEOPREALIZADA, HORAINICIAL, HORAFINAL, TEMPO, DESCRICAO, "
                    + "DATAGERACAO, ULTALTERACAO, DATA, SUBTOTAL, DESCONTOS, TOTAL, JUROS, PARCELASMES, VALORPORHORA) VALUES (?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, ordemServico.getCodigo());
            stm.setString(2, ordemServico.getNomeVendedor());
            stm.setString(3, ordemServico.getSituacao());
            stm.setString(4, ordemServico.getPlacaVei());
            stm.setString(5, ordemServico.getCondPagto());
            stm.setString(6, ordemServico.getFormaPagto());
            stm.setString(7, ordemServico.getCpfCnpjClie());
            stm.setString(8, ordemServico.getNomeOPRealizada());
            stm.setString(9, ordemServico.getHoraInicial());
            stm.setString(10, ordemServico.getHoraFinal());
            stm.setString(11, ordemServico.getTempo());
            stm.setString(12, ordemServico.getDescricao());
            stm.setTimestamp(13, new Timestamp(ordemServico.getDataGeracao().getTime()));
            stm.setTimestamp(14, new Timestamp(ordemServico.getUltAlteracao().getTime()));
            stm.setDate(15, new Date(ordemServico.getData().getTime()));
            stm.setDouble(16, ordemServico.getSubTotal());
            stm.setDouble(17, ordemServico.getDescontos());
            stm.setDouble(18, ordemServico.getTotal());
            stm.setDouble(19, ordemServico.getJuros());
            stm.setDouble(20, ordemServico.getParcelasMes());
            stm.setDouble(21, ordemServico.getValorPorHora());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação de registro de ordem de serviços no banco de dados");
        }
    }

    @Override
    public boolean updateOrdemServico(OrdemServico ordemServico, Connection con) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBORDEMSERVICO SET NOMEVENDEDOR = ?, SITUACAO = ?, "
                    + "PLACAVEI = ?, CONDPAGTO = ?, FORMAPAGTO = ?, CPFCNPJCLIE = ?, NOMEOPREALIZADA = ?, HORAINICIAL = ?, HORAFINAL = ?, TEMPO = ?, DESCRICAO = ?, "
                    + "DATAGERACAO = ?, ULTALTERACAO = ?, DATA = ?, SUBTOTAL = ?, DESCONTOS = ?, TOTAL = ?, JUROS = ?, PARCELASMES = ?, VALORPORHORA = ? WHERE CODIGO = ?");
            stm.setString(1, ordemServico.getNomeVendedor());
            stm.setString(2, ordemServico.getSituacao());
            stm.setString(3, ordemServico.getPlacaVei());
            stm.setString(4, ordemServico.getCondPagto());
            stm.setString(5, ordemServico.getFormaPagto());
            stm.setString(6, ordemServico.getCpfCnpjClie());
            stm.setString(7, ordemServico.getNomeOPRealizada());
            stm.setString(8, ordemServico.getHoraInicial());
            stm.setString(9, ordemServico.getHoraFinal());
            stm.setString(10, ordemServico.getTempo());
            stm.setString(11, ordemServico.getDescricao());
            stm.setTimestamp(12, new Timestamp(ordemServico.getDataGeracao().getTime()));
            stm.setTimestamp(13, new Timestamp(ordemServico.getUltAlteracao().getTime()));
            stm.setDate(14, new Date(ordemServico.getData().getTime()));
            stm.setDouble(15, ordemServico.getSubTotal());
            stm.setDouble(16, ordemServico.getDescontos());
            stm.setDouble(17, ordemServico.getTotal());
            stm.setDouble(18, ordemServico.getJuros());
            stm.setDouble(19, ordemServico.getParcelasMes());
            stm.setDouble(20, ordemServico.getValorPorHora());
            stm.setInt(21, ordemServico.getCodigo());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao executar operação no sistema");
        }
    }
}
