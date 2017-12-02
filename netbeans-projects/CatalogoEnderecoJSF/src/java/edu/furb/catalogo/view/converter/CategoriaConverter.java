package edu.furb.catalogo.view.converter;

import edu.furb.catalogo.model.Categoria;
import edu.furb.catalogo.persistence.CategoriaDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Caio
 */
@Named
public class CategoriaConverter implements Converter {

    @Inject
    private CategoriaDAO categoriaDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return categoriaDAO.getCategoria(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Categoria categoria = (Categoria) value;
        if (categoria == null) {
            return "";
        }
        return String.valueOf(categoria.getCodigo());
    }
}
