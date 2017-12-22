package view.patrimonio;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.Ambiente;
import model.Empresa;
import model.Fornecedor;
import model.Patrimonio;

import dbOracle.AmbienteDAO;
import dbOracle.EmpresaDAO;
import dbOracle.FornecedorDAO;
import dbOracle.PatrimonioDAO;

import view.CamposInt;
import view.PanelComponentes;
import view.ValidaObjeto;
import view.ambiente.ConsultaAmbiente;
import view.ambiente.ListenerAmbiente;
import view.empresa.ConsultaEmpresa;
import view.empresa.ListenerEmpresa;
import view.fornecedor.ConsultaFornecedor;
import view.fornecedor.ListenerFornecedor;

public class CadasPatrimonio extends JDialog implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	private JTextField tfCodigo, tfDataCadas, tfUltAlteracao, tfDescricao,
			tfCodAmbiente, tfAmbiente, tfNotaFiscal, tfNumSerie,
			tfMesesGarantia, tfValor, tfCodForn, tfFornecedor, tfCodEmp,
			tfEmpresa;
	private JFormattedTextField ftfDataAquisicao;
	private JComboBox cbSituacaoBem;
	private JButton btConsulta, btConsultaAmb, btConsultaForn, btConsultaEmp,
			btOk, btCancelar, btExcluir, btSair;
	private JLabel lbDescricaoObrig, lbCodAmbObrig, lbDataAquisicaoObrig,
			lbNumNotFiscalObrig, lbNumSerieObrig, lbMesesGarantiaObrig,
			lbValorObrig, lbCodFornObrig, lbCodEmpObrig, lbSitBemObrig;
	private SimpleDateFormat formatDate;
	private SimpleDateFormat formatHora;
	private SimpleDateFormat formatDateHora;
	private AmbienteDAO ambienteDAO;
	private FornecedorDAO fornecedorDAO;
	private EmpresaDAO empresaDAO;
	private PatrimonioDAO patrimonioDAO;
	private ValidaObjeto validaObjeto;

	public CadasPatrimonio(Connection con) {
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
		formatHora = new SimpleDateFormat("HH:mm");
		formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ambienteDAO = new AmbienteDAO(con);
		fornecedorDAO = new FornecedorDAO(con);
		empresaDAO = new EmpresaDAO(con);
		patrimonioDAO = new PatrimonioDAO(con);
		validaObjeto = new ValidaObjeto();
		try {
			initComponents();
		} catch (ParseException ex) {}
	}

	private void initComponents() throws ParseException {
		this.setTitle("Cadastro de Patrimônio");
		this.setLayout(null);

		PanelComponentes panel = new PanelComponentes(5, 5, 430, 307);
		this.add(panel);

		panel.addJLabel("Codigo", 20, 20, 50, 14);

		tfCodigo = panel.addJTextFieldNaoEdit(20, 38, 80, 20);
		try {
			tfCodigo.setText(Integer.toString(patrimonioDAO.getProxCodPatrimonio()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
		}
		tfCodigo.addFocusListener(this);

		btConsulta = panel.addJButton("...", "Consulta Patrimônios", 105, 34, 31, 26);
		btConsulta.addActionListener(this);

		panel.addJLabel("Cadastro em", 143, 20, 90, 14);

		tfDataCadas = panel.addJTextFieldNaoEdit(143, 38, 130, 20);
		tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
		tfDataCadas.addFocusListener(this);

		panel.addJLabel("Última Alteração", 280, 20, 100, 14);

		tfUltAlteracao = panel.addJTextFieldNaoEdit(280, 38, 130, 20);
		tfUltAlteracao.addFocusListener(this);

		panel.addJLabel("Descrição", 20, 63, 50, 14);

		lbDescricaoObrig = panel.addJLabelRED(69, 66, 10, 14);

		tfDescricao = panel.addJTextField(20, 81, 260, 20);
		tfDescricao.addFocusListener(this);

		panel.addJLabel("Cod. Ambiente", 290, 63, 80, 14);

		lbCodAmbObrig = panel.addJLabelRED(365, 66, 10, 14);

		tfCodAmbiente = panel.addJTextFieldNaoEdit(290, 81, 80, 20);
		tfCodAmbiente.addFocusListener(this);

		btConsultaAmb = panel.addJButton("...", "Consulta Ambientes", 380, 77, 31, 26);
		btConsultaAmb.addActionListener(this);

		panel.addJLabel("Ambiente", 20, 105, 70, 14);

		tfAmbiente = panel.addJTextFieldNaoEdit(20, 123, 143, 20);
		tfAmbiente.addFocusListener(this);

		panel.addJLabel("Data Aquisição", 170, 105, 80, 14);

		lbDataAquisicaoObrig = panel.addJLabelRED(243, 108, 10, 14);

		ftfDataAquisicao = panel.addJFormattedTextField("##/##/####", 170, 123, 83, 20);
		ftfDataAquisicao.addFocusListener(this);

		panel.addJLabel("Nº Nota Fiscal", 260, 105, 80, 14);

		lbNumNotFiscalObrig = panel.addJLabelRED(329, 108, 10, 14);

		tfNotaFiscal = panel.addJTextField(260, 123, 72, 20);
		tfNotaFiscal.setDocument(new CamposInt());
		tfNotaFiscal.addFocusListener(this);

		panel.addJLabel("Num Série", 340, 105, 80, 14);

		lbNumSerieObrig = panel.addJLabelRED(390, 108, 10, 14);

		tfNumSerie = panel.addJTextField(340, 123, 70, 20);
		tfNumSerie.addFocusListener(this);

		panel.addJLabel("Meses Garantia", 20, 147, 80, 14);

		lbMesesGarantiaObrig = panel.addJLabelRED(96, 150, 10, 14);

		tfMesesGarantia = panel.addJTextField(20, 165, 80, 20);
		tfMesesGarantia.setDocument(new CamposInt());
		tfMesesGarantia.addFocusListener(this);

		panel.addJLabel("Valor", 107, 147, 50, 14);

		lbValorObrig = panel.addJLabelRED(132, 150, 10, 14);

		tfValor = panel.addJTextField(107, 165, 55, 20);
		tfValor.addFocusListener(this);

		panel.addJLabel("Cod. Fornecedor", 170, 147, 85, 14);

		lbCodFornObrig = panel.addJLabelRED(253, 150, 10, 14);

		tfCodForn = panel.addJTextFieldNaoEdit(170, 165, 80, 20);
		tfCodForn.addFocusListener(this);

		btConsultaForn = panel.addJButton("...", "Consulta Forcecedores", 255, 161, 31, 26);
		btConsultaForn.addActionListener(this);

		panel.addJLabel("Fornecedor", 290, 147, 70, 14);

		tfFornecedor = panel.addJTextFieldNaoEdit(290, 165, 120, 20);
		tfFornecedor.addFocusListener(this);

		panel.addJLabel("Cod. Empresa", 20, 189, 80, 14);

		lbCodEmpObrig = panel.addJLabelRED(89, 192, 10, 14);

		tfCodEmp = panel.addJTextFieldNaoEdit(20, 207, 80, 20);
		tfCodEmp.addFocusListener(this);

		btConsultaEmp = panel.addJButton("...", "Consulta Empresas", 105, 203, 31, 26);
		btConsultaEmp.addActionListener(this);

		panel.addJLabel("Empresa", 143, 189, 60, 14);

		tfEmpresa = panel.addJTextFieldNaoEdit(143, 207, 140, 20);
		tfEmpresa.addFocusListener(this);

		panel.addJLabel("Situação do Bem", 290, 189, 80, 14);

		lbSitBemObrig = panel.addJLabelRED(371, 192, 10, 14);

		final String[] itens = { "Ativo", "Com Defeito", "Conserto", "Inativo" };
		cbSituacaoBem = panel.addJComboBox(itens, 290, 207, 120, 20);
		cbSituacaoBem.addFocusListener(this);

		panel.addJSeparator(JSeparator.HORIZONTAL, 0, 242, 428, 3);

		btOk = panel.addJButtonOK(30, 262, 70, 26);
		btOk.addActionListener(this);

		btCancelar = panel.addJButtonCancelar(128, 262, 70, 26);
		btCancelar.addActionListener(this);

		btExcluir = panel.addJButtonExcuir(223, 262, 70, 26);
		btExcluir.addActionListener(this);

		btSair = panel.addJButtonSair(320, 262, 71, 26);
		btSair.addActionListener(this);

		Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke>(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
		newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);

		this.setSize(445, 345);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void limparTela() {
		try {
			tfCodigo.setText(Integer.toString(patrimonioDAO.getProxCodPatrimonio()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
		}
		tfDataCadas.setText(formatDate.format(new Date()) + " as " + formatHora.format(new Date()));
		tfUltAlteracao.setText("");
		tfDescricao.setText("");
		tfCodAmbiente.setText("");
		tfAmbiente.setText("");
		tfNotaFiscal.setText("");
		tfNumSerie.setText("");
		tfMesesGarantia.setText("");
		tfValor.setText("");
		tfCodForn.setText("");
		tfFornecedor.setText("");
		tfCodEmp.setText("");
		tfEmpresa.setText("");
		ftfDataAquisicao.setText("");
		cbSituacaoBem.setSelectedItem("Selecione");
		lbDescricaoObrig.setText("");
		lbCodAmbObrig.setText("");
		lbDataAquisicaoObrig.setText("");
		lbNumNotFiscalObrig.setText("");
		lbNumSerieObrig.setText("");
		lbMesesGarantiaObrig.setText("");
		lbValorObrig.setText("");
		lbCodFornObrig.setText("");
		lbCodEmpObrig.setText("");
		lbSitBemObrig.setText("");
	}

	private void gravar() throws Exception {
		boolean flag = false;
		if ("".equals(tfDescricao.getText().trim())) {
			lbDescricaoObrig.setText("*");
			flag = true;
		} else {
			lbDescricaoObrig.setText("");
		}
		if ("".equals(tfCodAmbiente.getText())) {
			lbCodAmbObrig.setText("*");
			flag = true;
		} else {
			lbCodAmbObrig.setText("");
		}
		if ("  /  /    ".equals(ftfDataAquisicao.getText())) {
			lbDataAquisicaoObrig.setText("*");
			flag = true;
		} else {
			lbDataAquisicaoObrig.setText("");
		}
		if ("".equals(tfNotaFiscal.getText())) {
			lbNumNotFiscalObrig.setText("*");
			flag = true;
		} else {
			lbNumNotFiscalObrig.setText("");
		}
		if ("".equals(tfNumSerie.getText().trim())) {
			lbNumSerieObrig.setText("*");
			flag = true;
		} else {
			lbNumSerieObrig.setText("");
		}
		if ("".equals(tfMesesGarantia.getText())) {
			lbMesesGarantiaObrig.setText("*");
			flag = true;
		} else {
			lbMesesGarantiaObrig.setText("");
		}
		if ("".equals(tfValor.getText().trim())) {
			lbValorObrig.setText("*");
			flag = true;
		} else {
			lbValorObrig.setText("");
		}
		if ("".equals(tfCodForn.getText())) {
			lbCodFornObrig.setText("*");
			flag = true;
		} else {
			lbCodFornObrig.setText("");
		}
		if ("".equals(tfCodEmp.getText())) {
			lbCodEmpObrig.setText("*");
			flag = true;
		} else {
			lbCodEmpObrig.setText("");
		}
		if ("Selecione".equals(cbSituacaoBem.getSelectedItem())) {
			lbSitBemObrig.setText("*");
			flag = true;
		} else {
			lbSitBemObrig.setText("");
		}
		if (!flag) {
			validaObjeto.validaDouble(tfValor, "Campo valor inválido", "Valor deve ser superior a 0", 0);
			if (!validaObjeto.validaData(ftfDataAquisicao)) {
				throw new Exception("Campo data de aquisição inválido");
			}
			Patrimonio patrimonio = new Patrimonio();
			patrimonio.setCodigo(Integer.parseInt(tfCodigo.getText()));
			patrimonio.setDataCadastro(formatDateHora.parse(tfDataCadas.getText().replace(" as ", " ").concat(":00")));
			patrimonio.setUltAlteracao(new Date());
			patrimonio.setDescricao(tfDescricao.getText());
			Ambiente ambiente = new Ambiente();
			ambiente.setCodigo(Integer.parseInt(tfCodAmbiente.getText()));
			patrimonio.setAmbiente(ambiente);
			patrimonio.setDataAquisicao(formatDate.parse(ftfDataAquisicao.getText()));
			patrimonio.setNumNotaFiscal(Integer.parseInt(tfNotaFiscal.getText()));
			patrimonio.setNumSerie(tfNumSerie.getText());
			patrimonio.setMesesGarantia(Integer.parseInt(tfMesesGarantia.getText()));
			patrimonio.setValor(Double.parseDouble(tfValor.getText()));
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setCodigo(Integer.parseInt(tfCodForn.getText()));
			patrimonio.setFornecedor(fornecedor);
			Empresa empresa = new Empresa();
			empresa.setCodigo(Integer.parseInt(tfCodEmp.getText()));
			patrimonio.setEmpresa(empresa);
			patrimonio.setSituacaoBem(cbSituacaoBem.getSelectedIndex());
			int codPatrCadas = patrimonioDAO.getPatrimonioCadastrado(tfNumSerie.getText(), Integer.parseInt(tfCodigo.getText()));// verifica se patrimonio já está cadastrado
			if (codPatrCadas != -1) {
				if (JOptionPane.showConfirmDialog(null, "Confirmar alteração nos dados", "Atenção", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (patrimonioDAO.updatePatrimonio(patrimonio)) {
						JOptionPane.showMessageDialog(null, "Dados alterados com sucesso", "Patrimonio", JOptionPane.INFORMATION_MESSAGE);
						limparTela();
					}
				}
			} else {
				codPatrCadas = patrimonioDAO.getPatrimonioCadastrado(tfNumSerie.getText());// verifica se número de série já está cadastrado
				if (codPatrCadas != -1) {
					JOptionPane.showMessageDialog(null, "Este número de série ja está cadastrado", "Atenção", JOptionPane.WARNING_MESSAGE);
				} else {
					if (patrimonioDAO.insertPatrimonio(patrimonio)) {
						JOptionPane.showMessageDialog(null, "Patrimonio cadastrado com sucesso", "Patrimonio", JOptionPane.INFORMATION_MESSAGE);
						limparTela();
					}
				}
			}
		} else {
			throw new Exception("Campos * obrigatórios não preenchidos");
		}
	}

	private void consultaPatrimonio() throws Exception {
		if (patrimonioDAO.isPatrimonioVazio()) {
			throw new Exception("Não há patrimonios cadastrados\nCadastre um patrimonio primeiro");
		}
		ConsultaPatrimonio consulta = new ConsultaPatrimonio(patrimonioDAO);
		consulta.setListener(new ListenerPatrimonio() {

			@Override
			public void exibePatrimonio(Patrimonio patrimonio) {
				limparTela();
				tfCodigo.setText(Integer.toString(patrimonio.getCodigo()));
				tfDataCadas.setText(formatDate.format(patrimonio.getDataCadastro()) + " as " + formatHora.format(patrimonio.getDataCadastro()));
				tfUltAlteracao.setText(formatDate.format(patrimonio.getUltAlteracao()) + " as " + formatHora.format(patrimonio.getUltAlteracao()));
				tfDescricao.setText(patrimonio.getDescricao());
				tfCodAmbiente.setText(Integer.toString(patrimonio.getAmbiente().getCodigo()));
				tfAmbiente.setText(patrimonio.getAmbiente().getAmbiente());
				ftfDataAquisicao.setText(formatDate.format(patrimonio.getDataAquisicao()));
				tfNotaFiscal.setText(Integer.toString(patrimonio.getNumNotaFiscal()));
				tfNumSerie.setText(patrimonio.getNumSerie());
				tfMesesGarantia.setText(Integer.toString(patrimonio.getMesesGarantia()));
				tfValor.setText(Double.toString(patrimonio.getValor()));
				tfCodForn.setText(Integer.toString(patrimonio.getFornecedor().getCodigo()));
				tfFornecedor.setText(patrimonio.getFornecedor().getNome());
				tfCodEmp.setText(Integer.toString(patrimonio.getEmpresa().getCodigo()));
				tfEmpresa.setText(patrimonio.getEmpresa().getNome());
				cbSituacaoBem.setSelectedIndex(patrimonio.getSituacaoBem());
			}
		});
		consulta.setModal(true);
		consulta.setVisible(true);
	}

	private void excluir() throws Exception {
		if (patrimonioDAO.isPatrimonioVazio()) {
			throw new Exception("Não há patrimonios cadastrados\nCadastre um patrimonio primeiro");
		}
		if (!patrimonioDAO.isPatrimonioCadastrado(Integer.parseInt(tfCodigo.getText()))) {
			throw new Exception("Selecione um patrimonio");
		}
		if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esse patrimonio", "Patrimonio", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (patrimonioDAO.deletePatrimonio(Integer.parseInt(tfCodigo.getText()))) {
				JOptionPane.showMessageDialog(null, "Patrimonio excluído com sucesso", "Patrimonio", JOptionPane.INFORMATION_MESSAGE);
				limparTela();
			}
		}
	}

	private void consultaAmbiente() throws Exception {
		if (ambienteDAO.isAmbienteVazio()) {
			throw new Exception("Não há ambientes cadastrados\nCadastre um ambiente primeiro");
		}
		ConsultaAmbiente consulta = new ConsultaAmbiente(ambienteDAO);
		consulta.setListener(new ListenerAmbiente() {

			@Override
			public void exibeAmbiente(Ambiente ambiente) {
				tfCodAmbiente.setText(Integer.toString(ambiente.getCodigo()));
				tfAmbiente.setText(ambiente.getAmbiente());
			}
		});
		consulta.setModal(true);
		consulta.setVisible(true);
	}

	private void consultaForn() throws Exception {
		if (fornecedorDAO.isFornVazio()) {
			throw new Exception("Não há fornecedores cadastrados\nCadastre um fornecedor primeiro");
		}
		ConsultaFornecedor consulta = new ConsultaFornecedor(fornecedorDAO);
		consulta.setListener(new ListenerFornecedor() {

			@Override
			public void exibeFornecedor(Fornecedor fornecedor) {
				tfCodForn.setText(Integer.toString(fornecedor.getCodigo()));
				tfFornecedor.setText(fornecedor.getNome());
			}
		});
		consulta.setModal(true);
		consulta.setVisible(true);
	}

	private void consultaEmpresa() throws Exception {
		if (empresaDAO.isEmpresaVazio()) {
			throw new Exception("Não há empresas cadastradas\nCadastre uma empresa primeiro");
		}
		ConsultaEmpresa consulta = new ConsultaEmpresa(empresaDAO);
		consulta.setListener(new ListenerEmpresa() {

			@Override
			public void exibeEmpresa(Empresa empresa) {
				tfCodEmp.setText(Integer.toString(empresa.getCodigo()));
				tfEmpresa.setText(empresa.getNome());
			}
		});
		consulta.setModal(true);
		consulta.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == btConsulta) {
			try {
				consultaPatrimonio();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btConsultaAmb) {
			try {
				consultaAmbiente();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btConsultaForn) {
			try {
				consultaForn();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btConsultaEmp) {
			try {
				consultaEmpresa();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btOk) {
			try {
				gravar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btCancelar) {
			limparTela();
		}
		if (evento.getSource() == btExcluir) {
			try {
				excluir();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro no sistema", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (evento.getSource() == btSair) {
			this.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent evento) {
		if (evento.getSource() == tfCodigo) {
			tfCodigo.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfDataCadas) {
			tfDataCadas.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfUltAlteracao) {
			tfUltAlteracao.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfDescricao) {
			tfDescricao.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfCodAmbiente) {
			tfCodAmbiente.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfAmbiente) {
			tfAmbiente.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfNotaFiscal) {
			tfNotaFiscal.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfNumSerie) {
			tfNumSerie.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfMesesGarantia) {
			tfMesesGarantia.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfValor) {
			tfValor.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfCodForn) {
			tfCodForn.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfFornecedor) {
			tfFornecedor.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfCodEmp) {
			tfCodEmp.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == tfEmpresa) {
			tfEmpresa.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == ftfDataAquisicao) {
			ftfDataAquisicao.setBackground(Color.YELLOW);
		}
		if (evento.getSource() == cbSituacaoBem) {
			cbSituacaoBem.setBackground(Color.YELLOW);
		}
	}

	@Override
	public void focusLost(FocusEvent evento) {
		tfCodigo.setBackground(Color.WHITE);
		tfDataCadas.setBackground(Color.WHITE);
		tfUltAlteracao.setBackground(Color.WHITE);
		tfDescricao.setBackground(Color.WHITE);
		tfCodAmbiente.setBackground(Color.WHITE);
		tfAmbiente.setBackground(Color.WHITE);
		tfNotaFiscal.setBackground(Color.WHITE);
		tfNumSerie.setBackground(Color.WHITE);
		tfMesesGarantia.setBackground(Color.WHITE);
		tfValor.setBackground(Color.WHITE);
		tfCodForn.setBackground(Color.WHITE);
		tfFornecedor.setBackground(Color.WHITE);
		tfCodEmp.setBackground(Color.WHITE);
		tfEmpresa.setBackground(Color.WHITE);
		ftfDataAquisicao.setBackground(Color.WHITE);
		cbSituacaoBem.setBackground(Color.WHITE);
	}
}
