package LeituraFuncionario;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        SimpleDateFormat forDate = new SimpleDateFormat("dd/MM/yyyy");
        LeituraFunc leituraFunc = new LeituraFunc();
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario\\ArqFuncionarios");
        try {
            leituraFunc.lerFuncs();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchMethodException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (InvocationTargetException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchFieldException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        for (Funcionario func : leituraFunc.getLista()) {
            if (func.getNome().equals("m")) {
                System.out.println("qtdadeFunc : " + leituraFunc.getQtdadeFunc());
                System.out.println("nome : " + func.getNome());
                System.out.println("sobrenome : " + func.getSobrenome());
                System.out.println("apelido : " + func.getApelido());
                System.out.println("banco : " + func.getBanco());
                System.out.println("agencia : " + func.getAgencia());
                System.out.println("conta : " + func.getConta());
                System.out.println("contaFGTS : " + func.getContaFGTS());
                System.out.println("cpg : " + func.getCpf());
                System.out.println("rg : " + func.getRg());
                System.out.println("carteiraHabilitacao : " + func.getCarteiraHabilitacao());
                System.out.println("carteiraTrabalho : " + func.getCarteiraTrabalho());
                System.out.println("certificadoReservista : " + func.getCertificadoReservista());
                System.out.println("tituloEleitor : " + func.getTituloEleitor());
                System.out.println("nomePai : " + func.getNomePai());
                System.out.println("nomeMae : " + func.getNomeMae());
                System.out.println("pais : " + func.getPais());
                System.out.println("rua : " + func.getRua());
                System.out.println("bairro : " + func.getBairro());
                System.out.println("cep : " + func.getCep());
                System.out.println("complemento : " + func.getComplemento());
                System.out.println("telefoneResidencial : " + func.getTelefoneResidencial());
                System.out.println("telefoneComercial : " + func.getTelefoneComercial());
                System.out.println("telefoneCelular : " + func.getTelefoneCelular());
                System.out.println("chefe : " + func.getChefe());
                System.out.println("cargo : " + func.getCargo());
                System.out.println("sexo : " + func.getSexo());
                System.out.println("estadoCivil : " + func.getEstadoCivil());
                System.out.println("digitoConta : " + func.getDigitoConta());
                System.out.println("centroCusto : " + func.getCentroCusto());
                System.out.println("cracha : " + func.getCracha());
                System.out.println("estabilidade : " + func.getEstabilidade());
                System.out.println("digitoCarteiraTrabalho : " + func.getDigitoCarteiraTrabalho());
                System.out.println("documentoEstrangeiro : " + func.getDocumentoEstrangeiro());
                System.out.println("descontoINSS : " + func.getDescontoINSS());
                System.out.println("dependentesIR : " + func.getDependentesIR());
                System.out.println("classeContribuicaoIR : " + func.getClasseContribuicaoIR());
                System.out.println("horasSemanais : " + func.getHorasSemanais());
                System.out.println("inscricaoINSS : " + func.getInscricaoINSS());
                System.out.println("pis : " + func.getPis());
                System.out.println("numeroContrato : " + func.getNumeroContrato());
                System.out.println("tipoContrato : " + func.getTipoContrato());
                System.out.println("dataNascimento : " + forDate.format(func.getDataNascimento()));
                System.out.println("dataContratacao : " + forDate.format(func.getDataContratacao()));
                System.out.println("dataPagamento : " + forDate.format(func.getDataPagamento()));
                System.out.println("salario : " + func.getSalario());
                System.out.println("saldoFGTS : " + func.getSaldoFGTS());
                System.out.println("insentoIR : " + func.isInsentoIR());
                System.out.println("contribuicaoSindical : " + func.isContribuicaoSindical());
            }
        }
    }
}
