
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculadora extends JFrame {

    JTextField texto;
    JButton on, off, limpar, igual, soma, subtracao, multiplicacao, divisao, zero, um, dois, tres, quatro, cinco, seis, sete, oito, nove;
    private String recebe, num = "", resultado, div, sum, mul, sub, memoria = "";

    Calculadora() {
        super("Calculadora");
        Container tela = getContentPane();
        tela.setLayout(null);
        zero = new JButton("0");
        um = new JButton("1");
        dois = new JButton("2");
        tres = new JButton("3");
        igual = new JButton("=");
        quatro = new JButton("4");
        cinco = new JButton("5");
        seis = new JButton("6");
        sete = new JButton("7");
        oito = new JButton("8");
        nove = new JButton("9");
        soma = new JButton("+");
        subtracao = new JButton("-");
        multiplicacao = new JButton("*");
        off = new JButton("OFF");
        on = new JButton("ON");
        limpar = new JButton("CE");
        divisao = new JButton("/");
        texto = new JTextField();
        Color cor = new Color(255, 255, 255);
        tela.add(zero);
        zero.setBounds(10, 160, 90, 30);
        zero.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "0");
                        num = num + "0";
                    }
                });
        tela.add(um);
        um.setBounds(10, 130, 41, 25);
        um.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "1");
                        num = num + "1";
                    }
                });
        tela.add(dois);
        dois.setBounds(60, 130, 41, 25);
        dois.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "2");
                        num = num + "2";
                    }
                });
        tela.add(tres);
        tres.setBounds(110, 130, 41, 25);
        tres.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "3");
                        num = num + "3";
                    }
                });
        tela.add(igual);
        igual.setBounds(110, 160, 41, 30);
        igual.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        if (sum == "1") {
                            int i = 0, y = 0, soma = 0;
                            String j;
                            recebe = num;
                            i = Integer.parseInt(recebe);
                            y = Integer.parseInt(resultado);//conversão de integer para String
                            soma = i + y;
                            j = Integer.toString(soma);
                            texto.setText(j);
                            memoria = j;
                            num = "";
                        }
                        if (sub == "1") {
                            int a = 0, b = 0, subtrai = 0;
                            String j;
                            recebe = num;
                            a = Integer.parseInt(recebe);
                            b = Integer.parseInt(resultado);
                            subtrai = b - a;
                            j = Integer.toString(subtrai);//conversão de integer para String
                            texto.setText(j);
                            memoria = j;
                            num = "";
                        }
                        if (mul == "1") {
                            int u = 0, k = 0, multi = 0;
                            String j;
                            recebe = num;
                            u = Integer.parseInt(recebe);
                            k = Integer.parseInt(resultado);
                            multi = u * k;
                            j = Integer.toString(multi);//conversão de integer para String
                            texto.setText(j);
                            memoria = j;
                            num = "";
                        }
                        if (div == "1") {
                            float c = 0, d = 0, divis = 0;
                            String j;
                            recebe = num;
                            c = Integer.parseInt(recebe);
                            d = Integer.parseInt(resultado);
                            divis = d / c;
                            j = Float.toString(divis);//conversão de float para String
                            texto.setText(j);
                            memoria = j;
                            num = "";
                        }
                        sum = "";
                        sub = "";
                        mul = "";
                        div = "";
                    }
                });
        tela.add(quatro);
        quatro.setBounds(10, 100, 41, 25);
        quatro.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "4");
                        num = num + "4";
                    }
                });
        tela.add(cinco);
        cinco.setBounds(60, 100, 41, 25);
        cinco.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "5");
                        num = num + "5";
                    }
                });
        tela.add(seis);
        seis.setBounds(110, 100, 41, 25);
        seis.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "6");
                        num = num + "6";
                    }
                });
        tela.add(sete);
        sete.setBounds(10, 70, 41, 25);
        sete.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "7");
                        num = num + "7";
                    }
                });
        tela.add(oito);
        oito.setBounds(60, 70, 41, 25);
        oito.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "8");
                        num = num + "8";
                    }
                });
        tela.add(nove);
        nove.setBounds(110, 70, 41, 25);
        nove.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(num + "9");
                        num = num + "9";
                    }
                });
        tela.add(soma);
        soma.setBounds(160, 160, 41, 30);
        soma.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        resultado = num;
                        if(memoria == ""){

                        }else{
                            resultado = memoria;
                        }
                        num = "";
                        sum = "1";
                    }
                });
        tela.add(subtracao);
        subtracao.setBounds(160, 130, 41, 25);
        subtracao.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        resultado = num;
                        if(memoria == ""){

                        }else{
                            resultado = memoria;
                        }
                        num = "";
                        sub = "1";
                    }
                });
        tela.add(multiplicacao);
        multiplicacao.setBounds(160, 100, 41, 25);
        multiplicacao.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        resultado = num;
                        if(memoria == ""){

                        }else{
                            resultado = memoria;
                        }
                        num = "";
                        mul = "1";
                    }
                });
        tela.add(divisao);
        divisao.setBounds(160, 70, 41, 25);
        divisao.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        resultado = num;
                        if(memoria == ""){

                        }else{
                            resultado = memoria;
                        }
                        num = "";
                        div = "1";
                    }
                });
        tela.add(off);
        off.setBounds(145, 40, 55, 25);
        off.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        System.exit(0);
                    }
                });
        tela.add(on);
        on.setBounds(78, 40, 55, 25);
        on.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        
                    }
                });
        tela.add(limpar);
        limpar.setBounds(10, 40, 55, 25);
        limpar.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(MouseEvent evento) {
                        texto.setText(null);
                        num = "";
                        memoria = "";
                    }
                });
        tela.add(texto);
        texto.setBounds(10, 10, 190, 20);
        texto.setEditable(false);
        texto.setBackground(cor);
        setSize(230, 240);
        setLocation(300, 300);
        setResizable(false);//Maximizado tornase falso
        setVisible(true);
    }
}
