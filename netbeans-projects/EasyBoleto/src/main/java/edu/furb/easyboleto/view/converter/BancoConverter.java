package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.Banco;
import edu.furb.easyboleto.persistence.dao.BancoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BancoConverter implements Converter {

    @Inject
    private BancoDAO bancoDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return bancoDAO.getBanco(Long.parseLong(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Banco();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Banco banco = (Banco) value;
        if (banco == null) {
            return "";
        }
        return String.valueOf(banco.getCdBanco());
    }
}
