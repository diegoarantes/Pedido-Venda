package com.absoft.pedidovenda.controller;

import com.absoft.pedidovenda.model.Produto;
import com.absoft.pedidovenda.repository.Produtos;
import com.absoft.pedidovenda.repository.filter.ProdutoFilter;
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
@Named(value = "pesquisaProdutosBean")
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    private ProdutoFilter filtro;

    private List<Produto> produtosFiltrados;

    private Produto produtoSelecionado;

    public PesquisaProdutosBean() {
        filtro = new ProdutoFilter();
    }

    public void pesquisar() {
        produtosFiltrados = produtos.filtrados(filtro);
    }

    public void excluir() {
        produtos.remover(produtoSelecionado);
        produtosFiltrados.remove(produtoSelecionado); //Exclui apenas o produto da lista
        FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso.");
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(ProdutoFilter filtro) {
        this.filtro = filtro;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

}
