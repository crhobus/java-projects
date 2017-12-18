package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.Banco;
import edu.furb.easyboleto.model.Convenio;
import edu.furb.easyboleto.persistence.dao.ConvenioDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConvenioConverter implements Converter {

    @Inject
    private ConvenioDAO convenioDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return convenioDAO.getConvenio(Long.parseLong(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Banco();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Convenio convenio = (Convenio) value;
        if (convenio == null) {
            return "";
        }
        return String.valueOf(convenio.getCdConvenio());
    }
}
