package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Cliente;
import com.absoft.pedidovenda.model.EnderecoEntrega;
import com.absoft.pedidovenda.model.FormaPagamento;
import com.absoft.pedidovenda.model.ItemPedido;
import com.absoft.pedidovenda.model.Pedido;
import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.model.Usuario;
import com.absoft.pedidovenda.repository.Clientes;
import com.absoft.pedidovenda.repository.Produtos;
import com.absoft.pedidovenda.repository.Usuarios;
import com.absoft.pedidovenda.service.CadastroPedidoService;
import com.absoft.pedidovenda.util.jsf.FacesUtil;
import com.absoft.pedidovenda.validation.SKU;
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
    private Produtos produtos;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @SKU
    private String sku;

    private Pedido pedido;

    private List<Usuario> vendedores;

    private Produto produtoLinhaEditavel;

    public CadastroPedidoBean() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostBack()) {
            this.vendedores = usuarios.vendedores();

            this.pedido.adicionarItemVazio();

            this.recalcularPedido(); //Verificar Depois se é Necessário
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

    public void recalcularPedido() {
        this.pedido.recalcularValorTotal();
    }

    public void carregarProdutoPorSku() {
        if (this.sku != null || this.sku.equals("")) {
            this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
            this.carregarProdutoLinhaEditavel();
        }
    }

    public void carregarProdutoLinhaEditavel() {
        ItemPedido item = this.pedido.getItens().get(0);

        if (this.produtoLinhaEditavel != null) {
            if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
                FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
            } else {

                item.setProduto(this.produtoLinhaEditavel);
                item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.sku = null;

                this.pedido.recalcularValorTotal();

            }
        }
    }

    private boolean existeItemComProduto(Produto produto) {
        boolean existeItem = false;

        for (ItemPedido item : this.getPedido().getItens()) {
            if ((produto).equals(item.getProduto())) {
                existeItem = true;
                break;
            }
        }
        return existeItem;
    }

    public List<Produto> completarProduto(String nome) {
        return this.produtos.porNome(nome);
    }

    public void atualizarQuantidade(ItemPedido item, int linha) {
        if (item.getQuantidade() < 1) {
            if (linha == 0) {
                item.setQuantidade(1); //Volta pra 1
            } else {
                this.getPedido().getItens().remove(linha); //Remove o Item pela linha
            }
        }

        this.pedido.recalcularValorTotal();
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

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isEditando() {
        return this.pedido.getId() != null;
    }

}
