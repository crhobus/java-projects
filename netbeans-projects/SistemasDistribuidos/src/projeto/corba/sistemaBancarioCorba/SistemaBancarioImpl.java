package projeto.corba.sistemaBancarioCorba;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import org.omg.CORBA.StringHolder;
import projeto.rmi.rmi.RMIInterface;

public class SistemaBancarioImpl extends InterfaceCorbaPOA {

    private Connection con;
    private RMIInterface rmiInterface;

    public SistemaBancarioImpl(Connection con, RMIInterface rmiInterface) {
        this.con = con;
        this.rmiInterface = rmiInterface;
    }

    @Override
    public boolean emprestimo(int nrConta, String senha, double valor, StringHolder retorno) {
        CallableStatement cs = null;
        try {
            cs = con.prepareCall("{call efetuar_emprestimo(:nr_conta_p, :valor_p, :retorno_p)}");
            cs.registerOutParameter("retorno_p", 2);//2 - OracleTypes.NUMBER;
            cs.setInt(1, nrConta);
            cs.setDouble(2, valor);
            cs.setDouble(3, 0);
            cs.executeQuery();
            double v = cs.getDouble("retorno_p");
            if (v == -1) {
                retorno.value = "Valor excede o máximo disponível para emprestimo";
                return false;
            }
            if (v == -2) {
                retorno.value = "Valor não disponível no momento";
                return false;
            }
            try {
                rmiInterface.deposito(nrConta, valor);
                retorno.value = "Emprestimo realizado com sucesso";
                return true;
            } catch (RemoteException ex) {
                retorno.value = ex.getMessage() + "\n";
                return false;
            }
        } catch (Exception ex) {
            retorno.value = "Erro na leitura dos dados do banco no sistema, verifique sua conexão";
            return false;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public boolean pagamento(int nrConta, String senha, int nrTitulo, double valor, StringHolder retorno) {
        try {
            rmiInterface.saque(nrConta, senha, valor);
        } catch (RemoteException ex) {
            retorno.value = ex.getMessage() + "\n";
            return false;
        }
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            rs = con.prepareStatement(
                    "                 select    ib.saldo saldo           "
                    + "               from      mc_internet_banking ib,  "
                    + "                         limite_conta lc          "
                    + "               where     lc.nr_conta = " + nrConta
                    + "               and       lc.cd_banco = ib.cd_banco").executeQuery();
            if (rs.next()) {
                double v = rs.getDouble("SALDO");
                v += valor;
                stm = con.prepareStatement(
                        "             update    mc_internet_banking  "
                        + "           set       saldo    = :saldo     ");
                stm.setDouble(1, v);
                stm.execute();
                retorno.value = "Pagamento realizado com sucesso";
                return true;
            }
            retorno.value = "Esta conta não existe";
            return false;
        } catch (Exception ex) {
            retorno.value = "Erro na leitura dos dados do banco no sistema, verifique sua conexão";
            return false;
        } finally {
            try {
                rs.close();
                stm.close();
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public boolean visualizarLimite(int nrConta, String senha, StringHolder retorno) {
        ResultSet rs = null;
        try {
            rs = con.prepareStatement(
                    "                 select    saldo                   "
                    + "                         limite                  "
                    + "               from      limite_conta            "
                    + "               where     nr_conta    = " + nrConta).executeQuery();
            if (rs.next()) {
                String ret = "Limite banco MC Internet banking\n";
                ret += "Saldo disponível: " + NumberFormat.getCurrencyInstance().format(rs.getDouble("SALDO")) + "\n";
                ret += "Limite disponível para emprestimo: " + NumberFormat.getCurrencyInstance().format(rs.getDouble("LIMITE")) + "\n";
                retorno.value = ret;
                return true;
            }
            retorno.value = "Esta conta não existe";
            return false;
        } catch (SQLException ex) {
            retorno.value = "Erro na leitura dos dados do banco no sistema, verifique sua conexão";
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
    }
}
