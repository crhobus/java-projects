package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.Pagador;
import edu.furb.easyboleto.persistence.dao.PagadorDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PagadorConverter implements Converter {

    @Inject
    private PagadorDAO pagadorDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return pagadorDAO.getPagador(Long.parseLong(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Pagador();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Pagador pagador = (Pagador) value;
        if (pagador == null) {
            return "";
        }
        return String.valueOf(pagador.getNrSequencia());
    }
}
