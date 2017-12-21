package Retangulos;

import java.util.*;
import javax.swing.*;

public class Sistema {

    public static void main(String[] args) {
        try {
            int limiteInf = 0, limiteSup = 0;
            boolean erro;
            do {
                String txtLimiteInf = JOptionPane.showInputDialog(null, "Entre com o limite inferior");
                if (txtLimiteInf == null) {
                    erro = false;
                } else {
                    try {
                        limiteInf = Integer.parseInt(txtLimiteInf);
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um valor válido");
                        erro = true;
                    }
                }
            } while (erro == true);

            do {
                String txtLimiteSup = JOptionPane.showInputDialog(null, "Entre com o limite superior");
                if (txtLimiteSup == null) {
                    erro = false;
                } else {
                    try {
                        limiteSup = Integer.parseInt(txtLimiteSup);
                        erro = false;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Entre com um valor válido");
                        erro = true;
                    }
                }
            } while (erro == true);

            Arquivo arquivo = new Arquivo();
            List<AreaPosicaoRet> listaArea = new ArrayList<AreaPosicaoRet>();
            List<Retangulo> listaRetan = new ArrayList<Retangulo>();
            listaArea = arquivo.getAreaPosicao(limiteInf, limiteSup);
            Collections.sort(listaArea);
            for (AreaPosicaoRet areaPosicaoRet : listaArea) {
                listaRetan.add(arquivo.lerRetangulo(areaPosicaoRet.getPosicao()));

            }
            for (Retangulo ret : listaRetan) {
                System.out.println("Nome: " + ret.getNome());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
