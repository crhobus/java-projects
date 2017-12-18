package edu.furb.easyboleto.view;

import edu.furb.easyboleto.model.Boleto;
import edu.furb.easyboleto.persistence.dao.BoletoDAO;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
public class BoletoBean {

    @Inject
    private Boleto boleto;
    @Inject
    private BoletoDAO boletoDAO;
    private List<Boleto> boletos;
    private UIData selecionado;

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (boleto.getVlBoleto() <= 0.0d) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Boleto", "O valor do boleto deve ser maior que zero"));
            return;
        }
        if (boleto.getNrBoleto() > 0l) {
            boletoDAO.update(boleto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Boleto", "Boleto atualizado com sucesso"));
        } else {
            boletoDAO.insert(boleto);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Boleto", "Boleto cadastrado com sucesso"));
        }
    }

    public String alterar() {
        boleto = (Boleto) selecionado.getRowData();
        return "/mainPages/boleto/formBoleto";
    }

    public void excluir() {
        try {
            boleto = (Boleto) selecionado.getRowData();
            boletoDAO.delete(boleto);
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Boleto", "Este boleto não pode ser excluído pois existem registros dependentes"));
        } finally {
            boletos = null;
        }
    }

    public List<Boleto> getBoletos() {
        if (boletos == null) {
            boletos = boletoDAO.getBoletos();
        }
        return boletos;
    }

    public void gerarBoleto() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (boleto.getNrBoleto() == 0l) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Boleto", "Favor selecionar um boleto!"
                    + "\nO boleto deve estar cadastrado para ser gerado"));
            return;
        }
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String reportFile = servletContext.getRealPath("/resources/reports/boleto.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("pr_nr_boleto", boleto.getNrBoleto());
        gerarRelatorioWeb(context, parametros, reportFile);
    }

    private void gerarRelatorioWeb(FacesContext context, Map<String, Object> parametros, String arquivo) {
        try {
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, boletoDAO.getConnection());
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.responseComplete();
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Boleto", "Erro na geração do boleto"));
        }
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public UIData getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(UIData selecionado) {
        this.selecionado = selecionado;
    }
}
