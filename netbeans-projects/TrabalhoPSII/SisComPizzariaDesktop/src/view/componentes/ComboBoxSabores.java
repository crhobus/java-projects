package view.componentes;

import control.Servidor;
import control.funcoes.ExceptionError;
import java.util.List;
import javax.swing.JComboBox;
import model.entidades.Sabor;

public class ComboBoxSabores extends JComboBox {

    private List<Sabor> sabores;

    public ComboBoxSabores() {
        super();
    }

    public void setDados(Servidor servidor) {
        try {

            sabores = servidor.getSaborAction().getSabores();
            for (Sabor sabor : sabores) {
                this.addItem(sabor.getNmSabor());
            }

        } catch (ExceptionError ex) {
            System.out.println("Não foi possível obter os tamanhos do sistema!");
        }
    }

    public String getSabor() {
        if (this.getSelectedIndex() >= 0) {
            return sabores.get(this.getSelectedIndex()).getDsSabor();
        }
        return "";
    }

    public Sabor getTodoSabor() {
        if (this.getSelectedIndex() >= 0) {
            return sabores.get(this.getSelectedIndex());
        }
        return null;
    }

    public void limpar() {
        this.setSelectedIndex(-1);
    }
}
