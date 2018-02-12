package jaxb;

import java.io.File;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import model.Conta;

public class LerXML {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Conta.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Conta conta = (Conta) unmarshaller.unmarshal(new File("conta.xml"));

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("Cliente");
            System.out.println("Nome: " + conta.getCliente().getNome());
            System.out.println("CPF: " + conta.getCliente().getCpf());
            System.out.println("RG: " + conta.getCliente().getRg());
            System.out.println("Endereço: " + conta.getCliente().getEndereco());
            System.out.println("Data Nescimento: " + formatDate.format(conta.getCliente().getDtNascimento()));
            System.out.println("Telefone: " + conta.getCliente().getTelefone());
            System.out.println("Conta");
            System.out.println("Banco: " + conta.getBanco());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Conta: " + conta.getConta());
            System.out.println("Valor: " + conta.getValor());
            System.out.println("Saldo: " + conta.getSaldo());
            System.out.println("Limite: " + conta.getLimite());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
