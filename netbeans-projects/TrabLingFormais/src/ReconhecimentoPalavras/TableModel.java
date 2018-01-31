package ReconhecimentoPalavras;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private List<ReconheLing> lista = new ArrayList<ReconheLing>();
    private String estados = "", resultado = "";

    public void addLista(String palavra, int linha) {
        ReconheLing reconheLing = new ReconheLing();
        analisar(palavra);
        reconheLing.setLinha(linha);
        reconheLing.setResultado(getResultado());
        reconheLing.setPalavra(palavra);
        reconheLing.setReconhecimento(getEstados());
        lista.add(reconheLing);
        estados = "";
    }

    private void analisar(String palavra) {
        String str[] = new String[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            str[i] = palavra.substring(i, i + 1);
        }
        e0(str);
    }

    private void e0(String vet[]) {
        int i = 0;
        if (vet[i].equals("a")) {
            estados += "e0";
            i++;
            e1(vet, i);
        } else {
            if (vet[i].equals("b")) {
                estados += "e0";
                i++;
                e2(vet, i);
            } else {
                resultado = "erro: símbolo(s) inválido(s)";
                estados += "e0";
                for (int y = 0; y < vet.length; y++) {
                    estados += " eerro";
                }
            }
        }
    }

    private void e1(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                while (i < vet.length && vet[i].equals("a")) {
                    i++;
                    estados += " e1";
                }
                if (i < vet.length) {
                    if (vet[i].equals("b")) {
                        i++;
                        estados += " e1";
                        e2(vet, i);
                    } else {
                        estados += " e1";
                        for (int y = i; y < vet.length; y++) {
                            estados += " eerro";
                        }
                        resultado = "erro: palavra inválida";
                    }
                } else {
                    estados += " e1";
                    resultado = "palavra válida";
                }
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e1";
                    e2(vet, i);
                } else {
                    estados += " e1";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e1";
            resultado = "palavra válida";
        }
    }

    private void e2(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                i++;
                estados += " e2";
                e3(vet, i);
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e2";
                    e4(vet, i);
                } else {
                    estados += " e2";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e2";
            resultado = "palavra válida";
        }
    }

    private void e3(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                while (i < vet.length && vet[i].equals("a")) {
                    i++;
                    estados += " e3";
                }
                if (i < vet.length) {
                    if (vet[i].equals("b")) {
                        i++;
                        estados += " e3";
                        e5(vet, i);
                    } else {
                        estados += " e3";
                        for (int y = i; y < vet.length; y++) {
                            estados += " eerro";
                        }
                        resultado = "erro: palavra inválida";
                    }
                } else {
                    estados += " e3";
                    resultado = "palavra válida";
                }
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e3";
                    e5(vet, i);
                } else {
                    estados += " e3";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e3";
            resultado = "palavra válida";
        }
    }

    private void e4(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                i++;
                estados += " e4";
                e6(vet, i);
            } else {
                estados += " e4";
                for (int y = i; y < vet.length; y++) {
                    estados += " eerro";
                }
                resultado = "erro: palavra inválida";
            }
        } else {
            estados += " e4";
            resultado = "palavra válida";
        }
    }

    private void e5(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                i++;
                estados += " e5";
                e3(vet, i);
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e5";
                    e4(vet, i);
                } else {
                    estados += " e5";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e5";
            resultado = "palavra válida";
        }
    }

    private void e6(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                while (i < vet.length && vet[i].equals("a")) {
                    i++;
                    estados += " e6";
                }
                if (i < vet.length) {
                    if (vet[i].equals("b")) {
                        i++;
                        estados += " e6";
                        e7(vet, i);
                    } else {
                        estados += " e6";
                        for (int y = i; y < vet.length; y++) {
                            estados += " eerro";
                        }
                        resultado = "erro: palavra inválida";
                    }
                } else {
                    estados += " e6";
                    resultado = "palavra válida";
                }
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e6";
                    e7(vet, i);
                } else {
                    estados += " e6";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e6";
            resultado = "palavra válida";
        }
    }

    private void e7(String vet[], int i) {
        if (i < vet.length) {
            if (vet[i].equals("a")) {
                i++;
                estados += " e7";
                e6(vet, i);
            } else {
                if (vet[i].equals("b")) {
                    i++;
                    estados += " e7";
                    e4(vet, i);
                } else {
                    estados += " e7";
                    for (int y = i; y < vet.length; y++) {
                        estados += " eerro";
                    }
                    resultado = "erro: palavra inválida";
                }
            }
        } else {
            estados += " e7";
            resultado = "palavra válida";
        }
    }

    private String getEstados() {
        return estados;
    }

    private String getResultado() {
        return resultado;
    }

    public void limparLista() {
        lista.clear();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        ReconheLing reconheLing = lista.get(linha);
        switch (coluna) {
            case 0:
                return reconheLing.getLinha();
            case 1:
                return reconheLing.getResultado();
            case 2:
                return reconheLing.getPalavra();
            default:
                return reconheLing.getReconhecimento();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "linha";
            case 1:
                return "resultado";
            case 2:
                return "palavra";
            default:
                return "reconhecimento";
        }
    }
}
