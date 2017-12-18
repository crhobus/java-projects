package edu.furb.easyboleto.view.converter;

import edu.furb.easyboleto.model.Beneficiario;
import edu.furb.easyboleto.persistence.dao.BeneficiarioDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BeneficiarioConverter implements Converter {

    @Inject
    private BeneficiarioDAO beneficiarioDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return beneficiarioDAO.getBeneficiario(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Beneficiario beneficiario = (Beneficiario) value;
        if (beneficiario == null) {
            return "";
        }
        return String.valueOf(beneficiario.getNrConta());
    }
}
