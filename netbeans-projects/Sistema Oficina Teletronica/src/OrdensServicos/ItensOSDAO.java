package OrdensServicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensOS;

public class ItensOSDAO {

    private Connection con;

    public ItensOSDAO(Connection con) {
        this.con = con;
    }

    public boolean insertItensOS(ItensOS itensOS) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBITENSOS (CODIGO, NUMEROOS, "
                    + "UNIDADE, NOMEITEM, DESCRICAO, VALORUNIT, VALORTOTAL, DATA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, itensOS.getCodigo());
            stm.setInt(2, itensOS.getCodOrdemServico());
            stm.setInt(3, itensOS.getUnidade());
            stm.setString(4, itensOS.getNomeItem());
            stm.setString(5, itensOS.getDescricao());
            stm.setDouble(6, itensOS.getValorUnit());
            stm.setDouble(7, itensOS.getValorTotal());
            stm.setTimestamp(8, new Timestamp(itensOS.getData().getTime()));
            stm.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na gravação de itens da ordem de serviço no banco de dados");
        }
    }

    public List<ItensOS> listItensOS(int numeroOS) throws Exception {
        try {
            List<ItensOS> lista = new ArrayList<ItensOS>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBITENSOS WHERE NUMEROOS = " + numeroOS + " ORDER BY CODIGO").executeQuery();
            while (rs.next()) {
                ItensOS itensOS = new ItensOS();
                itensOS.setCodigo(rs.getInt("CODIGO"));
                itensOS.setCodOrdemServico(rs.getInt("NUMEROOS"));
                itensOS.setUnidade(rs.getInt("UNIDADE"));
                itensOS.setNomeItem(rs.getString("NOMEITEM"));
                itensOS.setDescricao(rs.getString("DESCRICAO"));
                itensOS.setValorUnit(rs.getDouble("VALORUNIT"));
                itensOS.setValorTotal(rs.getDouble("VALORTOTAL"));
                itensOS.setData(rs.getTimestamp("DATA"));
                lista.add(itensOS);
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de itens da ordem de serviço do banco de dados");
        }
    }

    public int getProxCodItemOS() throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBITENSOS ORDER BY CODIGO").executeQuery();
            int cod = 1;
            while (rs.next()) {
                if (cod != rs.getInt("CODIGO")) {
                    return cod;
                }
                cod++;
            }
            return cod;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de itens da ordem de serviço do banco de dados");
        }
    }
}
