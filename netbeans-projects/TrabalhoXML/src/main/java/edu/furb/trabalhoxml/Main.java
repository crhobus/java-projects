package edu.furb.trabalhoxml;

import edu.furb.pagamento.ObjectFactory;
import edu.furb.pagamento.Pagamento;
import edu.furb.pagamento.Titulo;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) {
        try {
            Pagamento pagamento = new Pagamento("Caio Renan Hobus");
            pagamento.getTitulo().add(new Titulo("Cheque", new BigDecimal(200.76d).setScale(2, RoundingMode.HALF_UP), true, new BigInteger("1")));
            pagamento.getTitulo().add(new Titulo("Cartão", new BigDecimal(1500.0d).setScale(2, RoundingMode.HALF_UP), true, new BigInteger("2")));
            pagamento.getTitulo().add(new Titulo("Dinheiro", new BigDecimal(50.0d).setScale(2, RoundingMode.HALF_UP), false, new BigInteger("3")));

            JAXBContext context = JAXBContext.newInstance("edu.furb.pagamento");
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            JAXBElement<Pagamento> element = new ObjectFactory().createPagamento(pagamento);
            m.marshal(element, new FileOutputStream(new File("PagamentoGerado.xml")));

            Unmarshaller unm = context.createUnmarshaller();
            File file = new File("pagamento.xml");
            element = (JAXBElement<Pagamento>) unm.unmarshal(file);

            Pagamento p = element.getValue();
            System.out.println("\nPagameto: " + p);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
