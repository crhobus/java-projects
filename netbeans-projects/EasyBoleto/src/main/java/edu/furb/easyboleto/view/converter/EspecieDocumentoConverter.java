package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.EspecieDocumento;
import edu.furb.easyboleto.persistence.dao.EspecieDocumentoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EspecieDocumentoConverter implements Converter {

    @Inject
    private EspecieDocumentoDAO especieDocumentoDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return especieDocumentoDAO.getEspecieDocumento(Long.parseLong(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new EspecieDocumento();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        EspecieDocumento especieDocumento = (EspecieDocumento) value;
        if (especieDocumento == null) {
            return "";
        }
        return String.valueOf(especieDocumento.getNrSequencia());
    }
}
