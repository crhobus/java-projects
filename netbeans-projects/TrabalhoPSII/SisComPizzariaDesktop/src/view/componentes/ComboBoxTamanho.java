package view.componentes;

import control.Servidor;
import control.funcoes.ExceptionError;
import java.util.List;
import javax.swing.JComboBox;
import model.entidades.Sabor;
import model.entidades.Tamanho;

public class ComboBoxTamanho extends JComboBox {

    private List<Tamanho> tamanhos;

    public ComboBoxTamanho() {
        super();
    }

    public void setDados(Servidor servidor) {
        try {

            tamanhos = servidor.getTamanhoAction().getTamanhos();
            for (Tamanho tam : tamanhos) {
                this.addItem(tam.getDsTamanho());
            }

        } catch (ExceptionError ex) {
            System.out.println("Não foi possível obter os tamanhos do sistema!");
        }
    }

    public Tamanho getTamanho() {
        System.out.println(this.getSelectedIndex());
        if (this.getSelectedIndex() >= 0) {
            return tamanhos.get(this.getSelectedIndex());
        }
        return null;
    }
    
    public void limpar(){
        this.setSelectedIndex(-1);
    }
}
