package control.action.funcionario;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import control.funcoes.Funcoes;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import model.dao.FuncionarioDAO;
import model.dao.ServidorDAO;
import model.dao.UsuarioDAO;
import model.entidades.Endereco;
import model.entidades.Funcionario;
import model.entidades.Usuario;

public class FuncionarioAction {

    private FuncionarioDAO funcionarioDAO;
    private ServidorDAO servidorDAO;

    public FuncionarioAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.funcionarioDAO = new FuncionarioDAO(servidorDAO);
    }

    public List<Funcionario> getFuncionarios() throws ExceptionError {
        try {
            return funcionarioDAO.getFuncionarios();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os funcionários do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Funcionario salvar(Dados dados, Funcionario funcionario) throws ExceptionInfo, ExceptionError {
        Usuario u = null;
        try {
            u = funcionarioDAO.getUsuario(dados.getInfo("NM_USUARIO"), (funcionario == null ? 0 : funcionario.getCdFuncionario()));
        } catch (Exception ex) {
        }
        if (u != null) {
            throw new ExceptionInfo("NM_USUARIO", "Este usuário já existe, altere o nome do usuário");
        }
        if (!dados.getInfo("DS_CONFIRMA_SENHA").equals(dados.getInfo("DS_SENHA"))) {
            throw new ExceptionInfo("DS_CONFIRMA_SENHA", "Confirme sua senha");
        }
        Date dtAdmissao;
        Date dtDemissao;
        try {
            dtAdmissao = Funcoes.stringToDate(dados.getInfo("DT_ADMISSAO"), true);
            dtDemissao = Funcoes.stringToDate(dados.getInfo("DT_DEMISSAO"), true);
        } catch (ParseException ex) {
            throw new ExceptionError("Erro na validação das datas."
                    + "\nVerifique se as datas estão corretas");
        }
        if (dtAdmissao != null
                && dtDemissao != null
                && dtAdmissao.getTime() > dtDemissao.getTime()) {
            throw new ExceptionError("DT_DEMISSAO", "A data de admissão não pode ser maior que a data de demissão");
        }
        Endereco endereco;
        Usuario usuario;
        if (funcionario == null) {
            funcionario = new Funcionario();
            endereco = new Endereco();
            usuario = new Usuario();
        } else {
            endereco = funcionario.getEndereco();
            usuario = funcionario.getUsuario();
        }

        endereco.setDsEndereco(dados.getInfo("DS_ENDERECO"));
        endereco.setNmBairro(dados.getInfo("DS_BAIRRO"));
        endereco.setNrCep(dados.getInfo("NR_CEP"));
        endereco.setNrResidencia(Integer.parseInt(dados.getInfo("NR_RESIDENCIA")));
        endereco.setNmCidade(dados.getInfo("NM_CIDADE"));
        endereco.setDsReferencia(dados.getInfo("DS_REFERENCIA"));

        funcionario.setEndereco(endereco);

        usuario.setNmUsuario(dados.getInfo("NM_USUARIO"));
        usuario.setDsSenha(dados.getInfo("DS_SENHA"));
        usuario.setDsEmail(dados.getInfo("DS_EMAIL"));
        usuario.setDtAtualizacao(new Date());
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(servidorDAO);
            usuario.setTipoUsuario(usuarioDAO.getTipoUsuario(3));
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o funcionário no sistema."
                    + "\nTipo de usuário não encontrado");
        }

        funcionario.setUsuario(usuario);

        funcionario.setNmFuncionario(dados.getInfo("NM_FUNCIONARIO"));
        funcionario.setNrRg(dados.getInfo("NR_RG"));
        funcionario.setNrCpf(dados.getInfo("NR_CPF"));
        funcionario.setNrCarteiraTrabalho(dados.getInfo("NR_CARTEIRA_TRABALHO"));
        funcionario.setNrPisPasep(dados.getInfo("NR_PIS_PASEP"));
        funcionario.setNrCnh(dados.getInfo("NR_CNH"));
        funcionario.setDsCargo(dados.getInfo("DS_CARGO"));
        funcionario.setNrTefefone(dados.getInfo("NR_TELEFONE"));
        funcionario.setNrCelular(dados.getInfo("NR_CELULAR"));
        funcionario.setVlSalario(Double.parseDouble(dados.getInfo("VL_SALARIO").replace(",", ".")));
        try {
            funcionario.setDtAdmissao(Funcoes.stringToDate(dados.getInfo("DT_ADMISSAO"), true));
        } catch (ParseException ex) {
            throw new ExceptionError("DT_ADMISSAO", "Campo data de admissão inválido");
        }
        try {
            funcionario.setDtDemissao(Funcoes.stringToDate(dados.getInfo("DT_DEMISSAO"), true));
        } catch (ParseException ex) {
            throw new ExceptionError("DT_DEMISSAO", "Campo data de demissão inválido");
        }
        funcionario.setDsObservacao(dados.getInfo("DS_OBSERVACAO"));
        try {
            servidorDAO.salvar(funcionario, funcionario.getCdFuncionario() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o funcionário no sistema."
                    + "\nErro no Servidor");
        }
        return funcionario;
    }

    public void excluirFuncionario(int cdFuncionario) throws ExceptionError {
        try {
            funcionarioDAO.excluirFuncionario(cdFuncionario);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o funcionário do sistema."
                    + "\nErro no Servidor");
        }
    }
}
