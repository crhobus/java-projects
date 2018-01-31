package Control;

public class auxCalculoFPA {

    public int ALIeAIE(int tipoDado, int tipoReg) {
        switch (tipoReg) {
            case 1:
                if (tipoDado <= 50) {
                    return 1;
                } else {
                    return 2;
                }
            case 2:
            case 3:
            case 4:
            case 5:
                if (tipoDado < 20) {
                    return 1;
                } else {
                    if (tipoDado >= 20 && tipoDado <= 50) {
                        return 2;
                    } else {
                        return 3;
                    }
                }
            default:
                if (tipoDado < 20) {
                    return 2;
                } else {
                    return 3;
                }
        }
    }

    public int getAliInt(int tipoDado, int tipoReg) {
        int valorTab = ALIeAIE(tipoDado, tipoReg);
        switch (valorTab) {
            case 1:
                return 7;
            case 2:
                return 10;
            default:
                return 15;
        }
    }

    public String getAliString(int tipoDado, int tipoReg) {
        int valorTab = ALIeAIE(tipoDado, tipoReg);
        switch (valorTab) {
            case 1:
                return "Baixa";
            case 2:
                return "Média";
            default:
                return "Alta";
        }
    }

    public int getAieInt(int tipoDado, int tipoReg) {
        int valorTab = ALIeAIE(tipoDado, tipoReg);
        switch (valorTab) {
            case 1:
                return 5;
            case 2:
                return 7;
            default:
                return 10;
        }
    }

    public String getAieString(int tipoDado, int tipoReg) {
        int valorTab = ALIeAIE(tipoDado, tipoReg);
        switch (valorTab) {
            case 1:
                return "Baixa";
            case 2:
                return "Média";
            default:
                return "Alta";
        }
    }

    public int SEeCE(int tipoDado, int arqRef) {
        switch (arqRef) {
            case 1:
                if (tipoDado < 19) {
                    return 1;
                } else {
                    return 2;
                }
            case 2:
            case 3:
                if (tipoDado < 6) {
                    return 1;
                } else {
                    if (tipoDado > 19) {
                        return 3;
                    } else {
                        return 2;
                    }
                }
            default:
                if (tipoDado < 6) {
                    return 2;
                } else {
                    return 3;
                }
        }
    }

    public int getSeInt(int tipoDado, int arqRef) {
        int valorTab = SEeCE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return 4;
            case 2:
                return 5;
            default:
                return 7;
        }
    }

    public String getSeString(int tipoDado, int arqRef) {
        int valorTab = SEeCE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return "Baixa";
            case 2:
                return "Média";
            default:
                return "Alta";
        }
    }

    public int getCeInt(int tipoDado, int arqRef) {
        int valorTab = SEeCE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return 3;
            case 2:
                return 4;
            default:
                return 6;
        }
    }

    public String getCeString(int tipoDado, int arqRef) {
        int valorTab = SEeCE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return "Baixa";
            case 2:
                return "Média";
            default:
                return "Alta";
        }
    }

    public int EE(int tipoDado, int arqRef) {
        switch (arqRef) {
            case 1:
                if (tipoDado <= 15) {
                    return 1;
                } else {
                    return 2;
                }
            case 2:
                if (tipoDado < 5) {
                    return 1;
                } else {
                    if (tipoDado > 15) {
                        return 3;
                    } else {
                        return 2;
                    }
                }
            default:
                if (tipoDado < 5) {
                    return 2;
                } else {
                    return 3;
                }
        }
    }

    public int getEeInt(int tipoDado, int arqRef) {
        int valorTab = EE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return 3;
            case 2:
                return 4;
            default:
                return 6;
        }
    }

    public String getEeString(int tipoDado, int arqRef) {
        int valorTab = EE(tipoDado, arqRef);
        switch (valorTab) {
            case 1:
                return "Baixa";
            case 2:
                return "Média";
            default:
                return "Alta";
        }
    }

    public void NI(int[] caracteristicas) {
        int total = 0;
        for (int i = 0; i < caracteristicas.length; i++) {
            total += caracteristicas[i];
        }
    }

    public int getValorComplexidade(String tipo, int complexidade) {
        switch (complexidade) {
            case 1:
                if (tipo.equals("ALI")) {
                    return 7;
                }
                if (tipo.equals("AIE")) {
                    return 5;
                }
                if (tipo.equals("EE")) {
                    return 3;
                }
                if (tipo.equals("SE")) {
                    return 4;
                }
                if (tipo.equals("CE")) {
                    return 3;
                }
            case 2:
                if (tipo.equals("ALI")) {
                    return 10;
                }
                if (tipo.equals("AIE")) {
                    return 7;
                }
                if (tipo.equals("EE")) {
                    return 4;
                }
                if (tipo.equals("SE")) {
                    return 5;
                }
                if (tipo.equals("CE")) {
                    return 4;
                }
            case 3:
                if (tipo.equals("ALI")) {
                    return 15;
                }
                if (tipo.equals("AIE")) {
                    return 10;
                }
                if (tipo.equals("EE")) {
                    return 6;
                }
                if (tipo.equals("SE")) {
                    return 7;
                }
                if (tipo.equals("CE")) {
                    return 6;
                }
            default:
                return -1;
        }
    }
}
