package LogTela;

import Principal.Principal;
import Principal.TableModel;

public class Ordena extends Thread {

    private TableModel tableModel;
    private VariaveisOrdTabela controleTabela;
    private Principal principal;

    public Ordena(TableModel tableModel, VariaveisOrdTabela controleTabela, Principal principal) {
        this.tableModel = tableModel;
        this.controleTabela = controleTabela;
        this.principal = principal;
    }

    @Override
    public void run() {
        while (!principal.isTerminaOrdenacao()) {
            try {
                sleep(1);
            } catch (InterruptedException ex) {
            }
            controleTabela.ordena();
            tableModel.fireTableDataChanged();
        }
    }
}
