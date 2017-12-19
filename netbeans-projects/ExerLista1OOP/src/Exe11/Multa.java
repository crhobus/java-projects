package Exe11;

public class Multa {

    private int velMaxPer;

    public Multa(int velMaxPer) {
        this.velMaxPer = velMaxPer;
    }

    public String getMultaPagar(int velDirigindo) {
        if (velDirigindo > velMaxPer && velDirigindo <= velMaxPer + 10) {
            return "Multa a pagar: R$ 50,00";
        } else {
            if (velDirigindo >= velMaxPer + 11 && velDirigindo <= velMaxPer + 30) {
                return "Multa a pagar: R$ 100,00";
            } else {
                if (velDirigindo >= velMaxPer + 31) {
                    return "Multa a pagar: R$ 200,00";
                } else {
                    return "Bom motorista";
                }
            }
        }
    }
}
