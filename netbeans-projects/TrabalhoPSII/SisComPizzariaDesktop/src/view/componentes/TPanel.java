package view.componentes;

import control.funcoes.Dados;
import control.funcoes.Funcoes;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.desktop.ClickListener;

public class TPanel extends JPanel {

    private ClickListener clickListener;
    private boolean keyPressed;
    private boolean listenerkeyPressed;
    private boolean validaCamposPanel;
    private boolean temCamposObrigatorios;
    private JPopupMenu popupMenu;
    private boolean edicao;
    private boolean permiteAlterarDados;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public boolean isListenerkeyPressed() {
        return listenerkeyPressed;
    }

    public void setListenerkeyPressed(boolean listenerkeyPressed) {
        this.listenerkeyPressed = listenerkeyPressed;
    }

    public boolean isValidaCamposPanel() {
        return validaCamposPanel;
    }

    public void setValidaCamposPanel(boolean validaCamposPanel) {
        this.validaCamposPanel = validaCamposPanel;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public boolean isTemCamposObrigatorios() {
        return temCamposObrigatorios;
    }

    public void setTemCamposObrigatorios(boolean temCamposObrigatorios) {
        this.temCamposObrigatorios = temCamposObrigatorios;
    }

    public boolean isEdicao() {
        return edicao;
    }

    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public void inicializaPanel() {
        if (listenerkeyPressed) {
            addListenerKey();
        }
        if (popupMenu != null) {
            addPopupMenuInComponents();
        }
        if (validaCamposPanel) {
            validaCamposPanel();
        }
        if (temCamposObrigatorios) {
            tornarCampoObrigatorio();
        }
    }

    private void addListenerKey() {
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField) {
                ((JTextField) com).getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent evt) {
                        pressed();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent evt) {
                        pressed();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent evt) {
                        //
                    }
                });
            } else if (com instanceof JComboBox) {
                ((JComboBox) com).addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent evt) {
                        pressed();
                    }
                });
            } else if (com instanceof JCheckBox) {
                ((JCheckBox) com).addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent evt) {
                        pressed();
                    }
                });
            }
        }
    }

    private void pressed() {
        if (keyPressed
                && permiteAlterarDados) {
            clickListener.onSalvar(true);
            clickListener.onDesfazer(true);
            clickListener.onNovo(false);
            clickListener.onExcluir(false);
            edicao = true;
        }
    }

    private void addPopupMenuInComponents() {
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField) {
                ((JTextField) com).setComponentPopupMenu(popupMenu);
            } else if (com instanceof JComboBox) {
                ((JComboBox) com).setComponentPopupMenu(popupMenu);
            } else if (com instanceof JCheckBox) {
                ((JCheckBox) com).setComponentPopupMenu(popupMenu);
            }
        }
    }

    private void tornarCampoObrigatorio() {
        for (Component com : this.getComponents()) {
            if (com instanceof TTextField) {
                if (((TTextField) com).isObrigatorio()) {
                    ((TTextField) com).setBackground(new Color(173, 216, 230));
                }
            } else if (com instanceof TPasswordField) {
                if (((TPasswordField) com).isObrigatorio()) {
                    ((TPasswordField) com).setBackground(new Color(173, 216, 230));
                }
            } else if (com instanceof TFormattedTextField) {
                if (((TFormattedTextField) com).isObrigatorio()) {
                    ((TFormattedTextField) com).setBackground(new Color(173, 216, 230));
                }
            }
        }
    }

    private void validaCamposPanel() {
        for (final Component com : this.getComponents()) {
            if (com instanceof TFormattedTextField) {
                ((TFormattedTextField) com).addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent evt) {
                        //
                    }

                    @Override
                    public void focusLost(FocusEvent evt) {
                        String texto = ((TFormattedTextField) com).getText().replace("-", "").replace("/", "").replace(".", "").replace("(", "").replace(")", "").replace("_", "").replace(" ", "");
                        String nome = ((TFormattedTextField) com).getName();
                        String inicioNome = ((TFormattedTextField) com).getName().substring(0, 2);
                        boolean isCampoInvalido = false;
                        if ("DT".equalsIgnoreCase(inicioNome)
                                && !"".equalsIgnoreCase(texto)) {
                            if (texto.length() != 8) {
                                isCampoInvalido = true;
                            } else if (!Funcoes.validaData(texto)) {
                                isCampoInvalido = true;
                            }
                            if (isCampoInvalido) {
                                JOptionPane.showMessageDialog(null, "Campo data inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                                ((TFormattedTextField) com).requestFocus();
                            }
                        } else if ("NR_CEP".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 8) {
                            JOptionPane.showMessageDialog(null, "Campo cep inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if (("NR_TELEFONE".equalsIgnoreCase(nome)
                                || "NR_CELULAR".equalsIgnoreCase(nome))
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 10) {
                            JOptionPane.showMessageDialog(null, "Campo telefone/celular inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if ("NR_RG".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 7) {
                            JOptionPane.showMessageDialog(null, "Campo rg inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if ("NR_CPF".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 11) {
                            JOptionPane.showMessageDialog(null, "Campo cpf inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if ("NR_CARTEIRA_TRABALHO".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 11) {
                            JOptionPane.showMessageDialog(null, "Campo carteira de trabalho inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if ("NR_PIS_PASEP".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 11) {
                            JOptionPane.showMessageDialog(null, "Campo pis/pasep inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        } else if ("NR_CNH".equalsIgnoreCase(nome)
                                && !"".equalsIgnoreCase(texto)
                                && texto.length() != 11) {
                            JOptionPane.showMessageDialog(null, "Campo cnh inválido", "Pizza Nostra", JOptionPane.ERROR_MESSAGE);
                            ((TFormattedTextField) com).requestFocus();
                        }
                    }
                });
            }
        }
    }

    public Dados getDadosPanel(Dados dados) {
        if (dados == null) {
            dados = new Dados();
        }
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField) {
                dados.addInfo(com.getName(), ((JTextField) com).getText());
            } else if (com instanceof JComboBox) {
                dados.addInfo(com.getName(), ((String) ((JComboBox) com).getSelectedItem()));
            } else if (com instanceof JCheckBox) {
                dados.addInfo(com.getName(), (((JCheckBox) com).isSelected() ? "S" : "N"));
            }
        }
        return dados;
    }

    public void setFocusCampo(String nmCampo) {
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField) {
                if (nmCampo != null
                        && com.getName().equalsIgnoreCase(nmCampo)) {
                    ((JTextField) com).requestFocus();
                    return;
                }
            } else if (com instanceof JComboBox) {
                if (nmCampo != null
                        && com.getName().equalsIgnoreCase(nmCampo)) {
                    ((JComboBox) com).requestFocus();
                    return;
                }
            } else if (com instanceof JCheckBox) {
                if (nmCampo != null
                        && com.getName().equalsIgnoreCase(nmCampo)) {
                    ((JCheckBox) com).requestFocus();
                    return;
                }
            }
        }
    }

    public void setPermiteAlterarDados(boolean permiteAlterarDados) {
        this.permiteAlterarDados = permiteAlterarDados;
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField
                    && ((JTextField) com).isEditable()) {
                ((JTextField) com).setEditable(permiteAlterarDados);
            } else if (com instanceof JComboBox) {
                ((JComboBox) com).setEnabled(permiteAlterarDados);
            } else if (com instanceof JCheckBox) {
                ((JCheckBox) com).setEnabled(permiteAlterarDados);
            }
        }
    }

    public void limparCampos() {
        keyPressed = false;
        for (Component com : this.getComponents()) {
            if (com instanceof JTextField) {
                ((JTextField) com).setText("");
            } else if (com instanceof JComboBox) {
                ((JComboBox) com).setSelectedItem(null);
            } else if (com instanceof JCheckBox) {
                ((JCheckBox) com).setSelected(false);
            }
        }
        keyPressed = true;
    }

    public boolean verificaCamposObrigatoriosPreenchidos() {
        char[] arrayChar;
        String str;
        boolean preenchido = true;
        for (Component com : this.getComponents()) {
            if (com instanceof TTextField) {
                if (((TTextField) com).isObrigatorio()
                        && "".equalsIgnoreCase(((TTextField) com).getText().trim())) {
                    preenchido = false;
                    break;
                }
            } else if (com instanceof TPasswordField) {
                arrayChar = ((TPasswordField) com).getPassword();
                str = "";
                for (char c : arrayChar) {
                    str += c;
                }
                if (((TPasswordField) com).isObrigatorio()
                        && "".equalsIgnoreCase(str.trim())) {
                    preenchido = false;
                    break;
                }
            } else if (com instanceof TFormattedTextField) {
                if (((TFormattedTextField) com).isObrigatorio()
                        && "".equalsIgnoreCase(((TFormattedTextField) com).getText().replace("-", "").replace("/", "").replace(".", "").replace("(", "").replace(")", "").replace("_", "").replace(" ", ""))) {
                    preenchido = false;
                    break;
                }
            }
        }
        if (!preenchido) {
            JOptionPane.showMessageDialog(null, "Campos obrigatórios não preenchido", "Pizza Nostra", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
