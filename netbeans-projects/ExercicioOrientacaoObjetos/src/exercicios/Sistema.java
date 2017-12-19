/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicios;

/**
 *
 * @author CaioRenan
 */
public class Sistema {

    public static void main(String[] args) {
        InteiroPositivo inteiroPositivo = new InteiroPositivo();
        inteiroPositivo.setValor(5);
        System.out.println(inteiroPositivo.fatorial());
        System.out.println(inteiroPositivo.valorS());

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

        VetorDeReais vetorDeReais1 = new VetorDeReais(5);
        vetorDeReais1.setValor(0, 2.0d);
        vetorDeReais1.setValor(1, 2.4d);
        vetorDeReais1.setValor(2, 1.4d);
        vetorDeReais1.setValor(3, 2.6d);
        vetorDeReais1.setValor(4, 2.3d);
        VetorDeReais vetorDeReais2 = new VetorDeReais(5);
        vetorDeReais2.setValor(0, 5.3d);
        vetorDeReais2.setValor(1, 1.6d);
        vetorDeReais2.setValor(2, 5.3d);
        vetorDeReais2.setValor(3, 2.8d);
        vetorDeReais2.setValor(4, 4.2d);
        System.out.println(vetorDeReais1.multiplica(vetorDeReais2));
        System.out.println(vetorDeReais1.maiorDiferenca());

        System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
    }
}
