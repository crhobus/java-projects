package DAOFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MetodosFirebirdGenerico {

    public boolean isVazio(Connection con, String sql, String msg) throws Exception {
        try {
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de " + msg + " do banco de dados");
        }
    }

    public int getProxCod(Connection con, String sql, String msg) throws Exception {
        List<Integer> lista = new ArrayList<Integer>();
        try {
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("CODIGO"));
            }
            if (!lista.isEmpty()) {
                Collections.sort(lista);
                int y = 1;
                for (int i = 0; i < lista.get(lista.size() - 1); i++) {
                    if (lista.get(i) != y) {
                        return y;
                    }
                    y++;
                }
            }
        } catch (Exception ex) {
            throw new Exception("Erro na leitura de registros de " + msg + " do banco de dados");
        }
        return lista.size() + 1;
    }
}
