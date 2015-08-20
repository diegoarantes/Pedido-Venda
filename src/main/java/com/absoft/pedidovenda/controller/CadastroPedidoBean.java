package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Cliente;
import com.absoft.pedidovenda.model.EnderecoEntrega;
import com.absoft.pedidovenda.model.FormaPagamento;
import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.model.Usuario;
import com.absoft.pedidovenda.repository.Clientes;
import com.absoft.pedidovenda.repository.Usuarios;
import com.absoft.pedidovenda.service.CadastroPedidoService;
import com.absoft.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego Arantes
 */
@Named(value = "cadastroPedidoBean")
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    private Pedido pedido;

    private List<Usuario> vendedores;

    public CadastroPedidoBean() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostBack()) {
            this.vendedores = usuarios.vendedores();
        }
    }

    private void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public void salvar() {
        this.pedido = this.cadastroPedidoService.salvar(this.pedido);
        FacesUtil.addInfoMessage("Pedido salvo com sucesso!");

    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public List<Cliente> completarCliente(String nome) {
        return this.clientes.porNome(nome);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public boolean isEditando() {
        return this.pedido.getId() != null;
    }

}
