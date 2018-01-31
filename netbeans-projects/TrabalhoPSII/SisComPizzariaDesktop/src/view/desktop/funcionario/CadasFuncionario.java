package view.desktop.funcionario;

import control.Servidor;
import control.funcoes.Excecao;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import control.funcoes.Funcoes;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.entidades.Funcionario;
import model.entidades.Usuario;
import view.componentes.document.CampoValor;
import view.componentes.mascara.MaskFormater;
import view.componentes.table.TableRenderer;
import view.desktop.AcaoListener;
import view.desktop.ClickListener;
import view.desktop.SisComPizzaria;

public class CadasFuncionario extends JPanel implements AcaoListener {

    private SisComPizzaria sisCom;
    private ClickListener clickListener;
    private TableModelFuncionario tableModelFuncionario;
    private Funcionario funcionario;
    private String cardVisivel;
    private int linhaAtual;
    private CampoValor campoValorSalario;

    public CadasFuncionario(SisComPizzaria sisCom) {
        this.sisCom = sisCom;
        this.clickListener = sisCom;
        this.sisCom.setAcaoListener(this);
        this.tableModelFuncionario = new TableModelFuncionario(this);
        this.campoValorSalario = new CampoValor(true, false);
        initComponents();
        ativar();
    }

    private void ativar() {
        //table grig
        tbFuncionario.inicializaTable();
        tbFuncionario.getTabela().getColumnModel().getColumn(0).setMinWidth(200);
        tbFuncionario.getTabela().getColumnModel().getColumn(1).setMinWidth(105);
        tbFuncionario.getTabela().getColumnModel().getColumn(2).setMinWidth(237);
        tbFuncionario.getTabela().getColumnModel().getColumn(3).setMinWidth(150);
        tbFuncionario.getTabela().getColumnModel().getColumn(4).setMinWidth(70);
        tbFuncionario.getTabela().getColumnModel().getColumn(5).setMinWidth(140);
        tbFuncionario.getTabela().getColumnModel().getColumn(6).setMinWidth(160);
        tbFuncionario.getTabela().getColumnModel().getColumn(7).setMinWidth(95);
        tbFuncionario.getTabela().getColumnModel().getColumn(8).setMinWidth(235);
        pnDados.inicializaPanel();
        pnDados.setPermiteAlterarDados(sisCom.isPermiteAlterarDados(1));
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if (tableModelFuncionario.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        cardVisivel = "grid";
        linhaAtual = 0;
    }

    public Servidor getServidor() {
        return sisCom.getServidor();
    }

    public Usuario getUsuario() {
        return sisCom.getUsuario();
    }

    @Override
    public void onAlternarGridDetalhe(boolean grid) {
        if (grid) {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "grid");
            cardVisivel = "grid";
            desfazEdicao();
            if (tableModelFuncionario.possuiRegistros()) {
                tbFuncionario.selecionarLinha(tableModelFuncionario.getLinhaSelecionada());
            }
        } else {
            ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
            cardVisivel = "detalhe";
            linhaAtual = tbFuncionario.getTabela().getSelectedRow();
            mostraRegistro();
        }
    }

    @Override
    public void onNovo() {
        clickListener.onSalvar(true);
        clickListener.onDesfazer(true);
        clickListener.onNovo(false);
        clickListener.onExcluir(false);
        sisCom.acaoBtGridDetalhe(true);
        ((CardLayout) pnCard.getLayout()).show(pnCard, "detalhe");
        cardVisivel = "detalhe";
        pnDados.setEdicao(true);
        ftfDtAdmissao.setEditable(true);
        pnDados.limparCampos();
        funcionario = null;
        tfNmFuncionario.requestFocus();
    }

    @Override
    public void onSalvar() {
        if (pnDados.verificaCamposObrigatoriosPreenchidos()) {
            Excecao excecao = null;
            try {
                boolean novoFuncionario = false;
                if (funcionario == null) {
                    novoFuncionario = true;
                }
                funcionario = getServidor().getFuncionarioAction().salvar(pnDados.getDadosPanel(null), funcionario);
                if (funcionario != null) {
                    if (novoFuncionario) {
                        tableModelFuncionario.addFuncionario(funcionario);
                    }
                    tableModelFuncionario.atualizaTabela();
                    desfazEdicao();
                    ftfDtAdmissao.setEditable(false);
                }
            } catch (ExceptionInfo ex) {
                excecao = ex;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Pizza Nostra - Usuário", JOptionPane.INFORMATION_MESSAGE);
            } catch (ExceptionError ex) {
                excecao = ex;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
            }
            if (excecao != null) {
                pnDados.setFocusCampo(excecao.getDsCampoFocar());
            }
        }
    }

