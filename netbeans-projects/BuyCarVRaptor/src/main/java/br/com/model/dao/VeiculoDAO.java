package br.com.model.dao;

import br.com.model.beans.Veiculo;
import br.com.model.dao.util.DAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends DAOAdapter<Veiculo> {

    private final DAO dao = new DAO();

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        dao.conectar();
    }

    @Override
    public Veiculo insert(Veiculo veiculo) throws SQLException {
        veiculo.setNrSequencia(dao.getSequence("veiculo_seq.nextval"));
        PreparedStatement ps = null;
        try {
            StringBuilder insert = new StringBuilder();
            insert.append(" insert into veiculo ( ");
            insert.append("                     nr_sequencia, ");
            insert.append("                     modelo, ");
            insert.append("                     marca, ");
            insert.append("                     ano_fabricacao, ");
            insert.append("                     ano_modelo, ");
            insert.append("                     cor, ");
            insert.append("                     combustivel, ");
            insert.append("                     portas, ");
            insert.append("                     litros, ");
            insert.append("                     potencia, ");
            insert.append("                     cilindros, ");
            insert.append("                     valvulas, ");
            insert.append("                     categoria, ");
            insert.append("                     lotacao, ");
            insert.append("                     valor ");
            insert.append("            ) values ( ");
            insert.append("                     :nr_sequencia, ");
            insert.append("                     :modelo, ");
            insert.append("                     :marca, ");
            insert.append("                     :ano_fabricacao, ");
            insert.append("                     :ano_modelo, ");
            insert.append("                     :cor, ");
            insert.append("                     :combustivel, ");
            insert.append("                     :portas, ");
            insert.append("                     :litros, ");
            insert.append("                     :potencia, ");
            insert.append("                     :cilindros, ");
            insert.append("                     :valvulas, ");
            insert.append("                     :categoria, ");
            insert.append("                     :lotacao, ");
            insert.append("                     :valor) ");

            ps = dao.getPreparedStatement(insert.toString());

            ps.setLong(1, veiculo.getNrSequencia());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getMarca());
            ps.setInt(4, veiculo.getAnoFabricacao());
            ps.setInt(5, veiculo.getAnoModelo());
            ps.setString(6, veiculo.getCor());
            ps.setString(7, veiculo.getCombustivel());
            ps.setInt(8, veiculo.getPortas());
            ps.setBigDecimal(9, veiculo.getLitros());
            ps.setInt(10, veiculo.getPotencia());
            ps.setInt(11, veiculo.getCilindros());
            ps.setInt(12, veiculo.getValvulas());
            ps.setString(13, veiculo.getCategoria());
            ps.setInt(14, veiculo.getLotacao());
            ps.setBigDecimal(15, veiculo.getValor());

            ps.executeUpdate();

            return veiculo;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Veiculo update(Veiculo veiculo) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder update = new StringBuilder();
            update.append(" update veiculo ");
            update.append(" set    modelo           = :modelo, ");
            update.append("        marca            = :marca, ");
            update.append("        ano_fabricacao   = :ano_fabricacao, ");
            update.append("        ano_modelo       = :ano_modelo, ");
            update.append("        cor              = :cor, ");
            update.append("        combustivel      = :combustivel, ");
            update.append("        portas           = :portas, ");
            update.append("        litros           = :litros, ");
            update.append("        potencia         = :potencia, ");
            update.append("        cilindros        = :cilindros, ");
            update.append("        valvulas         = :valvulas, ");
            update.append("        categoria        = :categoria, ");
            update.append("        lotacao          = :lotacao, ");
            update.append("        valor            = :valor ");
            update.append(" where  nr_sequencia     = :nr_sequencia ");

            ps = dao.getPreparedStatement(update.toString());

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getMarca());
            ps.setInt(3, veiculo.getAnoFabricacao());
            ps.setInt(4, veiculo.getAnoModelo());
            ps.setString(5, veiculo.getCor());
            ps.setString(6, veiculo.getCombustivel());
            ps.setInt(7, veiculo.getPortas());
            ps.setBigDecimal(8, veiculo.getLitros());
            ps.setInt(9, veiculo.getPotencia());
            ps.setInt(10, veiculo.getCilindros());
            ps.setInt(11, veiculo.getValvulas());
            ps.setString(12, veiculo.getCategoria());
            ps.setInt(13, veiculo.getLotacao());
            ps.setBigDecimal(14, veiculo.getValor());
            ps.setLong(15, veiculo.getNrSequencia());

            ps.executeUpdate();

            return veiculo;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void delete(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder delete = new StringBuilder();
            delete.append(" delete ");
            delete.append(" from    veiculo ");
            delete.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(delete.toString());

            ps.setLong(1, nrSequencia);

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Veiculo get(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  * ");
            select.append(" from    veiculo ");
            select.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setAnoFabricacao(rs.getInt("ANO_FABRICACAO"));
                veiculo.setAnoModelo(rs.getInt("ANO_MODELO"));
                veiculo.setCor(rs.getString("COR"));
                veiculo.setCombustivel(rs.getString("COMBUSTIVEL"));
                veiculo.setPortas(rs.getInt("PORTAS"));
                veiculo.setLitros(rs.getBigDecimal("LITROS"));
                veiculo.setPotencia(rs.getInt("POTENCIA"));
                veiculo.setCilindros(rs.getInt("CILINDROS"));
                veiculo.setValvulas(rs.getInt("VALVULAS"));
                veiculo.setCategoria(rs.getString("CATEGORIA"));
                veiculo.setLotacao(rs.getInt("LOTACAO"));
                veiculo.setValor(rs.getBigDecimal("VALOR"));
                return veiculo;
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Veiculo> list(Operacao operacao) throws SQLException {
        switch (operacao) {
            case LISTAR:
                return listAll();
            default:
                return null;
        }
    }

    private List<Veiculo> listAll() throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select   * ");
            select.append(" from     veiculo ");
            select.append(" order by marca, ");
            select.append("          modelo ");

            ps = dao.getPreparedStatement(select.toString());
            ResultSet rs = ps.executeQuery();

            List<Veiculo> veiculos = new ArrayList<>();
            Veiculo veiculo;
            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                veiculo.setModelo(rs.getString("MODELO"));
                veiculo.setMarca(rs.getString("MARCA"));
                veiculo.setAnoFabricacao(rs.getInt("ANO_FABRICACAO"));
                veiculo.setAnoModelo(rs.getInt("ANO_MODELO"));
                veiculo.setCor(rs.getString("COR"));
                veiculo.setCombustivel(rs.getString("COMBUSTIVEL"));
                veiculo.setPortas(rs.getInt("PORTAS"));
                veiculo.setLitros(rs.getBigDecimal("LITROS"));
                veiculo.setPotencia(rs.getInt("POTENCIA"));
                veiculo.setCilindros(rs.getInt("CILINDROS"));
                veiculo.setValvulas(rs.getInt("VALVULAS"));
                veiculo.setCategoria(rs.getString("CATEGORIA"));
                veiculo.setLotacao(rs.getInt("LOTACAO"));
                veiculo.setValor(rs.getBigDecimal("VALOR"));
                veiculos.add(veiculo);
            }
            return veiculos;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void destroy() throws SQLException {
        dao.desconectar();
    }
}
