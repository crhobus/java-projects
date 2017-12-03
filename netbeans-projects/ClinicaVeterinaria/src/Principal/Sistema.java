package Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import ClinicaVeterinaria.Animal;
import ClinicaVeterinaria.Cliente;
import ClinicaVeterinaria.Consulta;
import ClinicaVeterinaria.Especie;
import ClinicaVeterinaria.Exame;
import ClinicaVeterinaria.Tratamento;
import ClinicaVeterinaria.Veterinario;
import DAOFactory.DAOFactory;
import DAOFactory.TipoArquivo;

public class Sistema {

    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getFactory(TipoArquivo.BINARIO);
        Especie especie1 = new Especie();
        especie1.setId(1);
        especie1.setDescricao("Cachorro");
        daoFactory.createEspecieDAO().gravar(especie1);
        imprimeEspecie(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Especie especie2 = new Especie();
        especie2.setId(2);
        especie2.setDescricao("Gato");
        daoFactory.createEspecieDAO().gravar(especie2);
        imprimeEspecie(daoFactory, 2);

        System.out.println("----------------------------------------------------");

        Animal animal1 = new Animal();
        animal1.setId(1);
        animal1.setIdade(5);
        animal1.setNome("Toby");
        animal1.setSexo("masculino");
        animal1.setEspecie(especie1);
        daoFactory.createAnimalDAO().gravar(animal1);
        imprimeAnimal(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setCpf(123674345);
        cliente1.setNome("Caio Renan Hobus");
        cliente1.setTelefone("(47)9125-1625");
        cliente1.setEndereco("Rua Uberaba");
        cliente1.setPontos(23);
        try {
            cliente1.setDataUltimaConsulta(formatDate.parse("09/10/2010"));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        cliente1.setAnimal(animal1);
        daoFactory.createClienteDAO().gravar(cliente1);
        imprimeCliente(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Exame exame1 = new Exame();
        exame1.setId(1);
        exame1.setDescricao("Foi feito um exame no cachorro");
        daoFactory.createExameDAO().gravar(exame1);
        imprimeExame(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Exame exame2 = new Exame();
        exame2.setId(2);
        exame2.setDescricao("Foi feito um 2° exame no cachorro");
        daoFactory.createExameDAO().gravar(exame2);
        imprimeExame(daoFactory, 2);

        System.out.println("----------------------------------------------------");

        Tratamento tratamento1 = new Tratamento();
        tratamento1.setId(5);
        try {
            tratamento1.setDataInicio(formatDate.parse("08/10/2009"));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            tratamento1.setDataFim(formatDate.parse("02/01/2010"));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tratamento1.setDescricao("Foi feito um tratamento no animal");
        tratamento1.setValor(23.98);
        tratamento1.setExame(exame1);
        daoFactory.createTratamentoDAO().gravar(tratamento1);
        imprimeTratamento(daoFactory, 5);

        System.out.println("----------------------------------------------------");

        Tratamento tratamento2 = new Tratamento();
        tratamento2.setId(1);
        try {
            tratamento2.setDataInicio(formatDate.parse("03/11/2009"));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            tratamento2.setDataFim(formatDate.parse("30/09/2010"));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        tratamento2.setDescricao("Foi feito um 2º tratamento no animal");
        tratamento2.setValor(30.70);
        tratamento2.setExame(exame2);
        daoFactory.createTratamentoDAO().gravar(tratamento2);
        imprimeTratamento(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Consulta consulta1 = new Consulta();
        consulta1.setId(1);
        try {
            consulta1.setData(formatDate.parse("08/12/2008"));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        consulta1.setValor(90);
        consulta1.setDescricao("Foi feita uma consulta no cochorro");
        consulta1.setAnimal(animal1);
        consulta1.setTratamento(tratamento2);
        daoFactory.createConsultaDAO().gravar(consulta1);
        imprimeConsulta(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        Veterinario veterinario1 = new Veterinario();
        veterinario1.setId(1);
        veterinario1.setCpf(875983167);
        veterinario1.setNome("João da Silva");
        veterinario1.setTelefone("(47)3345-8710");
        veterinario1.setEndereco("Rua das Nações");
        veterinario1.setTitulacao("Formação em veterinaria");
        veterinario1.setReferencias("Rua das Palmeiras");
        veterinario1.setConsulta(consulta1);
        daoFactory.createVeterinarioDAO().gravar(veterinario1);
        imprimeVeterinario(daoFactory, 1);

        System.out.println("----------------------------------------------------");

        //Consulta sem tratamento
        Consulta consulta2 = new Consulta();
        consulta2.setId(2);
        try {
            consulta2.setData(formatDate.parse("10/10/2010"));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        consulta2.setValor(70);
        consulta2.setDescricao("Foi feita uma consulta no cochorro sem tratamento");
        consulta2.setAnimal(animal1);
        consulta2.setTratamento(null);
        daoFactory.createConsultaDAO().gravar(consulta2);
        imprimeConsulta(daoFactory, 2);
    }

    private static void imprimeEspecie(DAOFactory daoFactory, int id) {
        System.out.println("Especie");
        Especie especie = daoFactory.createEspecieDAO().ler(id);
        if (especie != null) {
            System.out.println("Id: " + especie.getId());
            System.out.println("Descrição: " + especie.getDescricao());
        } else {
            System.out.println("Não há nenhuma especie");
        }
    }

    private static void imprimeAnimal(DAOFactory daoFactory, int id) {
        System.out.println("Animal");
        Animal animal = daoFactory.createAnimalDAO().ler(id);
        if (animal != null) {
            System.out.println("Id: " + animal.getId());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Idade: " + animal.getIdade());
            System.out.println("Sexo: " + animal.getSexo());
            imprimeEspecie(daoFactory, animal.getEspecie().getId());
        } else {
            System.out.println("Não há nenhum animnal");
        }
    }

    private static void imprimeCliente(DAOFactory daoFactory, int id) {
        System.out.println("Cliente");
        Cliente cliente = daoFactory.createClienteDAO().ler(id);
        if (cliente != null) {
            System.out.println("Id: " + cliente.getId());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Pontos: " + cliente.getPontos());
            System.out.println("Data Última Consulta: " + formatDate.format(cliente.getDataUltimaConsulta()));
            imprimeAnimal(daoFactory, cliente.getAnimal().getId());
        } else {
            System.out.println("Não há nenhum cliente");
        }
    }

    private static void imprimeExame(DAOFactory daoFactory, int id) {
        System.out.println("Exame");
        Exame exame = daoFactory.createExameDAO().ler(id);
        if (exame != null) {
            System.out.println("Id: " + exame.getId());
            System.out.println("Descrição: " + exame.getDescricao());
        } else {
            System.out.println("Não há nenhum exame");
        }
    }

    private static void imprimeTratamento(DAOFactory daoFactory, int id) {
        System.out.println("Tratamento");
        Tratamento tratamento = daoFactory.createTratamentoDAO().ler(id);
        if (tratamento != null) {
            System.out.println("Id: " + tratamento.getId());
            System.out.println("Data Inicio: " + formatDate.format(tratamento.getDataInicio()));
            System.out.println("Data Fim: " + formatDate.format(tratamento.getDataFim()));
            System.out.println("Descrição: " + tratamento.getDescricao());
            System.out.println("Valor: " + tratamento.getValor());
            imprimeExame(daoFactory, tratamento.getExame().getId());
        } else {
            System.out.println("Não há nenhum tratamento");
        }
    }

    private static void imprimeConsulta(DAOFactory daoFactory, int id) {
        System.out.println("Consulta");
        Consulta consulta = daoFactory.createConsultaDAO().ler(id);
        if (consulta != null) {
            System.out.println("Id: " + consulta.getId());
            System.out.println("Data: " + formatDate.format(consulta.getData()));
            System.out.println("Valor: " + consulta.getValor());
            System.out.println("Descrição: " + consulta.getDescricao());
            imprimeAnimal(daoFactory, consulta.getAnimal().getId());
            if (consulta.getTratamento() != null) {
                imprimeTratamento(daoFactory, consulta.getTratamento().getId());
            } else {
                System.out.println("Não há nenhum tratamento");
            }
        } else {
            System.out.println("Não há nenhuma consulta");
        }
    }

    private static void imprimeVeterinario(DAOFactory daoFactory, int id) {
        System.out.println("Veterinario");
        Veterinario veterinario = daoFactory.createVeterinarioDAO().ler(id);
        if (veterinario != null) {
            System.out.println("Id: " + veterinario.getId());
            System.out.println("CPF: " + veterinario.getCpf());
            System.out.println("Nome: " + veterinario.getNome());
            System.out.println("Telefone: " + veterinario.getTelefone());
            System.out.println("Endereço: " + veterinario.getEndereco());
            System.out.println("Titulação: " + veterinario.getTitulacao());
            System.out.println("Referencias: " + veterinario.getReferencias());
            imprimeConsulta(daoFactory, veterinario.getConsulta().getId());
        } else {
            System.out.println("Não há nenhum veterinario");
        }
    }
}
