package com.absoft.pedidovenda.converter;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Produtos;
import com.absoft.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Diego Arantes
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private Produtos produtos;

    public ProdutoConverter() {
        produtos = CDIServiceLocator.getBean(Produtos.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Produto retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = produtos.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Produto produto = (Produto) o;
            return produto.getId() == null ? null : produto.getId().toString();
        }
        return "";
    }

}
