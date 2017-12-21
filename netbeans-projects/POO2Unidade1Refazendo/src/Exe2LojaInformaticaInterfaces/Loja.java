package Exe2LojaInformaticaInterfaces;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    private List<LojaDespesa> listaDespesa = new ArrayList<LojaDespesa>();
    private List<LojaReceita> listaReceita = new ArrayList<LojaReceita>();

    public void addReceita(LojaReceita lojaReceita) {
        listaReceita.add(lojaReceita);
    }

    public void addDespesa(LojaDespesa lojaDespesa) {
        listaDespesa.add(lojaDespesa);
    }

    public double getTotalRendimentos() {
        double total = 0;
        for (LojaReceita lista : listaReceita) {
            total += lista.getValorReceita();
        }
        return total;
    }

    public double getTotalDespesas() {
        double total = 0;
        for (LojaDespesa lista : listaDespesa) {
            total += lista.getValorDespesa();
        }
        return total;
    }

    public double getlucroLoja() {
        return getTotalRendimentos() - getTotalDespesas();
    }

    public void mostrar() {
        for (LojaReceita lista : listaReceita) {
            System.out.printf("Receita - %s\t\tvalor: - %.2f\n", lista.getNomeReceita(), lista.getValorReceita());
        }
        for (LojaDespesa lista : listaDespesa) {
            System.out.printf("Despesa - %s\t\tvalor: - %.2f\n", lista.getNomeDespesa(), lista.getValorDespesa());
        }
        System.out.println();
    }
}
