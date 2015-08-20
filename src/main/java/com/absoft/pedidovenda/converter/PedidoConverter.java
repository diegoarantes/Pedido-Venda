package com.absoft.pedidovenda.converter;

import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.repository.Pedidos;
import com.absoft.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Diego Arantes
 */
@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    //@Inject   Usar EJB é muito melhor
    private Pedidos pedidos;

    public PedidoConverter() {
        pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Pedido retorno = null;
        if (string != null) {
            Long id = new Long(string);
            retorno = pedidos.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Pedido pedido = (Pedido) o;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }
        return "";
    }

}
