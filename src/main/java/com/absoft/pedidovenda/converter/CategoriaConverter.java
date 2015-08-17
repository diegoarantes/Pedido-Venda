package com.absoft.pedidovenda.converter;

import com.absoft.pedidovenda.model.Categoria;
import com.absoft.pedidovenda.repository.Categorias;
import com.absoft.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Diego Arantes
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Categoria retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = categorias.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((Categoria) o).getId().toString();
        }
        return "";
    }

}
