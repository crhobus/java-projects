package LeituraFuncionario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeituraFunc {

    private String diretorio;
    private List<Funcionario> lista = new ArrayList<Funcionario>();

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public int getQtdadeFunc() {
        return lista.size();
    }

    public void lerFuncs() throws IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException, Exception {
        if ("".equals(diretorio) || diretorio == null) {
            throw new Exception("informe um diretório válido");
        }
        File dir = new File(diretorio);
        if (!dir.exists()) {
            throw new Exception("diretório não encontrado");
        }
        File[] arqs = dir.listFiles();
        String atributo, valor, nomeMetodo;
        int y;
        Method method;
        Class<?> tipoAtributo;
        SimpleDateFormat forDate = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < arqs.length; i++) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(dir, arqs[i].getName())));
                Class<?> clazz = Funcionario.class;
                Funcionario func = new Funcionario();
                for (int x = 0; x < arqs[i].length(); x++) {
                    String linha = in.readLine();
                    if (linha == null || linha.equals("")) {
                        continue;
                    }
                    y = 0;
                    atributo = "";
                    while (true) {
                        if (linha == null) {
                            break;
                        }
                        if (y == linha.length()) {
                            break;
                        }
                        if ("=".equals(linha.substring(y, y + 1))) {
                            break;
                        }
                        atributo += linha.substring(y, y + 1);
                        y++;
                    }
                    if (linha != null) {
                        try {
                            String vet[] = linha.split(atributo + "=");
                            valor = "";
                            for (int j = 0; j < vet.length; j++) {
                                valor = vet[j];
                            }
                        } catch (Exception ex) {
                            throw new Exception("Erro na leitura do arquivo");
                        }
                        nomeMetodo = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1, atributo.length());
                        Method[] metodosClasse = clazz.getDeclaredMethods();
                        for (int j = 0; j < metodosClasse.length; j++) {
                            if (metodosClasse[j].getName().equals(nomeMetodo)) {
                                try {
                                    tipoAtributo = clazz.getDeclaredField(atributo).getType();
                                    method = clazz.getMethod(nomeMetodo, clazz.getDeclaredField(atributo).getType());
                                    try {
                                        if (tipoAtributo.equals(String.class)) {
                                            method.invoke(func, valor);
                                        } else {
                                            if (tipoAtributo.equals(int.class)) {
                                                method.invoke(func, Integer.parseInt(valor));
                                            } else {
                                                if (tipoAtributo.equals(char.class)) {
                                                    char c[] = valor.toCharArray();
                                                    method.invoke(func, c[0]);
                                                } else {
                                                    if (tipoAtributo.equals(Date.class)) {
                                                        try {
                                                            method.invoke(func, forDate.parse(valor));
                                                        } catch (ParseException ex) {
                                                            throw new Exception("Erro ao invocar o método: " + method.getName());
                                                        }
                                                    } else {
                                                        if (tipoAtributo.equals(double.class)) {
                                                            method.invoke(func, Double.parseDouble(valor));
                                                        } else {
                                                            if (tipoAtributo.equals(boolean.class)) {
                                                                method.invoke(func, Boolean.valueOf(valor));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } catch (NumberFormatException ex) {
                                        throw new Exception("Erro ao invocar o método: " + method.getName());
                                    }
                                } catch (NoSuchFieldException ex) {
                                    throw new NoSuchFieldException("Arquivo corrompido");
                                } catch (SecurityException ex) {
                                    throw new SecurityException("Arquivo corrompido");
                                } catch (NoSuchMethodException ex) {
                                    throw new NoSuchMethodException("Arquivo corrompido");
                                } catch (IllegalAccessException ex) {
                                    throw new IllegalAccessException("Arquivo corrompido");
                                } catch (IllegalArgumentException ex) {
                                    throw new IllegalArgumentException("Arquivo corrompido");
                                } catch (InvocationTargetException ex) {
                                    throw new InvocationTargetException(ex);
                                }
                            }
                        }
                    }
                }
                in.close();
                lista.add(func);
            } catch (IOException ex) {
                throw new IOException("Erro ao abrir o " + arqs[i].getName() + " para leitura");
            }
        }
    }

    public List<Funcionario> getLista() {
        return lista;
    }
}
