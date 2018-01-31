package View;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JOptionPane;

import Control.CalculoFPA;
import Control.auxCalculoFPA;
import Model.Dados;

public class Principal extends Componentes {

    private static final long serialVersionUID = 1L;
    private CalculoFPA calculoFPA;
    private NumberFormat numberFormat;
    private auxCalculoFPA auxCalculo;
    private String desc = "";
    private boolean flagCalculado;
    private int contadorADD = 0;
    private int contadorDEL = 0;
    private int contadorCHGB = 0;//complexidade anterior dos alterados
    private int contadorCHGA = 0;//complexidade a ser mudada
    private int campoCFP = 0;
    private double VAFA = 0;

    public Principal() {
        calculoFPA = new CalculoFPA();
        numberFormat = NumberFormat.getInstance(new Locale("pt_BR"));
        numberFormat.setMaximumFractionDigits(2);
        auxCalculo = new auxCalculoFPA();
        flagCalculado = false;
        habilitaTabProjeto(false);
    }

    private void addALI() {
        if ("".equals(tfDescricaoALI.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Preencha o campo descrição", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfDescricaoALI.grabFocus();
        } else {
            String s = calculoFPA.addALI(tfDescricaoALI.getText(), Integer.parseInt(spTipoDadosALI.getValue().toString()), Integer.parseInt(spTipoRegistroALI.getValue().toString()));
            if ("".equals(taResultadoALI.getText())) {
                taResultadoALI.setText("ALI (Arquivos)\n" + s);
            } else {
                taResultadoALI.setText(taResultadoALI.getText() + "\n" + s);
            }
            spTipoDadosALI.setValue(1);
            spTipoRegistroALI.setValue(1);
            tfDescricaoALI.setText("");
        }
    }

    private void calcularALI() {
        if ("".equals(taResultadoALI.getText())) {
            JOptionPane.showMessageDialog(null, "Entre com no mínimo um ALI", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            taResultadoALI.setText(taResultadoALI.getText() + "\n\n" + calculoFPA.getALI());
            btAddALI.setEnabled(false);
            btCalcularALI.setEnabled(false);
        }
    }

    private void cancelarALI() {
        spTipoDadosALI.setValue(1);
        spTipoRegistroALI.setValue(1);
        tfDescricaoALI.setText("");
        taResultadoALI.setText("");
        calculoFPA.zerarALI();
        btAddALI.setEnabled(true);
        btCalcularALI.setEnabled(true);
    }

    private void addAIE() {
        if ("".equals(tfDescricaoAIE.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Preencha o campo descrição", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfDescricaoAIE.grabFocus();
        } else {
            String s = calculoFPA.addAIE(tfDescricaoAIE.getText(), Integer.parseInt(spTipoDadosAIE.getValue().toString()), Integer.parseInt(spTipoRegistroAIE.getValue().toString()));
            if ("".equals(taResultadoAIE.getText())) {
                taResultadoAIE.setText("AIE (Interfaces)\n" + s);
            } else {
                taResultadoAIE.setText(taResultadoAIE.getText() + "\n" + s);
            }
            spTipoDadosAIE.setValue(1);
            spTipoRegistroAIE.setValue(1);
            tfDescricaoAIE.setText("");
        }
    }

    private void calcularAIE() {
        if ("".equals(taResultadoAIE.getText())) {
            JOptionPane.showMessageDialog(null, "Entre com no mínimo um AIE", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            taResultadoAIE.setText(taResultadoAIE.getText() + "\n\n" + calculoFPA.getAIE());
            btAddAIE.setEnabled(false);
            btCalcularAIE.setEnabled(false);
        }
    }

    private void cancelarAIE() {
        spTipoDadosAIE.setValue(1);
        spTipoRegistroAIE.setValue(1);
        tfDescricaoAIE.setText("");
        taResultadoAIE.setText("");
        calculoFPA.zeraAIE();
        btAddAIE.setEnabled(true);
        btCalcularAIE.setEnabled(true);
    }

    private void addEE() {
        if ("".equals(tfDescricaoEE.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Preencha o campo descrição", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfDescricaoEE.grabFocus();
        } else {
            String s = calculoFPA.addEE(tfDescricaoEE.getText(), Integer.parseInt(spTipoDadosEE.getValue().toString()), Integer.parseInt(spArquivosReferenciadosEE.getValue().toString()));
            if ("".equals(taResultadoEE.getText())) {
                taResultadoEE.setText("EE (Entradas)\n" + s);
            } else {
                taResultadoEE.setText(taResultadoEE.getText() + "\n" + s);
            }
            spTipoDadosEE.setValue(1);
            spArquivosReferenciadosEE.setValue(1);
            tfDescricaoEE.setText("");
        }
    }

    private void calcularEE() {
        if ("".equals(taResultadoEE.getText())) {
            JOptionPane.showMessageDialog(null, "Entre com no mínimo um EE", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            taResultadoEE.setText(taResultadoEE.getText() + "\n\n" + calculoFPA.getEE());
            btAddEE.setEnabled(false);
            btCalcularEE.setEnabled(false);
        }
    }

    private void cancelarEE() {
        spTipoDadosEE.setValue(1);
        spArquivosReferenciadosEE.setValue(1);
        tfDescricaoEE.setText("");
        taResultadoEE.setText("");
        calculoFPA.zeraEE();
        btAddEE.setEnabled(true);
        btCalcularEE.setEnabled(true);
    }

    private void addCE() {
        if ("".equals(tfDescricaoCE.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Preencha o campo descrição", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfDescricaoCE.grabFocus();
        } else {
            String s = calculoFPA.addCE(tfDescricaoCE.getText(), Integer.parseInt(spTipoDadosCE.getValue().toString()), Integer.parseInt(spArquivosReferenciadosCE.getValue().toString()));
            if ("".equals(taResultadoCE.getText())) {
                taResultadoCE.setText("CE (Consultas)\n" + s);
            } else {
                taResultadoCE.setText(taResultadoCE.getText() + "\n" + s);
            }
            spTipoDadosCE.setValue(1);
            spArquivosReferenciadosCE.setValue(1);
            tfDescricaoCE.setText("");
        }
    }

    private void calcularCE() {
        if ("".equals(taResultadoCE.getText())) {
            JOptionPane.showMessageDialog(null, "Entre com no mínimo um CE", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            taResultadoCE.setText(taResultadoCE.getText() + "\n\n" + calculoFPA.getCE());
            btAddCE.setEnabled(false);
            btCalcularCE.setEnabled(false);
        }
    }

    private void cancelarCE() {
        spTipoDadosCE.setValue(1);
        spArquivosReferenciadosCE.setValue(1);
        tfDescricaoCE.setText("");
        taResultadoCE.setText("");
        calculoFPA.zeraCE();
        btAddCE.setEnabled(true);
        btCalcularCE.setEnabled(true);
    }

    private void addSE() {
        if ("".equals(tfDescricaoSE.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Preencha o campo descrição", "Atenção", JOptionPane.WARNING_MESSAGE);
            tfDescricaoSE.grabFocus();
        } else {
            String s = calculoFPA.addSE(tfDescricaoSE.getText(), Integer.parseInt(spTipoDadosSE.getValue().toString()), Integer.parseInt(spArquivosReferenciadosSE.getValue().toString()));
            if ("".equals(taResultadoSE.getText())) {
                taResultadoSE.setText("SE (Saídas)\n" + s);
            } else {
                taResultadoSE.setText(taResultadoSE.getText() + "\n" + s);
            }
            spTipoDadosSE.setValue(1);
            spArquivosReferenciadosSE.setValue(1);
            tfDescricaoSE.setText("");
        }
    }

    private void calcularSE() {
        if ("".equals(taResultadoSE.getText())) {
            JOptionPane.showMessageDialog(null, "Entre com no mínimo um SE", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            taResultadoSE.setText(taResultadoSE.getText() + "\n\n" + calculoFPA.getSE());
            btAddSE.setEnabled(false);
            btCalcularSE.setEnabled(false);
        }
    }

    private void cancelarSE() {
        spTipoDadosSE.setValue(1);
        spArquivosReferenciadosSE.setValue(1);
        tfDescricaoSE.setText("");
        taResultadoSE.setText("");
        calculoFPA.zeraSE();
        btAddSE.setEnabled(true);
        btCalcularSE.setEnabled(true);
    }

    private void calcularNI() {
        if ("Selecione".equals(cbComuDados.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em comunicação de dados", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbComuDados.grabFocus();
            return;
        }
        if ("Selecione".equals(cbProsDistr.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em processamento distribuído", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbProsDistr.grabFocus();
            return;
        }
        if ("Selecione".equals(cbPerformance.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em performance", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbPerformance.grabFocus();
            return;
        }
        if ("Selecione".equals(cbConfAltUlt.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em configuraçãoo altamante utilizada", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbConfAltUlt.grabFocus();
            return;
        }
        if ("Selecione".equals(cbTaxTrans.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em taxa de transações", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbTaxTrans.grabFocus();
            return;
        }
        if ("Selecione".equals(cbEntDadosOn.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em entrada de dados on-line", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbEntDadosOn.grabFocus();
            return;
        }
        if ("Selecione".equals(cbEfUsuFinal.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em eficiência do usuário final", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbEfUsuFinal.grabFocus();
            return;
        }
        if ("Selecione".equals(cbAtuaOn.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em atualização on-line", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbAtuaOn.grabFocus();
            return;
        }
        if ("Selecione".equals(cbComProc.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em complexidade de processamento", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbComProc.grabFocus();
            return;
        }
        if ("Selecione".equals(cbReutilizacao.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em reutilização", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbReutilizacao.grabFocus();
            return;
        }
        if ("Selecione".equals(cbFacInstal.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em facilidade de instalação", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbFacInstal.grabFocus();
            return;
        }
        if ("Selecione".equals(cbFacOperacao.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em facilidade de operação", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbFacOperacao.grabFocus();
            return;
        }
        if ("Selecione".equals(cbMultLocal.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em múltiplas localidades", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbMultLocal.grabFocus();
            return;
        }
        if ("Selecione".equals(cbFacMudancas.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Selecione umas das opções em facilidade de mudanças", "Atenção", JOptionPane.WARNING_MESSAGE);
            cbFacMudancas.grabFocus();
            return;
        }
        int[] niveis = new int[6];
        String[] str = {(String) cbComuDados.getSelectedItem(),
            (String) cbProsDistr.getSelectedItem(),
            (String) cbPerformance.getSelectedItem(),
            (String) cbConfAltUlt.getSelectedItem(),
            (String) cbTaxTrans.getSelectedItem(),
            (String) cbEntDadosOn.getSelectedItem(),
            (String) cbEfUsuFinal.getSelectedItem(),
            (String) cbAtuaOn.getSelectedItem(),
            (String) cbComProc.getSelectedItem(),
            (String) cbReutilizacao.getSelectedItem(),
            (String) cbFacInstal.getSelectedItem(),
            (String) cbFacOperacao.getSelectedItem(),
            (String) cbMultLocal.getSelectedItem(),
            (String) cbFacMudancas.getSelectedItem()};
        for (String s : str) {
            if ("Sem".equals(s)) {
                niveis[0]++;
            }
            if ("Baixa/mínima".equals(s)) {
                niveis[1]++;
            }
            if ("Moderada".equals(s)) {
                niveis[2]++;
            }
            if ("Média".equals(s)) {
                niveis[3]++;
            }
            if ("Significante".equals(s)) {
                niveis[4]++;
            }
            if ("Grande/Alta".equals(s)) {
                niveis[5]++;
            }
        }
        taResultadoNI.setText("Nível de Influência:" + calculoFPA.addNI(niveis));
        btCalcularNI.setEnabled(false);
    }

    private void cancelarNI() {
        cbComuDados.setSelectedItem("Selecione");
        cbProsDistr.setSelectedItem("Selecione");
        cbPerformance.setSelectedItem("Selecione");
        cbConfAltUlt.setSelectedItem("Selecione");
        cbTaxTrans.setSelectedItem("Selecione");
        cbEntDadosOn.setSelectedItem("Selecione");
        cbEfUsuFinal.setSelectedItem("Selecione");
        cbAtuaOn.setSelectedItem("Selecione");
        cbComProc.setSelectedItem("Selecione");
        cbReutilizacao.setSelectedItem("Selecione");
        cbFacInstal.setSelectedItem("Selecione");
        cbFacOperacao.setSelectedItem("Selecione");
        cbMultLocal.setSelectedItem("Selecione");
        cbFacMudancas.setSelectedItem("Selecione");
        taResultadoNI.setText("");
        calculoFPA.zeraNI();
        btCalcularNI.setEnabled(true);
    }

    private void mostrar() {
        if (btCalcularALI.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o ALI primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (btCalcularAIE.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o AIE primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (btCalcularEE.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o EE primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (btCalcularCE.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o CE primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (btCalcularSE.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o SE primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (btCalcularNI.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Calcule o NI primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tfPFB.setText("ALI + AIE + EE + CE + SE: " + numberFormat.format(calculoFPA.getPFB()));
        tfFA.setText("(NI * 0,01) + 0,65: " + numberFormat.format(calculoFPA.getFA()));
        tfPFA.setText("PFB * FA: " + numberFormat.format(calculoFPA.getPFA()));
    }

    private void ok() throws Exception {
        if ("PFB * FA: ".equals(tfPFA.getText())) {
            JOptionPane.showMessageDialog(null, "Calcule os sub totais primeiro", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            if (Double.parseDouble(tfPontosFunc.getText()) > 0) {
                tfPrazo.setText(calculoFPA.getResultadoFinal(Double.parseDouble(tfPontosFunc.getText()), numberFormat));
                //permite carregar os combobox da melhoria;
                habilitaTabProjeto(true);
                carregaComboBoxProjMelhoria();
                this.tfUFPB.setText(calculoFPA.getPFB() + "");
                this.tfVAFB.setText("" + calculoFPA.getFA());
                this.tfUFPB.setEditable(false);
                this.tfVAFB.setEditable(false);
                this.tfCHGB.setEditable(false);
                return;
            }
            tfPontosFunc.grabFocus();
            throw new Exception("Entre com valores superiores a zero");
        } catch (NumberFormatException ex) {
            tfPontosFunc.grabFocus();
            throw new Exception("Campo ponto de função inválido");
        }
    }

    private void novo() {
        cancelarALI();
        cancelarAIE();
        cancelarEE();
        cancelarCE();
        cancelarSE();
        cancelarNI();
        tfPFB.setText("ALI + AIE + EE + CE + SE: ");
        tfFA.setText("(NI * 0,01) + 0,65: ");
        tfPFA.setText("PFB * FA: ");
        tfPrazo.setText("PFA * PF: ");
        tfPontosFunc.setText("");
        habilitaTabProjeto(false);
        limpaTudo();
    }

    //é carregado após Ok clicado
    private void carregaComboBoxProjMelhoria() {

        for (Dados dados : calculoFPA.getListaDado("ALI")) {
            this.cbArquivosDelALI.addItem(dados.getNome());
            this.cbArquivosAltALI.addItem(dados.getNome());
        }
        for (Dados dados : calculoFPA.getListaDado("AIE")) {
            this.cbArquivosDelAIE.addItem(dados.getNome());
            this.cbArquivosAltAIE.addItem(dados.getNome());
        }
        for (Dados dados : calculoFPA.getListaDado("EE")) {
            this.cbEntradasDelEE.addItem(dados.getNome());
            this.cbArquivosAltEE.addItem(dados.getNome());
        }
        for (Dados dados : calculoFPA.getListaDado("CE")) {
            this.cbConsultasCE.addItem(dados.getNome());
            this.cbArquivosAltCE.addItem(dados.getNome());
        }
        for (Dados dados : calculoFPA.getListaDado("SE")) {
            this.cbSaidasSE.addItem(dados.getNome());
            this.cbArquivosAltSE.addItem(dados.getNome());

        }
    }

    private int validaDEL() {
        int contador = 0;
        String delALI, delAIE, delEE, delCE, delSE;
        delALI = this.cbArquivosDelALI.getSelectedItem().toString();
        delAIE = this.cbArquivosDelAIE.getSelectedItem().toString();
        delEE = this.cbEntradasDelEE.getSelectedItem().toString();
        delCE = this.cbConsultasCE.getSelectedItem().toString();
        delSE = this.cbSaidasSE.getSelectedItem().toString();
        if (!delALI.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("ALI")) {
                if (dados.getNome().equals(delALI)) {
                    contador += dados.getValor();
                    //adicionar no painel os deletados
                    this.desc += "DEL (ALI)\n" + delALI + "\nValor   :" + dados.getValor() + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);

                    //remove das listagens
                    this.cbArquivosDelALI.removeItem(delALI);
                    this.cbArquivosAltALI.removeItem(delALI);
                }
            }
        }
        if (!delAIE.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("AIE")) {
                if (dados.getNome().equals(delAIE)) {
                    contador += dados.getValor();
                    //adicionar no painel os deletados
                    this.desc += "DEL (AIE)\n" + delAIE + "\nValor   :" + dados.getValor() + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    //remove das listagens
                    this.cbArquivosDelAIE.removeItem(delAIE);
                    this.cbArquivosAltAIE.removeItem(delAIE);

                }
            }
        }

        if (!delEE.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("EE")) {
                if (dados.getNome().equals(delEE)) {
                    contador += dados.getValor();
                    //adicionar no painel os deletados
                    this.desc += "DEL (EE)\n" + delEE + "\nValor   :" + dados.getValor() + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    //remove das listagens
                    this.cbEntradasDelEE.removeItem(delEE);
                    this.cbArquivosAltEE.removeItem(delEE);
                }
            }
        }

        if (!delCE.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("CE")) {
                if (dados.getNome().equals(delCE)) {
                    contador += dados.getValor();
                    //adicionar no painel os deletados
                    this.desc += "DEL (CE)\n" + delCE + "\nValor   :" + dados.getValor() + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    //remove das listagens
                    this.cbConsultasCE.removeItem(delCE);
                    this.cbArquivosAltCE.removeItem(delCE);
                }
            }
        }

        if (!delSE.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("SE")) {
                if (dados.getNome().equals(delSE)) {
                    contador += dados.getValor();
                    //adicionar no painel os deletados
                    this.desc += "DEL (SE)\n" + delSE + "\nValor   :" + dados.getValor() + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    //remove das listagens
                    this.cbSaidasSE.removeItem(delSE);
                    this.cbArquivosAltSE.removeItem(delSE);
                }
            }
        }
        return contador;
    } //fim valida

    public void limpaTudo() {
        this.campoCFP = 0;
        this.contadorADD = 0;
        this.contadorCHGA = 0;
        this.contadorCHGB = 0;
        this.contadorDEL = 0;
        this.VAFA = 0;
        this.tfCFP.setText("");
        this.tfCHGB.setText("");
        this.tfVAFA.setText("");
        //zera
        this.cbArquivosDelALI.removeAllItems();
        this.cbArquivosAltALI.removeAllItems();
        this.cbArquivosDelAIE.removeAllItems();
        this.cbArquivosAltAIE.removeAllItems();
        this.cbEntradasDelEE.removeAllItems();
        this.cbArquivosAltEE.removeAllItems();
        this.cbConsultasCE.removeAllItems();
        this.cbArquivosAltCE.removeAllItems();
        this.cbSaidasSE.removeAllItems();
        this.cbArquivosAltSE.removeAllItems();

        //inicia com "Selecione"
        this.cbArquivosDelALI.addItem("Selecione");
        this.cbArquivosAltALI.addItem("Selecione");
        this.cbArquivosDelAIE.addItem("Selecione");
        this.cbArquivosAltAIE.addItem("Selecione");
        this.cbEntradasDelEE.addItem("Selecione");
        this.cbArquivosAltEE.addItem("Selecione");
        this.cbConsultasCE.addItem("Selecione");
        this.cbArquivosAltCE.addItem("Selecione");
        this.cbSaidasSE.addItem("Selecione");
        this.cbArquivosAltSE.addItem("Selecione");
        this.taResultadoPRJ.setText("");
        this.desc = "";
        this.carregaComboBoxProjMelhoria();

    }

    private void addPrjMelhoria() {
        String itemTipo = (String) this.cbTipoAdd.getSelectedItem();
        String itemComplexidade = (String) this.cbComplexidadeAdd.getSelectedItem();
        if (itemTipo.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, informe o Tipo!");
        }
        if (itemComplexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, informe a Complexidade!");
        }
        if (!itemTipo.equals("Selecione") && !itemComplexidade.equals("Selecione")) {
            if (tfDescricaoAdd.getText().equals("")) {
                this.desc += "ADD (" + this.cbTipoAdd.getSelectedItem() + ")\n" + "adicionado [Descrição sem nome]\n";
            } else {
                this.desc += "ADD (" + this.cbTipoAdd.getSelectedItem() + ")\n" + tfDescricaoAdd.getText() + "\n";
            }
            this.desc += "Complexidade " + this.cbComplexidadeAdd.getSelectedItem() + "    : " + auxCalculo.getValorComplexidade(this.cbTipoAdd.getSelectedItem().toString(), cbComplexidadeAdd.getSelectedIndex()) + "\n";
            this.desc += "----------------------------------\n";
            this.taResultadoPRJ.setText(this.desc);
            int valorcompl = auxCalculo.getValorComplexidade(this.cbTipoAdd.getSelectedItem().toString(), cbComplexidadeAdd.getSelectedIndex());
            //soma os ADD
            this.contadorADD += valorcompl;
            //reseta campos
            this.cbComplexidadeAdd.setSelectedIndex(0);
            this.cbTipoAdd.setSelectedIndex(0);
            this.tfDescricaoAdd.setText("");

        }
    }

    private void altALI() {
        String ALI, complexidade;
        ALI = cbArquivosAltALI.getSelectedItem().toString();
        complexidade = cbComplexidadeAltALI.getSelectedItem().toString();

        if (ALI.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o ALI!");
        }
        if (complexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione a complexidade!");
        }
        if (!ALI.equals("Selecione") && !complexidade.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("ALI")) {
                if (dados.getNome().equals(ALI)) {
                    this.contadorCHGB += dados.getValor();
                    this.contadorCHGA += auxCalculo.getValorComplexidade("ALI", cbComplexidadeAltALI.getSelectedIndex());
                    this.desc += "ALTERADO (ALI)\n" + dados.getNome() + "\nValor anterior = " + dados.getValor() + "\nValor de alteração = " + auxCalculo.getValorComplexidade("ALI", cbComplexidadeAltALI.getSelectedIndex()) + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    this.tfCHGB.setText(this.contadorCHGB + "");
                    //remove da lista
                    this.cbArquivosDelALI.removeItem(ALI);
                    this.cbArquivosAltALI.removeItem(ALI);
                }
            }
        }
    }

    private void altAIE() {
        String AIE, complexidade;
        AIE = cbArquivosAltAIE.getSelectedItem().toString();
        complexidade = cbComplexidadeAltAIE.getSelectedItem().toString();
        if (AIE.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o AIE!");
        }
        if (complexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione a complexidade!");
        }
        if (!AIE.equals("Selecione") && !complexidade.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("AIE")) {
                if (dados.getNome().equals(AIE)) {
                    this.contadorCHGB += dados.getValor();
                    this.contadorCHGA += auxCalculo.getValorComplexidade("AIE", cbComplexidadeAltAIE.getSelectedIndex());

                    this.desc += "ALTERADO (AIE)\n" + dados.getNome() + "\nValor anterior = " + dados.getValor() + "\nValor de alteração = " + auxCalculo.getValorComplexidade("AIE", cbComplexidadeAltAIE.getSelectedIndex()) + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);

                    this.tfCHGB.setText(this.contadorCHGB + "");
                    this.cbArquivosDelAIE.removeItem(AIE);
                    this.cbArquivosAltAIE.removeItem(AIE);
                }
            }
        }
    }

    private void altCE() {
        String CE, complexidade;
        CE = cbArquivosAltCE.getSelectedItem().toString();
        complexidade = cbComplexidadeAltCE.getSelectedItem().toString();

        if (CE.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o CE!");
        }
        if (complexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione a complexidade!");
        }
        if (!CE.equals("Selecione") && !complexidade.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("CE")) {
                if (dados.getNome().equals(CE)) {
                    this.contadorCHGB += dados.getValor();
                    this.contadorCHGA += auxCalculo.getValorComplexidade("CE", cbComplexidadeAltCE.getSelectedIndex());

                    this.desc += "ALTERADO (CE)\n" + dados.getNome() + "\nValor anterior = " + dados.getValor() + "\nValor de alteração = " + auxCalculo.getValorComplexidade("CE", cbComplexidadeAltCE.getSelectedIndex()) + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);

                    this.tfCHGB.setText(this.contadorCHGB + "");
                    this.cbConsultasCE.removeItem(CE);
                    this.cbArquivosAltCE.removeItem(CE);
                }
            }
        }
    }

    private void altSE() {
        String SE, complexidade;
        SE = cbArquivosAltSE.getSelectedItem().toString();
        complexidade = cbComplexidadeAltSE.getSelectedItem().toString();

        if (SE.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o SE!");
        }
        if (complexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione a complexidade!");
        }
        if (!SE.equals("Selecione") && !complexidade.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("SE")) {
                if (dados.getNome().equals(SE)) {
                    this.contadorCHGB += dados.getValor();
                    this.contadorCHGA += auxCalculo.getValorComplexidade("SE", cbComplexidadeAltSE.getSelectedIndex());
                    this.desc += "ALTERADO (SE)\n" + dados.getNome() + "\nValor anterior = " + dados.getValor() + "\nValor de alteração = " + auxCalculo.getValorComplexidade("SE", cbComplexidadeAltSE.getSelectedIndex()) + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    this.tfCHGB.setText(this.contadorCHGB + "");
                    this.cbSaidasSE.removeItem(SE);
                    this.cbArquivosAltSE.removeItem(SE);
                }
            }
        }
    }

    private void altEE() {
        String EE, complexidade;
        EE = cbArquivosAltEE.getSelectedItem().toString();
        complexidade = cbComplexidadeAltEE.getSelectedItem().toString();
        if (EE.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione o EE!");
        }
        if (complexidade.equals("Selecione")) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione a complexidade!");
        }
        if (!EE.equals("Selecione") && !complexidade.equals("Selecione")) {
            for (Dados dados : calculoFPA.getListaDado("EE")) {
                if (dados.getNome().equals(EE)) {
                    this.contadorCHGB += dados.getValor();
                    this.contadorCHGA += auxCalculo.getValorComplexidade("EE", cbComplexidadeAltEE.getSelectedIndex());
                    this.desc += "ALTERADO (EE)\n" + dados.getNome() + "\nValor anterior = " + dados.getValor() + "\nValor de alteração = " + auxCalculo.getValorComplexidade("EE", cbComplexidadeAltEE.getSelectedIndex()) + "\n";
                    this.desc += "----------------------------------\n";
                    this.taResultadoPRJ.setText(this.desc);
                    this.tfCHGB.setText(this.contadorCHGB + "");
                    this.cbEntradasDelEE.removeItem(EE);
                    this.cbArquivosAltEE.removeItem(EE);
                }
            }
        }
    }

    private void calcularPRJ() {
        if (flagCalculado) {
            limpaTudo();
            btCalcularPRJ.setText("Calcular");
            flagCalculado = false;
            return;
        }
        try {
            if (tfCFP.getText().equals("")) {
                this.campoCFP = 0;
            }
            if (!this.tfVAFA.getText().equals("")) {
                this.campoCFP = Integer.parseInt(tfCFP.getText());
                VAFA = Double.parseDouble(this.tfVAFA.getText());
                //preenche a tela
                this.desc += "----------------------------------\n\n";
                this.desc += "         VALORES TOTAIS\n";
                this.desc += "ADD = " + contadorADD + "\n"
                        + "DEL = " + contadorDEL + "\n"
                        + "CHGA = " + contadorCHGA + "\n"
                        + "CHGB = " + contadorCHGB + "\n"
                        + "UFPB = " + this.calculoFPA.getPFB() + "\n"
                        + "VAFB = " + this.calculoFPA.getFA() + "\n"
                        + "VAFA = " + VAFA + "\n"
                        + "CFP = " + campoCFP + "\n";
                this.desc += "\nProjeto de melhoria.........." + ResultadoProjMelhoria() + "\n";
                this.desc += "Projeto após melhoria........" + ResultadoAposMelhoria() + "\n";
                if (ResultadoAposMelhoria() >= ResultadoProjMelhoria()) {
                    this.desc += "Tamanho da aplicação após melhoria:\n" + (ResultadoAposMelhoria() - ResultadoProjMelhoria() + "\n");
                } else {
                    this.desc += "Tamanho da aplicação após melhoria:\n" + (ResultadoProjMelhoria() - ResultadoAposMelhoria() + "\n");
                }

                this.taResultadoPRJ.setText(desc);
                this.flagCalculado = true;
                btCalcularPRJ.setText("Novo");
            } else {
                JOptionPane.showMessageDialog(null, "Campo VAFA esté sem valor!\nPor favor, Informe um valor.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entradas inválidas! Verifique.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double ResultadoProjMelhoria() {
        //FORMULA     EFP = [ (ADD + CHGA + CFP)*VAFA ]+(DEL * VAFB)
        return (((this.contadorADD + contadorCHGA + campoCFP) * VAFA) + (contadorDEL * this.calculoFPA.getFA()));
    }

    public double ResultadoAposMelhoria() {
        //FORMULA     AFP =  [(UFPB+ADD+CHGA)-(CHGB+DEL)] * VAFA
        return ((calculoFPA.getPFB() + contadorADD + contadorCHGA) - (contadorCHGB + contadorDEL)) * VAFA;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btDescricaoALI) {
            DescricaoALI descricao = new DescricaoALI();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btAddALI) {
            addALI();
        }
        if (evento.getSource() == btCalcularALI) {
            calcularALI();
        }
        if (evento.getSource() == btCancelarALI) {
            cancelarALI();
        }
        if (evento.getSource() == btDescricaoAIE) {
            DescricaoAIE descricao = new DescricaoAIE();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btAddAIE) {
            addAIE();
        }
        if (evento.getSource() == btCalcularAIE) {
            calcularAIE();
        }
        if (evento.getSource() == btCancelarAIE) {
            cancelarAIE();
        }
        if (evento.getSource() == btDescricaoEE) {
            DescricaoEE descricao = new DescricaoEE();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btAddEE) {
            addEE();
        }
        if (evento.getSource() == btCalcularEE) {
            calcularEE();
        }
        if (evento.getSource() == btCancelarEE) {
            cancelarEE();
        }
        if (evento.getSource() == btDescricaoCE) {
            DescricaoCE descricao = new DescricaoCE();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btAddCE) {
            addCE();
        }
        if (evento.getSource() == btCalcularCE) {
            calcularCE();
        }
        if (evento.getSource() == btCancelarCE) {
            cancelarCE();
        }
        if (evento.getSource() == btDescricaoSE) {
            DescricaoSE descricao = new DescricaoSE();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btAddSE) {
            addSE();
        }
        if (evento.getSource() == btCalcularSE) {
            calcularSE();
        }
        if (evento.getSource() == btCancelarSE) {
            cancelarSE();
        }
        if (evento.getSource() == btDescricaoNI) {
            DescricaoNI descricao = new DescricaoNI();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        if (evento.getSource() == btCalcularNI) {
            calcularNI();
        }
        if (evento.getSource() == btCancelarNI) {
            cancelarNI();
        }
        if (evento.getSource() == btMostrar) {
            mostrar();
        }
        if (evento.getSource() == btOK) {
            try {
                ok();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (evento.getSource() == btNovo) {
            novo();
        }
        if (evento.getSource() == btDescricaoPrj) {
            DescricaoPrj descricao = new DescricaoPrj();
            descricao.setModal(true);
            descricao.setVisible(true);
        }
        //projeto melhoria
        if (evento.getSource() == btAdd) {
            addPrjMelhoria();//adicionar um novo tipo
        }
        if (evento.getSource() == btDel) {
            this.contadorDEL += validaDEL();//deletar um tipo já existente.
        }
        if (evento.getSource() == btAltALI) {
            altALI();
        }
        if (evento.getSource() == btAltAIE) {
            altAIE();
        }
        if (evento.getSource() == btAltCE) {
            altCE();
        }
        if (evento.getSource() == btAltSE) {
            altSE();
        }
        if (evento.getSource() == btAltEE) {
            altEE();
        }
        if (evento.getSource() == btCalcularPRJ) {
            calcularPRJ();
        }
        if (evento.getSource() == btCancelarPRJ) {
            limpaTudo();
        }
    }
}
