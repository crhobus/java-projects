
import java.io.*;

public class ArrayPedidos implements Serializable {//Serializable Trabalha com arquivo

    private String Emissao, Situacao, Descricao;
    private int CodigoPed, CodigoVendedor, CodigoForne, CodTransp, CodItemPed;

    ArrayPedidos(String emissao, String situacao, String descricao, int codigoped, int codigovendedor, int codigoforne, int codtransp, int coditemped) {

        this.Emissao = emissao;
        this.Situacao = situacao;
        this.Descricao = descricao;
        this.CodigoPed = codigoped;
        this.CodigoVendedor = codigovendedor;
        this.CodigoForne = codigoforne;
        this.CodTransp = codtransp;
        this.CodItemPed = coditemped;
    }

    ArrayPedidos() {
    }

    public int getCodItemPed() {
        return CodItemPed;
    }

    public void setCodItemPed(int CodItemPed) {
        this.CodItemPed = CodItemPed;
    }

    public int getCodTransp() {
        return CodTransp;
    }

    public void setCodTransp(int CodTransp) {
        this.CodTransp = CodTransp;
    }

    public int getCodigoForne() {
        return CodigoForne;
    }

    public void setCodigoForne(int CodigoForne) {
        this.CodigoForne = CodigoForne;
    }

    public int getCodigoPed() {
        return CodigoPed;
    }

    public void setCodigoPed(int CodigoPed) {
        this.CodigoPed = CodigoPed;
    }

    public int getCodigoVendedor() {
        return CodigoVendedor;
    }

    public void setCodigoVendedor(int CodigoVendedor) {
        this.CodigoVendedor = CodigoVendedor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getEmissao() {
        return Emissao;
    }

    public void setEmissao(String Emissao) {
        this.Emissao = Emissao;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }
}
