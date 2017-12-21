package Exe2PedidoVenda;

public class Cliente {

    private String cliente, endereco, regiao;

    public Cliente(String cliente, String endereco, String regiao) {
        this.cliente = cliente;
        this.endereco = endereco;
        this.regiao = regiao;
        MostraCliente();
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void MostraCliente() {
        System.out.println("Dados Sobre Cliente");
        System.out.println("Nome do Cliente: " + cliente);
        System.out.println("Endereco do Cliente: " + endereco);
        System.out.println("Região do Cliente: " + regiao);
        System.out.println("");
    }
}


