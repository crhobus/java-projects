package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.ConvenioBeneficiario;
import edu.furb.easyboleto.persistence.dao.ConvenioBeneficiarioDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ConvenioBeneficiarioConverter implements Converter {

    @Inject
    private ConvenioBeneficiarioDAO convenioBeneficiarioDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return convenioBeneficiarioDAO.getConvenioBeneficiario(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ConvenioBeneficiario convenioBeneficiario = (ConvenioBeneficiario) value;
        if (convenioBeneficiario == null) {
            return "";
        }
        return String.valueOf(convenioBeneficiario.getNrSequencia());
    }
}
