package jaxb;

import java.io.File;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Cliente;
import model.Conta;

public class CriaXML {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Conta.class);
            Marshaller marshaller = context.createMarshaller();

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

            Cliente cliente = new Cliente();
            cliente.setNome("Caio Renan Hobus");
            cliente.setCpf("654.087.098-87");
            cliente.setRg("765.897.567");
            cliente.setEndereco("Rua Uberaba, 2381, Mulde, Indaial");
            cliente.setDtNascimento(formatDate.parse("05/10/1991"));
            cliente.setTelefone("(47) 9168-0330");

            Conta conta = new Conta();
            conta.setCliente(cliente);
            conta.setBanco("Bradesco");
            conta.setConta("7482987634");
            conta.setAgencia("3");
            conta.setValor(200);
            conta.setSaldo(1500.87);
            conta.setLimite(2000);

            marshaller.marshal(conta, new File("conta.xml"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
