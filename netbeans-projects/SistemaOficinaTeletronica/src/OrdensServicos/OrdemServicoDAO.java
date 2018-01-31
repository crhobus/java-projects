package OrdensServicos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.OrdemServico;

public class OrdemServicoDAO {

    private Connection con;

    public OrdemServicoDAO(Connection con) {
        this.con = con;
    }

    public boolean insertOrdemServico(OrdemServico ordemServico) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBORDEMSERVICO (NUMERO, NUMEROSERIEAPA, TEMPO, "
                    + "NOMEATENDENTEAPA, SITUACAO, NOMEAPA, MARCAAPA, MODELOAPA, CORAPA, ASSISTENCIAAPA, ACESSORIOS, "
                    + "CONDPAGTO, FORMAPAGTO, CPFCNPJCLIE, NOMEOPREALIZADA, HORAINICIAL, HORAFINAL, DESCRICAOAPA, "
                    + "DATAGERACAO, ULTALTERACAO, DATA, SUBTOTAL, DESCONTOS, TOTAL, JUROS, PARCELASMES, VALORHORA, "
                    + "ORCAMENTOAPA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, ordemServico.getNumeroOs());
            stm.setInt(2, ordemServico.getNumeroSerieApa());
            stm.setInt(3, ordemServico.getTempo());
            stm.setString(4, ordemServico.getNomeAtendente());
            stm.setString(5, ordemServico.getSituacao());
            stm.setString(6, ordemServico.getNomeApa());
            stm.setString(7, ordemServico.getMarcaApa());
            stm.setString(8, ordemServico.getModeloApa());
            stm.setString(9, ordemServico.getCorApa());
            stm.setString(10, ordemServico.getAssistenciaApa());
            stm.setString(11, ordemServico.getAcessoriosApa());
            stm.setString(12, ordemServico.getCondPagto());
            stm.setString(13, ordemServico.getFormaPagto());
            stm.setString(14, ordemServico.getCpfCnpjClie());
            stm.setString(15, ordemServico.getNomeOPRealizada());
            stm.setString(16, ordemServico.getHoraInicial());
            stm.setString(17, ordemServico.getHoraFinal());
            stm.setString(18, ordemServico.getDescricaoApa());
            stm.setTimestamp(19, new Timestamp(ordemServico.getDataGeracao().getTime()));
            stm.setTimestamp(20, new Timestamp(ordemServico.getUltAlteracao().getTime()));
            stm.setDate(21, new Date(ordemServico.getData().getTime()));
            stm.setDouble(22, ordemServico.getSubTotal());
            stm.setDouble(23, ordemServico.getDescontos());
            stm.setDouble(24, ordemServico.getTotal());
            stm.setDouble(25, ordemServico.getJuros());
            stm.setDouble(26, ordemServico.getParcelasMes());
            stm.setDouble(27, ordemServico.getValorPorHora());
            if (ordemServico.isOrcamentoApa()) {
                stm.setString(28, "T");
            } else {
                stm.setString(28, "F");
            }
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação da ordem de serviço no banco de dados");
        }
    }

    public boolean updateOrdemServico(OrdemServico ordemServico) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBORDEMSERVICO SET NUMEROSERIEAPA = ?, TEMPO = ?, "
                    + "NOMEATENDENTEAPA = ?, SITUACAO = ?, NOMEAPA = ?, MARCAAPA = ?, MODELOAPA = ?, CORAPA = ?, "
                    + "ASSISTENCIAAPA = ?, ACESSORIOS = ?, CONDPAGTO = ?, FORMAPAGTO = ?, CPFCNPJCLIE = ?, NOMEOPREALIZADA = ?, "
                    + "HORAINICIAL = ?, HORAFINAL = ?, DESCRICAOAPA = ?, DATAGERACAO = ?, ULTALTERACAO = ?, DATA = ?, "
                    + "SUBTOTAL = ?, DESCONTOS = ?, TOTAL = ?, JUROS = ?, PARCELASMES = ?, VALORHORA = ?, ORCAMENTOAPA = ? WHERE NUMERO = ?");
            stm.setInt(1, ordemServico.getNumeroSerieApa());
            stm.setInt(2, ordemServico.getTempo());
            stm.setString(3, ordemServico.getNomeAtendente());
            stm.setString(4, ordemServico.getSituacao());
            stm.setString(5, ordemServico.getNomeApa());
            stm.setString(6, ordemServico.getMarcaApa());
            stm.setString(7, ordemServico.getModeloApa());
            stm.setString(8, ordemServico.getCorApa());
            stm.setString(9, ordemServico.getAssistenciaApa());
            stm.setString(10, ordemServico.getAcessoriosApa());
            stm.setString(11, ordemServico.getCondPagto());
            stm.setString(12, ordemServico.getFormaPagto());
            stm.setString(13, ordemServico.getCpfCnpjClie());
            stm.setString(14, ordemServico.getNomeOPRealizada());
            stm.setString(15, ordemServico.getHoraInicial());
            stm.setString(16, ordemServico.getHoraFinal());
            stm.setString(17, ordemServico.getDescricaoApa());
            stm.setTimestamp(18, new Timestamp(ordemServico.getDataGeracao().getTime()));
            stm.setTimestamp(19, new Timestamp(ordemServico.getUltAlteracao().getTime()));
            stm.setDate(20, new Date(ordemServico.getData().getTime()));
            stm.setDouble(21, ordemServico.getSubTotal());
            stm.setDouble(22, ordemServico.getDescontos());
            stm.setDouble(23, ordemServico.getTotal());
            stm.setDouble(24, ordemServico.getJuros());
            stm.setDouble(25, ordemServico.getParcelasMes());
            stm.setDouble(26, ordemServico.getValorPorHora());
            if (ordemServico.isOrcamentoApa()) {
                stm.setString(27, "T");
            } else {
                stm.setString(27, "F");
            }
            stm.setInt(28, ordemServico.getNumeroOs());
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao executar operação no banco de dados");
        }
    }

    public boolean updateOrdemServicoSituacao(String situacao, int numeroOS) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE TBORDEMSERVICO SET SITUACAO = ? WHERE NUMERO = ?");
            stm.setString(1, situacao);
            stm.setInt(2, numeroOS);
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro ao executar operação no banco de dados");
        }
    }

    public OrdemServico selectOrdemServico(int numero) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO WHERE NUMERO = " + numero).executeQuery();
            if (rs.next()) {
                return getOrdemServico(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura da ordem de serviço do banco de dados");
        }
    }

    public List<OrdemServico> listOrdensServicos() throws Exception {
        try {
            List<OrdemServico> lista = new ArrayList<OrdemServico>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO ORDER BY NUMERO").executeQuery();
            while (rs.next()) {
                lista.add(getOrdemServico(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de ordens de serviços do banco de dados");
        }
    }

    private OrdemServico getOrdemServico(ResultSet rs) throws Exception {
        try {
            OrdemServico ordemServico = new OrdemServico();
            ordemServico.setNumeroOs(rs.getInt("NUMERO"));
            ordemServico.setNumeroSerieApa(rs.getInt("NUMEROSERIEAPA"));
            ordemServico.setTempo(rs.getInt("TEMPO"));
            ordemServico.setNomeAtendente(rs.getString("NOMEATENDENTEAPA"));
            ordemServico.setSituacao(rs.getString("SITUACAO"));
            ordemServico.setNomeApa(rs.getString("NOMEAPA"));
            ordemServico.setMarcaApa(rs.getString("MARCAAPA"));
            ordemServico.setModeloApa(rs.getString("MODELOAPA"));
            ordemServico.setCorApa(rs.getString("CORAPA"));
            ordemServico.setAssistenciaApa(rs.getString("ASSISTENCIAAPA"));
            ordemServico.setAcessoriosApa(rs.getString("ACESSORIOS"));
            ordemServico.setCondPagto(rs.getString("CONDPAGTO"));
            ordemServico.setFormaPagto(rs.getString("FORMAPAGTO"));
            ordemServico.setCpfCnpjClie(rs.getString("CPFCNPJCLIE"));
            ordemServico.setNomeOPRealizada(rs.getString("NOMEOPREALIZADA"));
            ordemServico.setHoraInicial(rs.getString("HORAINICIAL"));
            ordemServico.setHoraFinal(rs.getString("HORAFINAL"));
            ordemServico.setDescricaoApa(rs.getString("DESCRICAOAPA"));
            ordemServico.setDataGeracao(rs.getTimestamp("DATAGERACAO"));
            ordemServico.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            ordemServico.setData(rs.getDate("DATA"));
            ordemServico.setSubTotal(rs.getDouble("SUBTOTAL"));
            ordemServico.setDescontos(rs.getDouble("DESCONTOS"));
            ordemServico.setTotal(rs.getDouble("TOTAL"));
            ordemServico.setJuros(rs.getDouble("JUROS"));
            ordemServico.setParcelasMes(rs.getDouble("PARCELASMES"));
            ordemServico.setValorPorHora(rs.getDouble("VALORHORA"));
            if (rs.getString("ORCAMENTOAPA").equals("T")) {
                ordemServico.setOrcamentoApa(true);
            }
            return ordemServico;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura da ordem de serviço do banco de dados");
        }
    }

    public boolean isOSVazio() throws Exception {
        try {
            return !con.prepareStatement("SELECT * FROM TBORDEMSERVICO").executeQuery().next();
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de ordens de serviços do banco de dados");
        }
    }

    public int getProxNumOS() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO ORDER BY NUMERO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("NUMERO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de ordens de serviços do banco de dados");
        }
    }

    public List<OrdemServico> listaOSClie(String cpfCNPJ) throws Exception {
        try {
            List<OrdemServico> lista = new ArrayList<OrdemServico>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO ORDER BY NUMERO").executeQuery();
            while (rs.next()) {
                if (rs.getString("CPFCNPJCLIE").equals(cpfCNPJ)) {
                    lista.add(getOrdemServico(rs));
                }
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de ordens de serviços do banco de dados");
        }
    }

    public List<OrdemServico> listaOSData(int mes, int ano, String nomeOPRealizada) throws Exception {
        try {
            List<OrdemServico> lista = new ArrayList<OrdemServico>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBORDEMSERVICO WHERE EXTRACT (MONTH FROM DATA) = " + mes + " AND EXTRACT (YEAR FROM DATA) = " + ano + " AND NOMEOPREALIZADA = '" + nomeOPRealizada + "' ORDER BY NUMERO").executeQuery();
            while (rs.next()) {
                lista.add(getOrdemServico(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de ordens de serviços do banco de dados");
        }
    }
}
