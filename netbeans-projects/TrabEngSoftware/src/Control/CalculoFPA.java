package Control;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Dados;

public class CalculoFPA {

    private List<Dados> ALI, AIE, CE, SE, EE;
    private int totalALI, totalAIE, totalCE, totalSE, totalEE, totalNI;
    private auxCalculoFPA fpa;

    public CalculoFPA() {
        ALI = new ArrayList<Dados>();
        AIE = new ArrayList<Dados>();
        CE = new ArrayList<Dados>();
        SE = new ArrayList<Dados>();
        EE = new ArrayList<Dados>();
        fpa = new auxCalculoFPA();
    }

    public String addALI(String nome, int tipoDados, int tipoReg) {
        Dados dados = new Dados(nome, tipoDados, tipoReg);
        dados.setNivel(fpa.getAliString(tipoDados, tipoReg));
        int valor = fpa.getAliInt(tipoDados, tipoReg);
        totalALI += valor;
        dados.setValor(valor);
        ALI.add(dados);
        return nome + " (" + tipoDados + ", " + tipoReg + ")" + " - " + dados.getNivel();
    }

    public String getALI() {
        return getCalculo(ALI, 7, 10, 15);
    }

    public void zerarALI() {
        ALI.clear();
        totalALI = 0;
    }

    public String addAIE(String nome, int tipoDados, int tipoReg) {
        Dados dados = new Dados(nome, tipoDados, tipoReg);
        dados.setNivel(fpa.getAieString(tipoDados, tipoReg));
        int valor = fpa.getAieInt(tipoDados, tipoReg);
        totalAIE += valor;
        dados.setValor(valor);
        AIE.add(dados);
        return nome + " (" + tipoDados + ", " + tipoReg + ")" + " - " + dados.getNivel();
    }

    public String getAIE() {
        return getCalculo(AIE, 5, 7, 10);
    }

    public void zeraAIE() {
        AIE.clear();
        totalAIE = 0;
    }

    public String addEE(String nome, int tipoDados, int arqRef) {
        Dados dados = new Dados(nome, tipoDados, arqRef);
        dados.setNivel(fpa.getEeString(tipoDados, arqRef));
        int valor = fpa.getEeInt(tipoDados, arqRef);
        totalEE += valor;
        dados.setValor(valor);
        EE.add(dados);
        return nome + " (" + tipoDados + ", " + arqRef + ")" + " - " + dados.getNivel();
    }

    public String getEE() {
        return getCalculo(EE, 3, 4, 6);
    }

    public void zeraEE() {
        EE.clear();
        totalEE = 0;
    }

    public String addCE(String nome, int tipoDados, int arqRef) {
        Dados dados = new Dados(nome, tipoDados, arqRef);
        dados.setNivel(fpa.getCeString(tipoDados, arqRef));
        int valor = fpa.getCeInt(tipoDados, arqRef);
        totalCE += valor;
        dados.setValor(valor);
        CE.add(dados);
        return nome + " (" + tipoDados + ", " + arqRef + ")" + " - " + dados.getNivel();
    }

    public String getCE() {
        return getCalculo(CE, 3, 4, 6);
    }

    public void zeraCE() {
        CE.clear();
        totalCE = 0;
    }

    public String addSE(String nome, int tipoDados, int arqRef) {
        Dados dados = new Dados(nome, tipoDados, arqRef);
        dados.setNivel(fpa.getSeString(tipoDados, arqRef));
        int valor = fpa.getSeInt(tipoDados, arqRef);
        totalSE += valor;
        dados.setValor(valor);
        SE.add(dados);
        return nome + " (" + tipoDados + ", " + arqRef + ")" + " - " + dados.getNivel();
    }

    public String getSE() {
        return getCalculo(SE, 4, 5, 7);
    }

    public void zeraSE() {
        SE.clear();
        totalSE = 0;
    }

    private String getCalculo(List<Dados> lista, int baixa, int media, int alta) {
        int niveis[] = new int[3];
        int result;
        for (Dados dados : lista) {
            result = dados.getValor();
            if (result == baixa) {
                niveis[0]++;
            } else {
                if (result == media) {
                    niveis[1]++;
                } else {
                    niveis[2]++;
                }
            }
        }
        String str = "";
        result = 0;
        int parcial;
        if (niveis[0] > 0) {
            parcial = niveis[0] * baixa;
            str += niveis[0] + " (Baixa) * " + baixa + " = " + parcial + "\n";
            result += parcial;
        }
        if (niveis[1] > 0) {
            parcial = niveis[1] * media;
            str += niveis[1] + " (Média) * " + media + " = " + parcial + "\n";
            result += parcial;
        }
        if (niveis[2] > 0) {
            parcial = niveis[2] * alta;
            str += niveis[2] + " (Alta) * " + alta + " = " + parcial + "\n";
            result += parcial;
        }
        str += "----------------------\nTotal: " + result;
        return str;
    }

    public String addNI(int niveis[]) {
        for (int i = 0; i < niveis.length; i++) {
            totalNI += niveis[i] * i;
        }
        String str = "";
        if (niveis[0] != 0) {
            str += "\n" + niveis[0] + " (Sem) * 0 = 0";
        }
        if (niveis[1] != 0) {
            str += "\n" + niveis[1] + " (Baixa/Mínima) * 1 = " + (niveis[1] * 1);
        }
        if (niveis[2] != 0) {
            str += "\n" + niveis[2] + " (Moderada) * 2 = " + (niveis[2] * 2);
        }
        if (niveis[3] != 0) {
            str += "\n" + niveis[3] + " (Média) * 3 = " + (niveis[3] * 3);
        }
        if (niveis[4] != 0) {
            str += "\n" + niveis[4] + " (Significativa) * 4 = " + (niveis[4] * 4);
        }
        if (niveis[5] != 0) {
            str += "\n" + niveis[5] + " (Grande/Alta) * 5 = " + (niveis[5] * 5);
        }
        str += "\n----------------------\nTotal: " + totalNI;
        return str;
    }

    public void zeraNI() {
        totalNI = 0;
    }

    public double getPFB() {
        return totalALI + totalAIE + totalEE + totalCE + totalSE;
    }

    public double getFA() {
        return (totalNI * 0.01) + 0.65;
    }

    public double getPFA() {
        return getPFB() * getFA();
    }

    public String getResultadoFinal(double horas, NumberFormat numberFormat) {
        return "PFA * " + numberFormat.format(horas) + ": " + numberFormat.format((getPFA() * horas)) + " horas";
    }

    public List<Dados> getListaDado(String tipoDado) {
        if (tipoDado.equals("ALI")) {
            return this.ALI;
        }
        if (tipoDado.equals("AIE")) {
            return this.AIE;
        }
        if (tipoDado.equals("CE")) {
            return this.CE;
        }
        if (tipoDado.equals("SE")) {
            return this.SE;
        }
        if (tipoDado.equals("EE")) {
            return this.EE;
        }
        return null;
    }
}
