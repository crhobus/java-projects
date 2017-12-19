package Interface;

public class Teste {

    public static void main(String[] args) {
        APagar payableObjects[] = new APagar[4];
        payableObjects[0] = new Fatura("01234", "assento", 2, 375.00);
        payableObjects[1] = new Fatura("56789", "puxar", 4, 800.00);
        payableObjects[2] = new SalarioFuncionario("John", "Swith", "111-11-1111", 800.00);
        payableObjects[3] = new SalarioFuncionario("Lisa", "Barnes", "888-88-8888", 1200.00);
        System.out.println("Faturas e funcionários transformados polimorficamente:\n");
        for (APagar correntesPagar : payableObjects) {
            System.out.printf("%s \n%s: $%,.2f\n\n", correntesPagar.toString(), "pagamento devido", correntesPagar.getQtdadePagto());
        }
    }
}
