package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.model.StatusPedido;
import com.absoft.pedidovenda.repository.Pedidos;
import com.absoft.pedidovenda.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "pesquisaPedidosBean")
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    private List<Pedido> pedidosFiltrados;

    private PedidoFilter filtro;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filtro);
    }

    public StatusPedido[] getStatuses() {
        return StatusPedido.values();
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(PedidoFilter filtro) {
        this.filtro = filtro;
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

}
