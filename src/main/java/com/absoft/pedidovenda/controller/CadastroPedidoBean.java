package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.EnderecoEntrega;
import com.absoft.pedidovenda.model.Pedido;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroPedidoBean")
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Pedido pedido;
    
    
   
    public CadastroPedidoBean() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
    
}
