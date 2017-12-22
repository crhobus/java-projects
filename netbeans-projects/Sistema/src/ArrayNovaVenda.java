
import java.io.*;

public class ArrayNovaVenda implements Serializable {//Serializable Trabalha com arquivo

    private String DataEmissao, Situacao, CondPagto, FormaPagto, Descricao, EndedrecoEnt, BairroEnt, CidadeEnt, EstadoEnt, ContatoEnt;
    private int Codigo, CodigoVendedor, CodigoCliente, CodigoItemVendas, NumeroEnt;
    private float SubTotal, Descontos, Total;

    ArrayNovaVenda(String dataemissao, String situacao, String condpagto, String formapagto, String descricao, String enderecoent, String bairroent, String cidadeent,
            String estadoent, String contatoent, int codigo, int codigovendedor, int codigocliente, int codigoitemvendas, int numeroent, float subtotal, float descontos, float total) {

        this.DataEmissao = dataemissao;
        this.Situacao = situacao;
        this.CondPagto = condpagto;
        this.FormaPagto = formapagto;
        this.Descricao = descricao;
        this.EndedrecoEnt = enderecoent;
        this.NumeroEnt = numeroent;
        this.BairroEnt = bairroent;
        this.CidadeEnt = cidadeent;
        this.EstadoEnt = estadoent;
        this.ContatoEnt = contatoent;
        this.Codigo = codigo;
        this.CodigoVendedor = codigovendedor;
        this.CodigoCliente = codigocliente;
        this.CodigoItemVendas = codigoitemvendas;
        this.SubTotal = subtotal;
        this.Descontos = descontos;
        this.Total = total;
    }

    ArrayNovaVenda() {
    }

    public String getBairroEnt() {
        return BairroEnt;
    }

    public void setBairroEnt(String BairroEnt) {
        this.BairroEnt = BairroEnt;
    }

    public String getCidadeEnt() {
        return CidadeEnt;
    }

    public void setCidadeEnt(String CidadeEnt) {
        this.CidadeEnt = CidadeEnt;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getCodigoItemVendas() {
        return CodigoItemVendas;
    }

    public void setCodigoItemVendas(int CodigoItemVendas) {
        this.CodigoItemVendas = CodigoItemVendas;
    }

    public int getCodigoVendedor() {
        return CodigoVendedor;
    }

    public void setCodigoVendedor(int CodigoVendedor) {
        this.CodigoVendedor = CodigoVendedor;
    }

    public String getCondPagto() {
        return CondPagto;
    }

    public void setCondPagto(String CondPagto) {
        this.CondPagto = CondPagto;
    }

    public String getContatoEnt() {
        return ContatoEnt;
    }

    public void setContatoEnt(String ContatoEnt) {
        this.ContatoEnt = ContatoEnt;
    }

    public String getDataEmissao() {
        return DataEmissao;
    }

    public void setDataEmissao(String DataEmissao) {
        this.DataEmissao = DataEmissao;
    }

    public float getDescontos() {
        return Descontos;
    }

    public void setDescontos(float Descontos) {
        this.Descontos = Descontos;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getEndedrecoEnt() {
        return EndedrecoEnt;
    }

    public void setEndedrecoEnt(String EndedrecoEnt) {
        this.EndedrecoEnt = EndedrecoEnt;
    }

    public String getEstadoEnt() {
        return EstadoEnt;
    }

    public void setEstadoEnt(String EstadoEnt) {
        this.EstadoEnt = EstadoEnt;
    }

    public String getFormaPagto() {
        return FormaPagto;
    }

    public void setFormaPagto(String FormaPagto) {
        this.FormaPagto = FormaPagto;
    }

    public int getNumeroEnt() {
        return NumeroEnt;
    }

    public void setNumeroEnt(int NumeroEnt) {
        this.NumeroEnt = NumeroEnt;
    }

    public String getSituacao() {
        return Situacao;
    }

    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }

    public float getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(float SubTotal) {
        this.SubTotal = SubTotal;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
}
