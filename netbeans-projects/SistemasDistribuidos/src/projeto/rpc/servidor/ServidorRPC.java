package projeto.rpc.servidor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import projeto.rpc.SistemaBancarioRPC_svcb;
import projeto.rpc.params;

public class ServidorRPC extends SistemaBancarioRPC_svcb {

    private Connection con;

    public ServidorRPC() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sysrpc", "key50900");
        } catch (Exception ex) {
            System.out.println("Não foi possível conectar-se com o banco de dados");
        }
    }

    @Override
    public String cadastrarCliente(String in_arg) {
        String[] cliente = in_arg.split(";");
        PreparedStatement stm = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            stm = con.prepareStatement(
                    "             insert into cliente (             "
                    + "                                 cd_cliente, "
                    + "                                 nm_cliente, "
                    + "                                 cpf,        "
                    + "                                 rg,         "
                    + "                                 endereco,   "
                    + "                                 bairro,     "
                    + "                                 cep,        "
                    + "                                 cidade,     "
                    + "                                 estado,     "
                    + "                                 senha,      "
                    + "                                 sexo,       "
                    + "                                 numero,     "
                    + "                                 nr_conta,   "
                    + "                                 dt_nasc,    "
                    + "                                 saldo)      "
                    + "           values    (                       "
                    + "                                :cd_cliente, "
                    + "                                :nm_cliente, "
                    + "                                :cpf,        "
                    + "                                :rg,         "
                    + "                                :endereco,   "
                    + "                                :bairro,     "
                    + "                                :cep,        "
                    + "                                :cidade,     "
                    + "                                :estado,     "
                    + "                                :senha,      "
                    + "                                :sexo,       "
                    + "                                :numero,     "
                    + "                                :nr_conta,   "
                    + "                                :dt_nasc,    "
                    + "                                :saldo)      ");
            int cdCliente = getProxCod(
                    "                 select  max(cd_cliente) "
                    + "               from    cliente         ");
            stm.setInt(1, cdCliente);
            stm.setString(2, cliente[0]);
            stm.setString(3, cliente[1]);
            stm.setString(4, cliente[2]);
            stm.setString(5, cliente[3]);
            stm.setString(6, cliente[4]);
            stm.setString(7, cliente[5]);
            stm.setString(8, cliente[6]);
            stm.setString(9, cliente[7]);
            stm.setString(10, cliente[8]);
            stm.setString(11, cliente[9]);
            stm.setInt(12, Integer.parseInt(cliente[10]));
            stm.setLong(13, Long.parseLong(cliente[11]));
            stm.setDate(14, new Date(format.parse(cliente[12]).getTime()));
            stm.setDouble(15, Double.parseDouble(cliente[13]));
            stm.execute();
            return "cliente cadastrado com sucesso!\nNúmero do cliente: " + cdCliente;
        } catch (Exception ex) {
            return "Erro ao cadastrar o cliente no sistema, verifique se todos os parâmetros estão de acordo, verifique sua conexão";
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int getProxCod(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException ex) {
            throw new SQLException("");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public String getCliente(String in_arg) {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(
                    "                 select    *                       "
                    + "               from      cliente                 "
                    + "               where     nr_conta    = " + in_arg).executeQuery();
            if (rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String cliente = rs.getInt("cd_cliente") + ";";
                cliente += rs.getString("nm_cliente") + ";";
                cliente += rs.getString("cpf") + ";";
                cliente += rs.getString("rg") + ";";
                cliente += rs.getString("endereco") + ";";
                cliente += rs.getString("bairro") + ";";
                cliente += rs.getString("cep") + ";";
                cliente += rs.getString("cidade") + ";";
                cliente += rs.getString("estado") + ";";
                cliente += rs.getString("senha") + ";";
                cliente += rs.getString("sexo") + ";";
                cliente += rs.getInt("numero") + ";";
                cliente += rs.getLong("nr_conta") + ";";
                cliente += format.format(rs.getDate("dt_nasc")) + ";";
                cliente += rs.getString("saldo");
                return cliente;
            }
            return "";
        } catch (SQLException ex) {
            return "Erro na leitura do cliente no sistema, verifique sua conexão";
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public String extrato(params in_arg) {
        ResultSet rs = null;
        try {
            String str = getCliente(in_arg.param1);
            if ("".equals(str)) {
                return "Esta conta não existe";
            } else if (str.startsWith("Erro")) {
                return str;
            }
            String[] cliente = str.split(";");
            if (in_arg.param2.equalsIgnoreCase(cliente[9])) {
                rs = con.prepareStatement(
                        "               select      valor,                                                             "
                        + "                         tp_operacao,                                                       "
                        + "                         dt_operacao                                                        "
                        + "             from        movimentacao                                                       "
                        + "             where       cd_cliente  =  " + cliente[0]
                        + "             and         dt_operacao                                                        "
                        + "             between     to_date('" + in_arg.param3 + " 00:00:00', 'dd/mm/yyyy hh24:mi:ss') "
                        + "             and         to_date('" + in_arg.param4 + " 23:59:59', 'dd/mm/yyyy hh24:mi:ss') "
                        + "             order by    dt_operacao").executeQuery();
                SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
                String extrato = "Extrato bancário referente a data " + in_arg.param3 + " até " + in_arg.param4 + "\n";
                boolean temExtrato = false;
                while (rs.next()) {
                    temExtrato = true;
                    extrato += rs.getString("tp_operacao") + " -> ";
                    extrato += NumberFormat.getCurrencyInstance().format(rs.getDouble("valor")) + " em ";
                    extrato += formatData.format(rs.getDate("dt_operacao")) + " - ";
                    extrato += formatHora.format(rs.getTimestamp("dt_operacao")) + "\n";
                }
                if (temExtrato) {
                    return extrato;
                } else {
                    return "";
                }
            }
            return "Senha inválida";
        } catch (SQLException ex) {
            return "Erro ao gerar o relatório com o extrato, verifique sua conexão";
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public String addHistoricoMovimentacao(String in_arg) {
        String[] cliente = in_arg.split(";");
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call atualizar_historico_cliente(:cd_cliente_p, :valor_p, :tp_operacao_p)}");
            cs.setInt(1, Integer.parseInt(cliente[0]));
            cs.setDouble(2, Double.parseDouble(cliente[1]));
            cs.setString(3, cliente[2]);
            cs.execute();
            return "";
        } catch (Exception ex) {
            return "Erro ao efetuar a movimentação na conta, verifique se todos os parâmetros estão de acordo, verifique sua conexão";
        } finally {
            try {
                cs.close();
            } catch (SQLException ex) {
            }
        }
    }
}
