
import java.io.*;

public class ArrayPagamentos implements Serializable {//Serializable Trabalha com arquivo

    private String ValorPago, ParcelasRes, Situacao;
    private int Codigo, CodigoVen;

    ArrayPagamentos(String valorpago, String parcelasres, String situacao, int codigo, int codigoven) {
        this.ValorPago = valorpago;
        this.ParcelasRes = parcelasres;
        this.Situacao = situacao;
        this.Codigo = codigo;
        this.CodigoVen = codigoven;
    }

    ArrayPagamentos() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoVen() {
        return CodigoVen;
    }

    public void setCodigoVen(int CodigoVen) {
        this.CodigoVen = CodigoVen;
    }

    public String getParcelasRes() {
        return ParcelasRes;
    }

    public void setParcelasRes(String ParcelasRes) {
        this.ParcelasRes = ParcelasRes;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }

    public String getValorPago() {
        return ValorPago;
    }

    public void setValorPago(String ValorPago) {
        this.ValorPago = ValorPago;
    }
}
