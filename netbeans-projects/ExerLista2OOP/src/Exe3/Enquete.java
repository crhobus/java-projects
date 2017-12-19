package Exe3;

public class Enquete {

    private final String opcoes = "1 - Excelente\n2 - Ótimo\n3 - Bom\n4 - regular\n5 - ruim";
    private int excelente, otimo, bom, regular, ruim, PriIdade, SegIdade, TerIdade, qtdade;

    public void optar(int opcao, int idade) {
        qtdade++;
        switch (opcao) {
            case 1:
                excelente++;
                break;
            case 2:
                otimo++;
                break;
            case 3:
                bom++;
                break;
            case 4:
                regular++;
                break;
            case 5:
                ruim++;
                break;
        }
        if (idade >= 5 && idade <= 12) {
            PriIdade++;
        } else {
            if (idade >= 13 && idade <= 17) {
                SegIdade++;
            } else {
                if (idade >= 18 && idade <= 50) {
                    TerIdade++;
                }
            }
        }
    }

    public String getOpcoes() {
        return opcoes;
    }

    public boolean isVazio() {
        return qtdade == 0;
    }

    public String getExcelente() {
        return ((excelente * 100) / qtdade) + "% de excelente";
    }

    public String getOtimo() {
        return ((otimo * 100) / qtdade) + "% de ótimo";
    }

    public String getBom() {
        return ((bom * 100) / qtdade) + "% de bom";
    }

    public String getRegular() {
        return ((regular * 100) / qtdade) + "% de regular";
    }

    public String getRuim() {
        return ((ruim * 100) / qtdade) + "% de ruim";
    }

    public String getPriFaixaEtaria() {
        return "crianças de 5 á 12 anos = " + PriIdade;
    }

    public String getSegFaixaEtaria() {
        return "adolescente: 13 á 17 anos = " + SegIdade;
    }

    public String getTerFaixaEtaria() {
        return "adulto: 18 á 50 anos = " + TerIdade;
    }
}
