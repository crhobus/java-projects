package Exe1LocVenImovelCarroInterfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {

    public static void main(String[] args) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Imovel imovel = new Imovel();
            Carro carro = new Carro();
            Locacao locacao = new Locacao();
            Venda venda = new Venda();

            venda.setItemVenda(imovel);
            imovel.setEndereco("Rua Vidal Ramos");
            imovel.setValorBase(78900.65);
            venda.setDataVenda(dateFormater.parse("08/08/2010"));

            locacao.setItemLocavel(carro);
            carro.setPlaca("MKI-9890");
            carro.setValorLocacao(180);
            locacao.setDataInicio(dateFormater.parse("04/07/2008"));
            locacao.setDataFim(dateFormater.parse("07/08/2010"));

            System.out.println("Valor Base da venda do imóvel: " + venda.getValorBase() + " no dia " + dateFormater.format((Date) venda.getDataVenda()) + " na rua " + imovel.getEndereco() + "\n");
            System.out.println("Valor unitario da alocação do carro: " + locacao.getValorUnit() + " entre o periodo: " + dateFormater.format((Date) locacao.getDataInicio()) + " - " + dateFormater.format((Date) locacao.getDataFim()) + " com a placa: " + carro.getPlaca());
            System.out.println("Valor total da alocação: " + locacao.getValorTotal() + "\n");


            venda.setItemVenda(carro);
            locacao.setItemLocavel(carro);
            /*venda.setItemVenda(carro);
            carro.setPlaca("MJK-9876");
            carro.setValorBase(65000);
            venda.setDataVenda(dateFormater.parse("08/09/2007"));

            locacao.setItemLocavel(carro);
            carro.setPlaca("KMJ-9870");
            carro.setValorLocacao(98);
            locacao.setDataInicio(dateFormater.parse("08/02/2010"));
            locacao.setDataFim(dateFormater.parse("09/07/2010"));*/

            System.out.println("Valor Base da venda do carro: " + venda.getValorBase() + " no dia " + dateFormater.format((Date) venda.getDataVenda()) + " com a placa " + carro.getPlaca() + "\n");
            System.out.println("Valor unitario da alocação do carro: " + locacao.getValorUnit() + " entre o periodo: " + dateFormater.format((Date) locacao.getDataInicio()) + " - " + dateFormater.format((Date) locacao.getDataFim()) + " com a placa: " + carro.getPlaca());
            System.out.println("Valor total da alocação: " + locacao.getValorTotal() + "\n");




            /*locacao.setItemLocavel(imovel);
            imovel.setEndereco("Rua São Paulo");
            imovel.setValorLocacao(30);
            locacao.setDataInicio(dateFormater.parse("08/07/2006"));
            locacao.setDataFim(dateFormater.parse("04/07/2010"));
            System.out.println("Valor unitario da alocação do imóvel: " + locacao.getValorUnit() + " entre o periodo: " + dateFormater.format((Date) locacao.getDataInicio()) + " - " + dateFormater.format((Date) locacao.getDataFim()) + " na rua: " + imovel.getEndereco());
            System.out.println("Valor total da alocação: " + locacao.getValorTotal() + "\n");

            locacao.setItemLocavel(carro);
            carro.setPlaca("HBG-9876");
            carro.setValorLocacao(50);
            locacao.setDataInicio(dateFormater.parse("09/10/2008"));
            locacao.setDataFim(dateFormater.parse("23/12/2009"));
            System.out.println("Valor unitario da alocação do carro: " + locacao.getValorUnit() + " entre o periodo: " + dateFormater.format((Date) locacao.getDataInicio()) + " - " + dateFormater.format((Date) locacao.getDataFim()) + " com a placa: " + carro.getPlaca());
            System.out.println("Valor total da alocação: " + locacao.getValorTotal() + "\n");

            venda.setItemVenda(imovel);
            imovel.setEndereco("Rua Uberaba");
            imovel.setValorBase(98000);
            venda.setDataVenda(dateFormater.parse("10/05/2010"));
            System.out.println("Valor base da venda do imóvel: " + venda.getValorBase() + " no dia: " + dateFormater.format((Date) venda.getDataVenda()) + " na rua: " + imovel.getEndereco());

            venda.setItemVenda(carro);
            carro.setPlaca("JUH-0978");
            carro.setValorBase(4000.90);
            venda.setDataVenda(dateFormater.parse("09/07/2010"));
            System.out.println("Valor base da venda do carro: " + venda.getValorBase() + " no dia: " + dateFormater.format((Date) venda.getDataVenda()) + " com a placa: " + carro.getPlaca());*/
        } catch (ParseException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
