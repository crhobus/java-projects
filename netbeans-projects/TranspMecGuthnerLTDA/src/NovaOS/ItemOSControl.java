package NovaOS;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAOFactory.DAOFactory;
import Modelo.ItensOS;

public class ItemOSControl {

    private List<ItensOS> listaItensOS;
    private DAOFactory daoFactory;
    private Connection con;

    public ItemOSControl(DAOFactory daoFactory, Connection con) {
        listaItensOS = new ArrayList<ItensOS>();
        this.daoFactory = daoFactory;
        this.con = con;
    }

    public boolean setItensOS(ItensOS itensOS) throws Exception {
        if (daoFactory.createItemOSDAO().setItensOS(itensOS, con)) {
            listaItensOS.add(itensOS);
            return true;
        }
        return false;
    }

    public int getProxCodItemOS() throws Exception {
        return daoFactory.createItemOSDAO().getProxCodItenOS(con);
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

    public void listaItem(int codigoOS) throws Exception {
        listaItensOS = daoFactory.createItemOSDAO().listItensOSs(codigoOS, con);
    }

    public List<ItensOS> getLista(int codigoOS) throws Exception {
        return daoFactory.createItemOSDAO().listItensOSs(codigoOS, con);
    }

    public double getTotalItensOS() {
        double d = 0;
        for (ItensOS itensOS : listaItensOS) {
            d += itensOS.getValorTotal();
        }
        return d;
    }
}
