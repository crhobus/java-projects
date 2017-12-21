package Exe2PedidoVenda;

public class Fabrica {

    private String dataEmissao, regiao;

    public Fabrica(String DataEmissao, String Regiao) {
        this.dataEmissao = DataEmissao;
        this.regiao = Regiao;
        MostraFabrica();
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void MostraFabrica() {
        System.out.println("Dados Sobre a Fábrica");
        System.out.println("Data Emissao do Pedido: " + dataEmissao);
        System.out.println("Fabrica situa-se na região: " + regiao);
        System.out.println("");
    }
}
