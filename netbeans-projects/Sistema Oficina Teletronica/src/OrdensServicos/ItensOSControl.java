package OrdensServicos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Modelo.ItensOS;

public class ItensOSControl {

    private List<ItensOS> listaItensOS;
    private ItensOSDAO itensOSDAO;

    public ItensOSControl(Connection con) {
        listaItensOS = new ArrayList<ItensOS>();
        itensOSDAO = new ItensOSDAO(con);
    }

    public boolean insertItensOS(ItensOS itensOS) throws Exception {
        if (itensOSDAO.insertItensOS(itensOS)) {
            listaItensOS.add(itensOS);
            return true;
        }
        return false;
    }

    public int getProxCodItemOS() throws Exception {
        return itensOSDAO.getProxCodItemOS();
    }

    public int getQtdadeItensCadas() {
        return listaItensOS.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        ItensOS itensOS = listaItensOS.get(linha);
        switch (coluna) {
            case 0:
                return itensOS.getUnidade();
            case 1:
                return itensOS.getNomeItem();
            case 2:
                return itensOS.getDescricao();
            case 3:
                return itensOS.getValorUnit();
            default:
                return itensOS.getValorTotal();
        }
    }

    public void limparLista() {
        listaItensOS.clear();
    }

    public void listarItens(int numeroOS) throws Exception {
        listaItensOS = itensOSDAO.listItensOS(numeroOS);
    }

    public List<ItensOS> getListaItens(int numeroOS) throws Exception {
        return itensOSDAO.listItensOS(numeroOS);
    }

    public double getTotalItensOS() {
        double d = 0;
        for (ItensOS itensOS : listaItensOS) {
            d += itensOS.getValorTotal();
        }
        return d;
    }
}