    @Override
    public void onDesfazer() {
        if (pnDados.isEdicao()) {
            pnDados.limparCampos();
        }
        if (funcionario != null) {
            mostraRegistro();
        }
        desfazEdicao();
    }

    @Override
    public void onExcluir() {
        pnDados.setEdicao(false);
        try {
            if ("detalhe".equalsIgnoreCase(cardVisivel)) {
                getServidor().getFuncionarioAction().excluirFuncionario(tableModelFuncionario.removerFuncionario(funcionario).getCdFuncionario());
                pnDados.limparCampos();
                ftfDtAdmissao.setEditable(true);
            } else {
                getServidor().getFuncionarioAction().excluirFuncionario(tableModelFuncionario.removerFuncionario(tbFuncionario.getTabela().getSelectedRow()).getCdFuncionario());
            }
        } catch (ExceptionError ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
        funcionario = null;
        tableModelFuncionario.atualizaTabela();
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelFuncionario.possuiRegistros()) {
            tbFuncionario.selecionarLinha(tableModelFuncionario.getLinhaSelecionada());
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else {
            clickListener.onExcluir(false);
        }
    }

    private void mostraRegistro() {
        if (tableModelFuncionario.possuiRegistros()) {
            campoValorSalario.setKeyPressed(false);
            pnDados.setKeyPressed(false);
            funcionario = tableModelFuncionario.getFuncionario(linhaAtual);
            tfNmFuncionario.setText(funcionario.getNmFuncionario());
            tfNmUsuario.setText(funcionario.getUsuario().getNmUsuario());
            tfDsEndereco.setText(funcionario.getEndereco().getDsEndereco());
            tfNmBairro.setText(funcionario.getEndereco().getNmBairro());
            ftfNrCep.setText(funcionario.getEndereco().getNrCep());
            tfNrResidencia.setText(Integer.toString(funcionario.getEndereco().getNrResidencia()));
            tfNmCidade.setText(funcionario.getEndereco().getNmCidade());
            tfDsReferencia.setText(funcionario.getEndereco().getDsReferencia());
            ftfNrRG.setText(funcionario.getNrRg());
            ftfNrCPF.setText(funcionario.getNrCpf());
            ftfNrCarteiraTrabalho.setText(funcionario.getNrCarteiraTrabalho());
            ftfNrPisPasep.setText(funcionario.getNrPisPasep());
            ftfNrCNH.setText(funcionario.getNrCnh());
            tfDsCargo.setText(funcionario.getDsCargo());
            ftfNrTelefone.setText(funcionario.getNrTefefone());
            ftfNrCelular.setText(funcionario.getNrCelular());
            tfVlSalario.setText(Double.toString(funcionario.getVlSalario()).replace(".", ","));
            tfDsEmail.setText(funcionario.getUsuario().getDsEmail());
            ftfDtAdmissao.setText(Funcoes.dateToString(funcionario.getDtAdmissao(), 3));
            ftfDtDemissao.setText(Funcoes.dateToString(funcionario.getDtDemissao(), 3));
            tfDsObservacao.setText(funcionario.getDsObservacao());
            pfDsSenha.setText("");
            pfDsConfirmaSenha.setText("");
            ftfDtAdmissao.setEditable(false);
            tfNmFuncionario.requestFocus();
            pnDados.setKeyPressed(true);
        } else {
            pnDados.limparCampos();
            ftfDtAdmissao.setEditable(true);
            tfNmFuncionario.requestFocus();
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    private void desfazEdicao() {
        clickListener.onSalvar(false);
        clickListener.onDesfazer(false);
        clickListener.onNovo(sisCom.isPermiteAlterarDados(1));
        if ("grid".equalsIgnoreCase(cardVisivel)
                && tableModelFuncionario.possuiRegistros()) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        } else if (funcionario != null) {
            clickListener.onExcluir(sisCom.isPermiteAlterarDados(1));
        }
        pnDados.setEdicao(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        pnCard = new javax.swing.JPanel();
        pnGrid = new javax.swing.JPanel();
        tbFuncionario = new view.componentes.table.TTable();
        pnDetalhe = new javax.swing.JPanel();
        pnDados = new view.componentes.TPanel();
        lbNmFuncionario = new javax.swing.JLabel();
        tfNmFuncionario = new view.componentes.TTextField();
        lbNmUsuario = new javax.swing.JLabel();
        tfNmUsuario = new view.componentes.TTextField();
        lbDsSenha = new javax.swing.JLabel();
        pfDsSenha = new view.componentes.TPasswordField();
        lbDsConfirmaSenha = new javax.swing.JLabel();
        pfDsConfirmaSenha = new view.componentes.TPasswordField();
        lbDsEndereco = new javax.swing.JLabel();
        tfDsEndereco = new view.componentes.TTextField();
        lbNmBairro = new javax.swing.JLabel();
        tfNmBairro = new view.componentes.TTextField();
        lbNrCep = new javax.swing.JLabel();
        ftfNrCep = new view.componentes.TFormattedTextField();
        lbNrResidencia = new javax.swing.JLabel();
        tfNrResidencia = new view.componentes.TTextField();
        lbNmCidade = new javax.swing.JLabel();
        tfNmCidade = new view.componentes.TTextField();
        lbDsReferencia = new javax.swing.JLabel();
        tfDsReferencia = new view.componentes.TTextField();
        lbNrRG = new javax.swing.JLabel();
        ftfNrRG = new view.componentes.TFormattedTextField();
        lbNrCPF = new javax.swing.JLabel();
        ftfNrCPF = new view.componentes.TFormattedTextField();
        lbNrCarteiraTrabalho = new javax.swing.JLabel();
        ftfNrCarteiraTrabalho = new view.componentes.TFormattedTextField();
        lbNrPisPassep = new javax.swing.JLabel();
        ftfNrPisPasep = new view.componentes.TFormattedTextField();
        lbNrCNH = new javax.swing.JLabel();
        ftfNrCNH = new view.componentes.TFormattedTextField();
        lbDsCargo = new javax.swing.JLabel();
        tfDsCargo = new view.componentes.TTextField();
        lbNrTelefone = new javax.swing.JLabel();
        ftfNrTelefone = new view.componentes.TFormattedTextField();
        lbNrCelular = new javax.swing.JLabel();
        ftfNrCelular = new view.componentes.TFormattedTextField();
        lbVlSalario = new javax.swing.JLabel();
        tfVlSalario = new view.componentes.TTextField();
        lbDsEmail = new javax.swing.JLabel();
        tfDsEmail = new view.componentes.TTextField();
        lbDtAdmissao = new javax.swing.JLabel();
        ftfDtAdmissao = new view.componentes.TFormattedTextField();
        lbDtDemissao = new javax.swing.JLabel();
        ftfDtDemissao = new view.componentes.TFormattedTextField();
        lbDsObservacao = new javax.swing.JLabel();
        tfDsObservacao = new view.componentes.TTextField();

        setLayout(new java.awt.BorderLayout());

        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setPreferredSize(new java.awt.Dimension(0, 30));

        lbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitulo.setText("Funcionários");
        pnTitulo.add(lbTitulo);

        add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnCard.setLayout(new java.awt.CardLayout());

        pnGrid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnGrid.setLayout(new java.awt.BorderLayout());

        tbFuncionario.setConfigurar(true);
        tbFuncionario.setRendener(new TableRenderer(false, 0));
        tbFuncionario.setTableModel(tableModelFuncionario);
        pnGrid.add(tbFuncionario, java.awt.BorderLayout.CENTER);

        pnCard.add(pnGrid, "grid");

        pnDetalhe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnDetalhe.setLayout(null);

        pnDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnDados.setClickListener(clickListener);
        pnDados.setListenerkeyPressed(true);
        pnDados.setTemCamposObrigatorios(true);
        pnDados.setValidaCamposPanel(true);
        pnDados.setLayout(null);

        lbNmFuncionario.setText("Nome");
        pnDados.add(lbNmFuncionario);
        lbNmFuncionario.setBounds(55, 20, 30, 14);

        tfNmFuncionario.setName("NM_FUNCIONARIO");
        tfNmFuncionario.setObrigatorio(true);
        pnDados.add(tfNmFuncionario);
        tfNmFuncionario.setBounds(90, 18, 200, 20);

        lbNmUsuario.setText("Usuário");
        pnDados.add(lbNmUsuario);
        lbNmUsuario.setBounds(295, 20, 40, 14);

        tfNmUsuario.setName("NM_USUARIO");
        tfNmUsuario.setObrigatorio(true);
        tfNmUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNmUsuarioFocusGained(evt);
            }
        });
        pnDados.add(tfNmUsuario);
        tfNmUsuario.setBounds(335, 18, 100, 20);

        lbDsSenha.setText("Senha");
        pnDados.add(lbDsSenha);
        lbDsSenha.setBounds(440, 20, 30, 14);

        pfDsSenha.setName("DS_SENHA");
        pfDsSenha.setObrigatorio(true);
        pnDados.add(pfDsSenha);
        pfDsSenha.setBounds(475, 18, 100, 20);

        lbDsConfirmaSenha.setText("Confirma Senha");
        pnDados.add(lbDsConfirmaSenha);
        lbDsConfirmaSenha.setBounds(5, 48, 80, 14);

        pfDsConfirmaSenha.setName("DS_CONFIRMA_SENHA");
        pnDados.add(pfDsConfirmaSenha);
        pfDsConfirmaSenha.setBounds(90, 46, 100, 20);

        lbDsEndereco.setText("Endereço");
        pnDados.add(lbDsEndereco);
        lbDsEndereco.setBounds(195, 48, 45, 14);

        tfDsEndereco.setName("DS_ENDERECO");
        tfDsEndereco.setObrigatorio(true);
        pnDados.add(tfDsEndereco);
        tfDsEndereco.setBounds(245, 46, 330, 20);

        lbNmBairro.setText("Bairro");
        pnDados.add(lbNmBairro);
        lbNmBairro.setBounds(53, 76, 30, 14);

        tfNmBairro.setName("DS_BAIRRO");
        tfNmBairro.setObrigatorio(true);
        pnDados.add(tfNmBairro);
        tfNmBairro.setBounds(90, 74, 200, 20);

        lbNrCep.setText("Cep");
        pnDados.add(lbNrCep);
        lbNrCep.setBounds(295, 76, 20, 14);

        ftfNrCep.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCep.setFormatterFactory(MaskFormater.getMaskFormater(3));
        ftfNrCep.setName("NR_CEP");
        ftfNrCep.setObrigatorio(true);
        pnDados.add(ftfNrCep);
        ftfNrCep.setBounds(320, 74, 110, 20);

        lbNrResidencia.setText("Número");
        pnDados.add(lbNrResidencia);
        lbNrResidencia.setBounds(435, 76, 40, 14);

        tfNrResidencia.setDocument(new CampoValor(false, false));
        tfNrResidencia.setName("NR_RESIDENCIA");
        tfNrResidencia.setObrigatorio(true);
        pnDados.add(tfNrResidencia);
        tfNrResidencia.setBounds(480, 74, 95, 20);

        lbNmCidade.setText("Cidade");
        pnDados.add(lbNmCidade);
        lbNmCidade.setBounds(47, 104, 35, 14);

        tfNmCidade.setName("NM_CIDADE");
        tfNmCidade.setObrigatorio(true);
        pnDados.add(tfNmCidade);
        tfNmCidade.setBounds(90, 102, 160, 20);

        lbDsReferencia.setText("Referência");
        pnDados.add(lbDsReferencia);
        lbDsReferencia.setBounds(255, 104, 55, 14);

        tfDsReferencia.setName("DS_REFERENCIA");
        pnDados.add(tfDsReferencia);
        tfDsReferencia.setBounds(315, 102, 260, 20);

        lbNrRG.setText("RG");
        pnDados.add(lbNrRG);
        lbNrRG.setBounds(65, 132, 15, 14);

        ftfNrRG.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrRG.setFormatterFactory(MaskFormater.getMaskFormater(4));
        ftfNrRG.setName("NR_RG");
        ftfNrRG.setObrigatorio(true);
        pnDados.add(ftfNrRG);
        ftfNrRG.setBounds(90, 130, 90, 20);

        lbNrCPF.setText("CPF");
        pnDados.add(lbNrCPF);
        lbNrCPF.setBounds(187, 132, 20, 14);

        ftfNrCPF.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCPF.setFormatterFactory(MaskFormater.getMaskFormater(5));
        ftfNrCPF.setName("NR_CPF");
        ftfNrCPF.setObrigatorio(true);
        pnDados.add(ftfNrCPF);
        ftfNrCPF.setBounds(211, 130, 100, 20);

        lbNrCarteiraTrabalho.setText("Carteira Trab. / Série");
        pnDados.add(lbNrCarteiraTrabalho);
        lbNrCarteiraTrabalho.setBounds(320, 132, 105, 14);

        ftfNrCarteiraTrabalho.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCarteiraTrabalho.setFormatterFactory(MaskFormater.getMaskFormater(6));
        ftfNrCarteiraTrabalho.setName("NR_CARTEIRA_TRABALHO");
        ftfNrCarteiraTrabalho.setObrigatorio(true);
        pnDados.add(ftfNrCarteiraTrabalho);
        ftfNrCarteiraTrabalho.setBounds(427, 130, 147, 20);

        lbNrPisPassep.setText("Pis/Pasep");
        pnDados.add(lbNrPisPassep);
        lbNrPisPassep.setBounds(34, 160, 47, 14);

        ftfNrPisPasep.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrPisPasep.setFormatterFactory(MaskFormater.getMaskFormater(8));
        ftfNrPisPasep.setName("NR_PIS_PASEP");
        ftfNrPisPasep.setObrigatorio(true);
        pnDados.add(ftfNrPisPasep);
        ftfNrPisPasep.setBounds(90, 158, 120, 20);

        lbNrCNH.setText("CNH");
        pnDados.add(lbNrCNH);
        lbNrCNH.setBounds(215, 160, 25, 14);

        ftfNrCNH.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCNH.setFormatterFactory(MaskFormater.getMaskFormater(7));
        ftfNrCNH.setName("NR_CNH");
        pnDados.add(ftfNrCNH);
        ftfNrCNH.setBounds(240, 158, 100, 20);

        lbDsCargo.setText("Cargo");
        pnDados.add(lbDsCargo);
        lbDsCargo.setBounds(345, 160, 30, 14);

        tfDsCargo.setName("DS_CARGO");
        tfDsCargo.setObrigatorio(true);
        pnDados.add(tfDsCargo);
        tfDsCargo.setBounds(380, 158, 194, 20);

        lbNrTelefone.setText("Telefone");
        pnDados.add(lbNrTelefone);
        lbNrTelefone.setBounds(37, 188, 45, 14);

        ftfNrTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrTelefone.setFormatterFactory(MaskFormater.getMaskFormater(2));
        ftfNrTelefone.setName("NR_TELEFONE");
        pnDados.add(ftfNrTelefone);
        ftfNrTelefone.setBounds(90, 186, 120, 20);

        lbNrCelular.setText("Celular");
        pnDados.add(lbNrCelular);
        lbNrCelular.setBounds(217, 188, 35, 14);

        ftfNrCelular.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfNrCelular.setFormatterFactory(MaskFormater.getMaskFormater(2));
        ftfNrCelular.setName("NR_CELULAR");
        pnDados.add(ftfNrCelular);
        ftfNrCelular.setBounds(257, 186, 120, 20);

        lbVlSalario.setText("Salário");
        pnDados.add(lbVlSalario);
        lbVlSalario.setBounds(385, 188, 35, 20);

        tfVlSalario.setDocument(campoValorSalario);
        tfVlSalario.setName("VL_SALARIO");
        tfVlSalario.setObrigatorio(true);
        pnDados.add(tfVlSalario);
        tfVlSalario.setBounds(424, 186, 149, 20);

        lbDsEmail.setText("Email");
        pnDados.add(lbDsEmail);
        lbDsEmail.setBounds(54, 216, 25, 20);

        tfDsEmail.setName("DS_EMAIL");
        pnDados.add(tfDsEmail);
        tfDsEmail.setBounds(90, 214, 150, 20);

        lbDtAdmissao.setText("Dt. Admissão");
        pnDados.add(lbDtAdmissao);
        lbDtAdmissao.setBounds(247, 216, 65, 14);

        ftfDtAdmissao.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfDtAdmissao.setFormatterFactory(MaskFormater.getMaskFormater(1));
        ftfDtAdmissao.setName("DT_ADMISSAO");
        ftfDtAdmissao.setObrigatorio(true);
        pnDados.add(ftfDtAdmissao);
        ftfDtAdmissao.setBounds(315, 214, 90, 20);

        lbDtDemissao.setText("Dt. Demissão");
        pnDados.add(lbDtDemissao);
        lbDtDemissao.setBounds(410, 216, 65, 14);

        ftfDtDemissao.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        ftfDtDemissao.setFormatterFactory(MaskFormater.getMaskFormater(1));
        ftfDtDemissao.setName("DT_DEMISSAO");
        pnDados.add(ftfDtDemissao);
        ftfDtDemissao.setBounds(480, 214, 93, 20);

        lbDsObservacao.setText("Observação");
        pnDados.add(lbDsObservacao);
        lbDsObservacao.setBounds(21, 244, 60, 20);

        tfDsObservacao.setName("DS_OBSERVACAO");
        pnDados.add(tfDsObservacao);
        tfDsObservacao.setBounds(90, 242, 483, 20);

        pnDetalhe.add(pnDados);
        pnDados.setBounds(225, 110, 665, 280);

        pnCard.add(pnDetalhe, "detalhe");

        add(pnCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tfNmUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNmUsuarioFocusGained
        String[] s = tfNmFuncionario.getText().toLowerCase().split(" ");
        String nmUsuario = "";
        String str;
        for (int i = 0; i < s.length; i++) {
            str = s[i];
            if (i == s.length - 1) {
                nmUsuario += str;
            } else {
                nmUsuario += str.substring(0, 1);
            }
        }
        tfNmUsuario.setText(nmUsuario);
    }//GEN-LAST:event_tfNmUsuarioFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.componentes.TFormattedTextField ftfDtAdmissao;
    private view.componentes.TFormattedTextField ftfDtDemissao;
    private view.componentes.TFormattedTextField ftfNrCNH;
    private view.componentes.TFormattedTextField ftfNrCPF;
    private view.componentes.TFormattedTextField ftfNrCarteiraTrabalho;
    private view.componentes.TFormattedTextField ftfNrCelular;
    private view.componentes.TFormattedTextField ftfNrCep;
    private view.componentes.TFormattedTextField ftfNrPisPasep;
    private view.componentes.TFormattedTextField ftfNrRG;
    private view.componentes.TFormattedTextField ftfNrTelefone;
    private javax.swing.JLabel lbDsCargo;
    private javax.swing.JLabel lbDsConfirmaSenha;
    private javax.swing.JLabel lbDsEmail;
    private javax.swing.JLabel lbDsEndereco;
    private javax.swing.JLabel lbDsObservacao;
    private javax.swing.JLabel lbDsReferencia;
    private javax.swing.JLabel lbDsSenha;
    private javax.swing.JLabel lbDtAdmissao;
    private javax.swing.JLabel lbDtDemissao;
    private javax.swing.JLabel lbNmBairro;
    private javax.swing.JLabel lbNmCidade;
    private javax.swing.JLabel lbNmFuncionario;
    private javax.swing.JLabel lbNmUsuario;
    private javax.swing.JLabel lbNrCNH;
    private javax.swing.JLabel lbNrCPF;
    private javax.swing.JLabel lbNrCarteiraTrabalho;
    private javax.swing.JLabel lbNrCelular;
    private javax.swing.JLabel lbNrCep;
    private javax.swing.JLabel lbNrPisPassep;
    private javax.swing.JLabel lbNrRG;
    private javax.swing.JLabel lbNrResidencia;
    private javax.swing.JLabel lbNrTelefone;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbVlSalario;
    private view.componentes.TPasswordField pfDsConfirmaSenha;
    private view.componentes.TPasswordField pfDsSenha;
    private javax.swing.JPanel pnCard;
    private view.componentes.TPanel pnDados;
    private javax.swing.JPanel pnDetalhe;
    private javax.swing.JPanel pnGrid;
    private javax.swing.JPanel pnTitulo;
    private view.componentes.table.TTable tbFuncionario;
    private view.componentes.TTextField tfDsCargo;
    private view.componentes.TTextField tfDsEmail;
    private view.componentes.TTextField tfDsEndereco;
    private view.componentes.TTextField tfDsObservacao;
    private view.componentes.TTextField tfDsReferencia;
    private view.componentes.TTextField tfNmBairro;
    private view.componentes.TTextField tfNmCidade;
    private view.componentes.TTextField tfNmFuncionario;
    private view.componentes.TTextField tfNmUsuario;
    private view.componentes.TTextField tfNrResidencia;
    private view.componentes.TTextField tfVlSalario;
    // End of variables declaration//GEN-END:variables
}
