package NovaOSDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.MetodosFirebirdGenerico;
import Modelo.ItensOS;

public class ItemOSFirebird extends MetodosFirebirdGenerico implements ItemOSDAO {

    @Override
    public int getProxCodItenOS(Connection con) throws Exception {
        return getProxCod(con, "SELECT * FROM TBITENSOS", "itens da ordem de serviço");
    }

    @Override
    public List<ItensOS> listItensOSs(int codigoOrdemServico, Connection con) throws Exception {
        try {
            List<ItensOS> lista = new ArrayList<ItensOS>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBITENSOS").executeQuery();
            while (rs.next()) {
                if (rs.getInt("CODORDEMSERVICO") == codigoOrdemServico) {
                    ItensOS itensOS = new ItensOS();
                    itensOS.setCodigo(rs.getInt("CODIGO"));
                    itensOS.setCodOrdemServico(rs.getInt("CODORDEMSERVICO"));
                    itensOS.setUnidade(rs.getInt("UNIDADE"));
                    itensOS.setNomeItem(rs.getString("NOMEITEM"));
                    itensOS.setDescricao(rs.getString("DESCRICAO"));
                    itensOS.setValorUnit(rs.getDouble("VALORUNIT"));
                    itensOS.setValorTotal(rs.getDouble("VALORTOTAL"));
                    itensOS.setData(rs.getTimestamp("DATA"));
                    lista.add(itensOS);
                }
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de itens da ordem de serviço do banco de dados");
        }
    }

    @Override
    public boolean setItensOS(ItensOS itensOS, Connection con) throws Exception {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO TBITENSOS (CODIGO, CODORDEMSERVICO, "
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
            throw new Exception("Erro na gravação de registro de itens da ordem de serviço no banco de dados");
        }
    }
}
