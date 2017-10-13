package edu.furb.produtos.view;

import edu.furb.produtos.model.Produto;
import edu.furb.produtos.persistence.ProdutoDAO;
import edu.furb.produtos.persistence.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

@Named
@RequestScoped
public class ProdutoBean {

    @Inject
    private Produto produto;
    @Inject
    private ProdutoDAO produtoDAO;

    private UIData selecionado;
    private boolean update = false;

    public List<Produto> getProdutos() {
        return produtoDAO.listarProdutos();
    }

    @Transaction
    public String salvar() {
        if (update) {
            produtoDAO.atualizar(produto);
        } else {
            produtoDAO.inserir(produto);
        }
        return "produto?faces-redirect=true";
    }

    public String editar() {
        produto = (Produto) selecionado.getRowData();
        update = true;
        return "produto";
    }

    @Transaction
    public String excluir() {
        produto = (Produto) selecionado.getRowData();
        produtoDAO.excluir(produto);
        return "produto?faces-redirect=true";
    }

    public String limpar() {
        return "produto?faces-redirect=true";
    }

    public void gerarRelatorio() {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

        String reportFile = servletContext.getRealPath("/resources/reports/ProdutosReport.jasper");
        gerarRelatorioWeb(null, reportFile);
    }

    private void gerarRelatorioWeb(Map<String, Object> parametros, String arquivo) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, produtoDAO.getConnection());
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
