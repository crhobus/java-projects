package br.bancoDados;

import java.sql.PreparedStatement;

/**
 *
 * @author Caio
 */
public class CalcDB extends Conexao {

    public void insert(double i, double y, String operacao, double result) {
        PreparedStatement stm = null;
        try {
            iniciarConexaoBD();
            stm = getConexao().prepareStatement("INSERT INTO RESULTADO (NR_SEQUENCIA, VL_I, VL_Y, DS_OPERACAO, VL_RESULT) VALUES (RESULTADO_SEQ.NEXTVAL, ?, ?, ?, ?)");
            stm.setDouble(1, i);
            stm.setDouble(2, y);
            stm.setString(3, operacao);
            stm.setDouble(4, result);
            stm.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                fecharConexaoBD();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
