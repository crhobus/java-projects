package VeiculosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.MetodosFirebirdGenerico;
import Modelo.Veiculo;

public class VeiculoFirebird extends MetodosFirebirdGenerico implements VeiculosDAO {

    @Override
    public boolean isVeiVazio(Connection con) throws Exception {
        return isVazio(con, "SELECT * FROM TBVEICULO", "veículos");
    }

    @Override
    public int getProxCodVei(Connection con) throws Exception {
        return getProxCod(con, "SELECT * FROM TBVEICULO", "veículos");
    }

    @Override
    public Veiculo getVeiculo(String placa, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVEICULO WHERE PLACA = '" + placa + "'").executeQuery();
            if (rs.next()) {
                return leituraVeiculo(rs);
            }
            return null;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de cliente do banco de dados");
        }
    }

    private Veiculo leituraVeiculo(ResultSet rs) throws Exception {
        try {
            Veiculo veiculo = new Veiculo();
            veiculo.setCodigo(rs.getInt("CODIGO"));
            veiculo.setNumeroMotor(rs.getInt("NUMEROMOTOR"));
            veiculo.setPortas(rs.getInt("PORTAS"));
            veiculo.setAno(rs.getInt("ANO"));
            veiculo.setModelo(rs.getInt("MODELO"));
            veiculo.setCilindros(rs.getInt("CILINDROS"));
            veiculo.setCavalos(rs.getInt("CAVALOS"));
            veiculo.setLotacao(rs.getInt("LOTACAO"));
            veiculo.setVavulas(rs.getInt("VAVULAS"));
            veiculo.setNome(rs.getString("NOME"));
            veiculo.setMarca(rs.getString("MARCA"));
            veiculo.setCor(rs.getString("COR"));
            veiculo.setRodas(rs.getString("RODAS"));
            veiculo.setPlaca(rs.getString("PLACA"));
            veiculo.setChassi(rs.getString("CHASSI"));
            veiculo.setRenavam(rs.getString("RENAVAM"));
            veiculo.setCombustivel(rs.getString("COMBUSTIVEL"));
            veiculo.setTipo(rs.getString("TIPO"));
            veiculo.setCpfCNPJCliente(rs.getString("CPFCNPJCLIE"));
            veiculo.setDescricao(rs.getString("DESCRICAO"));
            veiculo.setDataCadastro(rs.getTimestamp("DATACADASTRO"));
            veiculo.setUltAlteracao(rs.getTimestamp("ULTALTERACAO"));
            veiculo.setPotencia(rs.getDouble("POTENCIA"));
            return veiculo;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de um registro de veículo do banco de dados");
        }
    }

    @Override
    public List<Veiculo> listVeiculos(Connection con) throws Exception {
        try {
            List<Veiculo> lista = new ArrayList<Veiculo>();
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVEICULO").executeQuery();
            while (rs.next()) {
                lista.add(leituraVeiculo(rs));
            }
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de veículos do banco de dados");
        }
    }

    @Override
    public boolean removeVeiculo(String placa, Connection con) throws Exception {
        try {
            if (getVeiculo(placa, con) != null) {
                PreparedStatement stm = con.prepareStatement("DELETE FROM TBVEICULO WHERE PLACA = ?");
                stm.setString(1, placa);
                stm.execute();
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir um registro de veículo do banco de dados");
        }
    }

    @Override
    public boolean setVeiculo(Veiculo veiculo, Connection con) throws Exception {
        try {
            ResultSet rs = con.prepareStatement("SELECT * FROM TBVEICULO WHERE CODIGO = " + veiculo.getCodigo()).executeQuery();
            int cod = 0;
            if (rs.next()) {
                cod = rs.getInt("CODIGO");
            }
            PreparedStatement stm;
            if (cod != 0) {
                stm = con.prepareStatement("UPDATE TBVEICULO SET NUMEROMOTOR = ?, PORTAS = ?, ANO = ?, MODELO = ?, "
                        + "CILINDROS = ?, CAVALOS = ?, LOTACAO = ?, VAVULAS = ?, NOME = ?, MARCA = ?, "
                        + "COR = ?, RODAS = ?, PLACA = ?, CHASSI = ?, RENAVAM = ?, COMBUSTIVEL = ?, "
                        + "TIPO = ?, CPFCNPJCLIE = ?, DESCRICAO = ?, DATACADASTRO = ?, ULTALTERACAO = ?, POTENCIA = ?"
                        + " WHERE CODIGO = ?");
                stm.setInt(1, veiculo.getNumeroMotor());
                stm.setInt(2, veiculo.getPortas());
                stm.setInt(3, veiculo.getAno());
                stm.setInt(4, veiculo.getModelo());
                stm.setInt(5, veiculo.getCilindros());
                stm.setInt(6, veiculo.getCavalos());
                stm.setInt(7, veiculo.getLotacao());
                stm.setInt(8, veiculo.getVavulas());
                stm.setString(9, veiculo.getNome());
                stm.setString(10, veiculo.getMarca());
                stm.setString(11, veiculo.getCor());
                stm.setString(12, veiculo.getRodas());
                stm.setString(13, veiculo.getPlaca());
                stm.setString(14, veiculo.getChassi());
                stm.setString(15, veiculo.getRenavam());
                stm.setString(16, veiculo.getCombustivel());
                stm.setString(17, veiculo.getTipo());
                stm.setString(18, veiculo.getCpfCNPJCliente());
                stm.setString(19, veiculo.getDescricao());
                stm.setTimestamp(20, new Timestamp(veiculo.getDataCadastro().getTime()));
                stm.setTimestamp(21, new Timestamp(veiculo.getUltAlteracao().getTime()));
                stm.setDouble(22, veiculo.getPotencia());
                stm.setInt(23, veiculo.getCodigo());
                stm.execute();
                return true;
            } else {
                stm = con.prepareStatement("INSERT INTO TBVEICULO (CODIGO, NUMEROMOTOR, PORTAS, ANO, MODELO, CILINDROS, CAVALOS, LOTACAO, "
                        + "VAVULAS, NOME, MARCA, COR, RODAS, PLACA, CHASSI, RENAVAM, COMBUSTIVEL, "
                        + "TIPO, CPFCNPJCLIE, DESCRICAO, DATACADASTRO, ULTALTERACAO, POTENCIA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm.setInt(1, veiculo.getCodigo());
                stm.setInt(2, veiculo.getNumeroMotor());
                stm.setInt(3, veiculo.getPortas());
                stm.setInt(4, veiculo.getAno());
                stm.setInt(5, veiculo.getModelo());
                stm.setInt(6, veiculo.getCilindros());
                stm.setInt(7, veiculo.getCavalos());
                stm.setInt(8, veiculo.getLotacao());
                stm.setInt(9, veiculo.getVavulas());
                stm.setString(10, veiculo.getNome());
                stm.setString(11, veiculo.getMarca());
                stm.setString(12, veiculo.getCor());
                stm.setString(13, veiculo.getRodas());
                stm.setString(14, veiculo.getPlaca());
                stm.setString(15, veiculo.getChassi());
                stm.setString(16, veiculo.getRenavam());
                stm.setString(17, veiculo.getCombustivel());
                stm.setString(18, veiculo.getTipo());
                stm.setString(19, veiculo.getCpfCNPJCliente());
                stm.setString(20, veiculo.getDescricao());
                stm.setTimestamp(21, new Timestamp(veiculo.getDataCadastro().getTime()));
                stm.setTimestamp(22, new Timestamp(veiculo.getUltAlteracao().getTime()));
                stm.setDouble(23, veiculo.getPotencia());
                stm.execute();
                return true;
            }
        } catch (Exception ex) {
            throw new Exception("Erro na gravação de registro de veículo no banco de dados");
        }
    }
}
